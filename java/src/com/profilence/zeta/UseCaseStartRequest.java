// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: connector_service.proto

package com.profilence.zeta;

/**
 * Protobuf type {@code profilence.zeta.UseCaseStartRequest}
 */
public  final class UseCaseStartRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:profilence.zeta.UseCaseStartRequest)
    UseCaseStartRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UseCaseStartRequest.newBuilder() to construct.
  private UseCaseStartRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UseCaseStartRequest() {
    runId_ = "";
    useCaseName_ = "";
    useCaseId_ = "";
    targetProcess_ = "";
    requirementId_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new UseCaseStartRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UseCaseStartRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            runId_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            useCaseName_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            useCaseId_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            targetProcess_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            requirementId_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_UseCaseStartRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_UseCaseStartRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.profilence.zeta.UseCaseStartRequest.class, com.profilence.zeta.UseCaseStartRequest.Builder.class);
  }

  public static final int RUN_ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object runId_;
  /**
   * <code>string run_id = 1;</code>
   * @return The runId.
   */
  public java.lang.String getRunId() {
    java.lang.Object ref = runId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      runId_ = s;
      return s;
    }
  }
  /**
   * <code>string run_id = 1;</code>
   * @return The bytes for runId.
   */
  public com.google.protobuf.ByteString
      getRunIdBytes() {
    java.lang.Object ref = runId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      runId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int USE_CASE_NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object useCaseName_;
  /**
   * <code>string use_case_name = 2;</code>
   * @return The useCaseName.
   */
  public java.lang.String getUseCaseName() {
    java.lang.Object ref = useCaseName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      useCaseName_ = s;
      return s;
    }
  }
  /**
   * <code>string use_case_name = 2;</code>
   * @return The bytes for useCaseName.
   */
  public com.google.protobuf.ByteString
      getUseCaseNameBytes() {
    java.lang.Object ref = useCaseName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      useCaseName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int USE_CASE_ID_FIELD_NUMBER = 3;
  private volatile java.lang.Object useCaseId_;
  /**
   * <code>string use_case_id = 3;</code>
   * @return The useCaseId.
   */
  public java.lang.String getUseCaseId() {
    java.lang.Object ref = useCaseId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      useCaseId_ = s;
      return s;
    }
  }
  /**
   * <code>string use_case_id = 3;</code>
   * @return The bytes for useCaseId.
   */
  public com.google.protobuf.ByteString
      getUseCaseIdBytes() {
    java.lang.Object ref = useCaseId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      useCaseId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TARGET_PROCESS_FIELD_NUMBER = 4;
  private volatile java.lang.Object targetProcess_;
  /**
   * <code>string target_process = 4;</code>
   * @return The targetProcess.
   */
  public java.lang.String getTargetProcess() {
    java.lang.Object ref = targetProcess_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      targetProcess_ = s;
      return s;
    }
  }
  /**
   * <code>string target_process = 4;</code>
   * @return The bytes for targetProcess.
   */
  public com.google.protobuf.ByteString
      getTargetProcessBytes() {
    java.lang.Object ref = targetProcess_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      targetProcess_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int REQUIREMENT_ID_FIELD_NUMBER = 5;
  private volatile java.lang.Object requirementId_;
  /**
   * <code>string requirement_id = 5;</code>
   * @return The requirementId.
   */
  public java.lang.String getRequirementId() {
    java.lang.Object ref = requirementId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      requirementId_ = s;
      return s;
    }
  }
  /**
   * <code>string requirement_id = 5;</code>
   * @return The bytes for requirementId.
   */
  public com.google.protobuf.ByteString
      getRequirementIdBytes() {
    java.lang.Object ref = requirementId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      requirementId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getRunIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, runId_);
    }
    if (!getUseCaseNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, useCaseName_);
    }
    if (!getUseCaseIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, useCaseId_);
    }
    if (!getTargetProcessBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, targetProcess_);
    }
    if (!getRequirementIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, requirementId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getRunIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, runId_);
    }
    if (!getUseCaseNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, useCaseName_);
    }
    if (!getUseCaseIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, useCaseId_);
    }
    if (!getTargetProcessBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, targetProcess_);
    }
    if (!getRequirementIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, requirementId_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.profilence.zeta.UseCaseStartRequest)) {
      return super.equals(obj);
    }
    com.profilence.zeta.UseCaseStartRequest other = (com.profilence.zeta.UseCaseStartRequest) obj;

    if (!getRunId()
        .equals(other.getRunId())) return false;
    if (!getUseCaseName()
        .equals(other.getUseCaseName())) return false;
    if (!getUseCaseId()
        .equals(other.getUseCaseId())) return false;
    if (!getTargetProcess()
        .equals(other.getTargetProcess())) return false;
    if (!getRequirementId()
        .equals(other.getRequirementId())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RUN_ID_FIELD_NUMBER;
    hash = (53 * hash) + getRunId().hashCode();
    hash = (37 * hash) + USE_CASE_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getUseCaseName().hashCode();
    hash = (37 * hash) + USE_CASE_ID_FIELD_NUMBER;
    hash = (53 * hash) + getUseCaseId().hashCode();
    hash = (37 * hash) + TARGET_PROCESS_FIELD_NUMBER;
    hash = (53 * hash) + getTargetProcess().hashCode();
    hash = (37 * hash) + REQUIREMENT_ID_FIELD_NUMBER;
    hash = (53 * hash) + getRequirementId().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.profilence.zeta.UseCaseStartRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.UseCaseStartRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.UseCaseStartRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.UseCaseStartRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.UseCaseStartRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.UseCaseStartRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.UseCaseStartRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.UseCaseStartRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.profilence.zeta.UseCaseStartRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.UseCaseStartRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.profilence.zeta.UseCaseStartRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.UseCaseStartRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.profilence.zeta.UseCaseStartRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code profilence.zeta.UseCaseStartRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:profilence.zeta.UseCaseStartRequest)
      com.profilence.zeta.UseCaseStartRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_UseCaseStartRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_UseCaseStartRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.profilence.zeta.UseCaseStartRequest.class, com.profilence.zeta.UseCaseStartRequest.Builder.class);
    }

    // Construct using com.profilence.zeta.UseCaseStartRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      runId_ = "";

      useCaseName_ = "";

      useCaseId_ = "";

      targetProcess_ = "";

      requirementId_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_UseCaseStartRequest_descriptor;
    }

    @java.lang.Override
    public com.profilence.zeta.UseCaseStartRequest getDefaultInstanceForType() {
      return com.profilence.zeta.UseCaseStartRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.profilence.zeta.UseCaseStartRequest build() {
      com.profilence.zeta.UseCaseStartRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.profilence.zeta.UseCaseStartRequest buildPartial() {
      com.profilence.zeta.UseCaseStartRequest result = new com.profilence.zeta.UseCaseStartRequest(this);
      result.runId_ = runId_;
      result.useCaseName_ = useCaseName_;
      result.useCaseId_ = useCaseId_;
      result.targetProcess_ = targetProcess_;
      result.requirementId_ = requirementId_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.profilence.zeta.UseCaseStartRequest) {
        return mergeFrom((com.profilence.zeta.UseCaseStartRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.profilence.zeta.UseCaseStartRequest other) {
      if (other == com.profilence.zeta.UseCaseStartRequest.getDefaultInstance()) return this;
      if (!other.getRunId().isEmpty()) {
        runId_ = other.runId_;
        onChanged();
      }
      if (!other.getUseCaseName().isEmpty()) {
        useCaseName_ = other.useCaseName_;
        onChanged();
      }
      if (!other.getUseCaseId().isEmpty()) {
        useCaseId_ = other.useCaseId_;
        onChanged();
      }
      if (!other.getTargetProcess().isEmpty()) {
        targetProcess_ = other.targetProcess_;
        onChanged();
      }
      if (!other.getRequirementId().isEmpty()) {
        requirementId_ = other.requirementId_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.profilence.zeta.UseCaseStartRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.profilence.zeta.UseCaseStartRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object runId_ = "";
    /**
     * <code>string run_id = 1;</code>
     * @return The runId.
     */
    public java.lang.String getRunId() {
      java.lang.Object ref = runId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        runId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string run_id = 1;</code>
     * @return The bytes for runId.
     */
    public com.google.protobuf.ByteString
        getRunIdBytes() {
      java.lang.Object ref = runId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        runId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string run_id = 1;</code>
     * @param value The runId to set.
     * @return This builder for chaining.
     */
    public Builder setRunId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      runId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string run_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearRunId() {
      
      runId_ = getDefaultInstance().getRunId();
      onChanged();
      return this;
    }
    /**
     * <code>string run_id = 1;</code>
     * @param value The bytes for runId to set.
     * @return This builder for chaining.
     */
    public Builder setRunIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      runId_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object useCaseName_ = "";
    /**
     * <code>string use_case_name = 2;</code>
     * @return The useCaseName.
     */
    public java.lang.String getUseCaseName() {
      java.lang.Object ref = useCaseName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        useCaseName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string use_case_name = 2;</code>
     * @return The bytes for useCaseName.
     */
    public com.google.protobuf.ByteString
        getUseCaseNameBytes() {
      java.lang.Object ref = useCaseName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        useCaseName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string use_case_name = 2;</code>
     * @param value The useCaseName to set.
     * @return This builder for chaining.
     */
    public Builder setUseCaseName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      useCaseName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string use_case_name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearUseCaseName() {
      
      useCaseName_ = getDefaultInstance().getUseCaseName();
      onChanged();
      return this;
    }
    /**
     * <code>string use_case_name = 2;</code>
     * @param value The bytes for useCaseName to set.
     * @return This builder for chaining.
     */
    public Builder setUseCaseNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      useCaseName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object useCaseId_ = "";
    /**
     * <code>string use_case_id = 3;</code>
     * @return The useCaseId.
     */
    public java.lang.String getUseCaseId() {
      java.lang.Object ref = useCaseId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        useCaseId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string use_case_id = 3;</code>
     * @return The bytes for useCaseId.
     */
    public com.google.protobuf.ByteString
        getUseCaseIdBytes() {
      java.lang.Object ref = useCaseId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        useCaseId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string use_case_id = 3;</code>
     * @param value The useCaseId to set.
     * @return This builder for chaining.
     */
    public Builder setUseCaseId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      useCaseId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string use_case_id = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearUseCaseId() {
      
      useCaseId_ = getDefaultInstance().getUseCaseId();
      onChanged();
      return this;
    }
    /**
     * <code>string use_case_id = 3;</code>
     * @param value The bytes for useCaseId to set.
     * @return This builder for chaining.
     */
    public Builder setUseCaseIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      useCaseId_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object targetProcess_ = "";
    /**
     * <code>string target_process = 4;</code>
     * @return The targetProcess.
     */
    public java.lang.String getTargetProcess() {
      java.lang.Object ref = targetProcess_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        targetProcess_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string target_process = 4;</code>
     * @return The bytes for targetProcess.
     */
    public com.google.protobuf.ByteString
        getTargetProcessBytes() {
      java.lang.Object ref = targetProcess_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        targetProcess_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string target_process = 4;</code>
     * @param value The targetProcess to set.
     * @return This builder for chaining.
     */
    public Builder setTargetProcess(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      targetProcess_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string target_process = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearTargetProcess() {
      
      targetProcess_ = getDefaultInstance().getTargetProcess();
      onChanged();
      return this;
    }
    /**
     * <code>string target_process = 4;</code>
     * @param value The bytes for targetProcess to set.
     * @return This builder for chaining.
     */
    public Builder setTargetProcessBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      targetProcess_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object requirementId_ = "";
    /**
     * <code>string requirement_id = 5;</code>
     * @return The requirementId.
     */
    public java.lang.String getRequirementId() {
      java.lang.Object ref = requirementId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        requirementId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string requirement_id = 5;</code>
     * @return The bytes for requirementId.
     */
    public com.google.protobuf.ByteString
        getRequirementIdBytes() {
      java.lang.Object ref = requirementId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        requirementId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string requirement_id = 5;</code>
     * @param value The requirementId to set.
     * @return This builder for chaining.
     */
    public Builder setRequirementId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      requirementId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string requirement_id = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearRequirementId() {
      
      requirementId_ = getDefaultInstance().getRequirementId();
      onChanged();
      return this;
    }
    /**
     * <code>string requirement_id = 5;</code>
     * @param value The bytes for requirementId to set.
     * @return This builder for chaining.
     */
    public Builder setRequirementIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      requirementId_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:profilence.zeta.UseCaseStartRequest)
  }

  // @@protoc_insertion_point(class_scope:profilence.zeta.UseCaseStartRequest)
  private static final com.profilence.zeta.UseCaseStartRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.profilence.zeta.UseCaseStartRequest();
  }

  public static com.profilence.zeta.UseCaseStartRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UseCaseStartRequest>
      PARSER = new com.google.protobuf.AbstractParser<UseCaseStartRequest>() {
    @java.lang.Override
    public UseCaseStartRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UseCaseStartRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UseCaseStartRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UseCaseStartRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.profilence.zeta.UseCaseStartRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

