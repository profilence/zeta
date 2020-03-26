Profilence ZETA
====

Profilence Zeta-driver is a cross-platform, multi-language library to access Profilence analytics and cloud services. Profilence Zeta serves two main purposes:

1. Harness your existing test automation/execution system with Profilence analytics, and push all the data to cloud: to be instantly shared with your organization. Zeta has built-in analytic capabilitis for Android and Linux based devices.

2. Easily build a test cloud by publishing any machine as a test node, ready to serve test requests coming from your build system - with your own test automation system.

Core of the project is the proto file for the GRPC service:

*./proto/connector_service.proto* and its dependency proto files

Java client
-----------

Standalone JAR for the client API can be found at
./java/bin/zeta-connector-api-1.0.0.jar. The file bundles all the dependency
libraries as well. The library can imported to a java project, but can also be
executed as such for testing the connection to the Zeta service, i.e:

$ java –jar zeta-connector-api-1.0.0.jar localhost:50051

*./java/src/com/profilence/zeta* -directory: Connector.java is the main
class/file; otherwise the directory contains mostly protoc-generated code. The
project files are currently for eclipse/ant

For proto-code generation, run *./proto_compile/[win-x86_64\|linux\|mac]/java_compile.[bat\|sh]*


Python client
-----------

Python driver for the client API can be found at ./python/src/connector.py. The driver can imported to a python project, but can also be executed as such for testing the connection to the Zeta service, i.e:

$ python connector.py localhost:50051

./python/src -directory: connector.py is the main class/file; otherwise the directory contains mostly protoc-generated code.

For proto-code generation, run ./proto_compile/[win-x86_64|linux|mac]/python_compile.[bat|sh]

Python driver and code generator have dependency to grpcio-tools package ($ pip install grpcio-tools)


Example - Java: integrate analytics to simple sequencer
-----------

```
package demo;

import java.io.File;
import java.util.Random;

import com.profilence.zeta.Connector;
import com.profilence.zeta.ILogger;
import com.profilence.zeta.LogLevel;
import com.profilence.zeta.PingResponseType;

public class ClientExample {

    public static void main(String[] args) throws InterruptedException {
        
        Random rand = new Random(); 
        
        Connector.addLogger(new ILogger() {
            @Override
            public void onLogMessage(LogLevel level, String message) {
                print(message);
            }
        });
        
        Connector client = new Connector("localhost", 31321);
        
        try {
        
            PingResponseType response = client.ping();
            print(response);
            
            if (response == PingResponseType.Ok) {
              
                String testRunName = "demo";
                String testSetName = "dummy";
                String projectName = "AProject";
                String projectVersion = "1.1.1";
                String targetDeviceIdentifier = "emulator-5554";
                String targetDeviceType = "android";
                File profilingSettings = new File("/home/tests/profiling_settings.json");
              
                String testRunID = client.startRun(testRunName, testSetName, projectName, projectVersion, targetDeviceIdentifier, targetDeviceType, null, null, profilingSettings, null);
                print("testRunID: " + testRunID);
                if (testRunID != null) {
                    for(int i = 1; i <= 10; i++) {
                        String useCaseName = "use case " + i;
                        String useCaseID = "id_" + i;
                        client.logTrace(testRunID, "Starting use case " + useCaseName + " from java");
                        client.onUseCaseStart(testRunID, useCaseName, useCaseID, null, null);
                        try {
                            for(int j = 1; j <= 20; j++) {
                                String stepName = "step_" + j;
                                print("case: " + i + "; " + stepName);
                                boolean stepPassed = j % 5 == 0;
                                File screenShot = stepPassed == false ? new File("/home/tests/resources/dummy.png") : null;
                                client.onLogStep(testRunID, stepName, stepPassed, screenShot);
                                Thread.sleep(1000);
                            }
                        } catch(Exception e) {
                        }
                        boolean failed = i % 2 == 0;
                        long activeRunTime = i * Math.max(10, rand.nextInt(10001));
                        String cause = failed ? "Could not find item" : null;
                        boolean intendedReset = false;
                        client.onUseCaseEnd(testRunID, failed, activeRunTime, cause, intendedReset);
                    }
                    print("Successfully stopped test run: " + client.stopRun(testRunID, false));
                }
            }
        } finally {
          client.shutdown();
        }
    }
    
    public static void print(Object obj) {
        System.out.println(obj);
    }
}
```

Example - Python: integrate analytics to simple sequencer
-----------

```
from __future__ import print_function

import random
import time

from connector import Connector

Connector.log = lambda x, y: print(y)

client = None

try:
    client = Connector('localhost', 31312)
    if client.ping() == 1:

        test_run_name = 'demo'
        test_set_name = 'dummy'
        project_name = 'AProject'
        project_version = '1.1.1'
        target_device_identifier = 'emulator-5554'
        target_device_type = 'android'
        profiling_settings = open('/home/tests/profiling_settings.json')

        test_run_id = client.start_run(test_run_name, test_set_name, project_name, project_version,
                                       target_device_identifier, target_device_type, None, None, profiling_settings, None)

        print('testRunID: %s' % test_run_id)

        if test_run_id:
            for i in range(1, 11):
                use_case_name = 'use case %d' % i
                use_case_id = 'id_%d' % i
                client.log_trace(test_run_id, 'Starting use case %s from python' % use_case_name)
                client.on_use_case_start(test_run_id, use_case_name, use_case_id, None, None)
                try:
                    for j in range(1, 21):
                        step_name = 'step_%d' % j
                        print('case: %d; %s' % (i, step_name))
                        step_passed = j % 5 == 0
                        screenshot = open('/home/tests/resources/dummy.png').read() if not step_passed else None
                        client.on_log_step(test_run_id, step_name, step_passed, screenshot)
                        time.sleep(1)
                except:
                    pass
                failed = i % 2 == 0
                activeRunTime = i * random.randint(10, 10000)
                cause = 'Could not find item' if failed else None
                intendedReset = False
                client.on_use_case_end(test_run_id, failed, activeRunTime, cause, intendedReset)

            print('Successfully stopped test run: %s' % client.stop_run(test_run_id))
finally:
    if client:
        client.shutdown()

```

Example - Python: publish a test cloud node
-----------

```
import json
import threading
import time
from connector_types import TestRequestListenerBase


def get_node_properties():
    props = {'demo': True, 'region': 'US', 'operator': 'Sprint', 'product': 'Pixel4', 'platform': 'Android'}
    return json.dumps(props)


class DemoListener(TestRequestListenerBase):

    def __init__(self, cli, test_launcher):
        self.node_name = 'monkey_node'
        self.pool_name = 'monkey_pool'
        self.platform_type = 'Android'
        self.client = cli
        self.launcher = test_launcher
        self.__running = False

    def on_test_start_requested(self, request):
        project = request.project
        version = request.version
        run_id = request.run_id
        print('\nReceived request %s to test version %s for project %s' % (run_id, version, project))
        if self.launcher:
            self.client.respond_to_test_request(run_id, 'Started new monkey run', True, None, None)
            threading.Thread(target=self.launcher, args=[self.client, project, version], daemon=True).start()
        else:
            self.client.respond_to_test_request(run_id, 'Cannot start', False, 'No test runner available', None)

    def heart_beat(self):
        while self.__running:
            self.client.update_node(self.node_name, None, None, None, None, self.pool_name, get_node_properties())
            time.sleep(60)

    def start(self):
        okay = self.client.add_node(self.node_name, self.pool_name, self.platform_type, get_node_properties())
        print('Node added successfully: %s' % okay)
        if okay:
            self.__running = True
            self.client.subscribe_to_test_requests(self)
            threading.Thread(target=self.heart_beat, daemon=True).start()
            input('Press any key to continue....')
            self.__running = False
            self.client.remove_node(self.node_name)
```
see full example from [here](https://github.com/profilence/zeta/blob/master/python/src/examples.py)
