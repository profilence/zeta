// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: connector_service.proto

package com.profilence.zeta;

/**
 * Protobuf type {@code profilence.zeta.TestStopRequest}
 */
public  final class TestStopRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:profilence.zeta.TestStopRequest)
    TestStopRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TestStopRequest.newBuilder() to construct.
  private TestStopRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TestStopRequest() {
    primaryDeviceSerial_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new TestStopRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TestStopRequest(
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

            primaryDeviceSerial_ = s;
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
    return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_TestStopRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_TestStopRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.profilence.zeta.TestStopRequest.class, com.profilence.zeta.TestStopRequest.Builder.class);
  }

  public static final int PRIMARY_DEVICE_SERIAL_FIELD_NUMBER = 1;
  private volatile java.lang.Object primaryDeviceSerial_;
  /**
   * <code>string primary_device_serial = 1;</code>
   * @return The primaryDeviceSerial.
   */
  public java.lang.String getPrimaryDeviceSerial() {
    java.lang.Object ref = primaryDeviceSerial_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      primaryDeviceSerial_ = s;
      return s;
    }
  }
  /**
   * <code>string primary_device_serial = 1;</code>
   * @return The bytes for primaryDeviceSerial.
   */
  public com.google.protobuf.ByteString
      getPrimaryDeviceSerialBytes() {
    java.lang.Object ref = primaryDeviceSerial_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      primaryDeviceSerial_ = b;
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
    if (!getPrimaryDeviceSerialBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, primaryDeviceSerial_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getPrimaryDeviceSerialBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, primaryDeviceSerial_);
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
    if (!(obj instanceof com.profilence.zeta.TestStopRequest)) {
      return super.equals(obj);
    }
    com.profilence.zeta.TestStopRequest other = (com.profilence.zeta.TestStopRequest) obj;

    if (!getPrimaryDeviceSerial()
        .equals(other.getPrimaryDeviceSerial())) return false;
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
    hash = (37 * hash) + PRIMARY_DEVICE_SERIAL_FIELD_NUMBER;
    hash = (53 * hash) + getPrimaryDeviceSerial().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.profilence.zeta.TestStopRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.TestStopRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.TestStopRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.TestStopRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.TestStopRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.profilence.zeta.TestStopRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.profilence.zeta.TestStopRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.TestStopRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.profilence.zeta.TestStopRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.TestStopRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.profilence.zeta.TestStopRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.profilence.zeta.TestStopRequest parseFrom(
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
  public static Builder newBuilder(com.profilence.zeta.TestStopRequest prototype) {
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
   * Protobuf type {@code profilence.zeta.TestStopRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:profilence.zeta.TestStopRequest)
      com.profilence.zeta.TestStopRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_TestStopRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_TestStopRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.profilence.zeta.TestStopRequest.class, com.profilence.zeta.TestStopRequest.Builder.class);
    }

    // Construct using com.profilence.zeta.TestStopRequest.newBuilder()
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
      primaryDeviceSerial_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.profilence.zeta.DriverProto.internal_static_profilence_zeta_TestStopRequest_descriptor;
    }

    @java.lang.Override
    public com.profilence.zeta.TestStopRequest getDefaultInstanceForType() {
      return com.profilence.zeta.TestStopRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.profilence.zeta.TestStopRequest build() {
      com.profilence.zeta.TestStopRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.profilence.zeta.TestStopRequest buildPartial() {
      com.profilence.zeta.TestStopRequest result = new com.profilence.zeta.TestStopRequest(this);
      result.primaryDeviceSerial_ = primaryDeviceSerial_;
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
      if (other instanceof com.profilence.zeta.TestStopRequest) {
        return mergeFrom((com.profilence.zeta.TestStopRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.profilence.zeta.TestStopRequest other) {
      if (other == com.profilence.zeta.TestStopRequest.getDefaultInstance()) return this;
      if (!other.getPrimaryDeviceSerial().isEmpty()) {
        primaryDeviceSerial_ = other.primaryDeviceSerial_;
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
      com.profilence.zeta.TestStopRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.profilence.zeta.TestStopRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object primaryDeviceSerial_ = "";
    /**
     * <code>string primary_device_serial = 1;</code>
     * @return The primaryDeviceSerial.
     */
    public java.lang.String getPrimaryDeviceSerial() {
      java.lang.Object ref = primaryDeviceSerial_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        primaryDeviceSerial_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string primary_device_serial = 1;</code>
     * @return The bytes for primaryDeviceSerial.
     */
    public com.google.protobuf.ByteString
        getPrimaryDeviceSerialBytes() {
      java.lang.Object ref = primaryDeviceSerial_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        primaryDeviceSerial_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string primary_device_serial = 1;</code>
     * @param value The primaryDeviceSerial to set.
     * @return This builder for chaining.
     */
    public Builder setPrimaryDeviceSerial(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      primaryDeviceSerial_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string primary_device_serial = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPrimaryDeviceSerial() {
      
      primaryDeviceSerial_ = getDefaultInstance().getPrimaryDeviceSerial();
      onChanged();
      return this;
    }
    /**
     * <code>string primary_device_serial = 1;</code>
     * @param value The bytes for primaryDeviceSerial to set.
     * @return This builder for chaining.
     */
    public Builder setPrimaryDeviceSerialBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      primaryDeviceSerial_ = value;
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


    // @@protoc_insertion_point(builder_scope:profilence.zeta.TestStopRequest)
  }

  // @@protoc_insertion_point(class_scope:profilence.zeta.TestStopRequest)
  private static final com.profilence.zeta.TestStopRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.profilence.zeta.TestStopRequest();
  }

  public static com.profilence.zeta.TestStopRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TestStopRequest>
      PARSER = new com.google.protobuf.AbstractParser<TestStopRequest>() {
    @java.lang.Override
    public TestStopRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TestStopRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TestStopRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TestStopRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.profilence.zeta.TestStopRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

