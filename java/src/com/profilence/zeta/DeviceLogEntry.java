// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: connector_service.proto

package com.profilence.zeta;

/**
 * Protobuf type {@code profilence.zeta.DeviceLogEntry}
 */
public  final class DeviceLogEntry extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:profilence.zeta.DeviceLogEntry)
    DeviceLogEntryOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DeviceLogEntry.newBuilder() to construct.
  private DeviceLogEntry(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DeviceLogEntry() {
    runId_ = "";
    tag_ = "";
    data_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new DeviceLogEntry();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DeviceLogEntry(
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

            deviceIndex_ = input.readInt32();
            break;
          }
          case 25: {

            timestamp_ = input.readDouble();
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            tag_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            data_ = s;
            break;
          }
          case 48: {

            priority_ = input.readInt32();
            break;
          }
          case 56: {

            sourceBuffer_ = input.readInt32();
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
    return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_DeviceLogEntry_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_DeviceLogEntry_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.profilence.zeta.DeviceLogEntry.class, com.profilence.zeta.DeviceLogEntry.Builder.class);
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

  public static final int DEVICE_INDEX_FIELD_NUMBER = 2;
  private int deviceIndex_;
  /**
   * <code>int32 device_index = 2;</code>
   * @return The deviceIndex.
   */
  public int getDeviceIndex() {
    return deviceIndex_;
  }

  public static final int TIMESTAMP_FIELD_NUMBER = 3;
  private double timestamp_;
  /**
   * <code>double timestamp = 3;</code>
   * @return The timestamp.
   */
  public double getTimestamp() {
    return timestamp_;
  }

  public static final int TAG_FIELD_NUMBER = 4;
  private volatile java.lang.Object tag_;
  /**
   * <code>string tag = 4;</code>
   * @return The tag.
   */
  public java.lang.String getTag() {
    java.lang.Object ref = tag_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      tag_ = s;
      return s;
    }
  }
  /**
   * <code>string tag = 4;</code>
   * @return The bytes for tag.
   */
  public com.google.protobuf.ByteString
      getTagBytes() {
    java.lang.Object ref = tag_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      tag_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DATA_FIELD_NUMBER = 5;
  private volatile java.lang.Object data_;
  /**
   * <code>string data = 5;</code>
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
   * <code>string data = 5;</code>
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

  public static final int PRIORITY_FIELD_NUMBER = 6;
  private int priority_;
  /**
   * <code>int32 priority = 6;</code>
   * @return The priority.
   */
  public int getPriority() {
    return priority_;
  }

  public static final int SOURCE_BUFFER_FIELD_NUMBER = 7;
  private int sourceBuffer_;
  /**
   * <code>int32 source_buffer = 7;</code>
   * @return The sourceBuffer.
   */
  public int getSourceBuffer() {
    return sourceBuffer_;
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
    if (deviceIndex_ != 0) {
      output.writeInt32(2, deviceIndex_);
    }
    if (timestamp_ != 0D) {
      output.writeDouble(3, timestamp_);
    }
    if (!getTagBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, tag_);
    }
    if (!getDataBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, data_);
    }
    if (priority_ != 0) {
      output.writeInt32(6, priority_);
    }
    if (sourceBuffer_ != 0) {
      output.writeInt32(7, sourceBuffer_);
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
    if (deviceIndex_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, deviceIndex_);
    }
    if (timestamp_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, timestamp_);
    }
    if (!getTagBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, tag_);
    }
    if (!getDataBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, data_);
    }
    if (priority_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, priority_);
    }
    if (sourceBuffer_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, sourceBuffer_);
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
    if (!(obj instanceof com.profilence.zeta.DeviceLogEntry)) {
      return super.equals(obj);
    }
    com.profilence.zeta.DeviceLogEntry other = (com.profilence.zeta.DeviceLogEntry) obj;

    if (!getRunId()
        .equals(other.getRunId())) return false;
    if (getDeviceIndex()
        != other.getDeviceIndex()) return false;
    if (java.lang.Double.doubleToLongBits(getTimestamp())
        != java.lang.Double.doubleToLongBits(
            other.getTimestamp())) return false;
    if (!getTag()
        .equals(other.getTag())) return false;
    if (!getData()
        .equals(other.getData())) return false;
    if (getPriority()
        != other.getPriority()) return false;
    if (getSourceBuffer()
        != other.getSourceBuffer()) return false;
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
    hash = (37 * hash) + DEVICE_INDEX_FIELD_NUMBER;
    hash = (53 * hash) + getDeviceIndex();
    hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getTimestamp()));
    hash = (37 * hash) + TAG_FIELD_NUMBER;
    hash = (53 * hash) + getTag().hashCode();
    hash = (37 * hash) + DATA_FIELD_NUMBER;
    hash = (53 * hash) + getData().hashCode();
    hash = (37 * hash) + PRIORITY_FIELD_NUMBER;
    hash = (53 * hash) + getPriority();
    hash = (37 * hash) + SOURCE_BUFFER_FIELD_NUMBER;
    hash = (53 * hash) + getSourceBuffer();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.profilence.zeta.DeviceLogEntry parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.DeviceLogEntry parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.DeviceLogEntry parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.DeviceLogEntry parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.DeviceLogEntry parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.DeviceLogEntry parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.DeviceLogEntry parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.DeviceLogEntry parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.profilence.zeta.DeviceLogEntry parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.DeviceLogEntry parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.profilence.zeta.DeviceLogEntry parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.DeviceLogEntry parseFrom(
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
  public static Builder newBuilder(com.profilence.zeta.DeviceLogEntry prototype) {
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
   * Protobuf type {@code profilence.zeta.DeviceLogEntry}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:profilence.zeta.DeviceLogEntry)
      com.profilence.zeta.DeviceLogEntryOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_DeviceLogEntry_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_DeviceLogEntry_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.profilence.zeta.DeviceLogEntry.class, com.profilence.zeta.DeviceLogEntry.Builder.class);
    }

    // Construct using com.profilence.zeta.DeviceLogEntry.newBuilder()
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

      deviceIndex_ = 0;

      timestamp_ = 0D;

      tag_ = "";

      data_ = "";

      priority_ = 0;

      sourceBuffer_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_DeviceLogEntry_descriptor;
    }

    @java.lang.Override
    public com.profilence.zeta.DeviceLogEntry getDefaultInstanceForType() {
      return com.profilence.zeta.DeviceLogEntry.getDefaultInstance();
    }

    @java.lang.Override
    public com.profilence.zeta.DeviceLogEntry build() {
      com.profilence.zeta.DeviceLogEntry result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.profilence.zeta.DeviceLogEntry buildPartial() {
      com.profilence.zeta.DeviceLogEntry result = new com.profilence.zeta.DeviceLogEntry(this);
      result.runId_ = runId_;
      result.deviceIndex_ = deviceIndex_;
      result.timestamp_ = timestamp_;
      result.tag_ = tag_;
      result.data_ = data_;
      result.priority_ = priority_;
      result.sourceBuffer_ = sourceBuffer_;
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
      if (other instanceof com.profilence.zeta.DeviceLogEntry) {
        return mergeFrom((com.profilence.zeta.DeviceLogEntry)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.profilence.zeta.DeviceLogEntry other) {
      if (other == com.profilence.zeta.DeviceLogEntry.getDefaultInstance()) return this;
      if (!other.getRunId().isEmpty()) {
        runId_ = other.runId_;
        onChanged();
      }
      if (other.getDeviceIndex() != 0) {
        setDeviceIndex(other.getDeviceIndex());
      }
      if (other.getTimestamp() != 0D) {
        setTimestamp(other.getTimestamp());
      }
      if (!other.getTag().isEmpty()) {
        tag_ = other.tag_;
        onChanged();
      }
      if (!other.getData().isEmpty()) {
        data_ = other.data_;
        onChanged();
      }
      if (other.getPriority() != 0) {
        setPriority(other.getPriority());
      }
      if (other.getSourceBuffer() != 0) {
        setSourceBuffer(other.getSourceBuffer());
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
      com.profilence.zeta.DeviceLogEntry parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.profilence.zeta.DeviceLogEntry) e.getUnfinishedMessage();
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

    private int deviceIndex_ ;
    /**
     * <code>int32 device_index = 2;</code>
     * @return The deviceIndex.
     */
    public int getDeviceIndex() {
      return deviceIndex_;
    }
    /**
     * <code>int32 device_index = 2;</code>
     * @param value The deviceIndex to set.
     * @return This builder for chaining.
     */
    public Builder setDeviceIndex(int value) {
      
      deviceIndex_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 device_index = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearDeviceIndex() {
      
      deviceIndex_ = 0;
      onChanged();
      return this;
    }

    private double timestamp_ ;
    /**
     * <code>double timestamp = 3;</code>
     * @return The timestamp.
     */
    public double getTimestamp() {
      return timestamp_;
    }
    /**
     * <code>double timestamp = 3;</code>
     * @param value The timestamp to set.
     * @return This builder for chaining.
     */
    public Builder setTimestamp(double value) {
      
      timestamp_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double timestamp = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearTimestamp() {
      
      timestamp_ = 0D;
      onChanged();
      return this;
    }

    private java.lang.Object tag_ = "";
    /**
     * <code>string tag = 4;</code>
     * @return The tag.
     */
    public java.lang.String getTag() {
      java.lang.Object ref = tag_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        tag_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string tag = 4;</code>
     * @return The bytes for tag.
     */
    public com.google.protobuf.ByteString
        getTagBytes() {
      java.lang.Object ref = tag_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        tag_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string tag = 4;</code>
     * @param value The tag to set.
     * @return This builder for chaining.
     */
    public Builder setTag(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      tag_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string tag = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearTag() {
      
      tag_ = getDefaultInstance().getTag();
      onChanged();
      return this;
    }
    /**
     * <code>string tag = 4;</code>
     * @param value The bytes for tag to set.
     * @return This builder for chaining.
     */
    public Builder setTagBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      tag_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object data_ = "";
    /**
     * <code>string data = 5;</code>
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
     * <code>string data = 5;</code>
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
     * <code>string data = 5;</code>
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
     * <code>string data = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearData() {
      
      data_ = getDefaultInstance().getData();
      onChanged();
      return this;
    }
    /**
     * <code>string data = 5;</code>
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

    private int priority_ ;
    /**
     * <code>int32 priority = 6;</code>
     * @return The priority.
     */
    public int getPriority() {
      return priority_;
    }
    /**
     * <code>int32 priority = 6;</code>
     * @param value The priority to set.
     * @return This builder for chaining.
     */
    public Builder setPriority(int value) {
      
      priority_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 priority = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearPriority() {
      
      priority_ = 0;
      onChanged();
      return this;
    }

    private int sourceBuffer_ ;
    /**
     * <code>int32 source_buffer = 7;</code>
     * @return The sourceBuffer.
     */
    public int getSourceBuffer() {
      return sourceBuffer_;
    }
    /**
     * <code>int32 source_buffer = 7;</code>
     * @param value The sourceBuffer to set.
     * @return This builder for chaining.
     */
    public Builder setSourceBuffer(int value) {
      
      sourceBuffer_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 source_buffer = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearSourceBuffer() {
      
      sourceBuffer_ = 0;
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


    // @@protoc_insertion_point(builder_scope:profilence.zeta.DeviceLogEntry)
  }

  // @@protoc_insertion_point(class_scope:profilence.zeta.DeviceLogEntry)
  private static final com.profilence.zeta.DeviceLogEntry DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.profilence.zeta.DeviceLogEntry();
  }

  public static com.profilence.zeta.DeviceLogEntry getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DeviceLogEntry>
      PARSER = new com.google.protobuf.AbstractParser<DeviceLogEntry>() {
    @java.lang.Override
    public DeviceLogEntry parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DeviceLogEntry(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DeviceLogEntry> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DeviceLogEntry> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.profilence.zeta.DeviceLogEntry getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
