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

public enum SourceBuffer {
	Main,
	Radio,
	Events,
	System,
	Kernel,
	Marker;
	
	public int toValue() {
		switch(this) {
			case Main:
			default:
				return 0x01;
			case Radio:
				return 0x02;
			case Events:
				return 0x04;
			case System:
				return 0x08;
			case Kernel:
				return 0x10;
			case Marker:
				return 0x20;
		}
	}
}
