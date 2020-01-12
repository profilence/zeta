from __future__ import print_function

import threading
from io import TextIOBase

import grpc

import connector_service_pb2
import connector_service_pb2_grpc
import wrappers_pb2
import empty_pb2

from os import path
import sys
import re

__copyright__ = "Copyright 2020 Profilence"
__license__ = "Apache License, Version 2.0"


class TestRequestListenerBase(object):
    """ Base class for test request handling """

    def on_error(self, e):
        """ Called if error occurs while listening test requests

        Parameters:
            e (Exception): The exception object

        """

        pass

    def on_completed(self):
        """ Called when the asynchronous sequence completes """

        pass

    def on_test_start_requested(self, request):
        """ Called by service for starting a new test in the test node (this client)

         Parameters:
             request (TestStartRequest): Start request details

         """

        pass

    def on_test_stop_requested(self, request):
        """ Called by server for stopping ongoing test in the test node (this client)

         Parameters:
             request (TestStopRequest): Stop request details

         """
        pass


class Connector(object):
    """ Driver class for connecting to Profilence ZETA test service """

    log = None

    @staticmethod
    def _log(level, message):
        if Connector.log is not None:
            Connector.log(level, message)

    def __init__(self, host, port):
        """ Creates a new Connector instance and connects to the service

        Parameters:
            host: The service address
            port: The service port
        """

        self._channel = grpc.insecure_channel('%s:%d' % (host, port))
        self._blockingStub = connector_service_pb2_grpc.ConnectorServiceStub(self._channel)

    def shutdown(self):
        """ Shuts down the connection """

        self._channel.close()

    def ping(self):
        """ Pings the service

        Returns:
            True on success; False otherwise

        """

        value = 0
        try:
            value = self._blockingStub.Ping(connector_service_pb2.PingMessage()).result
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return value

    def start_run_with_recommended_settings(self, run_name, set_name, project, version, primary_device_serial,
                                            secondary_device_serial, tags):
        """ Requests the service for a new test run with recommended profiling settings

        Parameters:
            run_name (str):                 Name of the test run
            set_name (str):                 Name of the test set
            project (str):                  Name of the project under test
            version (str):                  Version of the project
            primary_device_serial (str):    Identifier of the primary DUT
            secondary_device_serial (str):  Identifier of the secondary DUT
            tags (dict):                    Tags for the test run

        Returns:
            Test run id (str) on success; None otherwise

        """

        return self.start_run(run_name,
                              set_name,
                              project,
                              version,
                              primary_device_serial,
                              secondary_device_serial,
                              None,
                              tags)

    def start_run(self, run_name, set_name, project, version, primary_device_serial, secondary_device_serial,
                  profiling_settings, tags):
        """ Requests the service for a new test run

        Parameters:
            run_name (str):                         Name of the test run
            set_name (str):                         Name of the test set
            project (str):                          Name of the project under test
            version (str):                          Version of the project
            primary_device_serial (str):            Identifier of the primary DUT
            secondary_device_serial (str):          Identifier of the secondary DUT
            profiling_settings (str|TextIOBase):    Profiling settings as JSON string, or a file handle to JSON file
            tags (dict):                            Tags for the test run

        Returns:
            Test run id (str) on success; None otherwise

        """

        if profiling_settings and isinstance(profiling_settings, TextIOBase):
            try:
                profiling_settings = profiling_settings.read()
            except IOError as ioe:
                self._log(2, 'File read failed: %s' % str(ioe))
                return None

        request = connector_service_pb2.StartRunRequest()
        request.run_name = run_name or ''
        request.set_name = set_name or ''
        request.project = project or ''
        request.version = version or ''
        request.primary_device_serial = primary_device_serial or ''
        request.secondary_device_serial = secondary_device_serial or ''
        request.profiling_settings = profiling_settings or ''
        request.tags.update(tags or {})
        try:
            response = self._blockingStub.StartRun(request)
            return response.run_id
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return None

    def on_use_case_start(self, run_id, use_case_name, use_case_id, target_process, requirement_id):
        """ Called to notify the service about start of a new use/test case

        Parameters:
            run_id (str):           ID of the test run
            use_case_name (str):    Name of the use/test case
            use_case_id (str):      ID of the use/test case
            target_process (str):   Name of a process to monitor more closely during the use/test case
            requirement_id (str):   ID of requirement this use/test case verifies

        Returns:
            True is notification sent successfully; otherwise False

        """

        if run_id is None or len(run_id.strip()) == 0:
            return False

        use_case_name_invalid = use_case_name is None or len(use_case_name.strip()) == 0
        use_case_id_invalid = use_case_id is None or len(use_case_id.strip()) == 0

        if use_case_name_invalid and use_case_id_invalid:
            return False

        request = connector_service_pb2.UseCaseStartRequest()
        request.run_id = run_id
        request.use_case_name = use_case_name or ''
        request.use_case_id = use_case_id or ''
        request.target_process = target_process or ''
        request.requirement_id = requirement_id or ''
        try:
            self._blockingStub.OnUseCaseStart(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def on_use_case_end(self, run_id, result, active_run_time, fail_cause, reset_intended):
        """ Called to notify the service about end of the use/test case

        Parameters:
            run_id (str):               ID of the test run
            result (bool):              Result of the use/test case (True=Pass; False=Failure)
            active_run_time (long):     Time (milliseconds) used active testing during the test
            fail_cause (str):           Fail cause, if any
            reset_intended (bool):      True if a DUT reset was caused by the test; otherwise False

        Returns:
            True is notification sent successfully; otherwise False

        """

        if run_id is None or len(run_id.strip()) == 0:
            return False

        request = connector_service_pb2.UseCaseEndRequest()
        request.run_id = run_id
        request.result = result
        request.activeRunTime = active_run_time
        request.fail_cause = fail_cause or ''
        request.reset_intended = reset_intended
        try:
            self._blockingStub.OnUseCaseEnd(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def on_log_step(self, run_id, step_name, result, screenshot):
        """ Called to notify the service about a new step within the use/test case

        Parameters:
            run_id (str):                   ID of the test run
            step_name (str):                Step name/description
            result (bool):                  True if the step passed as expected; otherwise False
            screenshot (bool|bytes|str):    True for requesting the service to take a screenshot;
                                            image bytes or path to image file for sending a local screenshot for
                                            the service. If requesting the service to take a screenshot, make sure
                                            it's supported for the DUT platform by the service.

        Returns:
            True is notification sent successfully; otherwise False

        """

        take_screenshot = isinstance(screenshot, bool) and screenshot is True
        screenshot_bytes = None
        if not take_screenshot:
            if isinstance(screenshot, bytes) and len(screenshot):
                screenshot_bytes = screenshot
            elif isinstance(screenshot, str):
                screenshot_bytes = get_bytes_from_file(screenshot)

        self._on_log_step(run_id, step_name, result, take_screenshot, screenshot_bytes)

    def _on_log_step(self, run_id, step_name, result, take_screenshot, screenshot_bytes):

        if run_id is None or len(run_id.strip()) == 0:
            return False

        if not take_screenshot and (not screenshot_bytes or len(screenshot_bytes) == 0) and (
                step_name is None or len(step_name.strip()) == 0):
            return False

        request = connector_service_pb2.LogStepRequest()
        request.run_id = run_id
        request.step_name = step_name or ''
        request.result = result
        request.take_screenshot = take_screenshot
        if screenshot_bytes:
            request.screenshot_bytes = screenshot_bytes
        try:
            self._blockingStub.OnLogStep(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def log_trace(self, run_id, data):
        """ Send a line or internal/execution log to the service

        Parameters:
            run_id (str):   ID of the test run
            data (str):     Log line

        Returns:
            True is notification sent successfully; otherwise False

        """

        if run_id is None or len(run_id.strip()) == 0:
            return False

        if data is None or len(data.strip()) == 0:
            return False

        request = connector_service_pb2.LogTraceRequest()
        request.run_id = run_id
        request.data = data
        try:
            self._blockingStub.LogTrace(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def stop_run(self, run_id, discard_results=False):
        """ Requests the service to stop an ongoing test run

        Parameters:
            run_id:             ID of the test run
            discard_results:    True to discard al the test data

        Returns:
            True is notification sent successfully; otherwise False

        """

        if run_id is None or len(run_id.strip()) == 0:
            return False

        request = connector_service_pb2.StopRunRequest()
        request.run_id = run_id
        request.discard_results = discard_results
        try:
            self._blockingStub.StopRun(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    # For implementing a test node for the test cloud -->

    def subscribe_to_test_requests(self, listener):
        """ Subscribe to test requests

        Parameters:
            listener (TestRequestListenerBase): Listener handler for asynchronous requests

        """

        if isinstance(listener, TestRequestListenerBase):
            threading.Thread(target=self._subscribe_to_test_requests, args=[listener], daemon=False).start()

    def _subscribe_to_test_requests(self, listener):

        if isinstance(listener, TestRequestListenerBase):
            try:
                for request in self._blockingStub.SubscribeToTestRequests(empty_pb2.Empty()):
                    if request and hasattr(request, 'request_type'):
                        try:
                            rt = request.request_type
                            if rt == 1:
                                start_req = connector_service_pb2.TestStartRequest()
                                start_req.ParseFromString(request.payload)
                                listener.on_test_start_requested(start_req)
                            elif rt == 2:
                                stop_req = connector_service_pb2.TestStopRequest()
                                stop_req.ParseFromString(request.payload)
                                listener.on_test_stop_requested(stop_req)
                        except Exception as ex:
                            listener.on_error(ex)
                listener.on_completed()
            except grpc.RpcError as e:
                listener.on_error(e)
                self._log(2, 'RPC failed: %s' % str(e))

    def respond_to_test_request(self, run_id, status, result, fail_cause, log):
        """ Send response to a test request

        Parameters:
            run_id (str):         ID of the local test run
            status (str):         Status
            result (bool):        True if test run was executed without issues
            fail_cause (str):     Fail cause if any
            log (list[str]):      Tool log

        Returns:
            True if response was sent to service; otherwise False

        """

        if run_id is None or len(run_id.strip()) == 0:
            return False

        resp = connector_service_pb2.TestStartResponse()
        resp.run_id = run_id
        resp.status = status or ''
        resp.result = result
        resp.fail_cause = fail_cause or ''
        if log and len(log):
            resp.log.extend(log)
        try:
            self._blockingStub.RespondToTestRequest(resp)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def add_node(self, node_id, pool, node_type, variables):
        """ Establishes a test node for the service (test farm)

        Parameters:
            node_id (str):      Unique ID of the node
            pool (str):         Name of the pool/group this node belongs to
            node_type (str):    Type of the node
            variables (str):    Variables

        Return:
            True if request was successfully sent to service; otherwise False

        """

        if node_id is None or len(node_id.strip()) == 0:
            return False

        request = connector_service_pb2.NodeAdded()
        request.node_id = node_id
        request.pool = pool or ''
        request.type = node_type or ''
        request.variables = variables or ''
        try:
            self._blockingStub.AddNode(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def remove_node(self, node_id):
        """ Removes the test node from the service (test farm)

        Parameters:
            node_id:    Unique ID of the node

        Returns:
            True if request was successfully sent to service; otherwise False

        """

        if node_id is None or len(node_id.strip()) == 0:
            return False

        request = connector_service_pb2.NodeRemoved()
        request.node_id = node_id
        try:
            self._blockingStub.RemoveNode(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def update_node(self, node_id, current_use_case, run_state, current_run_id, node_state, pool, variables):
        """ Updates the service with information from an existing test node

        Parameters:
            node_id (str):              Unique ID of the node
            current_use_case (str):     Name of current use case
            run_state (str):            State of the run
            current_run_id (str):       ID of the current run
            node_state (str):           State of the node
            pool (str):                 Name of the pool/group this node belongs to
            variables (str):            Variables

        Returns:
            True if request was successfully sent to service; otherwise False

        """

        if node_id is None or len(node_id.strip()) == 0:
            return False

        request = connector_service_pb2.NodeUpdated()
        if current_use_case and len(current_use_case.strip()):
            request.current_use_case = wrappers_pb2.StringValue()
            request.current_use_case.value = current_use_case
        if current_run_id and len(current_run_id.strip()):
            request.current_run_id = wrappers_pb2.StringValue()
            request.current_run_id.value = current_run_id
        if pool and len(pool.strip()):
            request.pool = wrappers_pb2.StringValue()
            request.pool.value = pool
        if variables and len(variables.strip()):
            request.variables = wrappers_pb2.StringValue()
            request.variables.value = variables
        if node_state is not None:
            request.node_state = wrappers_pb2.Int32Value()
            request.node_state.value = node_state
        if run_state is not None:
            request.run_state = wrappers_pb2.Int32Value()
            request.run_state.value = run_state
        try:
            self._blockingStub.UpdateNode(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False


# noinspection PyBroadException
def get_bytes_from_file(filename):
    if filename is not None and path.exists(filename):
        try:
            return open(filename, "rb").read()
        except Exception as e:
            pass


if __name__ == '__main__':

    if len(sys.argv):
        args = sys.argv[1:]
        p = re.compile('([^:]+):([0-9]+)')

        m = None
        address = None
        port = None

        if len(args) >= 1 and (m := p.match(args[0])):
            address = m.group(1)
            port = int(m.group(2))
        elif len(args) >= 2 and re.search('[0-9]+', args[1]):
            address = args[0]
            port = int(args[1])
        else:
            print('Invalid parameters; give address and port for ping test: [address]:[port]')

        Connector.log = lambda x, y: print(y)
        print('Connecting to %s:%d ...' % (address, port))
        client = None
        try:
            client = Connector(address, port)
            print('Pinging ..')
            if client.ping() == 1:
                print('Successfully pinged the service')
            else:
                print('Failed to ping the service')
        finally:
            if client:
                print('Shutting down ...')
                client.shutdown()
    else:
        print('Invalid parameters; give address and port for ping test: [address]:[port]')
