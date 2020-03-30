import json
import threading
import time
from connector_types import TestRequestListenerBase

__copyright__ = "Copyright 2020 Profilence"
__license__ = "Apache License, Version 2.0"


def get_node_properties():
    props = {'demo': True, 'region': 'US', 'operator': 'Sprint', 'product': 'Pixel4', 'platform': 'Android'}
    return json.dumps(props)


class DemoListener(TestRequestListenerBase):

    def __init__(self, cli, test_launcher):
        self.node_name = 'monkey_node'
        self.pool_name = 'monkey_pool'
        self.platform_type = 'Android'
        self.client = cli
        self.launcher = test_launcher
        self.__running = False

    def on_test_start_requested(self, request):
        project = request.project
        version = request.version
        run_id = request.run_id
        print('\nReceived request %s to test version %s for project %s' % (run_id, version, project))
        if self.launcher:
            self.launcher(self.client, run_id, project, version)

    def heart_beat(self):
        while self.__running:
            self.client.update_node(self.node_name, None, None, None, None, self.pool_name, get_node_properties())
            time.sleep(60)

    def start(self):
        okay = self.client.add_node(self.node_name, self.pool_name, self.platform_type, get_node_properties())
        print('Node added successfully: %s' % okay)
        if okay:
            self.__running = True
            self.client.subscribe_to_test_requests(self)
            threading.Thread(target=self.heart_beat, daemon=True).start()
            input('Press any key to continue....')
            self.__running = False
            self.client.remove_node(self.node_name)
