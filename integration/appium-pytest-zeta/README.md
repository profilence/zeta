﻿# Zeta - Appium/Pytest integration

This is an example of how Zeta can be integrated into Appium and Pytest. This will be different with other sequencers, but the Appium integration is more or less the same.

**Note**: You should disable device log capture from Appium, since Zeta does its own device log capturing. For disabling Appium logcat you can use `desired_caps['skipLogcatCapture'] = 'true'` and `desired_caps['skipLogCapture'] = 'true'` when giving Appium webdriver capabilities.

## Zeta Integration -files

First, move `zeta_integration.py` and `zeta_webdriver.py` in the same folder where your sequencer is. There is also an example 
sequencer `zeta_test_runner.py` in this repo.

In the sequencer, you’ll want to first create an instance of `ZetaIntegration` class with all the necessary information. Before starting the testrun sequence, assign a client for your Zeta instance by using `zeta.client = zeta.connect().`

Get a new `test_run_id` by starting a run with `zeta.client.start_run(args)`. This will generate a `test_run_id` that every Zeta interaction needs to assign data to the right test run.

## Appium Webdriver

For Appium webdriver, modify these sections of the code in `appium\webdriver\webdriver.py`:      

    class  WebDriver(..):
        def __init__(self, command_executor: str = 'http://127.0.0.1:4444/wd/hub',
                     desired_capabilities: Optional[Dict] = None, browser_profile: str = None, proxy: str = None, keep_alive: bool = True, direct_connection: bool = False, zeta=None):
            #Zeta integration, has to be before super
            self.zeta = zeta
            if self.zeta is not None:
                self.zeta_client = zeta.client
                self.zeta_test_run_id = zeta.test_run_id
                print("Zeta client: " + str(self.zeta_client))
        .......

When Appium initializes Webdriver, it can now be given Zeta integration as an argument aswell.

In the same file, insert this block of code inside the `WebDriver` class. It most likely doesn't exist in your Appium webdriver, since it uses the Selenium webdriver `execute()` method. However, this will override it.

    
    def execute(self, driver_command, params=None):
        """
        Override for Zeta integration
        Sends a command to be executed by a command.CommandExecutor.

        :Args:
            - driver_command: The name of the command to execute as a string.
            - params: A dictionary of named parameters to send with the command.

        :Returns:
            The command's JSON response loaded into a dictionary object.
        """
        if self.session_id is not None:
            if not params:
                params = {'sessionId': self.session_id}
            elif 'sessionId' not in params:
                params['sessionId'] = self.session_id
        #zeta integration: trace log
        if self.zeta is not None:
            logtrace = "Execute: " + str(driver_command)+ " with params: " + str(params)
            print(logtrace +"\n")
            self.zeta_client.log_trace(self.zeta_test_run_id,str(logtrace))
        params = self._wrap_value(params)
        response = self.command_executor.execute(driver_command, params)
        if response:
            #zeta error handling
            if self.zeta is not None:
                try:
                    self.error_handler.check_response(response)
                except Exception as e:
                    errormsg = str(e) + " in " + logtrace
                    print(errormsg)
                    self.zeta_client.on_log_step(self.zeta_test_run_id, errormsg,False, False)
                    self.zeta_client.on_log_step(self.zeta_test_run_id, errormsg,False, True)
                else:
                    self.zeta_client.on_log_step(self.zeta_test_run_id,logtrace,True, False)
            else:
                self.error_handler.check_response(response)
            response['value'] = self._unwrap_value(
                response.get('value', None))
            return response
        # If the server doesn't send a response, assume the command was
        # a success
        return {'success': 0, 'value': None, 'sessionId': self.session_id}

It is identical to the Selenium Webdriver code, but there's few rows inserted for Zeta. These commands will report what Appium is doing to Zeta in real time.

## PyTest integration
To get accurate testrun information, such as SetUps, TearDowns and when testrun starts and ends, we'll have to integrate Zeta with PyTest. 

In the sequencer, you can see we give Zeta instance as an argument to `TextTestRunner` method. Runner will move the Zeta object to Suite and Case as the test runs.

    #create test suite from all tests in tests folder
    suite = unittest.TestLoader().discover(start_dir="./tests")
    #give zeta object to testrunner and zeta interfaced webdriver to appium
    testcase = unittest.TextTestRunner(verbosity=2, zeta=zeta, webdriver=ZetaWebDriver(zeta=zeta)).run(suite)

You'll have to add Zeta as a parameter to these three files: `runner.py`, `suite.py` and `case.py`. These files are in `unittest` folder.

### runner.py

In runner file, modify these code blocks. First, add `zeta` and `webdriver` parameters to the `TextTestRunner` class ``__init__`` method as parameters. Then add the `Zeta integration` part. These will give your runner Zeta `client` and `test_run_id` to work with.

    class TextTestRunner(object):
    
    ....
    
    def __init__(self, stream=None, descriptions=True, verbosity=1,
                 failfast=False, buffer=False, resultclass=None, warnings=None,
                 *, tb_locals=False, zeta=None, webdriver=None):
       
       .........
       
        #Zeta integration
        self.zeta = zeta #used for getting the class and its tools
        if self.zeta is not None:
            self.zetaclient = zeta.client #the client that is running the connection
            self.test_run_id = zeta.test_run_id #test run id that specifies the run
        self.webdriver = webdriver

In the `run()` method, do following changes. 
This will add the Zeta instance to the test suite, which will in turn give it to the test case.

    with warnings.catch.warnings():
    ....
        try:
            test(result, zeta=self.zeta, webdriver=self.webdriver) #zeta integration: runs the test case/suite
    
The last bit is optional: You can add `log_trace` to everything you want to log after pytest has done it's run, so it'll print a summary of information what pytest collects.

For example:

	    if infos:
	    self.stream.writeln(" (%s)" % (", ".join(infos),))
	    #Zeta: log all the possible information of all the test suite data at the end of test
	    if self.zeta is not None:
	        for item in infos:
	            self.zetaclient.log_trace(self.test_run_id, str(item))

This will print small summary of, for example, how many errors were found during the whole test to the trace logs.

### suite.py

In Suite file, we don't have to add many changes. The Zeta instance and Zeta Webdriver are just passed to the case file through the suite. This way every testcase in the suite gets the Zeta integration, without having to modify the actual test files.

First modify the `run()` method in `BaseTestSuite` class. This way we ensure Zeta will be integrated to the case files, even when running very basic test suites.

    class  BaseTestSuite(object):
    ...
    def run(self, result, zeta=None, webdriver=None):
        for index, test in enumerate(self):
            if result.shouldStop:
                break
            test(result, zeta=zeta, webdriver=webdriver)
            if self._cleanup:
                self._removeTestAtIndex(index)
        return result

Second, modify the `run()` in `TestSuite(BaseTestSuite)` class. Here we do same thing as above, but this is the class the example uses.

    
    class TestSuite(BaseTestSuite):
    ....
           def run(self, result, debug=False, zeta=None, webdriver=None):
           ....
            for index, test in enumerate(self):
            ....
                if not debug:
                    test(result, zeta=zeta, webdriver=webdriver) #zeta: this is run method in testcase class, not init
                else:
                    test.debug()

### case.py

The `TestCase` class is where Zeta reports when a testcase starts, stops and runs SetUp and TearDown methods. This is also where we can give the ZetaWebDriver to the test scripts.

First, add `driver = None` to the start of the `TestCase` class.

    class  TestCase(object):
    ....
	    driver = None

Then modify both `_callSetUp` and `_callTearDown` methods to report to Zeta when they start and stop.

**_callSetUp()**
    
    #zeta integration: mention setup in logs
    def _callSetUp(self):
        if self.zeta is not None:
            start_setup = '=== Start SetUp ==='
            print(start_setup)
            self.zetaclient.log_trace(self.test_run_id, start_setup)
            self.zetaclient.on_log_step(self.test_run_id, start_setup, True, False)
        self.setUp()
        if self.zeta is not None:
            end_setup = '=== End SetUp ==='
            print(end_setup)
            self.zetaclient.log_trace(self.test_run_id, end_setup)
            self.zetaclient.on_log_step(self.test_run_id, end_setup, True, False)

**_callTearDown()**

        #zeta integration: mention teardown in logs
    def _callTearDown(self):
        if self.zeta is not None:
            start_teardown = '=== Start TearDown ==='
            print(start_teardown)
            self.zetaclient.log_trace(self.test_run_id, start_teardown)
            self.zetaclient.on_log_step(self.test_run_id, start_teardown, True, False)
        self.tearDown()
        if self.zeta is not None:
            end_teardown = '=== End TearDown ==='
            print(end_teardown)
            self.zetaclient.log_trace(self.test_run_id, end_teardown)
            self.zetaclient.on_log_step(self.test_run_id, end_teardown, True, False)

Finally, in `run()` method you'll have to add Zeta and Webdriver as parameters, start the usecase with the `test_run_id` in the Zeta instance and also end it and log all possible errors. You'll also want to `import time` so you can log how long the test took.

    
    def run(self, result=None, zeta=None, webdriver=None):
        #appium webdriver
        if webdriver is not None:
            self.driver = webdriver.Connect()
            print(str(self.driver))

        #zeta integration
        if zeta is not None:
            self.zeta = zeta
            self.zetaclient = self.zeta.client
            self.test_run_id = self.zeta.test_run_id
        ....
        startTime = time.perf_counter() #start timer for test
        
        result.startTest(self)

        testMethod = getattr(self, self._testMethodName)

        #Zeta: Get test name and log it
        if self.zeta is not None:
            self.zetaclient.log_trace(self.test_run_id, 'Starting test case %s ' % self._testMethodName)
            self.zetaclient.on_use_case_start(self.test_run_id, 
                                                self._testMethodName, 
                                                self.zeta.use_case_id, 
                                                self.zeta.test_case_group_name,
                                                self.zeta.test_set_name,
                                                self.zeta.test_type,
                                                self.zeta.target_process,
                                                self.zeta.requirement_id)
                                                
       try:
       ....
       finally:
            stopTime = time.perf_counter() #stop timer
            timeTaken = stopTime - startTime #calculate time
            #Zeta: end testcase
            if self.zeta is not None:
                time_in_ms = timeTaken * 1000 #get time in ms for Zeta
                if outcome.success: 
                    self.zetaclient.on_use_case_end(self.test_run_id,outcome.success,int(time_in_ms),"",False)
                else:
                    #Zeta: Add errors to use case end in readable format.
                    errorText = "Error: "
                    for er in outcome.errors:
                        #get the last item in a tuple which is the error message
                        if er[-1] is not None:
                            txt = str(er[-1]) + ". "
                            errorText += str(txt)
                    self.zetaclient.on_use_case_end(self.test_run_id,outcome.success,int(time_in_ms),errorText,False)
          .....

### Conclusion

That's it! If you want to quickly test this out, try running couple Appium testcases with `zeta_test_runner.py`. **Note: Run zeta_test_runner.py with Python, not PyTest!**
