// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: connector_service.proto

package com.profilence.zeta;

/**
 * Protobuf type {@code profilence.zeta.LogTraceRequest}
 */
public  final class LogTraceRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:profilence.zeta.LogTraceRequest)
    LogTraceRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use LogTraceRequest.newBuilder() to construct.
  private LogTraceRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private LogTraceRequest() {
    runId_ = "";
    data_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new LogTraceRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private LogTraceRequest(
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

            data_ = s;
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
    return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_LogTraceRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_LogTraceRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.profilence.zeta.LogTraceRequest.class, com.profilence.zeta.LogTraceRequest.Builder.class);
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

  public static final int DATA_FIELD_NUMBER = 2;
  private volatile java.lang.Object data_;
  /**
   * <code>string data = 2;</code>
   * @return The data.
   */
  public java.lang.String getData() {
    java.lang.Object ref = data_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      data_ = s;
      return s;
    }
  }
  /**
   * <code>string data = 2;</code>
   * @return The bytes for data.
   */
  public com.google.protobuf.ByteString
      getDataBytes() {
    java.lang.Object ref = data_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      data_ = b;
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
    if (!getDataBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, data_);
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
    if (!getDataBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, data_);
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
    if (!(obj instanceof com.profilence.zeta.LogTraceRequest)) {
      return super.equals(obj);
    }
    com.profilence.zeta.LogTraceRequest other = (com.profilence.zeta.LogTraceRequest) obj;

    if (!getRunId()
        .equals(other.getRunId())) return false;
    if (!getData()
        .equals(other.getData())) return false;
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
    hash = (37 * hash) + DATA_FIELD_NUMBER;
    hash = (53 * hash) + getData().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.profilence.zeta.LogTraceRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.LogTraceRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.LogTraceRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.LogTraceRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.LogTraceRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.LogTraceRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.LogTraceRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.LogTraceRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.profilence.zeta.LogTraceRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.LogTraceRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.profilence.zeta.LogTraceRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.LogTraceRequest parseFrom(
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
  public static Builder newBuilder(com.profilence.zeta.LogTraceRequest prototype) {
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
   * Protobuf type {@code profilence.zeta.LogTraceRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:profilence.zeta.LogTraceRequest)
      com.profilence.zeta.LogTraceRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_LogTraceRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_LogTraceRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.profilence.zeta.LogTraceRequest.class, com.profilence.zeta.LogTraceRequest.Builder.class);
    }

    // Construct using com.profilence.zeta.LogTraceRequest.newBuilder()
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

      data_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_LogTraceRequest_descriptor;
    }

    @java.lang.Override
    public com.profilence.zeta.LogTraceRequest getDefaultInstanceForType() {
      return com.profilence.zeta.LogTraceRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.profilence.zeta.LogTraceRequest build() {
      com.profilence.zeta.LogTraceRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.profilence.zeta.LogTraceRequest buildPartial() {
      com.profilence.zeta.LogTraceRequest result = new com.profilence.zeta.LogTraceRequest(this);
      result.runId_ = runId_;
      result.data_ = data_;
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
      if (other instanceof com.profilence.zeta.LogTraceRequest) {
        return mergeFrom((com.profilence.zeta.LogTraceRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.profilence.zeta.LogTraceRequest other) {
      if (other == com.profilence.zeta.LogTraceRequest.getDefaultInstance()) return this;
      if (!other.getRunId().isEmpty()) {
        runId_ = other.runId_;
        onChanged();
      }
      if (!other.getData().isEmpty()) {
        data_ = other.data_;
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
      com.profilence.zeta.LogTraceRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.profilence.zeta.LogTraceRequest) e.getUnfinishedMessage();
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

    private java.lang.Object data_ = "";
    /**
     * <code>string data = 2;</code>
     * @return The data.
     */
    public java.lang.String getData() {
      java.lang.Object ref = data_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        data_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string data = 2;</code>
     * @return The bytes for data.
     */
    public com.google.protobuf.ByteString
        getDataBytes() {
      java.lang.Object ref = data_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        data_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string data = 2;</code>
     * @param value The data to set.
     * @return This builder for chaining.
     */
    public Builder setData(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      data_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string data = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearData() {
      
      data_ = getDefaultInstance().getData();
      onChanged();
      return this;
    }
    /**
     * <code>string data = 2;</code>
     * @param value The bytes for data to set.
     * @return This builder for chaining.
     */
    public Builder setDataBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      data_ = value;
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


    // @@protoc_insertion_point(builder_scope:profilence.zeta.LogTraceRequest)
  }

  // @@protoc_insertion_point(class_scope:profilence.zeta.LogTraceRequest)
  private static final com.profilence.zeta.LogTraceRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.profilence.zeta.LogTraceRequest();
  }

  public static com.profilence.zeta.LogTraceRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<LogTraceRequest>
      PARSER = new com.google.protobuf.AbstractParser<LogTraceRequest>() {
    @java.lang.Override
    public LogTraceRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new LogTraceRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<LogTraceRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<LogTraceRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.profilence.zeta.LogTraceRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

