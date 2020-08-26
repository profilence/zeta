# Zeta - Selenium integration

This is an example of how Zeta can be integrated into Selenium and Pytest. This will be different with other sequencers, but the Selenium integration is more or less the same. 

## Zeta Integration -files

First, move `zeta_integration.py` and `zeta_webdriver.py` in the same folder where your sequencer is. There is also an example 
sequencer `zeta_test_runner.py` in this repo.

In the sequencer, you’ll want to first create an instance of `ZetaIntegration` class with all the necessary information. Before starting the testrun sequence, assign a client for your Zeta instance by using `zeta.client = zeta.connect().`

Get a new `test_run_id` by starting a run with `zeta.client.start_run(args)`. This will generate a `test_run_id` that every Zeta interaction needs to assign data to the right test run.

Also, check the `zeta_webdriver.py` file for any necessary settings/capabilities for the tested browser. You'll want to at least do the following (These  examples are for Chrome):

 - Add loggingprefs: 
	 - `capabilities['loggingPrefs'] = { 'browser':'ALL', 'performance':'ALL'}`
 - Enable precise memory info: 
	 - `options = webdriver.ChromeOptions()
options.add_argument("--enable-precise-memory-info")`
- Disable W3C compliancy! This is important for getting some of the data and avoid errors: 
	- `options.add_experimental_option('w3c', False)##disable w3c compliancy to get more data`



## Selenium Webdriver

For Selenium webdriver, modify these sections of the code in `selenium\webdriver\remote\webdriver.py`:      

    class  WebDriver(..):
        ...
        zeta = None
        ...
        def __init__(self, command_executor='http://127.0.0.1:4444/wd/hub',
                 desired_capabilities=None, browser_profile=None, proxy=None,
                 keep_alive=False, file_detector=None, options=None, zeta=None):
        ....
        #Zeta integration
        self.zeta = zeta
        if self.zeta is not None:
            self.zeta_client = zeta.client
            self.zeta_test_run_id = zeta.test_run_id
            print("Zeta client: " + str(self.zeta_client))

        .......

**Note**: You'll have to add the same to **each browser** you're testing on! For example, with Chrome `selenium\webdriver\chrome\webdriver.py`:

    def __init__(self, executable_path="chromedriver", port=0,
                     options=None, service_args=None,
                     desired_capabilities=None, service_log_path=None,
                     chrome_options=None, keep_alive=True, zeta=None):
        ....
        #Zeta integration
        self.zeta = zeta
        if self.zeta is not None:
            self.zeta_client = zeta.client
            self.zeta_test_run_id = zeta.test_run_id
            print("Zeta client: " + str(self.zeta_client))

This way Zeta can be passed on to remote WebDriver easily.

When Selenium initializes Webdriver, it can now be given Zeta integration as an argument aswell.

In the `selenium\webdriver\remote\webdriver.py` file, find `execute()` method and modify it. This will make Selenium report what it's doing to Zeta, and any possible errors, in real time.

    def execute(self, driver_command, params=None):
            """
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

#### Screenshots
Continuing on `execute()` method, you can get screenshots of possible errors with `self.get_screenshot_as_png()`. This uses the current webdriver to take a screenshot in byte data (even it says as png). Then, you can directly add this method to 
`self.zeta_client.on_log_step(self.zeta_test_run_id, errormsg,False, self.get_screenshot_as_png())`. No need to save it as a file anywhere, Zeta will read it directly from byte data.

            if response:
                #zeta error handling
                if self.zeta is not None:
                    try:
                        self.error_handler.check_response(response)
                    except Exception as e:
                        errormsg = str(e) + " in " + logtrace
                        print("Exception, try to take screenshot: " + errormsg)
                        self.zeta_client.on_log_step(self.zeta_test_run_id, errormsg,False, False) #show the error message
                        self.zeta_client.on_log_step(self.zeta_test_run_id, errormsg,False, self.get_screenshot_as_png()) #show screenshot of the error
                    else:
                        self.zeta_client.on_log_step(self.zeta_test_run_id,logtrace,True, False)
                else:
                    self.error_handler.check_response(response)
                response['value'] = self._unwrap_value(
                    response.get('value', None))
                return response
            # If the server doesn't send a response, assume the command was
            # a success
            if self.zeta is not None:
                self.zeta_client.on_log_step(self.zeta_test_run_id,logtrace,True, False)
            return {'success': 0, 'value': None, 'sessionId': self.session_id}

Lastly, if you want to get used memory amount and logs, find `close()` method and modify it like this:

    def close(self):
        """
        Closes the current window.

        :Usage:
            driver.close()
        """
#### Memory graph

In the `close()` method, we can update a memory graph each time a test is done. This could also be moved after each action in the `execute()` method, if we want faster updating memory graph.

        if self.zeta is not None:
            #Zeta integration: get used memory amount 
            memory = self.execute_script("return window.performance.memory.usedJSHeapSize")
            self.zeta_client.update_single_system_series(self.zeta_test_run_id, "Memory", time.time(), memory)

            ##TODO: get cpu

#### Device log and events
Device logs and events are also fetched in the `close()` method.

            #Zeta integration: get browser logs
            datas = self.get_log('browser')
            for data in datas:
                datalines = [str(data['level']), str(data['message'])]
                priority = 13
                if str(data['level']) == 'SEVERE':
                    priority = 15
                    print(time.time())
                    self.zeta_client.notify_event(self.zeta_test_run_id,time.time(),8,True,str(data['level']),self.name,str('error'),datalines)
                self.zeta_client.on_device_log(self.zeta_test_run_id,time.time(),priority,1,'',str(data['message']))
        self.execute(Command.CLOSE)

This will get all the logs after the test and report them to Zeta, and raise any possible events. You can add more priorities by checking the `data['level']` and looking for the matching enums in `connector_types.py` file.

## PyTest integration
To get accurate testrun information, such as SetUps, TearDowns and when testrun starts and ends, we'll have to integrate Zeta with PyTest. 

In the sequencer, you can see we give Zeta instance as an argument to `TextTestRunner` method. Runner will move the Zeta object to Suite and Case as the test runs.

    #create test suite from all tests in tests folder
    suite = unittest.TestLoader().discover(start_dir="./tests")
    #give zeta object to testrunner and zeta interfaced webdriver to selenium
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
This will the Zeta instance to the test suite, which will in turn give it to the test case.

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
        #selenium webdriver
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

That's it! If you want to quickly test this out, try running couple Selenium testcases with `zeta_test_runner.py`. **Note: Run zeta_test_runner.py with Python, not PyTest!**

