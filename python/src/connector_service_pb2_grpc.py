# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import connector_service_pb2 as connector__service__pb2
import empty_pb2 as empty__pb2


class ConnectorServiceStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.Ping = channel.unary_unary(
                '/profilence.zeta.ConnectorService/Ping',
                request_serializer=connector__service__pb2.PingMessage.SerializeToString,
                response_deserializer=connector__service__pb2.PongMessage.FromString,
                )
        self.StartRun = channel.unary_unary(
                '/profilence.zeta.ConnectorService/StartRun',
                request_serializer=connector__service__pb2.StartRunRequest.SerializeToString,
                response_deserializer=connector__service__pb2.StartRunResponse.FromString,
                )
        self.OnUseCaseStart = channel.unary_unary(
                '/profilence.zeta.ConnectorService/OnUseCaseStart',
                request_serializer=connector__service__pb2.UseCaseStartRequest.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.OnLogStep = channel.unary_unary(
                '/profilence.zeta.ConnectorService/OnLogStep',
                request_serializer=connector__service__pb2.LogStepRequest.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.LogTrace = channel.unary_unary(
                '/profilence.zeta.ConnectorService/LogTrace',
                request_serializer=connector__service__pb2.LogTraceRequest.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.LogDevice = channel.stream_unary(
                '/profilence.zeta.ConnectorService/LogDevice',
                request_serializer=connector__service__pb2.DeviceLogEntry.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.NotifyReset = channel.unary_unary(
                '/profilence.zeta.ConnectorService/NotifyReset',
                request_serializer=connector__service__pb2.ResetEntry.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.NotifyEvent = channel.unary_unary(
                '/profilence.zeta.ConnectorService/NotifyEvent',
                request_serializer=connector__service__pb2.EventEntry.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.OnUseCaseEnd = channel.unary_unary(
                '/profilence.zeta.ConnectorService/OnUseCaseEnd',
                request_serializer=connector__service__pb2.UseCaseEndRequest.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.StopRun = channel.unary_unary(
                '/profilence.zeta.ConnectorService/StopRun',
                request_serializer=connector__service__pb2.StopRunRequest.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.CreateTimeSeries = channel.unary_unary(
                '/profilence.zeta.ConnectorService/CreateTimeSeries',
                request_serializer=connector__service__pb2.DynamicSeriesInformation.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.UpdateSingleSystemSeries = channel.unary_unary(
                '/profilence.zeta.ConnectorService/UpdateSingleSystemSeries',
                request_serializer=connector__service__pb2.DynamicSingleSeriesUpdate.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.UpdateSingleProcessSeries = channel.unary_unary(
                '/profilence.zeta.ConnectorService/UpdateSingleProcessSeries',
                request_serializer=connector__service__pb2.DynamicProcessSingleSeriesUpdate.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.UpdateCompositeSystemSeries = channel.unary_unary(
                '/profilence.zeta.ConnectorService/UpdateCompositeSystemSeries',
                request_serializer=connector__service__pb2.DynamicCompositeSeriesUpdate.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.UpdateCompositeProcessSeries = channel.unary_unary(
                '/profilence.zeta.ConnectorService/UpdateCompositeProcessSeries',
                request_serializer=connector__service__pb2.DynamicProcessCompositeSeriesUpdate.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.SubscribeToTestRequests = channel.unary_stream(
                '/profilence.zeta.ConnectorService/SubscribeToTestRequests',
                request_serializer=empty__pb2.Empty.SerializeToString,
                response_deserializer=connector__service__pb2.TestRequestMessageWrapper.FromString,
                )
        self.RespondToTestRequest = channel.unary_unary(
                '/profilence.zeta.ConnectorService/RespondToTestRequest',
                request_serializer=connector__service__pb2.TestStartResponse.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.AddNode = channel.unary_unary(
                '/profilence.zeta.ConnectorService/AddNode',
                request_serializer=connector__service__pb2.NodeAdded.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.RemoveNode = channel.unary_unary(
                '/profilence.zeta.ConnectorService/RemoveNode',
                request_serializer=connector__service__pb2.NodeRemoved.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.UpdateNode = channel.unary_unary(
                '/profilence.zeta.ConnectorService/UpdateNode',
                request_serializer=connector__service__pb2.NodeUpdated.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )
        self.PingRun = channel.unary_unary(
                '/profilence.zeta.ConnectorService/PingRun',
                request_serializer=connector__service__pb2.PingRunRequest.SerializeToString,
                response_deserializer=empty__pb2.Empty.FromString,
                )


class ConnectorServiceServicer(object):
    """Missing associated documentation comment in .proto file."""

    def Ping(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def StartRun(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def OnUseCaseStart(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def OnLogStep(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def LogTrace(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def LogDevice(self, request_iterator, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def NotifyReset(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def NotifyEvent(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def OnUseCaseEnd(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def StopRun(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def CreateTimeSeries(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def UpdateSingleSystemSeries(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def UpdateSingleProcessSeries(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def UpdateCompositeSystemSeries(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def UpdateCompositeProcessSeries(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def SubscribeToTestRequests(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def RespondToTestRequest(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def AddNode(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def RemoveNode(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def UpdateNode(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def PingRun(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_ConnectorServiceServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'Ping': grpc.unary_unary_rpc_method_handler(
                    servicer.Ping,
                    request_deserializer=connector__service__pb2.PingMessage.FromString,
                    response_serializer=connector__service__pb2.PongMessage.SerializeToString,
            ),
            'StartRun': grpc.unary_unary_rpc_method_handler(
                    servicer.StartRun,
                    request_deserializer=connector__service__pb2.StartRunRequest.FromString,
                    response_serializer=connector__service__pb2.StartRunResponse.SerializeToString,
            ),
            'OnUseCaseStart': grpc.unary_unary_rpc_method_handler(
                    servicer.OnUseCaseStart,
                    request_deserializer=connector__service__pb2.UseCaseStartRequest.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'OnLogStep': grpc.unary_unary_rpc_method_handler(
                    servicer.OnLogStep,
                    request_deserializer=connector__service__pb2.LogStepRequest.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'LogTrace': grpc.unary_unary_rpc_method_handler(
                    servicer.LogTrace,
                    request_deserializer=connector__service__pb2.LogTraceRequest.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'LogDevice': grpc.stream_unary_rpc_method_handler(
                    servicer.LogDevice,
                    request_deserializer=connector__service__pb2.DeviceLogEntry.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'NotifyReset': grpc.unary_unary_rpc_method_handler(
                    servicer.NotifyReset,
                    request_deserializer=connector__service__pb2.ResetEntry.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'NotifyEvent': grpc.unary_unary_rpc_method_handler(
                    servicer.NotifyEvent,
                    request_deserializer=connector__service__pb2.EventEntry.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'OnUseCaseEnd': grpc.unary_unary_rpc_method_handler(
                    servicer.OnUseCaseEnd,
                    request_deserializer=connector__service__pb2.UseCaseEndRequest.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'StopRun': grpc.unary_unary_rpc_method_handler(
                    servicer.StopRun,
                    request_deserializer=connector__service__pb2.StopRunRequest.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'CreateTimeSeries': grpc.unary_unary_rpc_method_handler(
                    servicer.CreateTimeSeries,
                    request_deserializer=connector__service__pb2.DynamicSeriesInformation.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'UpdateSingleSystemSeries': grpc.unary_unary_rpc_method_handler(
                    servicer.UpdateSingleSystemSeries,
                    request_deserializer=connector__service__pb2.DynamicSingleSeriesUpdate.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'UpdateSingleProcessSeries': grpc.unary_unary_rpc_method_handler(
                    servicer.UpdateSingleProcessSeries,
                    request_deserializer=connector__service__pb2.DynamicProcessSingleSeriesUpdate.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'UpdateCompositeSystemSeries': grpc.unary_unary_rpc_method_handler(
                    servicer.UpdateCompositeSystemSeries,
                    request_deserializer=connector__service__pb2.DynamicCompositeSeriesUpdate.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'UpdateCompositeProcessSeries': grpc.unary_unary_rpc_method_handler(
                    servicer.UpdateCompositeProcessSeries,
                    request_deserializer=connector__service__pb2.DynamicProcessCompositeSeriesUpdate.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'SubscribeToTestRequests': grpc.unary_stream_rpc_method_handler(
                    servicer.SubscribeToTestRequests,
                    request_deserializer=empty__pb2.Empty.FromString,
                    response_serializer=connector__service__pb2.TestRequestMessageWrapper.SerializeToString,
            ),
            'RespondToTestRequest': grpc.unary_unary_rpc_method_handler(
                    servicer.RespondToTestRequest,
                    request_deserializer=connector__service__pb2.TestStartResponse.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'AddNode': grpc.unary_unary_rpc_method_handler(
                    servicer.AddNode,
                    request_deserializer=connector__service__pb2.NodeAdded.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'RemoveNode': grpc.unary_unary_rpc_method_handler(
                    servicer.RemoveNode,
                    request_deserializer=connector__service__pb2.NodeRemoved.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'UpdateNode': grpc.unary_unary_rpc_method_handler(
                    servicer.UpdateNode,
                    request_deserializer=connector__service__pb2.NodeUpdated.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
            'PingRun': grpc.unary_unary_rpc_method_handler(
                    servicer.PingRun,
                    request_deserializer=connector__service__pb2.PingRunRequest.FromString,
                    response_serializer=empty__pb2.Empty.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'profilence.zeta.ConnectorService', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class ConnectorService(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def Ping(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/Ping',
            connector__service__pb2.PingMessage.SerializeToString,
            connector__service__pb2.PongMessage.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def StartRun(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/StartRun',
            connector__service__pb2.StartRunRequest.SerializeToString,
            connector__service__pb2.StartRunResponse.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def OnUseCaseStart(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/OnUseCaseStart',
            connector__service__pb2.UseCaseStartRequest.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def OnLogStep(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/OnLogStep',
            connector__service__pb2.LogStepRequest.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def LogTrace(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/LogTrace',
            connector__service__pb2.LogTraceRequest.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def LogDevice(request_iterator,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.stream_unary(request_iterator, target, '/profilence.zeta.ConnectorService/LogDevice',
            connector__service__pb2.DeviceLogEntry.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def NotifyReset(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/NotifyReset',
            connector__service__pb2.ResetEntry.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def NotifyEvent(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/NotifyEvent',
            connector__service__pb2.EventEntry.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def OnUseCaseEnd(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/OnUseCaseEnd',
            connector__service__pb2.UseCaseEndRequest.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def StopRun(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/StopRun',
            connector__service__pb2.StopRunRequest.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def CreateTimeSeries(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/CreateTimeSeries',
            connector__service__pb2.DynamicSeriesInformation.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def UpdateSingleSystemSeries(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/UpdateSingleSystemSeries',
            connector__service__pb2.DynamicSingleSeriesUpdate.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def UpdateSingleProcessSeries(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/UpdateSingleProcessSeries',
            connector__service__pb2.DynamicProcessSingleSeriesUpdate.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def UpdateCompositeSystemSeries(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/UpdateCompositeSystemSeries',
            connector__service__pb2.DynamicCompositeSeriesUpdate.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def UpdateCompositeProcessSeries(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/UpdateCompositeProcessSeries',
            connector__service__pb2.DynamicProcessCompositeSeriesUpdate.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def SubscribeToTestRequests(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_stream(request, target, '/profilence.zeta.ConnectorService/SubscribeToTestRequests',
            empty__pb2.Empty.SerializeToString,
            connector__service__pb2.TestRequestMessageWrapper.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def RespondToTestRequest(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/RespondToTestRequest',
            connector__service__pb2.TestStartResponse.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def AddNode(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/AddNode',
            connector__service__pb2.NodeAdded.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def RemoveNode(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/RemoveNode',
            connector__service__pb2.NodeRemoved.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def UpdateNode(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/UpdateNode',
            connector__service__pb2.NodeUpdated.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def PingRun(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/profilence.zeta.ConnectorService/PingRun',
            connector__service__pb2.PingRunRequest.SerializeToString,
            empty__pb2.Empty.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)
