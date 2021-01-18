import argparse
import time
import datetime
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
    
    def ask_input(self, variable, prompt):
        if variable == "":
            return input(prompt)

    #setup
    def setup(self):
        print("===Setup===")
        self.project_name = self.ask_input(variable=self.project_name,prompt="Project name: ")
        self.project_version = self.ask_input(variable=self.project_version, prompt="Project version (leave empty to use default): ")
        self.target_device_identifier = self.ask_input(variable=self.target_device_identifier, prompt="Target device identifier: ")
        self.target_device_type = self.ask_input(variable=self.target_device_type, prompt="Target device type (android/ios/etc..): ")
        self.secondary_device_identifier = self.ask_input(variable=self.secondary_device_identifier, prompt="Secondary device identifier (if any): ")
        if self.secondary_device_identifier == "":
            self.secondary_device_identifier = None
            self.secondary_device_type = None
        else:
            self.secondary_device_type = self.ask_input(variable=self.secondary_device_identifier, prompt="Secondary device type (if any, android/ios/etc..): ")
        self.profiling_settings_path = self.ask_input(variable=self.profiling_settings_path, prompt="Path to profiling settings, if any (example: /home/tests/profiling_settings.json): ")

    #usecase loop
    def usecase_loop(self, test_run_id):
        print("===Usecase===")
        self.use_case_name = self.ask_input(variable=self.use_case_name, prompt="Use case name: ")
        self.use_case_id = self.ask_input(variable=self.use_case_id, prompt="Use case id: ")
        self.use_case_group = self.ask_input(variable=self.use_case_group, prompt="Use case group: ")
        self.target_process_name = self.ask_input(variable=self.target_process_name, prompt="Target process name: ")
        self.requirement_id = self.ask_input(variable=self.requirement_id, prompt="Requirement id: ")
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
        self.test_run_name = self.ask_input(variable=self.test_run_name, prompt="Test run name: ")
        self.test_set_name = self.ask_input(variable=self.test_set_name, prompt="Test set name: ")

        test_run_id = client.start_run(self.test_run_name, self.test_set_name, self.project_name, self.project_version, self.target_device_identifier, self.target_device_type, self.secondary_device_identifier, self.secondary_device_type, open(self.profiling_settings_path), None, "")

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
    
    parser = argparse.ArgumentParser()

    parser.add_argument("-address", help="Connector address", metavar="address", default="localhost")
    parser.add_argument("-port", help="Connector port", type=int, metavar="port", default=31321)
    parser.add_argument("-test_run_name", help="Test run name", default="")
    parser.add_argument("-test_set_name", help="Test set name", default="")
    parser.add_argument("-project_name", help="Project name", default="")
    parser.add_argument("-project_version", help="Project version, leave empty to use default", default=None)
    parser.add_argument("-target_device_identifier", help="Target device identifier", default="")
    parser.add_argument("-target_device_type", help="Target device type (android by default)", default="android")
    parser.add_argument("-secondary_device_identifier", help="Secondary device identifier (if any)", default=None)
    parser.add_argument("-secondary_device_type", help="Secondary device type (if any)", default=None)
    parser.add_argument("-profiling_settings_path", help="Path to profiling settings", default="")
    parser.add_argument("-use_case_name", help="Use case name", default="")
    parser.add_argument("-use_case_id", help="Use case id", default="")
    parser.add_argument("-use_case_group", help="Use case group", default="")
    parser.add_argument("-target_process_name", help="Target process name", default="")
    parser.add_argument("-requirement_id", help="Requirement id", default="")

    
    args = parser.parse_args()

    zeta = ZetaManualTester(test_run_name=args.test_run_name, 
                            test_set_name=args.test_set_name, project_name=args.project_name, 
                            project_version=args.project_version, target_device_identifier=args.target_device_identifier, 
                            target_device_type=args.target_device_type, secondary_device_identifier=args.secondary_device_identifier, 
                            secondary_device_type=args.secondary_device_type, profiling_settings_path=args.profiling_settings_path, 
                            use_case_name=args.use_case_name, use_case_id=args.use_case_id, use_case_group=args.use_case_group, 
                            target_process_name=args.target_process_name, requirement_id=args.requirement_id)

    #program
    try:
        client = Connector(args.address, args.port)
        if client.ping() == 1:
            print("Connection to Zeta succesful!")
            zeta.setup()
            zeta.main_loop()
        else:
            print("Connection to Zeta failed. Check address and port")
    finally:
        if client:
            client.shutdown()

    

