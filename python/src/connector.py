from __future__ import print_function

import threading
from io import TextIOBase

import grpc

import connector_service_pb2
import connector_service_pb2_grpc
import wrappers_pb2
import empty_pb2

from os import path

__copyright__ = "Copyright 2020 Profilence"
__license__ = "Apache License, Version 2.0"


class TestRequestListenerBase(object):

    def on_error(self, e):
        pass

    def on_completed(self):
        pass

    def on_test_start_requested(self, request):
        pass

    def on_test_stop_requested(self, request):
        pass


class Connector(object):
    log = None

    def __init__(self, host, port):
        self._channel = grpc.insecure_channel('%s:%d' % (host, port))
        self._blockingStub = connector_service_pb2_grpc.ConnectorServiceStub(self._channel)

    @staticmethod
    def _log(level, message):
        if Connector.log is not None:
            Connector.log(level, message)

    def shutdown(self):
        self._channel.close()

    def ping(self):
        value = 0
        try:
            value = self._blockingStub.Ping(connector_service_pb2.PingMessage()).result
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return value

    def start_run_with_recommended_settings(self, run_name, set_name, project, version, primary_device_serial,
                                            secondary_device_serial, tags):
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

    def stop_run(self, run_id, discard_results):

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

    def subscribe_to_test_requests(self, listener):
        if isinstance(listener, TestRequestListenerBase):
            threading.Thread(target=self._subscribe_to_test_requests, args=[listener], daemon=False).start()

    def _subscribe_to_test_requests(self, listener):

        if isinstance(listener, TestRequestListenerBase):
            try:
                for request in self._blockingStub.SubscribeToTestRequests(empty_pb2.Empty()):
                    if request and hasattr(request, 'request_type'):
                        try:
                            rt = request.request_type
                            if rt is 1:
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


def get_bytes_from_file(filename):
    if filename is not None and path.exists(filename):
        try:
            return open(filename, "rb").read()
        except Exception as e:
            pass


def cw(level, message):
    print(message)


if __name__ == '__main__':
    Connector.log = cw
    connector = Connector('localhost', 31321)
    print(connector.start_run('python', 'demo', 'PProject', '1.0.0', None, None, None, None))
    connector.shutdown()
