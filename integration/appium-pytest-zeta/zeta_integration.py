import re
import sys
from connector import Connector
from connector_types import PingResponseType, TestRequestListenerBase, TestType

#Zeta default address
#address = '127.0.0.1'
#port = 31321


class ZetaIntegration():

    def __init__(self,
                test_run_name, 
                test_set_name,
                project_name,
                project_version,
                primary_device_serial,
                primary_device_type,
                address='127.0.0.1', 
                port=31321, 
                test_case_group_name="",
                secondary_device_serial="",
                secondary_device_type="", 
                profiling_settings=None,
                tags=None,
                use_case_id='0',
                test_type=0, 
                target_process=None,
                requirement_id=None):
        """Initializes all data for Zeta and holds it for easier use.

        Args:
            test_run_name (str): Name of the test run
            test_set_name (str): Name of the test set
            project_name (str): Name of project
            project_version (str): Project version
            primary_device_serial (str): Device serial/ID, such as 'emulator-5554' 
            primary_device_type (str): Device type, such as 'Android'
            address (str, optional): Zeta connection address. Defaults to '127.0.0.1'.
            port (int, optional): Zeta connection port. Defaults to 31321.
            test_case_group_name (str, optional): Name of the test case group. Defaults to "".
            secondary_device_serial (str, optional): Secondary device serial/ID. Leave empty if no secondary device.
            secondary_device_type (str, optional): Secondary device type. Leave empty if no secondary device.
            profiling_settings (str/TextIOBase, optional): Profiling settings as JSON string, or file handle to JSON file. 
            tags (dict, optional): Tags for the test run. Defaults to None.
            use_case_id (str, optional): Used for use case ID's. If you don't have any use case ID's, keep this 0.
            test_type (int, optional): Type of the use case: Normal, Precondition, PostCondition (Normal by default)
            target_process (str, optional): Name of a process to monitor more closely during the use/test case. Defaults to None.
            requirement_id (str, optional): ID of requirement this use/test case verifies. Defaults to None.
        """
        self.client = None
        self.test_run_id = ""
        self.address = address
        self.port = port
        self.test_run_name = test_run_name
        self.test_set_name = test_set_name
        self.test_case_group_name = test_case_group_name
        self.project_name = project_name
        self.project_version = project_version
        self.primary_device_serial = primary_device_serial
        self.primary_device_type = primary_device_type
        self.secondary_device_serial = secondary_device_serial
        self.secondary_device_type = secondary_device_type
        self.profiling_settings = profiling_settings
        self.tags = tags
        self.use_case_id = use_case_id
        self.test_type = test_type
        self.target_process = target_process
        self.requirement_id = requirement_id

    def connect(self):
        """Connect to zeta"""
        Connector.log = lambda x, y: print(y)
        print('Connecting to %s:%d ...' % (self.address, self.port))
        self.client = None
        try:
            self.client = Connector(self.address, self.port)
            print('Pinging ..')
            if self.client.ping() == PingResponseType.OK:
                print('Successfully pinged the service')
            else:
                print('Failed to ping the service')
        finally:
            return self.client

    def disconnect(self):
        """Disconnect from zeta"""
        if self.client:
            print('Shutting down ...')
            self.client.shutdown()


if __name__ == '__main__':
    #Test zeta connection
    z = ZetaIntegration('127.0.0.1',31321)
    z.connect()
    z.disconnect()