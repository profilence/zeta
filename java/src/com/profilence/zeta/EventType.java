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

public enum EventType {
	Warning,
	NativeCrash,
	StrictModeViolation,
	TerribleFailure,
	LowMemory,
	AppNativeCrash,
	AppCrash,
	AppNotResponding,
	Watchdog,
	KernelPanic,
	ConsoleRamoops,
	CoreDump,
	Other;
	
	public int toValue() {
		switch(this) {
			case Warning:
				return 1;
			case NativeCrash:
				return 2;
			case StrictModeViolation:
				return 4;
			case TerribleFailure:
				return 8;
			case LowMemory:
				return 16;
			case AppNativeCrash:
				return 32;
			case AppCrash:
				return 64;
			case AppNotResponding:
				return 128;
			case Watchdog:
				return 256;
			case KernelPanic:
				return 16384;
			case ConsoleRamoops:
				return 262144;
			case Other:
			default:
				return 2147483647;
		}
	}
}
