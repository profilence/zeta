// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: connector_service.proto

package com.profilence.zeta;

public interface LogStepRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:profilence.zeta.LogStepRequest)
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
   * <code>string step_name = 2;</code>
   * @return The stepName.
   */
  java.lang.String getStepName();
  /**
   * <code>string step_name = 2;</code>
   * @return The bytes for stepName.
   */
  com.google.protobuf.ByteString
      getStepNameBytes();

  /**
   * <code>bool result = 3;</code>
   * @return The result.
   */
  boolean getResult();

  /**
   * <code>bool take_screenshot = 4;</code>
   * @return The takeScreenshot.
   */
  boolean getTakeScreenshot();

  /**
   * <code>bytes screenshot_bytes = 5;</code>
   * @return The screenshotBytes.
   */
  com.google.protobuf.ByteString getScreenshotBytes();
}
