from __future__ import print_function

import grpc

import connector_service_pb2
import connector_service_pb2_grpc

import os.path
from os import path


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

    def start_run(self, run_name, set_name, project, version, primary_device_serial, secondary_device_serial,
                  profiling_settings, tags):

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

        if not take_screenshot and (not screenshot_bytes or len(screenshot_bytes) == 0) and (step_name is None or len(step_name.strip()) == 0):
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

    def subscribe_to_test_requests(self, listener):

        if isinstance(listener, TestRequestListenerBase):
            pass


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
