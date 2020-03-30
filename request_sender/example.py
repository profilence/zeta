import json
import urllib
import urllib.request
import requests
from datetime import datetime
import time
from os import path


def check_resp(code, url):
    if code != 200:
        raise Exception('Response code %s from url %s' % (code, url))


def get_resp_obj(content):
    return json.loads(content)


def str2bool(v):
    if isinstance(v, bool):
        return v
    return v.lower() in ("yes", "true", "t", "1")


class Login:

    def __init__(self, address, username, password):
        self.end_point = address.rstrip('/')
        self.username = username
        self.password = password
        self.key = None
        self.fetch_retries = 5
        self.fetch_retry_timeout_seconds = 5

    def fetch(self, path, params, method):
        url = '%s/%s' % (self.end_point, path)
        if self.key:
            url = url + '?apiKey=%s' % self.key
        if params:
            params = urllib.parse.urlencode(params)

        f = None
        err = None
        retries = self.fetch_retries
        timeout = self.fetch_retry_timeout_seconds

        for i in range(retries):

            if err:
                print(err + " Wait %s seconds before retry.." % timeout)
                err = None
                time.sleep(timeout)
                print('Retry %s/%s..' % (i, retries - 1))

            try:
                if method == 'POST':
                    f = urllib.request.urlopen(url, params.encode('utf-8'))
                else:
                    if params:
                        f = urllib.request.urlopen(url + '?' + params)
                    else:
                        f = urllib.request.urlopen(url)
            except Exception as e:
                print('Caught exception while connecting: %s' % e)
                err = 'Problems connecting to server.'
            else:
                if f and f.code == 504:
                    err = 'A gateway timeout. Gateway may be temporarily down or request serving node does not respond.'

            if err is None:
                break

        if not f:
            raise Exception('Unable to get response from %s' % self.end_point)

        return f.read(), check_resp(f.code, url)

    def post(self, url, params=None):
        return self.fetch(url, params, 'POST')

    def process(self):
        data = dict(Username=self.username, Password=self.password)
        resp, response_code = self.post('/login', data)
        resp_obj = get_resp_obj(resp)
        if resp_obj.__contains__('apiKey'):
            self.key = resp_obj['apiKey']
        if not self.key:
            raise Exception('Could not login')
        print('Successful login with password')
        return self.key


class TestRequestResponse:

    def __init__(self, params):
        self.params = params
        self.test_run_id = params.get('TestRunId')
        self.exception = params.get('Exception')
        self.log = params.get('Log')
        self.result = params.get('Result')
        self.status = params.get('Status')
        self.result_type = params.get('ResultType')
        self.timestamp = datetime.fromtimestamp((params.get('Timestamp') or 0) / 1000.0)

    def __str__(self):
        return '%s: %s' % (self.result_type, self.status)


class TestRun:

    def __init__(self, test_request_response, log_in):
        self.test_request_response = test_request_response
        self.test_run_id = test_request_response.test_run_id
        self.log_in = log_in

    def __str__(self):
        return str(self.test_request_response)

    def get_status(self):
        url = '%s/gateway/testRuns/status/%s?apiKey=%s' % (self.log_in.end_point, self.test_run_id, self.log_in.key)
        f = urllib.request.urlopen(url)
        check_resp(f.code, url)
        resp = get_resp_obj(f.read())
        return resp

    def is_live(self, throw=False):
        try:
            resp = self.get_status()
        except Exception as e:
            print('Caught exception %s when requesting run status. Run not does not exist?' % e)
            if throw:
                raise e
            return False

        if resp.__contains__('IsLive'):
            is_live = str2bool(resp['IsLive'])
            if is_live:
                return True
            else:
                return False

        raise Exception('No test_run_id %s found' % self.test_run_id)

    def wait_finish(self):
        self.wait_shared_run()
        wait_secs = 5 * 60
        start_time = time.time()
        while self.is_live():
            if time.time() - start_time > wait_secs:
                print('Timeout waiting tests.')
                break
            print('Test is ongoing..')
            time.sleep(5)

    def wait_shared_run(self):
        found = False
        for i in range(30):
            try:
                self.is_live(True)
                found = True
                break
            except Exception as e:
                print('Waiting for run %s to be shared..' % self.test_run_id)
            time.sleep(3)

        if not found:
            raise Exception('Test run %s was not found in shared test runs' % self.test_run_id)


class TestNode:

    def __init__(self, log_in, params):
        self.log_in = log_in
        self.params = params
        self.node_id = params.get('NodeId')
        self.pool = params.get('Pool')
        self.node_type = params.get('NodeType')

    def start_run(self, project, version, zip_file=None):

        if not self.log_in.key:
            self.log_in.process()

        url = '{}/nodes/startRun{}'.format(self.log_in.end_point,
                                           self.log_in.key and '?apiKey={}'.format(self.log_in.key) or '')

        content = None
        if zip_file and path.isfile(zip_file):
            content = open(zip_file, 'rb')

        data = {'NodePool': self.pool, 'TargetProject': project, 'TargetVersion': version}
        files = {
            'data': (None, json.dumps(data), 'application/json'),
            'file': ('File', content, 'application/octet-stream')
        }

        resp = requests.post(url,
                             files=files,
                             verify=False
                             )

        resp.raise_for_status()

        resp_obj = resp.json()

        if resp_obj:
            return TestRun(TestRequestResponse(resp_obj), self.log_in)

        return None

    def __str__(self):
        return str(self.params)


class TestNodes:

    def __init__(self, log_in):
        self.log_in = log_in

    def list_nodes(self):
        if not self.log_in.key:
            self.log_in.process()
        end_point = self.log_in.end_point
        api_key = self.log_in.key
        url = '%s/nodes/all' % end_point
        params = urllib.parse.urlencode({'apiKey': api_key})
        url = url + '?' + params
        f = urllib.request.urlopen(url)
        check_resp(f.code, url)
        return [TestNode(self.log_in, p) for p in get_resp_obj(f.read())]


if __name__ == "__main__":

    address = 'this_is_omicron_address'
    username = 'this_is_omicron_username'
    password = 'this_is_omicron_password'
    
    t = TestNodes(Login(address, username, password))
    nodes = t.list_nodes()
    if len(nodes):
        run = nodes[0].start_run('Monkey', '1.1.6', r'c:\work\test_set_for_my_tool_of_choice.zip')
        if run:
            print('Started test run: %s' % run.test_run_id)
            run.wait_finish()

