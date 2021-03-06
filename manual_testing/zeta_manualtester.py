import argparse
import time
import datetime
import json
from connector import Connector
from connector_types import TestType

class ZetaManualTester():
    """Used to test devices manually with Zeta"""

    client = None

    test_run_name = ""
    test_set_name = ""
    project_name = ""
    project_version = ""
    target_device_identifier = ""
    target_device_type = ""
    secondary_device_identifier = ""
    secondary_device_type = ""
    profiling_settings_path = ""

    use_case_name = ""
    use_case_id = ""
    use_case_group = ""
    target_process_name = ""
    requirement_id = ""

    def __init__(self, test_run_name="", 
                test_set_name="", project_name="", 
                project_version="", target_device_identifier="", 
                target_device_type="", secondary_device_identifier="", 
                secondary_device_type="", profiling_settings_path="", 
                use_case_name="", use_case_id="", use_case_group="", 
                target_process_name="", requirement_id=""):
        self.test_run_name = test_run_name
        self.test_set_name = test_set_name
        self.project_name = project_name
        self.project_version = project_version
        self.target_device_identifier = target_device_identifier
        self.target_device_type = target_device_type
        self.secondary_device_identifier = secondary_device_identifier
        self.secondary_device_type = secondary_device_type
        self.profiling_settings_path = profiling_settings_path

        self.use_case_name = use_case_name
        self.use_case_id = use_case_id
        self.use_case_group = use_case_group
        self.target_process_name = target_process_name
        self.requirement_id = requirement_id


    #helpers
    def get_bool(self, prompt):
        while True:
            try:
                return {"true":True,"false":False,"y":True,"n":False}[input(prompt).lower()]
            except KeyError:
                print("Invalid input please enter True or False!")

    #usecase loop
    def usecase_loop(self, test_run_id):
        print("===Usecase===")
        client.log_trace(test_run_id, "Starting use case %s" % self.use_case_name)
        start_time = datetime.datetime.now()
        client.on_use_case_start(test_run_id, self.use_case_name, self.use_case_id, self.use_case_group, self.test_set_name, TestType.NORMAL, self.target_process_name, self.requirement_id)
        run_usecase = True
        failed = False
        cause = ""
        
        while run_usecase:
            print("===Running usecase %s===" % self.use_case_name)
            step_name = input("Step name (leave empty to end usecase): ")
            if step_name == "":
                break
            step_passed = self.get_bool("Step succeeded (y/n): ")
            if step_passed == False:
                failed = True
                cause += input("Failure cause: ")
                cause += " | "
            take_screenshot = self.get_bool("Take screenshot (y/n): ")
            client.on_log_step(test_run_id, step_name, step_passed, take_screenshot)
            time.sleep(1)
            run_usecase = self.get_bool("Add another step? (y/n): ")
        end_time = datetime.datetime.now()
        time_diff = (end_time - start_time)
        execution_time = time_diff.total_seconds() * 1000
        client.on_use_case_end(test_run_id, failed, execution_time, cause, False)


    #main test loop
    def main_loop(self):
        print("===Starting new test run===")
        self.test_run_name =str(time.time())+"-"+self.test_run_name
        profiling_settings = None
        if (self.profiling_settings_path != ""):
            profiling_settings = open(self.profiling_settings_path)
        test_run_id = client.start_run(self.test_run_name, self.test_set_name, self.project_name, self.project_version, self.target_device_identifier, self.target_device_type, self.secondary_device_identifier, self.secondary_device_type, profiling_settings, None, "")

        if test_run_id:
            run_test = True
            print("===Running test %s===" % self.test_run_name)
            while run_test:
                self.usecase_loop(test_run_id=test_run_id)
                run_test = self.get_bool("Continue running this test run? (y/n): ")
                time.sleep(1)
            
            client.stop_run(test_run_id)
            start_new_run = self.get_bool("Start new test run? (y/n): ")
            if start_new_run:
                self.main_loop()
            else:
                print("Stopping test")

#parameters for setup part + run + set
if __name__ == "__main__":
    
    #setup

    #read json
    f = open("manualtest_settings.json")
    data = json.loads(f.read())
    f.close()

    #parse arguments
    parser = argparse.ArgumentParser(description="Script for using Zeta with manual testing. Fill manualtest_settings.json with necessary information. "+
                                                "You can use following parameters to override the json file if necessary. For custom address and port, use the parameters.")

    parser.add_argument("-address", help="Connector address (default: localhost)", metavar="address", default="localhost")
    parser.add_argument("-port", help="Connector port (default: 31321)", type=int, metavar="port", default=31321)
    parser.add_argument("-test_run_name", help="Test run name")
    parser.add_argument("-test_set_name", help="Test set name")
    parser.add_argument("-project_name", help="Project name")
    parser.add_argument("-project_version", help="Project version, leave empty to use default")
    parser.add_argument("-target_device_identifier", help="Target device identifier")
    parser.add_argument("-target_device_type", help="Target device type (android by default)")
    parser.add_argument("-secondary_device_identifier", help="Secondary device identifier (if any)")
    parser.add_argument("-secondary_device_type", help="Secondary device type (if any)")
    parser.add_argument("-profiling_settings_path", help="Path to profiling settings")
    parser.add_argument("-use_case_name", help="Use case name")
    parser.add_argument("-use_case_id", help="Use case id")
    parser.add_argument("-use_case_group", help="Use case group")
    parser.add_argument("-target_process_name", help="Target process name")
    parser.add_argument("-requirement_id", help="Requirement id")

    args = parser.parse_args()

    #create new zeta manual tester based on args and json. if arg is not empty, use it instead of json.
    zeta = ZetaManualTester(test_run_name=data['test_run_name'] if args.test_run_name == None else args.test_run_name, 
                            test_set_name=data['test_set_name'] if args.test_set_name == None else args.test_set_name, 
                            project_name=data['project_name'] if args.project_name == None else args.project_name, 
                            project_version=data['project_version'] if args.project_version == None else args.project_version, 
                            target_device_identifier=data['target_device_identifier'] if args.target_device_identifier == None else args.target_device_identifier, 
                            target_device_type=data['target_device_type'] if args.target_device_type == None else args.target_device_type, 
                            secondary_device_identifier=data['secondary_device_identifier'] if args.secondary_device_identifier == None else args.secondary_device_identifier, 
                            secondary_device_type=data['secondary_device_type'] if args.secondary_device_type == None else args.secondary_device_type, 
                            profiling_settings_path=data['profiling_settings_path'] if args.profiling_settings_path == None else args.profiling_settings_path, 
                            use_case_name=data['use_case_name'] if args.use_case_name == None else args.use_case_name, 
                            use_case_id=data['use_case_id'] if args.use_case_id == None else args.use_case_id, 
                            use_case_group=data['use_case_group'] if args.use_case_group == None else args.use_case_group, 
                            target_process_name=data['target_process_name'] if args.target_process_name == None else args.target_process_name, 
                            requirement_id=data['requirement_id'] if args.requirement_id == None else args.requirement_id)


    #program
    try:
        client = Connector(args.address, args.port)
        if client.ping() == 1:
            print("Connection to Zeta succesful!")
            zeta.main_loop()
        else:
            print("Connection to Zeta failed. Check address and port")
    finally:
        if client:
            client.shutdown()

    

