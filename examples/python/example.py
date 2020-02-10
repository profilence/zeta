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
