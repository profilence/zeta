// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: connector_service.proto

package com.profilence.zeta;

/**
 * Protobuf type {@code profilence.zeta.UseCaseEndRequest}
 */
public  final class UseCaseEndRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:profilence.zeta.UseCaseEndRequest)
    UseCaseEndRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UseCaseEndRequest.newBuilder() to construct.
  private UseCaseEndRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UseCaseEndRequest() {
    runId_ = "";
    failCause_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new UseCaseEndRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UseCaseEndRequest(
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
          case 16: {

            result_ = input.readBool();
            break;
          }
          case 24: {

            activeRunTime_ = input.readInt64();
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            failCause_ = s;
            break;
          }
          case 40: {

            resetIntended_ = input.readBool();
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
    return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_UseCaseEndRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_UseCaseEndRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.profilence.zeta.UseCaseEndRequest.class, com.profilence.zeta.UseCaseEndRequest.Builder.class);
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

  public static final int RESULT_FIELD_NUMBER = 2;
  private boolean result_;
  /**
   * <code>bool result = 2;</code>
   * @return The result.
   */
  public boolean getResult() {
    return result_;
  }

  public static final int ACTIVERUNTIME_FIELD_NUMBER = 3;
  private long activeRunTime_;
  /**
   * <code>int64 activeRunTime = 3;</code>
   * @return The activeRunTime.
   */
  public long getActiveRunTime() {
    return activeRunTime_;
  }

  public static final int FAIL_CAUSE_FIELD_NUMBER = 4;
  private volatile java.lang.Object failCause_;
  /**
   * <code>string fail_cause = 4;</code>
   * @return The failCause.
   */
  public java.lang.String getFailCause() {
    java.lang.Object ref = failCause_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      failCause_ = s;
      return s;
    }
  }
  /**
   * <code>string fail_cause = 4;</code>
   * @return The bytes for failCause.
   */
  public com.google.protobuf.ByteString
      getFailCauseBytes() {
    java.lang.Object ref = failCause_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      failCause_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int RESET_INTENDED_FIELD_NUMBER = 5;
  private boolean resetIntended_;
  /**
   * <code>bool reset_intended = 5;</code>
   * @return The resetIntended.
   */
  public boolean getResetIntended() {
    return resetIntended_;
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
    if (result_ != false) {
      output.writeBool(2, result_);
    }
    if (activeRunTime_ != 0L) {
      output.writeInt64(3, activeRunTime_);
    }
    if (!getFailCauseBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, failCause_);
    }
    if (resetIntended_ != false) {
      output.writeBool(5, resetIntended_);
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
    if (result_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(2, result_);
    }
    if (activeRunTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, activeRunTime_);
    }
    if (!getFailCauseBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, failCause_);
    }
    if (resetIntended_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(5, resetIntended_);
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
    if (!(obj instanceof com.profilence.zeta.UseCaseEndRequest)) {
      return super.equals(obj);
    }
    com.profilence.zeta.UseCaseEndRequest other = (com.profilence.zeta.UseCaseEndRequest) obj;

    if (!getRunId()
        .equals(other.getRunId())) return false;
    if (getResult()
        != other.getResult()) return false;
    if (getActiveRunTime()
        != other.getActiveRunTime()) return false;
    if (!getFailCause()
        .equals(other.getFailCause())) return false;
    if (getResetIntended()
        != other.getResetIntended()) return false;
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
    hash = (37 * hash) + RESULT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getResult());
    hash = (37 * hash) + ACTIVERUNTIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getActiveRunTime());
    hash = (37 * hash) + FAIL_CAUSE_FIELD_NUMBER;
    hash = (53 * hash) + getFailCause().hashCode();
    hash = (37 * hash) + RESET_INTENDED_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getResetIntended());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.profilence.zeta.UseCaseEndRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.UseCaseEndRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.UseCaseEndRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.UseCaseEndRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.UseCaseEndRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.UseCaseEndRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.UseCaseEndRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.UseCaseEndRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.profilence.zeta.UseCaseEndRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.UseCaseEndRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.profilence.zeta.UseCaseEndRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.UseCaseEndRequest parseFrom(
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
  public static Builder newBuilder(com.profilence.zeta.UseCaseEndRequest prototype) {
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
   * Protobuf type {@code profilence.zeta.UseCaseEndRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:profilence.zeta.UseCaseEndRequest)
      com.profilence.zeta.UseCaseEndRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_UseCaseEndRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_UseCaseEndRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.profilence.zeta.UseCaseEndRequest.class, com.profilence.zeta.UseCaseEndRequest.Builder.class);
    }

    // Construct using com.profilence.zeta.UseCaseEndRequest.newBuilder()
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

      result_ = false;

      activeRunTime_ = 0L;

      failCause_ = "";

      resetIntended_ = false;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_UseCaseEndRequest_descriptor;
    }

    @java.lang.Override
    public com.profilence.zeta.UseCaseEndRequest getDefaultInstanceForType() {
      return com.profilence.zeta.UseCaseEndRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.profilence.zeta.UseCaseEndRequest build() {
      com.profilence.zeta.UseCaseEndRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.profilence.zeta.UseCaseEndRequest buildPartial() {
      com.profilence.zeta.UseCaseEndRequest result = new com.profilence.zeta.UseCaseEndRequest(this);
      result.runId_ = runId_;
      result.result_ = result_;
      result.activeRunTime_ = activeRunTime_;
      result.failCause_ = failCause_;
      result.resetIntended_ = resetIntended_;
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
      if (other instanceof com.profilence.zeta.UseCaseEndRequest) {
        return mergeFrom((com.profilence.zeta.UseCaseEndRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.profilence.zeta.UseCaseEndRequest other) {
      if (other == com.profilence.zeta.UseCaseEndRequest.getDefaultInstance()) return this;
      if (!other.getRunId().isEmpty()) {
        runId_ = other.runId_;
        onChanged();
      }
      if (other.getResult() != false) {
        setResult(other.getResult());
      }
      if (other.getActiveRunTime() != 0L) {
        setActiveRunTime(other.getActiveRunTime());
      }
      if (!other.getFailCause().isEmpty()) {
        failCause_ = other.failCause_;
        onChanged();
      }
      if (other.getResetIntended() != false) {
        setResetIntended(other.getResetIntended());
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
      com.profilence.zeta.UseCaseEndRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.profilence.zeta.UseCaseEndRequest) e.getUnfinishedMessage();
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

    private boolean result_ ;
    /**
     * <code>bool result = 2;</code>
     * @return The result.
     */
    public boolean getResult() {
      return result_;
    }
    /**
     * <code>bool result = 2;</code>
     * @param value The result to set.
     * @return This builder for chaining.
     */
    public Builder setResult(boolean value) {
      
      result_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool result = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearResult() {
      
      result_ = false;
      onChanged();
      return this;
    }

    private long activeRunTime_ ;
    /**
     * <code>int64 activeRunTime = 3;</code>
     * @return The activeRunTime.
     */
    public long getActiveRunTime() {
      return activeRunTime_;
    }
    /**
     * <code>int64 activeRunTime = 3;</code>
     * @param value The activeRunTime to set.
     * @return This builder for chaining.
     */
    public Builder setActiveRunTime(long value) {
      
      activeRunTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 activeRunTime = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearActiveRunTime() {
      
      activeRunTime_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object failCause_ = "";
    /**
     * <code>string fail_cause = 4;</code>
     * @return The failCause.
     */
    public java.lang.String getFailCause() {
      java.lang.Object ref = failCause_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        failCause_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string fail_cause = 4;</code>
     * @return The bytes for failCause.
     */
    public com.google.protobuf.ByteString
        getFailCauseBytes() {
      java.lang.Object ref = failCause_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        failCause_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string fail_cause = 4;</code>
     * @param value The failCause to set.
     * @return This builder for chaining.
     */
    public Builder setFailCause(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      failCause_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string fail_cause = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearFailCause() {
      
      failCause_ = getDefaultInstance().getFailCause();
      onChanged();
      return this;
    }
    /**
     * <code>string fail_cause = 4;</code>
     * @param value The bytes for failCause to set.
     * @return This builder for chaining.
     */
    public Builder setFailCauseBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      failCause_ = value;
      onChanged();
      return this;
    }

    private boolean resetIntended_ ;
    /**
     * <code>bool reset_intended = 5;</code>
     * @return The resetIntended.
     */
    public boolean getResetIntended() {
      return resetIntended_;
    }
    /**
     * <code>bool reset_intended = 5;</code>
     * @param value The resetIntended to set.
     * @return This builder for chaining.
     */
    public Builder setResetIntended(boolean value) {
      
      resetIntended_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool reset_intended = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearResetIntended() {
      
      resetIntended_ = false;
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


    // @@protoc_insertion_point(builder_scope:profilence.zeta.UseCaseEndRequest)
  }

  // @@protoc_insertion_point(class_scope:profilence.zeta.UseCaseEndRequest)
  private static final com.profilence.zeta.UseCaseEndRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.profilence.zeta.UseCaseEndRequest();
  }

  public static com.profilence.zeta.UseCaseEndRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UseCaseEndRequest>
      PARSER = new com.google.protobuf.AbstractParser<UseCaseEndRequest>() {
    @java.lang.Override
    public UseCaseEndRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UseCaseEndRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UseCaseEndRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UseCaseEndRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.profilence.zeta.UseCaseEndRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

