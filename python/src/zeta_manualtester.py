import argparse
import time
import datetime
import traceback
import json
import os
from step_type import StepType
from connector import Connector
from connector_types import TestType

class ZetaManualTester():
    """Example implementation on how to test manually Zeta"""

    client = None

    testRunName = ""
    testSet = ""
    project = ""
    version = ""
    primaryDeviceId = ""
    primaryDeviceType = ""
    secondaryDeviceId = ""
    secondaryDeviceType = ""
    profilingSettings = ""
    testCases = None

    def __init__(self, testRunName="", 
                testSet="", project="", 
                version="", primaryDeviceId="", 
                primaryDeviceType="", secondaryDeviceId="", 
                secondaryDeviceType="", profilingSettings="", 
                testCases=None):
        #necessary parameters
        self.testSet = testSet
        self.project = project
        self.version = version
        self.primaryDeviceType = primaryDeviceType
        self.profilingSettings = profilingSettings
        #optional parameters
        self.testRunName = testRunName
        self.testCases = testCases
        self.primaryDeviceId = primaryDeviceId
        self.secondaryDeviceId = secondaryDeviceId
        self.secondaryDeviceType = secondaryDeviceType

    #helpers
    def get_bool(self, prompt, default):
        while True:
            try:
                val = {"true":True,"false":False,"y":True,"n":False,"":default}[input(prompt).lower()]
                return val
            except KeyError:
                print("Invalid input please enter True or False! (y/n)")


    def get_item(self, item, data):
        if item is None or data is None:
            return None
        elif item in data:
            return data[item]
        else:
            return None

    #gets correct step value from StepType class
    def get_step(self, stepName):
        if stepName == None:
            return None
        for stepType in StepType:
            if stepType.value == stepName:
                return stepType.value
            elif stepType.name == stepName:
                return stepType.value
    
    #gets step name for prints
    def get_step_name(self, step_value):
        if isinstance(step_value, int):
            for stepType in StepType:
                if stepType.value == step_value:
                    return stepType.name
        else:
            return step_value

    ##check if json is actually a path
    def is_file(self, path):
        if os.path.isfile(path):
            return True
        else:
            return False

    def default_action(self, test_run_id, testCaseId, testGroup):
        testName = input("Use case name: ")
        client.on_use_case_start(test_run_id, testName, testCaseId, testGroup)
        start_time = round(time.time() * 1000)
        success = self.get_bool("Did the test succeed? (y/n)", True)
        fail_causes = None
        if not success:
            fail_causes = input("Test fail cause: ")
        end_time = round(time.time() * 1000) - start_time
        client.on_use_case_end(test_run_id, success, end_time, fail_causes, True)

    #main loop
    def main_loop(self):
        print("=== Starting new test run ===")
        profilingSettings = None
        if self.profilingSettings != "":
            self.profilingSettings = str(self.profilingSettings).replace("\'", "\"")
            if self.is_file(self.profilingSettings):
                profilingSettings = open(self.profilingSettings)
        test_run_id = client.start_run(self.testRunName, self.testSet, self.project, self.version, self.primaryDeviceId, self.primaryDeviceType, self.secondaryDeviceId, self.secondaryDeviceType, profilingSettings, None, "")

        if test_run_id:
            run_test = True
            print("=== Running test %s ===" % self.testRunName)            
            if self.testCases != None:
                if len(self.testCases) == 0:
                    self.default_action(test_run_id,None,None)
                for group in self.testCases:
                    print("=== Running group %s ===" % self.get_item('testGroup', group))
                    #iterate through tests
                    if 'tests' in group:
                        for test in group['tests']:
                            #start test
                            testName = self.get_item('testName', test)
                            if testName == None:
                                testName = input("Insert test case name: ")
                            print("=== Starting test case '{}' (process: '{}') (requirement id: '{}') ===".format(testName, self.get_item('targetProcess', test), self.get_item('requirementId',test)))
                            i = 0
                            client.on_use_case_start(test_run_id, self.get_item('testName', test), 
                                                    self.get_item('testCaseId', group), 
                                                    self.get_item('testGroup', group),
                                                    self.testSet, 
                                                    target_process=self.get_item('targetProcess',test), 
                                                    requirement_id=self.get_item('requirementId',test))
                            start_time = round(time.time() * 1000)
                            fail_causes = ""
                            #iterate steps
                            if 'steps' in test:
                                for step in test['steps']:
                                    i += 1
                                    ##TODO: if profiling settings has stepsSignalStart true, skip setup and teardown
                                    stepType = self.get_item('stepType',step)
                                    stepType = self.get_step(stepType)
                                    print("=== Step {}: {}, type: {} ===".format(str(i), self.get_item('stepName', step), self.get_step_name(stepType)))
                                    client.on_log_step(test_run_id, self.get_item('stepName',step), True, False, stepType)
                                    success = self.get_bool("Did the step succeed? (y/n)", default=True)
                                    if not success:
                                        fail_causes += input("Test fail cause: ")
                                        fail_causes += " | "
                                    ##TODO: create another step for screenshot/possible failure?
                                    
                            #if no steps, ask if test succeeded
                            else:
                                success = self.get_bool("Did the test succeed? (y/n)", True)
                                if not success:
                                    fail_causes = input("Test fail cause: ")
                            #end test
                            end_time = round(time.time() * 1000) - start_time
                    
                            client.on_use_case_end(test_run_id, success, end_time, fail_causes, True)
                    #if tests group is empty
                    else:
                        self.default_action(test_run_id,self.get_item("testCaseId", group), self.get_item("testGroup"))
            else:
                self.default_action(test_run_id,None, None)

            client.stop_run(test_run_id)
            start_new_run = self.get_bool("Start a new test run? (y/n): ", default=False)
            if start_new_run:
                self.main_loop()
            else:
                print("Stopping test")
        else:
            print("Connection to Zeta failed, could not get test run id")

###### main program ######


def is_file(path):
    if os.path.isfile(path):
        return True
    else:
        return False

#parameters for setup part + run + set
if __name__ == "__main__":
    
    #setup

    #read json
    f = open("manual_test_settings.json")
    data = json.loads(f.read())
    f.close()

    #parse arguments
    parser = argparse.ArgumentParser(description="Script for using Zeta with manual testing. Fill manual_test_settings.json with necessary information. "+
                                                "You can use following parameters to override the json file if necessary. For custom address and port, use the parameters.")

    parser.add_argument("-address", help="Connector address (default: localhost)", metavar="address", default="localhost")
    parser.add_argument("-port", help="Connector port (default: 31321)", type=int, metavar="port", default=31321)
    parser.add_argument("-testRunName", help="Test run name")
    parser.add_argument("-testSet", help="Test set name")
    parser.add_argument("-project", help="Project name")
    parser.add_argument("-version", help="Project version, leave empty to use default")
    parser.add_argument("-primaryDeviceId", help="Primary device identifier, leaving empty causes Zeta to pick the first device in alphabetical order")
    parser.add_argument("-primaryDeviceType", help="Primary device type (Android by default)")
    parser.add_argument("-secondaryDeviceId", help="Secondary device identifier (if any)")
    parser.add_argument("-secondaryDeviceType", help="Secondary device type (if any)")
    parser.add_argument("-profilingSettings", help="Path to profiling settings json file")

    args = parser.parse_args()

    #get device ids from profiling settings
    #Profiling settings can either be inside profilingSettings.json or manual_test_settings.json under "profilingSettings"
    profiling = None      
    if is_file(str(data['profilingSettings'])):
        f = open(data['profilingSettings'])
        profiling = json.loads(f.read())
        f.close()
    else:
        profiling = data['profilingSettings']

    # if profiling['deviceSettings'].keys() is not None:
    #     ids = list(profiling['deviceSettings'].keys())
    #     primary_device_id = ids[0]
    #     try:
    #         if ids[1] is not None:
    #             secondary_device_id = ids[1]
    #     except:
    #         secondary_device_id = None

    testRunName = None
    if 'testRunName' in data: testRunName = data['testRunName']
    if args.testRunName != None: testRunName = args.testRunName

    testSet = None
    if 'testSet' in data: testSet = data['testSet']
    if args.testSet != None: testSet = args.testSet
    if testSet == None: 
        print("Missing argument 'testSet'")
        exit()

    project = None
    if 'project' in data: project = data['project']
    if args.project != None: project = args.project

    version = None
    if 'version' in data: version = data['version']
    if args.version != None: version = args.version

    primary_device_id = '*'
    if 'primaryDeviceId' in data: primary_device_id = data['primaryDeviceId']
    if args.primaryDeviceId != None: primary_device_id = args.primaryDeviceId

    primary_device_type = 'Android'
    if 'primaryDeviceType' in data: primary_device_type = data['primaryDeviceType']
    if args.primaryDeviceType != None: primary_device_type = args.primaryDeviceType   

    secondary_device_id = None
    if 'secondaryDeviceId' in data: secondary_device_id = data['secondaryDeviceId']
    if args.secondaryDeviceId != None: secondary_device_id = args.secondaryDeviceId    

    secondary_device_type = None
    if 'secondaryDeviceType' in data: secondary_device_type = data['secondaryDeviceType']
    if args.secondaryDeviceType != None: secondary_device_type = args.secondaryDeviceType    

    print("Primary device id: " + str(primary_device_id))
    print("Secondary device id: " + str(secondary_device_id))      
    
    #create new zeta manual tester based on args and json. if arg is not empty, use it instead of json.
    zeta = ZetaManualTester(testRunName=testRunName,
                            testSet=testSet,
                            project=project,
                            version=version,
                            primaryDeviceId=primary_device_id,
                            primaryDeviceType=primary_device_type,
                            secondaryDeviceId=secondary_device_id,
                            secondaryDeviceType=secondary_device_type,
                            profilingSettings=data['profilingSettings'],
                            testCases=data['testCases'] if 'testCases' in data else None)

    #program
    try:
        client = Connector(args.address, args.port)
        if client.ping() == 1:
            print("Connection to Zeta succesful")
            zeta.main_loop()
        else:
            print("Connection to Zeta failed. Check address and port.")
    except Exception as e:
        print("Error: " + str(e))
    finally:
        if client:
            client.shutdown()


