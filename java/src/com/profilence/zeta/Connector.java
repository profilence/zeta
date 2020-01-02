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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.google.protobuf.StringValue;


public class Connector {
	
	
	public static void main(String[] args) throws Exception {
		Connector.addLogger(new ILogger() {
			@Override
			public void onLogMessage(LogLevel level, String message) {
				System.out.println(message);
			}
		});
		Connector client = new Connector("localhost", 50052);
	    try {
	      client.ping();
	    } finally {
	      client.shutdown();
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
	
    /** Construct client connecting to Connector server at {@code host:port}. */
	public Connector(String host, int port) {
	    this(ManagedChannelBuilder.forAddress(host, port)
            // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
            // needing certificates.
            .usePlaintext()
            .build());
	}
	
	/** Construct client for accessing Connector server using the existing channel. */
	Connector(ManagedChannel channel) {
		this.channel = channel;
		blockingStub = ConnectorServiceGrpc.newBlockingStub(channel);
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
	public int ping() {
		PingMessage request = PingMessage.newBuilder().build();
	    try {
	    	PongMessage response = blockingStub.ping(request);
	    	return response.getResult();
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getStatus());
	    	return -1;
	    }
	}
	
	/**
	 * Request server to start new test run
	 * @param runName
	 * @param setName
	 * @param project
	 * @param version
	 * @param primaryDeviceSerial
	 * @param secondaryDeviceSerial
	 * @param profilingSettings
	 * @param tags
	 * @return Test run id if successfully started; null otherwise
	 */
	public String startRun(
			String runName, 
			String setName, 
			String project, 
			String version, 
			String primaryDeviceSerial, 
			String secondaryDeviceSerial,
			String profilingSettings,
			Map<String, String> tags) {
		
		StartRunRequest request = StartRunRequest.newBuilder()
				.setRunName(runName != null ? runName : "")
				.setSetName(setName != null ? setName : "")
				.setProject(project != null ? project : "")
				.setVersion(version != null ? version : "")
				.setPrimaryDeviceSerial(primaryDeviceSerial != null ? primaryDeviceSerial : "")
				.setSecondaryDeviceSerial(secondaryDeviceSerial != null ? secondaryDeviceSerial : "")
				.setProfilingSettings(profilingSettings != null ? profilingSettings : "")
				.putAllTags(tags != null ? tags : new HashMap<>())
				.build();
		 
	    try {
	    	StartRunResponse response = blockingStub.startRun(request);
	    	return response.getRunId();
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getStatus());
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
	    	log(LogLevel.Warning, "RPC failed: " + e.getStatus());
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
	    	log(LogLevel.Warning, "RPC failed: " + e.getStatus());
	    	return false;
	    }
	}
	
	/**
	 * Notify server about log step
	 * @param runID
	 * @param stepName
	 * @param result
	 * @param takeScreenshot
	 * @return True if successfully notified; false otherwise
	 */
	public boolean onLogStep(
			String runID, 
			String stepName, 
			boolean result, 
			boolean takeScreenshot) {
		
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
				.setTakeScreenshot(takeScreenshot)
				.build();
		
	    try {
	    	blockingStub.onLogStep(request);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getStatus());
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
	    	log(LogLevel.Warning, "RPC failed: " + e.getStatus());
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
	    	log(LogLevel.Warning, "RPC failed: " + e.getStatus());
	    	return false;
	    }
	}
	
	
	/***
	 * 
	 * For implementing a test node for the test cloud -->
	 */
	
	/**
	 * Subscribe to test requests
	 * @return Test requests stream/iterator
	 */
	public Iterator<TestRequest> subscribeToTestRequests() {
		
		Iterator<TestRequest> iterator = null;
		
		try {
			blockingStub.subscribeToTestRequests(Empty.newBuilder().build());
		}  catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getStatus());
	    }
		return iterator;
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
		
		TestRequestAck ack = TestRequestAck.newBuilder()
				.setStatus(status != null ? status : "")
				.setRunId(runID)
				.setResult(result)
				.setFailCause(failCause != null ? failCause : "")
				.addAllLog(log != null ? log : new ArrayList<>())
				.build();
		
	    try {
	    	blockingStub.respondToTestRequest(ack);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getStatus());
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
				.setVariables(variables != null ? variables : "")
				.build();
	
	    try {
	    	blockingStub.addNode(request);
	    	return true;
	    } catch (StatusRuntimeException e) {
	    	log(LogLevel.Warning, "RPC failed: " + e.getStatus());
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
	    	log(LogLevel.Warning, "RPC failed: " + e.getStatus());
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
		    	log(LogLevel.Warning, "RPC failed: " + e.getStatus());
		    	return false;
		    }
	}
	
}
