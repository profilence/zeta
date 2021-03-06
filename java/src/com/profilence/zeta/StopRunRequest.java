// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: connector_service.proto

package com.profilence.zeta;

/**
 * Protobuf type {@code profilence.zeta.StopRunRequest}
 */
public  final class StopRunRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:profilence.zeta.StopRunRequest)
    StopRunRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use StopRunRequest.newBuilder() to construct.
  private StopRunRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StopRunRequest() {
    runId_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new StopRunRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private StopRunRequest(
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

            discardResults_ = input.readBool();
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
    return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_StopRunRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_StopRunRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.profilence.zeta.StopRunRequest.class, com.profilence.zeta.StopRunRequest.Builder.class);
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

  public static final int DISCARD_RESULTS_FIELD_NUMBER = 2;
  private boolean discardResults_;
  /**
   * <code>bool discard_results = 2;</code>
   * @return The discardResults.
   */
  public boolean getDiscardResults() {
    return discardResults_;
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
    if (discardResults_ != false) {
      output.writeBool(2, discardResults_);
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
    if (discardResults_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(2, discardResults_);
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
    if (!(obj instanceof com.profilence.zeta.StopRunRequest)) {
      return super.equals(obj);
    }
    com.profilence.zeta.StopRunRequest other = (com.profilence.zeta.StopRunRequest) obj;

    if (!getRunId()
        .equals(other.getRunId())) return false;
    if (getDiscardResults()
        != other.getDiscardResults()) return false;
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
    hash = (37 * hash) + DISCARD_RESULTS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getDiscardResults());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.profilence.zeta.StopRunRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.StopRunRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.StopRunRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.StopRunRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.StopRunRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.StopRunRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.StopRunRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.StopRunRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.profilence.zeta.StopRunRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.StopRunRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.profilence.zeta.StopRunRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.StopRunRequest parseFrom(
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
  public static Builder newBuilder(com.profilence.zeta.StopRunRequest prototype) {
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
   * Protobuf type {@code profilence.zeta.StopRunRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:profilence.zeta.StopRunRequest)
      com.profilence.zeta.StopRunRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_StopRunRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_StopRunRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.profilence.zeta.StopRunRequest.class, com.profilence.zeta.StopRunRequest.Builder.class);
    }

    // Construct using com.profilence.zeta.StopRunRequest.newBuilder()
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

      discardResults_ = false;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_StopRunRequest_descriptor;
    }

    @java.lang.Override
    public com.profilence.zeta.StopRunRequest getDefaultInstanceForType() {
      return com.profilence.zeta.StopRunRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.profilence.zeta.StopRunRequest build() {
      com.profilence.zeta.StopRunRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.profilence.zeta.StopRunRequest buildPartial() {
      com.profilence.zeta.StopRunRequest result = new com.profilence.zeta.StopRunRequest(this);
      result.runId_ = runId_;
      result.discardResults_ = discardResults_;
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
      if (other instanceof com.profilence.zeta.StopRunRequest) {
        return mergeFrom((com.profilence.zeta.StopRunRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.profilence.zeta.StopRunRequest other) {
      if (other == com.profilence.zeta.StopRunRequest.getDefaultInstance()) return this;
      if (!other.getRunId().isEmpty()) {
        runId_ = other.runId_;
        onChanged();
      }
      if (other.getDiscardResults() != false) {
        setDiscardResults(other.getDiscardResults());
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
      com.profilence.zeta.StopRunRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.profilence.zeta.StopRunRequest) e.getUnfinishedMessage();
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

    private boolean discardResults_ ;
    /**
     * <code>bool discard_results = 2;</code>
     * @return The discardResults.
     */
    public boolean getDiscardResults() {
      return discardResults_;
    }
    /**
     * <code>bool discard_results = 2;</code>
     * @param value The discardResults to set.
     * @return This builder for chaining.
     */
    public Builder setDiscardResults(boolean value) {
      
      discardResults_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool discard_results = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearDiscardResults() {
      
      discardResults_ = false;
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


    // @@protoc_insertion_point(builder_scope:profilence.zeta.StopRunRequest)
  }

  // @@protoc_insertion_point(class_scope:profilence.zeta.StopRunRequest)
  private static final com.profilence.zeta.StopRunRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.profilence.zeta.StopRunRequest();
  }

  public static com.profilence.zeta.StopRunRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StopRunRequest>
      PARSER = new com.google.protobuf.AbstractParser<StopRunRequest>() {
    @java.lang.Override
    public StopRunRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new StopRunRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StopRunRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StopRunRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.profilence.zeta.StopRunRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

