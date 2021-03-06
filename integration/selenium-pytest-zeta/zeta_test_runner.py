import unittest
import sys
import zeta_integration
import inspect
import webhook_listener
from zeta_webdriver import ZetaWebDriver
from connector_types import PingResponseType, TestRequestListenerBase, TestType
from selenium import webdriver

"""
    This is an example test runner/sequencer, to show how to interface Pytest testcases and Selenium Webdriver with Zeta.

    1. Initialize Zeta, then try connecting it. If connection is succesful, we go forward.
    2. Generate Test Run ID. 
    3. Load testsuite by loading all the test cases from a folder.
    4. Run pytest testsuite X times. Give Zeta to both Pytest and Selenium.
    5. Stop the test

    Zeta is injected into the test suite class, where it travels to the test case class to keep up with the setups and teardowns.

    Webdriver is in its own class so it can be easily given the same information between tests, instead of writing it for every test separately. It also makes it easier to give webdriver zeta, without having to modify the test scripts at all.

    Run this script with Python, not Pytest!

"""

##TODO: give tests separate groups, testsets etc. Somekind of property for test scripts, that zeta looks for?

browser = 'Chrome'

if __name__ == '__main__':
    total_runs = 2 #How many times the suite is run.

    #Zeta integration class
    zeta = zeta_integration.ZetaIntegration(test_run_name='zeta_selenium_test', 
                                test_set_name='test_selenium_set',
                                test_case_group_name='test_selenium_test_case',
                                project_name='test_project',
                                project_version='321321',
                                primary_device_serial='',
                                primary_device_type=browser)
    
    zeta.client = zeta.connect()
    print(zeta.test_run_name +  " " + zeta.test_set_name +  " " + zeta.project_name)
    if zeta.client.ping() == 1:
        #start test run with specific parameters
        zeta.test_run_id = zeta.client.start_run(zeta.test_run_name, 
                                                zeta.test_set_name, 
                                                zeta.project_name, 
                                                zeta.project_version, 
                                                zeta.primary_device_serial, 
                                                zeta.primary_device_type, 
                                                zeta.secondary_device_serial, 
                                                zeta.secondary_device_type, 
                                                zeta.profiling_settings, 
                                                zeta.tags, 
                                                zeta.test_run_id)
        print('Test run id: %s' % zeta.test_run_id)
        #run test suite x times
        for i in range(0,total_runs):
            #create test suite from all tests in tests folder
            suite = unittest.TestLoader().discover(start_dir="./tests")
            #give zeta object to testrunner and zeta interfaced webdriver to Selenium
            testcase = unittest.TextTestRunner(verbosity=2, zeta=zeta, webdriver=ZetaWebDriver(zeta=zeta, browser=browser)).run(suite)
        print("The test run id was: " + str(zeta.test_run_id))
        zeta.client.stop_run(zeta.test_run_id)
        zeta.disconnect()