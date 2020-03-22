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

public enum LogPriority {
	NotAvailable,
	Unknown,
	KernelEmergency,
	KernelAlert,
	KernelCritical,
	KernelError,
	KernelWarning,
	KernelNotice,
	KernelInfo,
	KernelDebug,
	Default,
	Verbose,
	Debug,
	Info,
	Warning,
	Error,
	Fatal,
	Silent;
	
	public int toValue() {
		switch(this) {
			case NotAvailable:
			default:
				return 0;
			case Unknown:
				return 1;
			case KernelEmergency:
				return 2;
			case KernelAlert:
				return 3;
			case KernelCritical:
				return 4;
			case KernelError:
				return 5;
			case KernelWarning:
				return 6;
			case KernelNotice:
				return 7;
			case KernelInfo:
				return 8;
			case KernelDebug:
				return 9;
			case Default:
				return 10;
			case Verbose:
				return 11;
			case Debug:
				return 12;
			case Info:
				return 13;
			case Warning:
				return 14;
			case Error:
				return 15;
			case Fatal:
				return 16;
			case Silent:
				return 17;
		}
	}
			
}
