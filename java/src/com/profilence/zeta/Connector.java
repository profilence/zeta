/*
 * Copyright 2020 Profilence
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.profilence.zeta;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.google.protobuf.StringValue;


public class Connector {
	
	
	public static void main(String[] args) throws Exception {
						
		if (args.length > 0) {
			
			Pattern p = Pattern.compile("([^:]+):([0-9]+)");
			Matcher m;
			String address;
			int port;
			
			if (args.length >= 1 &&  (m = p.matcher(args[0])).find()) {
				address = m.group(1);
				port = Integer.parseInt(m.group(2));
			} else if (args.length >= 2 && args[1].matches("[0-9]+")) {
				address = args[0];
				port = Integer.parseInt(args[1]);
			} else {
				System.out.println("Invalid parameters; give address and port for ping test: [address]:[port]");
				return;
			}
			
			Connector.addLogger(new ILogger() {
				@Override
				public void onLogMessage(LogLevel level, String message) {
					System.out.println(message);
				}
			});
			System.out.println("Connecting to " + address + ":" + port + " ...");
			Connector client = new Connector(address, port);
		    try {
		    	System.out.println("Pinging ...");
			    if (client.ping() == PingResponseType.Ok) {
			    	System.out.println("Successfully pinged the service");
			    } else {
			        System.out.println("Failed to ping the service");
			    }
		    } finally {
		    	System.out.println("Shutting down ...");
		    	client.shutdown();
		    }
		} else {
			System.out.println("Invalid parameters; give address and port for ping test: [address]:[port]");
			return;
		}
	}
	
	/**
	 * 
	 * @param logger
	 */
	public static void addLogger(ILogger logger) {
		synchronized(loggerLock) {
			if (logger == null || loggers.contains(logger)) {
				return;
			}
			loggers.add(logger);
		}
	}
	
	private static void log(LogLevel level, String message) {
		synchronized(loggerLock) {
			for(ILogger logger : loggers) {
				logger.onLogMessage(level, message);
			}
		}
	}
	
	private static List<ILogger> loggers = new ArrayList<>();
	private static Object loggerLock = new Object();
	private final ManagedChannel channel;
   	private final ConnectorServiceGrpc.ConnectorServiceBlockingStub blockingStub;
   	private final ConnectorServiceGrpc.ConnectorServiceStub stub;
	private final StreamObserver<DeviceLogEntry> deviceLogSubject;
	   	
    /** Construct client connecting to Connector server at {@code host:port}. */
	public Connector(String host, int port) {
	    this(ManagedChannelBuilder.forAddress(host, port)
            // Channels are secure by default (via SSL/TLS).
            .usePlaintext()
            .build());
	}
	
	/** Construct client for accessing Connector server using the existing channel. */
	Connector(ManagedChannel channel) {
		this.channel = channel;
		blockingStub = ConnectorServiceGrpc.newBlockingStub(channel);
		stub = ConnectorServiceGrpc.newStub(channel);
		deviceLogSubject = stub.logDevice(new StreamObserver<Empty>() {
	        @Override
	        public void onNext(Empty __) {
	        }

			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable __) {						
			}
	    });
  	}
	
	/**
	 * Close the connection to server
	 * @throws InterruptedException
	 */
	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
	
	/**
	 * Ping Connector service
	 */
	public PingResponseType ping() {
		PingMessage request = PingMessage.newBuilder().build();
	    try {
	    	PongMessage response = blockingStub.ping(request);
	    	return PingResponseType.fromValue(response.getResult());
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return PingResponseType.Failed;
	    }
	}
	
	/**
	 * Request server to start new test run with recommended profiling settings
	 * @param runName
	 * @param setName
	 * @param project
	 * @param version
	 * @param primaryDeviceSerial
	 * @param primaryDeviceType
	 * @param secondaryDeviceSerial
	 * @param secondaryDeviceType
	 * @param tags
	 * @return Test run id if successfully started; null otherwise
	 */
	public String startRunWithRecommendedSettings(
			String runName, 
			String setName, 
			String project, 
			String version, 
			String primaryDeviceSerial, 
			String primaryDeviceType, 
			String secondaryDeviceSerial,
			String secondaryDeviceType,
			Map<String, String> tags) {
		return startRun(runName, setName, project, version, primaryDeviceSerial, primaryDeviceType, secondaryDeviceSerial, secondaryDeviceType, (String)null, tags, null);
	}
	
	/**
	 * Request server to start new test run with recommended profiling settings
	 * @param runName
	 * @param setName
	 * @param project
	 * @param version
	 * @param primaryDeviceSerial
	 * @param primaryDeviceType
	 * @param secondaryDeviceSerial
	 * @param secondaryDeviceType
	 * @param tags
	 * @param runId
	 * @return Test run id if successfully started; null otherwise
	 */
	public String startRunWithRecommendedSettings(
			String runName, 
			String setName, 
			String project, 
			String version, 
			String primaryDeviceSerial, 
			String primaryDeviceType, 
			String secondaryDeviceSerial,
			String secondaryDeviceType,
			Map<String, String> tags,
			String runId) {
		return startRun(runName, setName, project, version, primaryDeviceSerial, primaryDeviceType, secondaryDeviceSerial, secondaryDeviceType, (String)null, tags, runId);
	}
	
	/**
	 * Request server to start new test run
	 * @param runName
	 * @param setName
	 * @param project
	 * @param version
	 * @param primaryDeviceSerial
	 * @param primaryDeviceType
	 * @param secondaryDeviceSerial
	 * @param secondaryDeviceType
	 * @param profilingSettings
	 * @param tags
	 * @return
	 */
	public String startRun(
			String runName, 
			String setName, 
			String project, 
			String version, 
			String primaryDeviceSerial, 
			String primaryDeviceType, 
			String secondaryDeviceSerial,
			String secondaryDeviceType,
			ProfilingConfiguration profilingSettings,
			Map<String, String> tags) {
		return startRun(runName, setName, project, version, primaryDeviceSerial, primaryDeviceType, secondaryDeviceSerial, secondaryDeviceType, profilingSettings, tags, null);
	}
	
	/**
	 * Request server to start new test run
	 * @param runName
	 * @param setName
	 * @param project
	 * @param version
	 * @param primaryDeviceSerial
	 * @param primaryDeviceType
	 * @param secondaryDeviceSerial
	 * @param secondaryDeviceType
	 * @param profilingSettings
	 * @param tags
	 * @param runId
	 * @return
	 */
	public String startRun(
			String runName, 
			String setName, 
			String project, 
			String version, 
			String primaryDeviceSerial, 
			String primaryDeviceType, 
			String secondaryDeviceSerial,
			String secondaryDeviceType,
			ProfilingConfiguration profilingSettings,
			Map<String, String> tags,
			String runId) {
		String configJson = profilingSettings != null ? profilingSettings.toJson() : null;
		return startRun(runName, setName, project, version, primaryDeviceSerial, primaryDeviceType, secondaryDeviceSerial, secondaryDeviceType, configJson, tags, runId);
	}
	
	
	/**
	 * Request server to start new test run
	 * @param runName
	 * @param setName
	 * @param project
	 * @param version
	 * @param primaryDeviceSerial
	 * @param primaryDeviceType
	 * @param secondaryDeviceSerial
	 * @param secondaryDeviceType
	 * @param profilingSettingsFile JSON file containing profiling settings
	 * @param tags
	 * @return Test run id if successfully started; null otherwise
	 */
	public String startRun(
			String runName, 
			String setName, 
			String project, 
			String version, 
			String primaryDeviceSerial, 
			String primaryDeviceType, 
			String secondaryDeviceSerial,
			String secondaryDeviceType,
			File profilingSettingsFile,
			Map<String, String> tags) {
		return startRun(runName, setName, project, version, primaryDeviceSerial, primaryDeviceType, secondaryDeviceSerial, secondaryDeviceType, profilingSettingsFile, tags, null);
	}
	
	/**
	 * Request server to start new test run
	 * @param runName
	 * @param setName
	 * @param project
	 * @param version
	 * @param primaryDeviceSerial
	 * @param primaryDeviceType
	 * @param secondaryDeviceSerial
	 * @param secondaryDeviceType
	 * @param profilingSettingsFile JSON file containing profiling settings
	 * @param tags
	 * @param runId
	 * @return Test run id if successfully started; null otherwise
	 */
	public String startRun(
			String runName, 
			String setName, 
			String project, 
			String version, 
			String primaryDeviceSerial, 
			String primaryDeviceType, 
			String secondaryDeviceSerial,
			String secondaryDeviceType,
			File profilingSettingsFile,
			Map<String, String> tags,
			String runId) {
		
		String settingsJson = null;
		if (profilingSettingsFile != null && profilingSettingsFile.exists()) {
		    StringBuilder contentBuilder = new StringBuilder();
		    try (BufferedReader br = new BufferedReader(new FileReader(profilingSettingsFile))) 
		    {
		        String sCurrentLine;
		        while ((sCurrentLine = br.readLine()) != null) 
		        {
		            contentBuilder.append(sCurrentLine).append("\n");
		        }
		    } catch (Exception e) { 
		    } 
		    settingsJson = contentBuilder.toString();
		    if (settingsJson != null && settingsJson.trim().isEmpty()) {
		    	settingsJson = null;
		    }
		}
		return startRun(runName, setName, project, version, primaryDeviceSerial, primaryDeviceType, secondaryDeviceSerial, secondaryDeviceType, settingsJson, tags, runId);
	}
	
	/**
	 * Request server to start new test run
	 * @param runName
	 * @param setName
	 * @param project
	 * @param version
	 * @param primaryDeviceSerial
	 * @param primaryDeviceType
	 * @param secondaryDeviceSerial
	 * @param secondaryDeviceType
	 * @param profilingSettingsFile JSON file containing profiling settings
	 * @param tags
	 * @return Test run id if successfully started; null otherwise
	 */
	public String startRun(
			String runName, 
			String setName, 
			String project, 
			String version, 
			String primaryDeviceSerial, 
			String primaryDeviceType, 
			String secondaryDeviceSerial,
			String secondaryDeviceType,
			String profilingSettings,
			Map<String, String> tags) {
		return startRun(runName, setName, project, version, primaryDeviceSerial, primaryDeviceType, secondaryDeviceSerial, secondaryDeviceType, profilingSettings, tags, null);
	}
	
	/**
	 * Request server to start new test run
	 * @param runName
	 * @param setName
	 * @param project
	 * @param version
	 * @param primaryDeviceSerial
	 * @param primaryDeviceType
	 * @param secondaryDeviceSerial
	 * @param secondaryDeviceType
	 * @param profilingSettings JSON string providing analyzer settings
	 * @param tags
	 * @param runId
	 * @return Test run id if successfully started; null otherwise
	 */
	public String startRun(
			String runName, 
			String setName, 
			String project, 
			String version, 
			String primaryDeviceSerial, 
			String primaryDeviceType, 
			String secondaryDeviceSerial,
			String secondaryDeviceType,
			String profilingSettings,
			Map<String, String> tags,
			String runId) {
		
		StartRunRequest request = StartRunRequest.newBuilder()
				.setRunName(runName != null ? runName : "")
				.setSetName(setName != null ? setName : "")
				.setProject(project != null ? project : "")
				.setVersion(version != null ? version : "")
				.setPrimaryDeviceType(primaryDeviceType != null ? primaryDeviceType : "")
				.setSecondaryDeviceType(secondaryDeviceType != null ? secondaryDeviceType : "")
				.setPrimaryDeviceSerial(primaryDeviceSerial != null ? primaryDeviceSerial : "")
				.setSecondaryDeviceSerial(secondaryDeviceSerial != null ? secondaryDeviceSerial : "")
				.setProfilingSettings(profilingSettings != null ? profilingSettings : "")
				.putAllTags(tags != null ? tags : new HashMap<>())
				.setRunId(runId != null ? runId : "")
				.build();
		 
	    try {
	    	StartRunResponse response = blockingStub.startRun(request);
	    	return response.getRunId();
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return null;
	    }
	}
	
	/**
	 * Notify server about new use case
	 * @param runID
	 * @param useCaseName
	 * @param useCaseID
	 * @param targetProcess
	 * @param requirementID
	 * @return True if successfully notified; false otherwise
	 */
	public boolean onUseCaseStart(
			String runID,
			String useCaseName,
			String useCaseID,
			String targetProcess,
			String requirementID) {
		
		if (runID == null || runID.trim().isEmpty()) {
			return false;
		}
		
		boolean useCaseNameInvalid = useCaseName == null || useCaseName.trim().isEmpty();
		boolean useCaseIDInvalid = useCaseID == null || useCaseID.trim().isEmpty();
		
		if (useCaseNameInvalid && useCaseIDInvalid) {
			return false;
		}
				
		UseCaseStartRequest request = UseCaseStartRequest.newBuilder()
				.setRunId(runID)
				.setUseCaseName(useCaseName != null ? useCaseName : "")
				.setUseCaseId(useCaseID != null ? useCaseID : "")
				.setTargetProcess(targetProcess != null ? targetProcess : "")
				.setRequirementId(requirementID != null ? requirementID : "")
				.build();
		
	    try {
	    	blockingStub.onUseCaseStart(request);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * Notify server about use case end
	 * @param runID
	 * @param result
	 * @param activeRunTime
	 * @param failCause
	 * @param resetIntended
	 * @return True if successfully notified; false otherwise 
	 */
	public boolean onUseCaseEnd(
			String runID,
			boolean result,
			long activeRunTime,
			String failCause,
			boolean resetIntended) {
		
		if (runID == null || runID.trim().isEmpty()) {
			return false;
		}
		
		UseCaseEndRequest request = UseCaseEndRequest.newBuilder()
				.setRunId(runID)
				.setResult(result)
				.setActiveRunTime(activeRunTime)
				.setFailCause(failCause != null ? failCause : "")
				.setResetIntended(resetIntended)
				.build();
		
	    try {
	    	blockingStub.onUseCaseEnd(request);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * Notify server about log step
	 * @param runID
	 * @param stepName
	 * @param result
	 * @param takeScreenShot
	 * @return True if successfully notified; false otherwise
	 */
	public boolean onLogStep(
			String runID, 
			String stepName, 
			boolean result, 
			boolean takeScreenShot) {
		return onLogStep(runID, stepName, result, takeScreenShot, null);
	}
	
	/**
	 * Notify server about log step
	 * @param runID
	 * @param stepName
	 * @param result
	 * @param screenShot
	 * @return True if successfully notified; false otherwise
	 */
	public boolean onLogStep (
			String runID, 
			String stepName, 
			boolean result, 
			File screenShot) throws IOException {
			
		byte[] bytes = null;
		
		if (screenShot != null && screenShot.exists() && screenShot.length() > 0) {
			bytes = read(screenShot);
		}
				
		return onLogStep(runID, stepName, result, bytes);
	}
	
	/**
	 * Notify server about log step
	 * @param runID
	 * @param stepName
	 * @param result
	 * @param screenShotBytes
	 * @return True if successfully notified; false otherwise
	 */
	public boolean onLogStep(
			String runID, 
			String stepName, 
			boolean result, 
			byte[] screenShotBytes) {
		return onLogStep(runID, stepName, result, false, screenShotBytes);
	}
	
	/**
	 * Notify server about log step
	 * @param runID
	 * @param stepName
	 * @param result
	 * @param takeScreenShot
	 * @param screenShotBytes
	 * @return True if successfully notified; false otherwise
	 */
	private boolean onLogStep(
			String runID, 
			String stepName, 
			boolean result, 
			boolean takeScreenShot,
			byte[] screenShotBytes) {
		
		if (runID == null || runID.trim().isEmpty()) {
			return false;
		}
		
		if (stepName == null || stepName.trim().isEmpty()) {
			return false;
		}
		
		LogStepRequest request = LogStepRequest.newBuilder()
				.setRunId(runID)
				.setStepName(stepName)
				.setResult(result)
				.setTakeScreenshot(takeScreenShot)
				.setScreenshotBytes(ByteString.copyFrom(screenShotBytes != null ? screenShotBytes : new byte[0]))
				.build();
		
	    try {
	    	blockingStub.onLogStep(request);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * Log a message to servers execution log
	 * @param runID
	 * @param data
	 * @return True if successfully logged; false otherwise
	 */
	public boolean logTrace(String runID, String data) {
		
		if (runID == null || runID.trim().isEmpty()) {
			return false;
		}
		
		if (data == null || data.trim().isEmpty()) {
			return false;
		}
		
		LogTraceRequest request = LogTraceRequest.newBuilder()
				.setRunId(runID)
				.setData(data)
				.build();
		
	    try {
	    	blockingStub.logTrace(request);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * Stop the test run on server
	 * @param runID
	 * @param discardResults
	 * @return True if successfully stopped; false otherwise
	 */
	public boolean stopRun(String runID, boolean discardResults) {
		
		if (runID == null || runID.trim().isEmpty()) {
			return false;
		}
		
		StopRunRequest request = StopRunRequest.newBuilder()
				.setRunId(runID)
				.setDiscardResults(discardResults)
				.build();
			
	    try {
	    	blockingStub.stopRun(request);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * Notify server about a reset occurred in DUT
	 * @param runID
	 * @param timeOccured Time 
	 * @param type
	 * @param resetReasons
	 * @param systemPropertiesAfter
	 * @return
	 */
	public boolean notifyReset(String runID, long timeOccured, ResetType type, Map<String, String> resetReasons, Map<String, String> systemPropertiesAfter) {
		
		if (runID == null || runID.trim().isEmpty()) {
			return false;
		}
					
		ResetEntry entry = ResetEntry.newBuilder()
			.setRunId(runID)
			.setTimestamp(timeOccured)
			.setType(type.toValue())
			.putAllProperties(resetReasons != null ? resetReasons : new HashMap<String, String>())
			.putAllReasons(systemPropertiesAfter != null ? systemPropertiesAfter : new HashMap<String, String>())
			.build();
		
	    try {
	    	blockingStub.notifyReset(entry);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * 
	 * @param runID
	 * @param timeOccured
	 * @param type
	 * @param isSystemProcess
	 * @param name
	 * @param process
	 * @param exceptionType
	 * @param dataLines
	 * @return
	 */
	public boolean notifyEvent(String runID, long timeOccured, EventType type, boolean isSystemProcess, String name, String process, String exceptionType, String[] dataLines) {
		return notifyEvent(runID, timeOccured, type, isSystemProcess, name, process, exceptionType, dataLines != null ? Arrays.asList(dataLines) : null);
	}
	
	/**
	 * Notify server about an event occurred with the DUT: crash, freeze, warning, etc.
	 * @param runID
	 * @param timeOccured
	 * @param type
	 * @param isSystemProcess
	 * @param name
	 * @param process
	 * @param exceptionType
	 * @param dataLines
	 * @return
	 */
	public boolean notifyEvent(String runID, long timeOccured, EventType type, boolean isSystemProcess, String name, String process, String exceptionType, Iterable<String> dataLines) {
		
		if (runID == null || runID.trim().isEmpty()) {
			return false;
		}
		
		EventEntry entry = EventEntry.newBuilder()
			.setRunId(runID)
			.setTimestamp(timeOccured)
			.setType(type.toValue())
			.setIsSystemProcess(isSystemProcess)
			.setName(name != null ? name : "")
			.setProcess(process != null ? process : "")
			.setExceptionType(exceptionType != null ? exceptionType : "")
			.addAllData(dataLines != null ? dataLines : new ArrayList<>())
			.build();
		
	    try {
	    	blockingStub.notifyEvent(entry);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * 
	 * @param runID
	 * @param seriedID
	 * @param seriesName
	 * @param group
	 * @param yAxisName
	 * @param unit
	 * @param type
	 * @param namespace
	 * @param process
	 * @param description
	 * @return
	 */
	public boolean createTimeSeries(String runID, String seriesID, String seriesName, String group, String yAxisName, String unit, SeriesType type, String namespace, String process, String description) {
		
		if (runID == null || runID.trim().isEmpty()) {
			return false;
		}
		
		if (seriesID == null || seriesID.trim().isEmpty()) {
			return false;
		}
		
		DynamicSeriesInformation info = DynamicSeriesInformation.newBuilder()
				.setRunId(runID)
				.setSeriesId(seriesID)
				.setSeriesName(seriesName != null ? seriesName : "")
				.setGroup(group != null ? group : "")
				.setYAxisName(yAxisName != null ? yAxisName : "")
				.setUnit(unit != null ? unit : "")
				.setType(type.toValue())
				.setNamespace(namespace != null ? namespace : "")
				.setProcess(process != null ? process : "")
				.setDescription(description != null ? description : "")
				.build();
				
	    try {
	    	blockingStub.createTimeSeries(info);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * 
	 * @param runID
	 * @param seriesID
	 * @param timestamp
	 * @param pkg
	 * @param process
	 * @param values
	 * @return
	 */
	public boolean updateCompositeProcessSeries(String runID, String seriesID, long timestamp, String pkg, String process, Map<String, Float> values) {
		
		if (runID == null || runID.trim().isEmpty()) {
			return false;
		}
		
		if (seriesID == null || seriesID.trim().isEmpty()) {
			return false;
		}
		
		if ((pkg == null || pkg.trim().isEmpty()) && (process == null || process.trim().isEmpty())) {
			return false;
		}
		
		DynamicProcessCompositeSeriesUpdate update = DynamicProcessCompositeSeriesUpdate.newBuilder()
				.setRunId(runID)
				.setSeriesId(seriesID)
				.setTimestamp(timestamp)
				.setPackage(pkg != null ? pkg : "")
				.setProcess(process != null ? process : "")
				.putAllValues(values != null ? values : new HashMap<>())
				.build();

	    try {
	    	blockingStub.updateCompositeProcessSeries(update);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * 
	 * @param runID
	 * @param seriesID
	 * @param timestamp
	 * @param values
	 * @return
	 */
	public boolean updateCompositeSystemSeries(String runID, String seriesID, long timestamp, Map<String, Float> values) {
		
		if (runID == null || runID.trim().isEmpty()) {
			return false;
		}
		
		if (seriesID == null || seriesID.trim().isEmpty()) {
			return false;
		}
		
		DynamicCompositeSeriesUpdate update = DynamicCompositeSeriesUpdate.newBuilder()
				.setRunId(runID)
				.setSeriesId(seriesID)
				.setTimestamp(timestamp)
				.putAllValues(values != null ? values : new HashMap<>())
				.build();

	    try {
	    	blockingStub.updateCompositeSystemSeries(update);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
			
	/**
	 * 
	 * @param runID
	 * @param timestamp
	 * @param priority
	 * @param sourceBufferType
	 * @param tag
	 * @param data
	 * @return
	 */
	public boolean onDeviceLog(String runID, long timestamp, LogPriority priority, SourceBuffer sourceBufferType, String tag, String data) {
		return onDeviceLogImpl(runID, 1, timestamp, priority, sourceBufferType, tag, data);
	}
	
	/**
	 * 
	 * @param runID
	 * @param timestamp
	 * @param priority
	 * @param sourceBufferType
	 * @param tag
	 * @param data
	 * @return
	 */
	public boolean onDevice2Log(String runID, long timestamp, LogPriority priority, SourceBuffer sourceBufferType, String tag, String data) {
		return onDeviceLogImpl(runID, 2, timestamp, priority, sourceBufferType, tag, data);
	}

	private boolean onDeviceLogImpl(String runID, int deviceIndex, long timestamp, LogPriority priority, SourceBuffer sourceBufferType, String tag, String data) {
		
		if (runID == null || runID.trim().isEmpty()) {
			return false;
		}
					
		DeviceLogEntry entry = DeviceLogEntry.newBuilder()
				.setRunId(runID)
				.setDeviceIndex(deviceIndex)
				.setTimestamp(timestamp)
				.setTag(tag != null ? tag : "")
				.setData(data != null ? data : "")
				.setPriority(priority.toValue())
				.setSourceBuffer(sourceBufferType.toValue())
				.build();
				
	    try {
	    	deviceLogSubject.onNext(entry);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
		
	/**
	 * 
	 * @param runID
	 * @param seriesID
	 * @param pkg
	 * @param process
	 * @param timestamp
	 * @param value
	 * @return
	 */
	public boolean updateSingleProcessSeries(String runID, String seriesID, String pkg, String process, long timestamp, float value) {
		
		if (runID == null || runID.trim().isEmpty()) {
			return false;
		}
		
		if (seriesID == null || seriesID.trim().isEmpty()) {
			return false;
		}
		
		if ((pkg == null || pkg.trim().isEmpty()) && (process == null || process.trim().isEmpty())) {
			return false;
		}
		
		DynamicProcessSingleSeriesUpdate update = DynamicProcessSingleSeriesUpdate.newBuilder()
				.setRunId(runID)
				.setSeriesId(seriesID)
				.setTimestamp(timestamp)
				.setPackage(pkg != null ? pkg : "")
				.setProcess(process != null ? process : "")
				.setValue(value)
				.build();
		
	    try {
	    	blockingStub.updateSingleProcessSeries(update);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * 
	 * @param runID
	 * @param seriesID
	 * @param timestamp
	 * @param value
	 * @return
	 */
	public boolean updateSingleSystemSeries(String runID, String seriesID, long timestamp, float value) {
		
		if (runID == null || runID.trim().isEmpty()) {
			return false;
		}
		
		if (seriesID == null || seriesID.trim().isEmpty()) {
			return false;
		}
		
		DynamicSingleSeriesUpdate update = DynamicSingleSeriesUpdate.newBuilder()
				.setRunId(runID)
				.setSeriesId(seriesID)
				.setTimestamp(timestamp)
				.setValue(value)
				.build();
		
	    try {
	    	blockingStub.updateSingleSystemSeries(update);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
	
	/***
	 * 
	 * For implementing a test node for the test cloud -->
	 */
	
	/**
	 * Subscribe to test requests
	 * @param listener handler for asynchronous requests
	 */
	public void subscribeToTestRequests(final ITestRequestListener listener) {
		if (listener != null) {
			StreamObserver<TestRequestMessageWrapper> observer = new StreamObserver<TestRequestMessageWrapper>() {
			      @Override
			      public void onNext(TestRequestMessageWrapper value) {
			    	  if (value != null) {
			    		  try {
				    		  switch (value.getRequestType()) {
				    		  	case 1:
				    		  		listener.onTestStartRequested(TestStartRequest.parseFrom(value.getPayload()));
				    		  		break;
				    		  	case 2: 
				    		  		listener.onTestStopRequested(TestStopRequest.parseFrom(value.getPayload()));
				    		  		break;
				    		  	default: 
				    		  		break;
				    		  }
			    		  } catch (Exception e) {
			    			  listener.onError(e);
			    		  }
			    	  }
			      }
			      @Override
			      public void onError(Throwable t) {
			    	  listener.onError(t);
			      }
			      @Override
			      public void onCompleted() {
			    	  listener.onCompleted();
			      }
			    };
			try {
				stub.subscribeToTestRequests(Empty.newBuilder().build(), observer);
			}  catch (StatusRuntimeException e) {
		    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
		    	listener.onError(e);
		    }
		}
	}
		
	/**
	 * Respond to a test requests
	 * @param runID
	 * @param status
	 * @param result
	 * @param failCause
	 * @param log
	 * @return
	 */
	public boolean respondToTestRequest(String runID, String status, boolean result, String failCause, Iterable<String> log) {
		
		if (runID == null || runID.trim().isEmpty()) {
			return false;
		}
		
		TestStartResponse resp = TestStartResponse.newBuilder()
				.setStatus(status != null ? status : "")
				.setRunId(runID)
				.setResult(result)
				.setFailCause(failCause != null ? failCause : "")
				.addAllLog(log != null ? log : new ArrayList<>())
				.build();
		
	    try {
	    	blockingStub.respondToTestRequest(resp);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * Adds a node the test cloud
	 * @param nodeID
	 * @param pool
	 * @param type
	 * @param variables
	 * @return True if successfully added; false otherwise
	 */
	public boolean addNode(
			String nodeID, 
			String pool, 
			String type, 
			String variables) {
		
		if (nodeID == null || nodeID.trim().isEmpty()) {
			return false;
		}
	
		NodeAdded request = NodeAdded.newBuilder()
				.setNodeId(nodeID)
				.setPool(pool != null ? pool : "")
				.setType(type != null ? type : "")
				.setVariables(variables != null && variables.trim().length() > 0 ? variables : "{}")
				.build();
	
	    try {
	    	blockingStub.addNode(request);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * Remove the test node from the test cloud
	 * @param nodeID ID of the node
	 * @return True if successfully removed; false otherwise
	 */
	public boolean removeNode(String nodeID) {
		
		if (nodeID == null || nodeID.trim().isEmpty()) {
			return false;
		}
		
		NodeRemoved request = NodeRemoved.newBuilder()
				.setNodeId(nodeID)
				.build();
		
	    try {
	    	blockingStub.removeNode(request);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
	    	return false;
	    }
	}
	
	/**
	 * Updates node state for the test cloud
	 * @param nodeID
	 * @param currentUseCase
	 * @param runState
	 * @param currentRunID
	 * @param nodeState
	 * @param pool
	 * @param variables
	 * @return True if successfully updated; false otherwise
	 */
	public boolean updateNode(
			String nodeID, 
			String currentUseCase, 
			Integer runState, 
			String currentRunID,
			Integer nodeState, 
			String pool, 
			String variables) {
		
		 if (nodeID == null || nodeID.trim().isEmpty()) {
			 return false;
		 }
		
		 		
		 NodeUpdated.Builder bob = NodeUpdated.newBuilder()
				.setNodeId(nodeID);
		 
		 if (currentUseCase != null && currentUseCase.trim().isEmpty() == false) {
			 bob.setCurrentUseCase(StringValue.newBuilder().setValue(currentUseCase).build());
		 }
		 
		 if (currentRunID != null && currentRunID.trim().isEmpty() == false) {
			 bob.setCurrentRunId(StringValue.newBuilder().setValue(currentRunID).build());
		 }
		 
		 if (pool != null && pool.trim().isEmpty() == false) {
			 bob.setPool(StringValue.newBuilder().setValue(pool).build());
		 }
		 
		 if (variables != null && variables.trim().isEmpty() == false) {
			 bob.setVariables(StringValue.newBuilder().setValue(variables).build());
		 }
		 
		 if (nodeState != null) {
			 bob.setNodeState(Int32Value.newBuilder().setValue(nodeState).build());
		 }
		 
		 if (runState != null) {
			 bob.setRunState(Int32Value.newBuilder().setValue(runState).build());
		 }
		 
		 NodeUpdated request = bob.build();
		 
		    try {
		    	blockingStub.updateNode(request);
		    	return true;
		    } catch (StatusRuntimeException e) {
		    	log(LogLevel.Warning, "RPC failed: " + e.getMessage());
		    	return false;
		    }
	}
	
	private static byte[] read(File file) throws IOException {

		byte[] buffer = new byte[(int) file.length()];
	    InputStream ios = null;
	    try {
	        ios = new FileInputStream(file);
	        if (ios.read(buffer) == -1) {
	            throw new IOException("EOF reached while trying to read the whole file");
	        }
	    } finally {
	        try {
	            if (ios != null) {
	                ios.close();
	            }
	        } catch (IOException e) {
	        }
	    }
	    return buffer;
	}
}
