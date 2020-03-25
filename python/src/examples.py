import re
import sys

from connector import Connector
from connector_types import PingResponseType
from runner_demo import DemoRunner
from node_demo import DemoListener

__copyright__ = "Copyright 2020 Profilence"
__license__ = "Apache License, Version 2.0"

PUBLISH_NODE = True

if __name__ == '__main__':

    if len(sys.argv):
        args = sys.argv[1:]
        p = re.compile('([^:]+):([0-9]+)')

        address = None
        port = None

        if len(args) >= 1 and (p.match(args[0])):
            m = p.match(args[0])
            address = m.group(1)
            port = int(m.group(2))
        elif len(args) >= 2 and re.search('[0-9]+', args[1]):
            address = args[0]
            port = int(args[1])
        else:
            print('Invalid parameters; give address and port for ping test: [address]:[port]')

        Connector.log = lambda x, y: print(y)
        print('Connecting to %s:%d ...' % (address, port))
        client = None
        try:
            client = Connector(address, port)
            print('Pinging ..')
            if client.ping() == PingResponseType.OK:
                print('Successfully pinged the service')
                runner = DemoRunner('emulator-5554')
                if PUBLISH_NODE:
                    listener = DemoListener(client, runner.run_monkey)
                    listener.start()
                else:
                    runner.run_monkey(client, 'runner_demo', '1.1.1.1')
            else:
                print('Failed to ping the service')
        finally:
            if client:
                print('Shutting down ...')
                client.shutdown()
    else:
        print('Invalid parameters; give address and port for ping test: [address]:[port]')
