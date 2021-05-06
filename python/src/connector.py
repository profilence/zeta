from __future__ import print_function

import threading
from io import TextIOBase
import json
import grpc

import connector_service_pb2
import connector_service_pb2_grpc
import wrappers_pb2
import empty_pb2
from connector_types import PingResponseType, TestRequestListenerBase, TestType
from profiling_configuration import ProfilingConfiguration

from os import path
import sys
import re
import queue
import time

__copyright__ = "Copyright 2020 Profilence"
__license__ = "Apache License, Version 2.0"


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
        self._device_log_entry_queue = queue.Queue(maxsize=0)
        self._shutdown = False
        self._device_log_sender = threading.Thread(target=self._start_device_log_listener, daemon=False)
        self._device_log_sender.start()

    def _start_device_log_listener(self):
        try:
            self._blockingStub.LogDevice(self._read_device_log_entries())
        except grpc.RpcError:
            pass

    def _read_device_log_entries(self):

        while not self._shutdown:
            entry = self._device_log_entry_queue.get(block=True, timeout=5)
            if entry:
                yield entry

    def _wait_device_log_queue(self):
        while not self._shutdown and not self._device_log_entry_queue.empty():
            time.sleep(0.05)

    def shutdown(self):
        """ Shuts down the connection """
        self._shutdown = True
        self._device_log_entry_queue.put(None)
        self._device_log_sender.join(5)
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
                                            primary_device_type, secondary_device_serial, secondary_device_type, tags):
        """ Requests the service for a new test run with recommended profiling settings

        Parameters:
            run_name (str):                 Name of the test run
            set_name (str):                 Name of the test set
            project (str):                  Name of the project under test
            version (str):                  Version of the project
            primary_device_serial (str):    Identifier of the primary DUT
            primary_device_type (str):      Type of the primary DUT
            secondary_device_serial (str):  Identifier of the secondary DUT
            secondary_device_type (str):    Type of the secondary DUT
            tags (dict):                    Tags for the test run

        Returns:
            Test run id (str) on success; None otherwise

        """

        return self.start_run(run_name,
                              set_name,
                              project,
                              version,
                              primary_device_serial,
                              primary_device_type,
                              secondary_device_serial,
                              secondary_device_type,
                              None,
                              tags)

    def start_run(self, run_name, set_name, project, version, primary_device_serial, primary_device_type,
                  secondary_device_serial, secondary_device_type, profiling_settings, tags, run_id=None):
        """ Requests the service for a new test run

        Parameters:
            run_name (str):                         Name of the test run
            set_name (str):                         Name of the test set
            project (str):                          Name of the project under test
            version (str):                          Version of the project
            primary_device_serial (str):            Identifier of the primary DUT
            primary_device_type (str):              Type of the primary DUT
            secondary_device_serial (str):          Identifier of the secondary DUT
            secondary_device_type (str):            Type of the secondary DUT
            profiling_settings (str/TextIOBase):    Profiling settings as JSON string, or a file handle to JSON file
            tags (dict):                            Tags for the test run
            run_id (str):                           ID of the test run if the run is requested by the server

        Returns:
            Test run id (str) on success; None otherwise

        """

        if profiling_settings and isinstance(profiling_settings, ProfilingConfiguration):
            profiling_settings = profiling_settings.to_json()
        elif profiling_settings and isinstance(profiling_settings, TextIOBase):
            try:
                json.load(profiling_settings) #validate json file
                profiling_settings = profiling_settings.read()
            except IOError as ioe:
                self._log(2, 'Profiling settings read failed: %s' % str(ioe))
                return
            except json.decoder.JSONDecodeError as jsone:
                self._log(2, 'Invalid JSON file: %s' % str(jsone))
                return

        request = connector_service_pb2.StartRunRequest()
        request.run_name = run_name or ''
        request.set_name = set_name or ''
        request.project = project or ''
        request.version = version or ''
        request.primary_device_serial = primary_device_serial or ''
        request.secondary_device_serial = secondary_device_serial or ''
        request.primary_device_type = primary_device_type or ''
        request.secondary_device_type = secondary_device_type or ''
        request.profiling_settings = profiling_settings or ''
        request.tags.update(tags or {})
        request.run_id = run_id
        try:
            response = self._blockingStub.StartRun(request)
            return response.run_id
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return None

    def on_use_case_start(self, run_id, use_case_name, use_case_id, test_case_group_name=None, test_set_name=None,
                          test_type=TestType.NORMAL, target_process=None, requirement_id=None):
        """ Called to notify the service about start of a new use/test case

        Parameters:
            run_id (str):               ID of the test run
            use_case_name (str):        Name of the use/test case
            use_case_id (str):          ID of the use/test case
            test_case_group_name (str): Name of the group the use case belongs to (optional)
            test_set_name (str):        Name of the test suite the use case belongs to (optional)
            test_type (int):            Type of the use case: Normal, Precondition, PostCondition (Normal by default)
            target_process (str):       Name of a process to monitor more closely during the use/test case (optional)
            requirement_id (str):       ID of requirement this use/test case verifies (optional)

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
        request.test_case_group_name = test_case_group_name or ''
        request.test_set_name = test_set_name or ''
        request.test_type = test_type
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
        request.activeRunTime = int(active_run_time)
        request.fail_cause = fail_cause or ''
        request.reset_intended = reset_intended
        try:
            self._blockingStub.OnUseCaseEnd(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def on_log_step(self, run_id, step_name, result, screenshot, step_type):
        """ Called to notify the service about a new step within the use/test case

        Parameters:
            run_id (str):                   ID of the test run
            step_name (str):                Step name/description
            result (bool):                  True if the step passed as expected; otherwise False
            screenshot (bool/bytes/str):    True for requesting the service to take a screenshot;
                                            image bytes or path to image file for sending a local screenshot for
                                            the service. If requesting the service to take a screenshot, make sure
                                            it's supported for the DUT platform by the service.
            step_type (enum):               Value of step type. Values can be found in connector_service_pb2.py under "StepType"

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

        self._on_log_step(run_id, step_name, result, take_screenshot, screenshot_bytes, step_type)

    def _on_log_step(self, run_id, step_name, result, take_screenshot, screenshot_bytes, step_type):

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
        if step_type == None:
            step_type = 1
        request.stepType = step_type
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

    def notify_reset(self, run_id, timestamp, reset_type, reset_reasons, system_properties_after):
        """ Notify the service about a reset detected in the DUT

        Parameters:
            run_id (str):                   ID of the test run
            timestamp (float):              Time (milliseconds after epoc) when the reset took place
            reset_type (int):               Type of the reset (use ResetType enum)
            reset_reasons (dict):           Reset reasons if known
            system_properties_after (dict): DUT's system properties after the recovered from the reset

        Returns:
            True is notification sent successfully; otherwise False

        """

        if run_id is None or len(run_id.strip()) == 0:
            return False

        request = connector_service_pb2.ResetEntry()
        request.run_id = run_id
        request.timestamp = timestamp
        request.type = reset_type
        request.reasons.update(reset_reasons or {})
        request.properties.update(system_properties_after or {})

        try:
            self._blockingStub.NotifyReset(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def notify_event(self, run_id, timestamp, event_type, is_system_process, name, process, exception_type, data_lines):
        """ Notify the service about an event detected in the DUT

        Parameters:
            run_id (str):               ID of the test run
            timestamp (float):          Time (milliseconds after epoc) when the event took place
            event_type (int):           Type of the event (use EventType enum)
            is_system_process (bool):   True if caused by system process; False otherwise
            name (str):                 Name of the event dump
            process (str):              Name of the process created/caused the event
            exception_type (str):       Description of the event: e.g. 'OutOfMemoryException'
            data_lines (list[str]):     Event data

        Returns:
            True is notification sent successfully; otherwise False

        """

        if run_id is None or len(run_id.strip()) == 0:
            return False

        request = connector_service_pb2.EventEntry()
        request.run_id = run_id
        request.timestamp = timestamp
        request.type = event_type
        request.is_system_process = is_system_process
        request.name = name or ''
        request.process = process or ''
        request.exception_type = exception_type or ''
        if data_lines and len(data_lines):
            request.data.extend(data_lines)
        try:
            self._blockingStub.NotifyEvent(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def create_time_series(self, run_id, series_id, series_name, group, y_axis_name,
                           unit, series_type, namespace, process, description):
        """ Initialize a new time series chart

        Parameters:
            run_id (str):       ID of the test run
            series_id (str):    Unique ID of the series. This ID will be used when sending the values for the series
            series_name (str):  Name of the series
            group (str):        Group name for the series
            y_axis_name (str):  Name of the Y-axis
            unit (str):         Unit of the data
            series_type (int):  Type of the series (use SeriesType enum)
            namespace (str):    Process/package namespace (only for process specific charts)
            process (str):      Name of the process (only for process specific charts)
            description (str):  Description for the series

        Returns:
            True is data sent successfully; otherwise False

        """

        if run_id is None or len(run_id.strip()) == 0:
            return False

        if series_id is None or len(series_id.strip()) == 0:
            return False

        request = connector_service_pb2.DynamicSeriesInformation()
        request.run_id = run_id
        request.series_id = series_id
        request.series_name = series_name or ''
        request.group = group or ''
        request.y_axis_name = y_axis_name or ''
        request.unit = unit or ''
        request.type = series_type
        request.namespace = namespace or ''
        request.process = process or ''
        request.description = description or ''

        try:
            self._blockingStub.CreateTimeSeries(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def update_single_system_series(self, run_id, series_id, timestamp, value):
        """ Update system singe series data

        Parameters:
            run_id (str):       ID of the test run
            series_id (str):    Unique ID of the series.
            timestamp (float):  X-value. Time (milliseconds after epoc)
            value (float):      Y-value

        Returns:
            True is data sent successfully; otherwise False

        """

        if run_id is None or len(run_id.strip()) == 0:
            return False

        if series_id is None or len(series_id.strip()) == 0:
            return False

        request = connector_service_pb2.DynamicSingleSeriesUpdate()
        request.run_id = run_id
        request.series_id = series_id
        request.timestamp = timestamp
        request.value = value

        try:
            self._blockingStub.UpdateSingleSystemSeries(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def update_composite_system_series(self, run_id, series_id, timestamp, values):
        """ Update system composite series data

        Parameters:
            run_id (str):               ID of the test run
            series_id (str):            Unique ID of the series.
            timestamp (float):          X-value. Time (milliseconds after epoc)
            values (dict[str, float]):  Y-value per column

        Returns:
            True is data sent successfully; otherwise False

        """

        if run_id is None or len(run_id.strip()) == 0:
            return False

        if series_id is None or len(series_id.strip()) == 0:
            return False

        request = connector_service_pb2.DynamicCompositeSeriesUpdate()
        request.run_id = run_id
        request.series_id = series_id
        request.timestamp = timestamp
        request.values.update(values or {})
        try:
            self._blockingStub.UpdateCompositeSystemSeries(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def update_single_process_series(self, run_id, series_id, timestamp, package, process, value, pid=None):
        """ Update process specific single series data

        Parameters:
            run_id (str):       ID of the test run
            series_id (str):    Unique ID of the series.
            timestamp (float):  X-value. Time (milliseconds after epoc)
            package (str):      Namespace of the package
            process (str):      Name of the process
            value (float):      Y-value
            pid (int):          ID of the process (optional)

        Returns:
            True is data sent successfully; otherwise False

        """

        if run_id is None or len(run_id.strip()) == 0:
            return False

        if series_id is None or len(series_id.strip()) == 0:
            return False

        request = connector_service_pb2.DynamicProcessSingleSeriesUpdate()
        request.run_id = run_id
        request.series_id = series_id
        request.timestamp = timestamp
        request.package = package or ''
        request.process = process or ''
        request.value = value

        if pid is not None:
            pid_value = wrappers_pb2.Int32Value()
            pid_value.value = pid
            request.pid = pid_value

        try:
            self._blockingStub.UpdateSingleProcessSeries(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def update_composite_process_series(self, run_id, series_id, timestamp, package, process, values, pid=None):
        """ Update process specific composite series data

        Parameters:
            run_id (str):               ID of the test run
            series_id (str):            Unique ID of the series.
            timestamp (float):          X-value. Time (milliseconds after epoc)
            package (str):              Namespace of the package
            process (str):              Name of the process
            values (dict[str, float]):  Y-value per column
            pid (int):                  ID of the process (optional)

        Returns:
            True is data sent successfully; otherwise False

        """

        if run_id is None or len(run_id.strip()) == 0:
            return False

        if series_id is None or len(series_id.strip()) == 0:
            return False

        request = connector_service_pb2.DynamicProcessCompositeSeriesUpdate()
        request.run_id = run_id
        request.series_id = series_id
        request.timestamp = timestamp
        request.package = package or ''
        request.process = process or ''
        request.values.update(values or {})

        if pid is not None:
            pid_value = wrappers_pb2.Int32Value()
            pid_value.value = pid
            request.pid = pid_value

        try:
            self._blockingStub.UpdateCompositeProcessSeries(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def on_device_log(self, run_id, timestamp, log_priority, source_buffer_type, tag, data):
        """ Route a device log line to service

        Parameters:
            run_id (str):               ID of the test run
            timestamp (float):          Time (milliseconds after epoc)
            log_priority (int):         Priority of the log message (use LogPriority enum)
            source_buffer_type (int):   Source buffer of the log message (use SourceBuffer enum)
            tag (str):                  Tag of the message
            data (str):                 Message data

        Returns:
            True is data buffered successfully; otherwise False

        """

        self.__on_device_log_impl(run_id, 1, timestamp, log_priority, source_buffer_type, tag, data)

    def on_device2_log(self, run_id, timestamp, log_priority, source_buffer_type, tag, data):
        """ Route a device2 log line to service

        Parameters:
            run_id (str):               ID of the test run
            timestamp (float):          Time (milliseconds after epoc)
            log_priority (int):         Priority of the log message (use LogPriority enum)
            source_buffer_type (int):   Source buffer of the log message (use SourceBuffer enum)
            tag (str):                  Tag of the message
            data (str):                 Message data

        Returns:
            True is data buffered successfully; otherwise False

        """

        self.__on_device_log_impl(run_id, 2, timestamp, log_priority, source_buffer_type, tag, data)

    def __on_device_log_impl(self, run_id, device_index, timestamp, log_priority, source_buffer_type, tag, data):

        if run_id is None or len(run_id.strip()) == 0:
            return False

        entry = connector_service_pb2.DeviceLogEntry()
        entry.run_id = run_id
        entry.device_index = device_index
        entry.timestamp = timestamp
        entry.priority = log_priority
        entry.source_buffer = source_buffer_type
        entry.tag = tag or ''
        entry.data = data or ''

        try:
            self._device_log_entry_queue.put(entry)
            return True
        except Exception as e:
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
            self._wait_device_log_queue()
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
            True if response was successfully sent to service; otherwise False

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
        request.variables = variables or '{}'
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
            run_state (int):            State of the run
            current_run_id (str):       ID of the current run
            node_state (int):           State of the node
            pool (str):                 Name of the pool/group this node belongs to
            variables (str):            Variables

        Returns:
            True if request was successfully sent to service; otherwise False

        """

        if node_id is None or len(node_id.strip()) == 0:
            return False

        request = connector_service_pb2.NodeUpdated()
        request.node_id = node_id

        if current_use_case and len(current_use_case.strip()):
            request.current_use_case.value = current_use_case
        if current_run_id and len(current_run_id.strip()):
            request.current_run_id.value = current_run_id
        if pool and len(pool.strip()):
            request.pool.value = pool
        if variables and len(variables.strip()):
            request.variables.value = variables
        if node_state is not None:
            request.node_state.value = node_state
        if run_state is not None:
            request.run_state.value = run_state
        try:
            self._blockingStub.UpdateNode(request)
            return True
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return False

    def ping_run(self, run_id):

        """ Ping Run

        Parameters:
            run_id (str):         ID of the local test run

        Returns:
            True if request was successfully sent to service; otherwise False
        """
        if run_id is None or len(run_id.strip()) == 0:
            return False
        
        request = connector_service_pb2.PingRunRequest()
        request.run_id = run_id
        
        try:
            self._blockingStub.PingRun(request)
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

        address = None
        port = None

        if len(args) >= 1 and (p.match(args[0])):
            m = p.match(args[0])
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
            if client.ping() == PingResponseType.OK:
                print('Successfully pinged the service')
            else:
                print('Failed to ping the service')
        finally:
            if client:
                print('Shutting down ...')
                client.shutdown()
    else:
        print('Invalid parameters; give address and port for ping test: [address]:[port]')
