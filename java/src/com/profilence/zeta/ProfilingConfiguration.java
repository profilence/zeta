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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfilingConfiguration {
	
	public class HPROFArgumentPair {
		public String processName;
		public boolean includeSubprocesses;
	}
	
	public class DumpsysServiceArgumentPair {
		public String service;
		public String arguments;
	}
	
	public boolean cpuLoad = true;
	public boolean cpuStates = true;
	public boolean powerMeasure = true;
	public boolean batteryStats = true;
	public boolean frameStats = false;
	public boolean cpuLoadPerCore = false;
	public boolean netstats = false;
	public boolean diskUsage = false;
	public boolean thermal = true;
	public boolean gpuLoad = false;
	public boolean gpuStates = false;
	public boolean lightMemoryProfiling = true;
	public boolean processMemoryProfiling = false;
	public boolean systemMemoryProfiling = false;
	public boolean slabs = false;
	public boolean deviceLog = true;
	public boolean device2Log = false;
	public boolean events = true;
	public boolean resets = true;
	
	public final List<HPROFArgumentPair> dumpHeaps;
	public final List<DumpsysServiceArgumentPair> dumpServices;
	public final Map<String, Map<String, Object>> deviceSettings;
	
	public ProfilingConfiguration() {
		dumpHeaps = new ArrayList<>();
		dumpServices = new ArrayList<>();
		deviceSettings = new HashMap<>();
	}
	
	public void addDeviceSetting(String deviceID, String key, Object value) {
		if (deviceSettings.containsKey(deviceID) == false) {
			deviceSettings.put(deviceID, new HashMap<String, Object>());
		}
		deviceSettings.get(deviceID).put(key, value);
	}
	
	public void addDumpsysService(String serviceName, String arguments) {
		DumpsysServiceArgumentPair item = new DumpsysServiceArgumentPair();
		item.service = serviceName;
		item.arguments = arguments;
		dumpServices.add(item);
	}
	
	public void addDumpHeapProcess(String processName, boolean includeSubprocesses) {
		HPROFArgumentPair item = new HPROFArgumentPair();
		item.processName = processName;
		item.includeSubprocesses = includeSubprocesses;
		dumpHeaps.add(item);
	}
	
	public String toJson() {
		
		StringBuilder bob = new StringBuilder("{ ");
		addJsonValue(bob, "cpuLoad", cpuLoad, false);
		addJsonValue(bob, "cpuStates", cpuStates, false);
		addJsonValue(bob, "powerMeasure", powerMeasure, false);
		addJsonValue(bob, "batteryStats", batteryStats, false);
		addJsonValue(bob, "frameStats", frameStats, false);
		addJsonValue(bob, "cpuLoadPerCore", cpuLoadPerCore, false);
		addJsonValue(bob, "netstats", netstats, false);
		addJsonValue(bob, "diskUsage", diskUsage, false);
		addJsonValue(bob, "thermal", thermal, false);
		addJsonValue(bob, "gpuLoad", gpuLoad, false);
		addJsonValue(bob, "gpuStates", gpuStates, false);
		addJsonValue(bob, "lightMemoryProfiling", lightMemoryProfiling, false);
		addJsonValue(bob, "processMemoryProfiling", processMemoryProfiling, false);
		addJsonValue(bob, "systemMemoryProfiling", systemMemoryProfiling, false);
		addJsonValue(bob, "slabs", slabs, false);
		addJsonValue(bob, "deviceLog", deviceLog, false);
		addJsonValue(bob, "device2Log", device2Log, false);
		addJsonValue(bob, "events", events, false);
		addJsonValue(bob, "resets", resets, false);
		
		StringBuilder dumpsysBob = new StringBuilder("\"dumpServices\" : [ ");
		for(DumpsysServiceArgumentPair dp : dumpServices) {
			dumpsysBob.append("{ ");
			addJsonValue(dumpsysBob, "service", dp.service, false);
			addJsonValue(dumpsysBob, "arguments", dp.arguments, true);
			dumpsysBob.append(" }");
			if (dumpServices.indexOf(dp) < dumpServices.size() - 1) {
				dumpsysBob.append(", ");
			}
		}
		dumpsysBob.append(" ], ");
		bob.append(dumpsysBob.toString());
		
		StringBuilder hprofBob = new StringBuilder("\"dumpHeaps\" : [ ");
		for(HPROFArgumentPair hp : dumpHeaps) {
			hprofBob.append("{ ");
			addJsonValue(hprofBob, "processName", hp.processName, false);
			addJsonValue(hprofBob, "includeSubprocesses", hp.includeSubprocesses, true);
			hprofBob.append(" }");
			if (dumpHeaps.indexOf(hp) < dumpHeaps.size() - 1) {
				hprofBob.append(", ");
			}
		}
		hprofBob.append(" ], ");
		bob.append(hprofBob.toString());
		
		StringBuilder devBob = new StringBuilder("\"deviceSettings\" : { ");
		int i = 0;
		for(String deviceId : deviceSettings.keySet()) {
			devBob.append("\"" + deviceId + "\" : {");
			
			Map<String, Object> props = deviceSettings.get(deviceId);
			
			if (props != null) {
				int j = 0;
				for(String key : props.keySet()) {
					addJsonValue(devBob, key, props.get(key), (j == props.size() - 1));
					j++;
				}
			}
			devBob.append(" }");
			if (i < deviceSettings.size() - 1) {
				devBob.append(", ");
			}
			i++;
		}
		
		devBob.append(" }");
		bob.append(devBob.toString());
		
		bob.append(" }");
		return bob.toString();
	}
	
	private void addJsonValue(StringBuilder bob, String key, Object value, boolean isLast) {
		bob.append("\"" + key +  "\" : ");
		if (value instanceof String) {
			value = "\"" + value + "\"";
		}
		else if (value == null) {
			value = "null";
		}
		else if (value != null && value.equals(true)) {
			value = "true";
		}
		else if (value != null && value.equals(false)) {
			value = "false";
		}
		bob.append(value);
		if (isLast == false) {
			bob.append(", ");
		}
	}
}
