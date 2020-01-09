from __future__ import print_function

import random
import logging

import grpc

import connector_service_pb2
import connector_service_pb2_grpc

class Connector(object):
    
    log = None

    def __init__(self, host, port):
        self._channel = grpc.insecure_channel('%s:%d' % (host, port))
        self._blockingStub = connector_service_pb2_grpc.ConnectorServiceStub(self._channel)

    def _log(self, level, message):
        if (Connector.log != None):
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
   
    def startRun(self, run_name, set_name, project, version, primary_device_serial, secondary_device_serial, profiling_settings, tags):
        
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
            return response.run_id;
        except grpc.RpcError as e:
            self._log(2, 'RPC failed: %s' % str(e))
        return None  
    

def cw(level, message):
    print(message)

if __name__ == '__main__':

    Connector.log = cw
    connector = Connector('localhost', 31321)
    print(connector.startRun('python', 'demo', 'PProject', '1.0.0', None, None, None, None))
    connector.shutdown()
