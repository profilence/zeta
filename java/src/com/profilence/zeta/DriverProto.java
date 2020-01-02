// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: connector_service.proto

package com.profilence.zeta;

public final class DriverProto {
  private DriverProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_StartRunRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_StartRunRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_StartRunRequest_TagsEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_StartRunRequest_TagsEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_PingMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_PingMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_PongMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_PongMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_StartRunResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_StartRunResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_UseCaseStartRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_UseCaseStartRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_LogStepRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_LogStepRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_LogTraceRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_LogTraceRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_UseCaseEndRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_UseCaseEndRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_StopRunRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_StopRunRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_TestRequestAck_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_TestRequestAck_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_TestRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_TestRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_TestRequest_TagsEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_TestRequest_TagsEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_NodeAdded_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_NodeAdded_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_NodeRemoved_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_NodeRemoved_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_profilence_zeta_NodeUpdated_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_profilence_zeta_NodeUpdated_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027connector_service.proto\022\017profilence.ze" +
      "ta\032\016wrappers.proto\032\013empty.proto\"\232\002\n\017Star" +
      "tRunRequest\022\020\n\010run_name\030\001 \001(\t\022\020\n\010set_nam" +
      "e\030\002 \001(\t\022\017\n\007project\030\003 \001(\t\022\017\n\007version\030\004 \001(" +
      "\t\022\035\n\025primary_device_serial\030\005 \001(\t\022\037\n\027seco" +
      "ndary_device_serial\030\006 \001(\t\022\032\n\022profiling_s" +
      "ettings\030\007 \001(\t\0228\n\004tags\030\010 \003(\0132*.profilence" +
      ".zeta.StartRunRequest.TagsEntry\032+\n\tTagsE" +
      "ntry\022\013\n\003key\030\001 \001(\t\022\r\n\005value\030\002 \001(\t:\0028\001\"\r\n\013" +
      "PingMessage\"\035\n\013PongMessage\022\016\n\006result\030\001 \001" +
      "(\005\"\"\n\020StartRunResponse\022\016\n\006run_id\030\001 \001(\t\"\201" +
      "\001\n\023UseCaseStartRequest\022\016\n\006run_id\030\001 \001(\t\022\025" +
      "\n\ruse_case_name\030\002 \001(\t\022\023\n\013use_case_id\030\003 \001" +
      "(\t\022\026\n\016target_process\030\004 \001(\t\022\026\n\016requiremen" +
      "t_id\030\005 \001(\t\"\\\n\016LogStepRequest\022\016\n\006run_id\030\001" +
      " \001(\t\022\021\n\tstep_name\030\002 \001(\t\022\016\n\006result\030\003 \001(\010\022" +
      "\027\n\017take_screenshot\030\004 \001(\010\"/\n\017LogTraceRequ" +
      "est\022\016\n\006run_id\030\001 \001(\t\022\014\n\004data\030\002 \001(\t\"v\n\021Use" +
      "CaseEndRequest\022\016\n\006run_id\030\001 \001(\t\022\016\n\006result" +
      "\030\002 \001(\010\022\025\n\ractiveRunTime\030\003 \001(\003\022\022\n\nfail_ca" +
      "use\030\004 \001(\t\022\026\n\016reset_intended\030\005 \001(\010\"9\n\016Sto" +
      "pRunRequest\022\016\n\006run_id\030\001 \001(\t\022\027\n\017discard_r" +
      "esults\030\002 \001(\010\"a\n\016TestRequestAck\022\016\n\006status" +
      "\030\001 \001(\t\022\016\n\006run_id\030\002 \001(\t\022\013\n\003log\030\003 \003(\t\022\016\n\006r" +
      "esult\030\005 \001(\010\022\022\n\nfail_cause\030\006 \001(\t\"\206\002\n\013Test" +
      "Request\022\016\n\006run_id\030\030 \001(\t\022\020\n\010run_name\030\002 \001(" +
      "\t\022\017\n\007node_id\030\005 \001(\t\022\022\n\nparameters\030\006 \001(\t\022\017" +
      "\n\007project\030\n \001(\t\022\017\n\007version\030\020 \001(\t\022\032\n\022test" +
      "_set_file_path\030\014 \001(\t\022\017\n\007payload\030\016 \001(\014\0224\n" +
      "\004tags\030\024 \003(\0132&.profilence.zeta.TestReques" +
      "t.TagsEntry\032+\n\tTagsEntry\022\013\n\003key\030\001 \001(\t\022\r\n" +
      "\005value\030\002 \001(\t:\0028\001\"K\n\tNodeAdded\022\017\n\007node_id" +
      "\030\001 \001(\t\022\014\n\004pool\030\004 \001(\t\022\014\n\004type\030\005 \001(\t\022\021\n\tva" +
      "riables\030\006 \001(\t\"\036\n\013NodeRemoved\022\017\n\007node_id\030" +
      "\001 \001(\t\"\312\002\n\013NodeUpdated\022\017\n\007node_id\030\001 \001(\t\0226" +
      "\n\020current_use_case\030\003 \001(\0132\034.google.protob" +
      "uf.StringValue\022.\n\trun_state\030\004 \001(\0132\033.goog" +
      "le.protobuf.Int32Value\0224\n\016current_run_id" +
      "\030\005 \001(\0132\034.google.protobuf.StringValue\022/\n\n" +
      "node_state\030\006 \001(\0132\033.google.protobuf.Int32" +
      "Value\022*\n\004pool\030\007 \001(\0132\034.google.protobuf.St" +
      "ringValue\022/\n\tvariables\030\010 \001(\0132\034.google.pr" +
      "otobuf.StringValue2\226\007\n\020ConnectorService\022" +
      "D\n\004Ping\022\034.profilence.zeta.PingMessage\032\034." +
      "profilence.zeta.PongMessage\"\000\022Q\n\010StartRu" +
      "n\022 .profilence.zeta.StartRunRequest\032!.pr" +
      "ofilence.zeta.StartRunResponse\"\000\022P\n\016OnUs" +
      "eCaseStart\022$.profilence.zeta.UseCaseStar" +
      "tRequest\032\026.google.protobuf.Empty\"\000\022F\n\tOn" +
      "LogStep\022\037.profilence.zeta.LogStepRequest" +
      "\032\026.google.protobuf.Empty\"\000\022F\n\010LogTrace\022 " +
      ".profilence.zeta.LogTraceRequest\032\026.googl" +
      "e.protobuf.Empty\"\000\022L\n\014OnUseCaseEnd\022\".pro" +
      "filence.zeta.UseCaseEndRequest\032\026.google." +
      "protobuf.Empty\"\000\022D\n\007StopRun\022\037.profilence" +
      ".zeta.StopRunRequest\032\026.google.protobuf.E" +
      "mpty\"\000\022S\n\027SubscribeToTestRequests\022\026.goog" +
      "le.protobuf.Empty\032\034.profilence.zeta.Test" +
      "Request\"\0000\001\022Q\n\024RespondToTestRequest\022\037.pr" +
      "ofilence.zeta.TestRequestAck\032\026.google.pr" +
      "otobuf.Empty\"\000\022?\n\007AddNode\022\032.profilence.z" +
      "eta.NodeAdded\032\026.google.protobuf.Empty\"\000\022" +
      "D\n\nRemoveNode\022\034.profilence.zeta.NodeRemo" +
      "ved\032\026.google.protobuf.Empty\"\000\022D\n\nUpdateN" +
      "ode\022\034.profilence.zeta.NodeUpdated\032\026.goog" +
      "le.protobuf.Empty\"\000B$\n\023com.profilence.ze" +
      "taB\013DriverProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.WrappersProto.getDescriptor(),
          com.google.protobuf.EmptyProto.getDescriptor(),
        });
    internal_static_profilence_zeta_StartRunRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_profilence_zeta_StartRunRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_StartRunRequest_descriptor,
        new java.lang.String[] { "RunName", "SetName", "Project", "Version", "PrimaryDeviceSerial", "SecondaryDeviceSerial", "ProfilingSettings", "Tags", });
    internal_static_profilence_zeta_StartRunRequest_TagsEntry_descriptor =
      internal_static_profilence_zeta_StartRunRequest_descriptor.getNestedTypes().get(0);
    internal_static_profilence_zeta_StartRunRequest_TagsEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_StartRunRequest_TagsEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    internal_static_profilence_zeta_PingMessage_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_profilence_zeta_PingMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_PingMessage_descriptor,
        new java.lang.String[] { });
    internal_static_profilence_zeta_PongMessage_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_profilence_zeta_PongMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_PongMessage_descriptor,
        new java.lang.String[] { "Result", });
    internal_static_profilence_zeta_StartRunResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_profilence_zeta_StartRunResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_StartRunResponse_descriptor,
        new java.lang.String[] { "RunId", });
    internal_static_profilence_zeta_UseCaseStartRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_profilence_zeta_UseCaseStartRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_UseCaseStartRequest_descriptor,
        new java.lang.String[] { "RunId", "UseCaseName", "UseCaseId", "TargetProcess", "RequirementId", });
    internal_static_profilence_zeta_LogStepRequest_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_profilence_zeta_LogStepRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_LogStepRequest_descriptor,
        new java.lang.String[] { "RunId", "StepName", "Result", "TakeScreenshot", });
    internal_static_profilence_zeta_LogTraceRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_profilence_zeta_LogTraceRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_LogTraceRequest_descriptor,
        new java.lang.String[] { "RunId", "Data", });
    internal_static_profilence_zeta_UseCaseEndRequest_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_profilence_zeta_UseCaseEndRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_UseCaseEndRequest_descriptor,
        new java.lang.String[] { "RunId", "Result", "ActiveRunTime", "FailCause", "ResetIntended", });
    internal_static_profilence_zeta_StopRunRequest_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_profilence_zeta_StopRunRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_StopRunRequest_descriptor,
        new java.lang.String[] { "RunId", "DiscardResults", });
    internal_static_profilence_zeta_TestRequestAck_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_profilence_zeta_TestRequestAck_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_TestRequestAck_descriptor,
        new java.lang.String[] { "Status", "RunId", "Log", "Result", "FailCause", });
    internal_static_profilence_zeta_TestRequest_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_profilence_zeta_TestRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_TestRequest_descriptor,
        new java.lang.String[] { "RunId", "RunName", "NodeId", "Parameters", "Project", "Version", "TestSetFilePath", "Payload", "Tags", });
    internal_static_profilence_zeta_TestRequest_TagsEntry_descriptor =
      internal_static_profilence_zeta_TestRequest_descriptor.getNestedTypes().get(0);
    internal_static_profilence_zeta_TestRequest_TagsEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_TestRequest_TagsEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    internal_static_profilence_zeta_NodeAdded_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_profilence_zeta_NodeAdded_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_NodeAdded_descriptor,
        new java.lang.String[] { "NodeId", "Pool", "Type", "Variables", });
    internal_static_profilence_zeta_NodeRemoved_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_profilence_zeta_NodeRemoved_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_NodeRemoved_descriptor,
        new java.lang.String[] { "NodeId", });
    internal_static_profilence_zeta_NodeUpdated_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_profilence_zeta_NodeUpdated_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_profilence_zeta_NodeUpdated_descriptor,
        new java.lang.String[] { "NodeId", "CurrentUseCase", "RunState", "CurrentRunId", "NodeState", "Pool", "Variables", });
    com.google.protobuf.WrappersProto.getDescriptor();
    com.google.protobuf.EmptyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
