package com.profilence.zeta;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.26.0)",
    comments = "Source: connector_service.proto")
public final class ConnectorServiceGrpc {

  private ConnectorServiceGrpc() {}

  public static final String SERVICE_NAME = "profilence.zeta.ConnectorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.profilence.zeta.PingMessage,
      com.profilence.zeta.PongMessage> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Ping",
      requestType = com.profilence.zeta.PingMessage.class,
      responseType = com.profilence.zeta.PongMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.profilence.zeta.PingMessage,
      com.profilence.zeta.PongMessage> getPingMethod() {
    io.grpc.MethodDescriptor<com.profilence.zeta.PingMessage, com.profilence.zeta.PongMessage> getPingMethod;
    if ((getPingMethod = ConnectorServiceGrpc.getPingMethod) == null) {
      synchronized (ConnectorServiceGrpc.class) {
        if ((getPingMethod = ConnectorServiceGrpc.getPingMethod) == null) {
          ConnectorServiceGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<com.profilence.zeta.PingMessage, com.profilence.zeta.PongMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.profilence.zeta.PingMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.profilence.zeta.PongMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ConnectorServiceMethodDescriptorSupplier("Ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.profilence.zeta.StartRunRequest,
      com.profilence.zeta.StartRunResponse> getStartRunMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StartRun",
      requestType = com.profilence.zeta.StartRunRequest.class,
      responseType = com.profilence.zeta.StartRunResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.profilence.zeta.StartRunRequest,
      com.profilence.zeta.StartRunResponse> getStartRunMethod() {
    io.grpc.MethodDescriptor<com.profilence.zeta.StartRunRequest, com.profilence.zeta.StartRunResponse> getStartRunMethod;
    if ((getStartRunMethod = ConnectorServiceGrpc.getStartRunMethod) == null) {
      synchronized (ConnectorServiceGrpc.class) {
        if ((getStartRunMethod = ConnectorServiceGrpc.getStartRunMethod) == null) {
          ConnectorServiceGrpc.getStartRunMethod = getStartRunMethod =
              io.grpc.MethodDescriptor.<com.profilence.zeta.StartRunRequest, com.profilence.zeta.StartRunResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StartRun"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.profilence.zeta.StartRunRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.profilence.zeta.StartRunResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ConnectorServiceMethodDescriptorSupplier("StartRun"))
              .build();
        }
      }
    }
    return getStartRunMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.profilence.zeta.UseCaseStartRequest,
      com.google.protobuf.Empty> getOnUseCaseStartMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OnUseCaseStart",
      requestType = com.profilence.zeta.UseCaseStartRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.profilence.zeta.UseCaseStartRequest,
      com.google.protobuf.Empty> getOnUseCaseStartMethod() {
    io.grpc.MethodDescriptor<com.profilence.zeta.UseCaseStartRequest, com.google.protobuf.Empty> getOnUseCaseStartMethod;
    if ((getOnUseCaseStartMethod = ConnectorServiceGrpc.getOnUseCaseStartMethod) == null) {
      synchronized (ConnectorServiceGrpc.class) {
        if ((getOnUseCaseStartMethod = ConnectorServiceGrpc.getOnUseCaseStartMethod) == null) {
          ConnectorServiceGrpc.getOnUseCaseStartMethod = getOnUseCaseStartMethod =
              io.grpc.MethodDescriptor.<com.profilence.zeta.UseCaseStartRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OnUseCaseStart"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.profilence.zeta.UseCaseStartRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ConnectorServiceMethodDescriptorSupplier("OnUseCaseStart"))
              .build();
        }
      }
    }
    return getOnUseCaseStartMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.profilence.zeta.LogStepRequest,
      com.google.protobuf.Empty> getOnLogStepMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OnLogStep",
      requestType = com.profilence.zeta.LogStepRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.profilence.zeta.LogStepRequest,
      com.google.protobuf.Empty> getOnLogStepMethod() {
    io.grpc.MethodDescriptor<com.profilence.zeta.LogStepRequest, com.google.protobuf.Empty> getOnLogStepMethod;
    if ((getOnLogStepMethod = ConnectorServiceGrpc.getOnLogStepMethod) == null) {
      synchronized (ConnectorServiceGrpc.class) {
        if ((getOnLogStepMethod = ConnectorServiceGrpc.getOnLogStepMethod) == null) {
          ConnectorServiceGrpc.getOnLogStepMethod = getOnLogStepMethod =
              io.grpc.MethodDescriptor.<com.profilence.zeta.LogStepRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OnLogStep"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.profilence.zeta.LogStepRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ConnectorServiceMethodDescriptorSupplier("OnLogStep"))
              .build();
        }
      }
    }
    return getOnLogStepMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.profilence.zeta.LogTraceRequest,
      com.google.protobuf.Empty> getLogTraceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LogTrace",
      requestType = com.profilence.zeta.LogTraceRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.profilence.zeta.LogTraceRequest,
      com.google.protobuf.Empty> getLogTraceMethod() {
    io.grpc.MethodDescriptor<com.profilence.zeta.LogTraceRequest, com.google.protobuf.Empty> getLogTraceMethod;
    if ((getLogTraceMethod = ConnectorServiceGrpc.getLogTraceMethod) == null) {
      synchronized (ConnectorServiceGrpc.class) {
        if ((getLogTraceMethod = ConnectorServiceGrpc.getLogTraceMethod) == null) {
          ConnectorServiceGrpc.getLogTraceMethod = getLogTraceMethod =
              io.grpc.MethodDescriptor.<com.profilence.zeta.LogTraceRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LogTrace"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.profilence.zeta.LogTraceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ConnectorServiceMethodDescriptorSupplier("LogTrace"))
              .build();
        }
      }
    }
    return getLogTraceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.profilence.zeta.UseCaseEndRequest,
      com.google.protobuf.Empty> getOnUseCaseEndMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OnUseCaseEnd",
      requestType = com.profilence.zeta.UseCaseEndRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.profilence.zeta.UseCaseEndRequest,
      com.google.protobuf.Empty> getOnUseCaseEndMethod() {
    io.grpc.MethodDescriptor<com.profilence.zeta.UseCaseEndRequest, com.google.protobuf.Empty> getOnUseCaseEndMethod;
    if ((getOnUseCaseEndMethod = ConnectorServiceGrpc.getOnUseCaseEndMethod) == null) {
      synchronized (ConnectorServiceGrpc.class) {
        if ((getOnUseCaseEndMethod = ConnectorServiceGrpc.getOnUseCaseEndMethod) == null) {
          ConnectorServiceGrpc.getOnUseCaseEndMethod = getOnUseCaseEndMethod =
              io.grpc.MethodDescriptor.<com.profilence.zeta.UseCaseEndRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OnUseCaseEnd"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.profilence.zeta.UseCaseEndRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ConnectorServiceMethodDescriptorSupplier("OnUseCaseEnd"))
              .build();
        }
      }
    }
    return getOnUseCaseEndMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.profilence.zeta.StopRunRequest,
      com.google.protobuf.Empty> getStopRunMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StopRun",
      requestType = com.profilence.zeta.StopRunRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.profilence.zeta.StopRunRequest,
      com.google.protobuf.Empty> getStopRunMethod() {
    io.grpc.MethodDescriptor<com.profilence.zeta.StopRunRequest, com.google.protobuf.Empty> getStopRunMethod;
    if ((getStopRunMethod = ConnectorServiceGrpc.getStopRunMethod) == null) {
      synchronized (ConnectorServiceGrpc.class) {
        if ((getStopRunMethod = ConnectorServiceGrpc.getStopRunMethod) == null) {
          ConnectorServiceGrpc.getStopRunMethod = getStopRunMethod =
              io.grpc.MethodDescriptor.<com.profilence.zeta.StopRunRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StopRun"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.profilence.zeta.StopRunRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ConnectorServiceMethodDescriptorSupplier("StopRun"))
              .build();
        }
      }
    }
    return getStopRunMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.profilence.zeta.TestRequest> getSubscribeToTestRequestsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubscribeToTestRequests",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.profilence.zeta.TestRequest.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.profilence.zeta.TestRequest> getSubscribeToTestRequestsMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.profilence.zeta.TestRequest> getSubscribeToTestRequestsMethod;
    if ((getSubscribeToTestRequestsMethod = ConnectorServiceGrpc.getSubscribeToTestRequestsMethod) == null) {
      synchronized (ConnectorServiceGrpc.class) {
        if ((getSubscribeToTestRequestsMethod = ConnectorServiceGrpc.getSubscribeToTestRequestsMethod) == null) {
          ConnectorServiceGrpc.getSubscribeToTestRequestsMethod = getSubscribeToTestRequestsMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.profilence.zeta.TestRequest>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubscribeToTestRequests"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.profilence.zeta.TestRequest.getDefaultInstance()))
              .setSchemaDescriptor(new ConnectorServiceMethodDescriptorSupplier("SubscribeToTestRequests"))
              .build();
        }
      }
    }
    return getSubscribeToTestRequestsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.profilence.zeta.TestRequestAck,
      com.google.protobuf.Empty> getRespondToTestRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RespondToTestRequest",
      requestType = com.profilence.zeta.TestRequestAck.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.profilence.zeta.TestRequestAck,
      com.google.protobuf.Empty> getRespondToTestRequestMethod() {
    io.grpc.MethodDescriptor<com.profilence.zeta.TestRequestAck, com.google.protobuf.Empty> getRespondToTestRequestMethod;
    if ((getRespondToTestRequestMethod = ConnectorServiceGrpc.getRespondToTestRequestMethod) == null) {
      synchronized (ConnectorServiceGrpc.class) {
        if ((getRespondToTestRequestMethod = ConnectorServiceGrpc.getRespondToTestRequestMethod) == null) {
          ConnectorServiceGrpc.getRespondToTestRequestMethod = getRespondToTestRequestMethod =
              io.grpc.MethodDescriptor.<com.profilence.zeta.TestRequestAck, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RespondToTestRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.profilence.zeta.TestRequestAck.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ConnectorServiceMethodDescriptorSupplier("RespondToTestRequest"))
              .build();
        }
      }
    }
    return getRespondToTestRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.profilence.zeta.NodeAdded,
      com.google.protobuf.Empty> getAddNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddNode",
      requestType = com.profilence.zeta.NodeAdded.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.profilence.zeta.NodeAdded,
      com.google.protobuf.Empty> getAddNodeMethod() {
    io.grpc.MethodDescriptor<com.profilence.zeta.NodeAdded, com.google.protobuf.Empty> getAddNodeMethod;
    if ((getAddNodeMethod = ConnectorServiceGrpc.getAddNodeMethod) == null) {
      synchronized (ConnectorServiceGrpc.class) {
        if ((getAddNodeMethod = ConnectorServiceGrpc.getAddNodeMethod) == null) {
          ConnectorServiceGrpc.getAddNodeMethod = getAddNodeMethod =
              io.grpc.MethodDescriptor.<com.profilence.zeta.NodeAdded, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.profilence.zeta.NodeAdded.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ConnectorServiceMethodDescriptorSupplier("AddNode"))
              .build();
        }
      }
    }
    return getAddNodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.profilence.zeta.NodeRemoved,
      com.google.protobuf.Empty> getRemoveNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveNode",
      requestType = com.profilence.zeta.NodeRemoved.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.profilence.zeta.NodeRemoved,
      com.google.protobuf.Empty> getRemoveNodeMethod() {
    io.grpc.MethodDescriptor<com.profilence.zeta.NodeRemoved, com.google.protobuf.Empty> getRemoveNodeMethod;
    if ((getRemoveNodeMethod = ConnectorServiceGrpc.getRemoveNodeMethod) == null) {
      synchronized (ConnectorServiceGrpc.class) {
        if ((getRemoveNodeMethod = ConnectorServiceGrpc.getRemoveNodeMethod) == null) {
          ConnectorServiceGrpc.getRemoveNodeMethod = getRemoveNodeMethod =
              io.grpc.MethodDescriptor.<com.profilence.zeta.NodeRemoved, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.profilence.zeta.NodeRemoved.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ConnectorServiceMethodDescriptorSupplier("RemoveNode"))
              .build();
        }
      }
    }
    return getRemoveNodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.profilence.zeta.NodeUpdated,
      com.google.protobuf.Empty> getUpdateNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateNode",
      requestType = com.profilence.zeta.NodeUpdated.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.profilence.zeta.NodeUpdated,
      com.google.protobuf.Empty> getUpdateNodeMethod() {
    io.grpc.MethodDescriptor<com.profilence.zeta.NodeUpdated, com.google.protobuf.Empty> getUpdateNodeMethod;
    if ((getUpdateNodeMethod = ConnectorServiceGrpc.getUpdateNodeMethod) == null) {
      synchronized (ConnectorServiceGrpc.class) {
        if ((getUpdateNodeMethod = ConnectorServiceGrpc.getUpdateNodeMethod) == null) {
          ConnectorServiceGrpc.getUpdateNodeMethod = getUpdateNodeMethod =
              io.grpc.MethodDescriptor.<com.profilence.zeta.NodeUpdated, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.profilence.zeta.NodeUpdated.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ConnectorServiceMethodDescriptorSupplier("UpdateNode"))
              .build();
        }
      }
    }
    return getUpdateNodeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ConnectorServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConnectorServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConnectorServiceStub>() {
        @java.lang.Override
        public ConnectorServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConnectorServiceStub(channel, callOptions);
        }
      };
    return ConnectorServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ConnectorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConnectorServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConnectorServiceBlockingStub>() {
        @java.lang.Override
        public ConnectorServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConnectorServiceBlockingStub(channel, callOptions);
        }
      };
    return ConnectorServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ConnectorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConnectorServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConnectorServiceFutureStub>() {
        @java.lang.Override
        public ConnectorServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConnectorServiceFutureStub(channel, callOptions);
        }
      };
    return ConnectorServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ConnectorServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void ping(com.profilence.zeta.PingMessage request,
        io.grpc.stub.StreamObserver<com.profilence.zeta.PongMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    public void startRun(com.profilence.zeta.StartRunRequest request,
        io.grpc.stub.StreamObserver<com.profilence.zeta.StartRunResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStartRunMethod(), responseObserver);
    }

    /**
     */
    public void onUseCaseStart(com.profilence.zeta.UseCaseStartRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getOnUseCaseStartMethod(), responseObserver);
    }

    /**
     */
    public void onLogStep(com.profilence.zeta.LogStepRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getOnLogStepMethod(), responseObserver);
    }

    /**
     */
    public void logTrace(com.profilence.zeta.LogTraceRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getLogTraceMethod(), responseObserver);
    }

    /**
     */
    public void onUseCaseEnd(com.profilence.zeta.UseCaseEndRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getOnUseCaseEndMethod(), responseObserver);
    }

    /**
     */
    public void stopRun(com.profilence.zeta.StopRunRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getStopRunMethod(), responseObserver);
    }

    /**
     */
    public void subscribeToTestRequests(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.profilence.zeta.TestRequest> responseObserver) {
      asyncUnimplementedUnaryCall(getSubscribeToTestRequestsMethod(), responseObserver);
    }

    /**
     */
    public void respondToTestRequest(com.profilence.zeta.TestRequestAck request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getRespondToTestRequestMethod(), responseObserver);
    }

    /**
     */
    public void addNode(com.profilence.zeta.NodeAdded request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getAddNodeMethod(), responseObserver);
    }

    /**
     */
    public void removeNode(com.profilence.zeta.NodeRemoved request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveNodeMethod(), responseObserver);
    }

    /**
     */
    public void updateNode(com.profilence.zeta.NodeUpdated request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateNodeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.profilence.zeta.PingMessage,
                com.profilence.zeta.PongMessage>(
                  this, METHODID_PING)))
          .addMethod(
            getStartRunMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.profilence.zeta.StartRunRequest,
                com.profilence.zeta.StartRunResponse>(
                  this, METHODID_START_RUN)))
          .addMethod(
            getOnUseCaseStartMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.profilence.zeta.UseCaseStartRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_ON_USE_CASE_START)))
          .addMethod(
            getOnLogStepMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.profilence.zeta.LogStepRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_ON_LOG_STEP)))
          .addMethod(
            getLogTraceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.profilence.zeta.LogTraceRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_LOG_TRACE)))
          .addMethod(
            getOnUseCaseEndMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.profilence.zeta.UseCaseEndRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_ON_USE_CASE_END)))
          .addMethod(
            getStopRunMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.profilence.zeta.StopRunRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_STOP_RUN)))
          .addMethod(
            getSubscribeToTestRequestsMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                com.profilence.zeta.TestRequest>(
                  this, METHODID_SUBSCRIBE_TO_TEST_REQUESTS)))
          .addMethod(
            getRespondToTestRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.profilence.zeta.TestRequestAck,
                com.google.protobuf.Empty>(
                  this, METHODID_RESPOND_TO_TEST_REQUEST)))
          .addMethod(
            getAddNodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.profilence.zeta.NodeAdded,
                com.google.protobuf.Empty>(
                  this, METHODID_ADD_NODE)))
          .addMethod(
            getRemoveNodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.profilence.zeta.NodeRemoved,
                com.google.protobuf.Empty>(
                  this, METHODID_REMOVE_NODE)))
          .addMethod(
            getUpdateNodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.profilence.zeta.NodeUpdated,
                com.google.protobuf.Empty>(
                  this, METHODID_UPDATE_NODE)))
          .build();
    }
  }

  /**
   */
  public static final class ConnectorServiceStub extends io.grpc.stub.AbstractAsyncStub<ConnectorServiceStub> {
    private ConnectorServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConnectorServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConnectorServiceStub(channel, callOptions);
    }

    /**
     */
    public void ping(com.profilence.zeta.PingMessage request,
        io.grpc.stub.StreamObserver<com.profilence.zeta.PongMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void startRun(com.profilence.zeta.StartRunRequest request,
        io.grpc.stub.StreamObserver<com.profilence.zeta.StartRunResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStartRunMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void onUseCaseStart(com.profilence.zeta.UseCaseStartRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOnUseCaseStartMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void onLogStep(com.profilence.zeta.LogStepRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOnLogStepMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void logTrace(com.profilence.zeta.LogTraceRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogTraceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void onUseCaseEnd(com.profilence.zeta.UseCaseEndRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOnUseCaseEndMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void stopRun(com.profilence.zeta.StopRunRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStopRunMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void subscribeToTestRequests(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.profilence.zeta.TestRequest> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSubscribeToTestRequestsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void respondToTestRequest(com.profilence.zeta.TestRequestAck request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRespondToTestRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addNode(com.profilence.zeta.NodeAdded request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddNodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeNode(com.profilence.zeta.NodeRemoved request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveNodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateNode(com.profilence.zeta.NodeUpdated request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateNodeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ConnectorServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ConnectorServiceBlockingStub> {
    private ConnectorServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConnectorServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConnectorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.profilence.zeta.PongMessage ping(com.profilence.zeta.PingMessage request) {
      return blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.profilence.zeta.StartRunResponse startRun(com.profilence.zeta.StartRunRequest request) {
      return blockingUnaryCall(
          getChannel(), getStartRunMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty onUseCaseStart(com.profilence.zeta.UseCaseStartRequest request) {
      return blockingUnaryCall(
          getChannel(), getOnUseCaseStartMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty onLogStep(com.profilence.zeta.LogStepRequest request) {
      return blockingUnaryCall(
          getChannel(), getOnLogStepMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty logTrace(com.profilence.zeta.LogTraceRequest request) {
      return blockingUnaryCall(
          getChannel(), getLogTraceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty onUseCaseEnd(com.profilence.zeta.UseCaseEndRequest request) {
      return blockingUnaryCall(
          getChannel(), getOnUseCaseEndMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty stopRun(com.profilence.zeta.StopRunRequest request) {
      return blockingUnaryCall(
          getChannel(), getStopRunMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.profilence.zeta.TestRequest> subscribeToTestRequests(
        com.google.protobuf.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getSubscribeToTestRequestsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty respondToTestRequest(com.profilence.zeta.TestRequestAck request) {
      return blockingUnaryCall(
          getChannel(), getRespondToTestRequestMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty addNode(com.profilence.zeta.NodeAdded request) {
      return blockingUnaryCall(
          getChannel(), getAddNodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty removeNode(com.profilence.zeta.NodeRemoved request) {
      return blockingUnaryCall(
          getChannel(), getRemoveNodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty updateNode(com.profilence.zeta.NodeUpdated request) {
      return blockingUnaryCall(
          getChannel(), getUpdateNodeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ConnectorServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ConnectorServiceFutureStub> {
    private ConnectorServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConnectorServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConnectorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.profilence.zeta.PongMessage> ping(
        com.profilence.zeta.PingMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.profilence.zeta.StartRunResponse> startRun(
        com.profilence.zeta.StartRunRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStartRunMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> onUseCaseStart(
        com.profilence.zeta.UseCaseStartRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOnUseCaseStartMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> onLogStep(
        com.profilence.zeta.LogStepRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOnLogStepMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> logTrace(
        com.profilence.zeta.LogTraceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLogTraceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> onUseCaseEnd(
        com.profilence.zeta.UseCaseEndRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOnUseCaseEndMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> stopRun(
        com.profilence.zeta.StopRunRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStopRunMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> respondToTestRequest(
        com.profilence.zeta.TestRequestAck request) {
      return futureUnaryCall(
          getChannel().newCall(getRespondToTestRequestMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> addNode(
        com.profilence.zeta.NodeAdded request) {
      return futureUnaryCall(
          getChannel().newCall(getAddNodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> removeNode(
        com.profilence.zeta.NodeRemoved request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveNodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> updateNode(
        com.profilence.zeta.NodeUpdated request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateNodeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PING = 0;
  private static final int METHODID_START_RUN = 1;
  private static final int METHODID_ON_USE_CASE_START = 2;
  private static final int METHODID_ON_LOG_STEP = 3;
  private static final int METHODID_LOG_TRACE = 4;
  private static final int METHODID_ON_USE_CASE_END = 5;
  private static final int METHODID_STOP_RUN = 6;
  private static final int METHODID_SUBSCRIBE_TO_TEST_REQUESTS = 7;
  private static final int METHODID_RESPOND_TO_TEST_REQUEST = 8;
  private static final int METHODID_ADD_NODE = 9;
  private static final int METHODID_REMOVE_NODE = 10;
  private static final int METHODID_UPDATE_NODE = 11;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ConnectorServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ConnectorServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PING:
          serviceImpl.ping((com.profilence.zeta.PingMessage) request,
              (io.grpc.stub.StreamObserver<com.profilence.zeta.PongMessage>) responseObserver);
          break;
        case METHODID_START_RUN:
          serviceImpl.startRun((com.profilence.zeta.StartRunRequest) request,
              (io.grpc.stub.StreamObserver<com.profilence.zeta.StartRunResponse>) responseObserver);
          break;
        case METHODID_ON_USE_CASE_START:
          serviceImpl.onUseCaseStart((com.profilence.zeta.UseCaseStartRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_ON_LOG_STEP:
          serviceImpl.onLogStep((com.profilence.zeta.LogStepRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_LOG_TRACE:
          serviceImpl.logTrace((com.profilence.zeta.LogTraceRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_ON_USE_CASE_END:
          serviceImpl.onUseCaseEnd((com.profilence.zeta.UseCaseEndRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_STOP_RUN:
          serviceImpl.stopRun((com.profilence.zeta.StopRunRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_SUBSCRIBE_TO_TEST_REQUESTS:
          serviceImpl.subscribeToTestRequests((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.profilence.zeta.TestRequest>) responseObserver);
          break;
        case METHODID_RESPOND_TO_TEST_REQUEST:
          serviceImpl.respondToTestRequest((com.profilence.zeta.TestRequestAck) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_ADD_NODE:
          serviceImpl.addNode((com.profilence.zeta.NodeAdded) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_REMOVE_NODE:
          serviceImpl.removeNode((com.profilence.zeta.NodeRemoved) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_UPDATE_NODE:
          serviceImpl.updateNode((com.profilence.zeta.NodeUpdated) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ConnectorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ConnectorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.profilence.zeta.DriverProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ConnectorService");
    }
  }

  private static final class ConnectorServiceFileDescriptorSupplier
      extends ConnectorServiceBaseDescriptorSupplier {
    ConnectorServiceFileDescriptorSupplier() {}
  }

  private static final class ConnectorServiceMethodDescriptorSupplier
      extends ConnectorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ConnectorServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ConnectorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ConnectorServiceFileDescriptorSupplier())
              .addMethod(getPingMethod())
              .addMethod(getStartRunMethod())
              .addMethod(getOnUseCaseStartMethod())
              .addMethod(getOnLogStepMethod())
              .addMethod(getLogTraceMethod())
              .addMethod(getOnUseCaseEndMethod())
              .addMethod(getStopRunMethod())
              .addMethod(getSubscribeToTestRequestsMethod())
              .addMethod(getRespondToTestRequestMethod())
              .addMethod(getAddNodeMethod())
              .addMethod(getRemoveNodeMethod())
              .addMethod(getUpdateNodeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
