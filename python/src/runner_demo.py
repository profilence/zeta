import threading
import time
import random
from subprocess import Popen, PIPE

__copyright__ = "Copyright 2020 Profilence"
__license__ = "Apache License, Version 2.0"


class DemoRunner:

    def __init__(self, device_id):
        self.device_id = device_id

    def __spank(self, pkg, interactions, throttle, client, run_id):
        success = True
        try:
            arguments = ['adb', '-s', self.device_id, 'shell', 'monkey', '-p', pkg, '--ignore-security-exceptions',
                         '--throttle', str(throttle), str(interactions)]
            process = Popen(arguments, stdout=PIPE, stderr=PIPE)

            for line in iter(process.stdout.readline, ''):
                if not line:
                    break
                if len(line):
                    if isinstance(line, bytes):
                        line = line.decode('utf-8')
                    line = line.rstrip()
                    client.log_trace(run_id, line)
                    print(line)
                    if success and (line.__contains__('** Monkey aborted') or
                                    line.__contains__('// CRASH:') or
                                    line.__contains__('** System appears to have crashed')):
                        success = False
        except Exception as e:
            success = False

        return success

    def __monkey_spank(self, pkg, client, run_id):
        interaction_counts = [1000, 2000, 3000]
        throttles = [10, 20, 50]
        interaction_count = interaction_counts[random.randint(0, len(interaction_counts) - 1)]
        throttle = throttles[random.randint(0, len(throttles) - 1)]

        if interaction_count == interaction_counts[len(interaction_counts) - 1]:
            throttle = 0

        start = time.time()
        success = self.__spank(pkg, interaction_count, throttle, client, run_id)
        run_time = time.time() - start
        return {'success': success, 'runTime': run_time}

    def run_monkey(self, client, run_id, project_name, project_version):
        run_id = client.start_run('monkey', 'monkey_goes_bananas', project_name, project_version, self.device_id,
                                  'android', None, None, None, None, run_id)
        print('Test run id: %s' % run_id)
        if run_id:
            client.respond_to_test_request(run_id, 'Started new monkey run', True, None, None)
            for i, pkg in enumerate(
                    ['com.android.calculator2', 'com.android.contacts', 'com.google.android.deskclock']):
                use_case_name = 'Monkey goes bananas with %s' % pkg
                use_case_id = 'id_%d' % i
                print(use_case_name)
                client.log_trace(run_id, 'Starting use case %s from python' % use_case_name)
                client.on_use_case_start(run_id, use_case_name, use_case_id, None, None)
                client.on_log_step(run_id, 'Let the monkey loose!', True, False)
                result = self.__monkey_spank(pkg, client, run_id)
                client.on_log_step(run_id, 'capture', True, True)
                print('Ending use case ...')
                client.on_use_case_end(run_id, result['success'], result['runTime'], None, False)
                print('End.')
            print('Stopping test run ...')
            print('Successfully stopped test run: %s' % client.stop_run(run_id, False))
        else:
            client.respond_to_test_request(run_id, 'Cannot start', False, 'No test run ID', None)

    def run_monkey_async(self, client, run_id, project_name, project_version):
        threading.Thread(target=self.run_monkey,
                         args=[client, run_id, project_name, project_version],
                         daemon=True).start()
