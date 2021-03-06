// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: connector_service.proto

package com.profilence.zeta;

public interface DynamicProcessSingleSeriesUpdateOrBuilder extends
    // @@protoc_insertion_point(interface_extends:profilence.zeta.DynamicProcessSingleSeriesUpdate)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string run_id = 1;</code>
   * @return The runId.
   */
  java.lang.String getRunId();
  /**
   * <code>string run_id = 1;</code>
   * @return The bytes for runId.
   */
  com.google.protobuf.ByteString
      getRunIdBytes();

  /**
   * <code>string series_id = 2;</code>
   * @return The seriesId.
   */
  java.lang.String getSeriesId();
  /**
   * <code>string series_id = 2;</code>
   * @return The bytes for seriesId.
   */
  com.google.protobuf.ByteString
      getSeriesIdBytes();

  /**
   * <code>string package = 3;</code>
   * @return The package.
   */
  java.lang.String getPackage();
  /**
   * <code>string package = 3;</code>
   * @return The bytes for package.
   */
  com.google.protobuf.ByteString
      getPackageBytes();

  /**
   * <code>string process = 4;</code>
   * @return The process.
   */
  java.lang.String getProcess();
  /**
   * <code>string process = 4;</code>
   * @return The bytes for process.
   */
  com.google.protobuf.ByteString
      getProcessBytes();

  /**
   * <code>double timestamp = 5;</code>
   * @return The timestamp.
   */
  double getTimestamp();

  /**
   * <code>float value = 6;</code>
   * @return The value.
   */
  float getValue();

  /**
   * <code>.google.protobuf.Int32Value pid = 7;</code>
   * @return Whether the pid field is set.
   */
  boolean hasPid();
  /**
   * <code>.google.protobuf.Int32Value pid = 7;</code>
   * @return The pid.
   */
  com.google.protobuf.Int32Value getPid();
  /**
   * <code>.google.protobuf.Int32Value pid = 7;</code>
   */
  com.google.protobuf.Int32ValueOrBuilder getPidOrBuilder();
}
