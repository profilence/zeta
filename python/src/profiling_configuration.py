__copyright__ = "Copyright 2020 Profilence"
__license__ = "Apache License, Version 2.0"


def to_json_value(key, value, is_last):
    if isinstance(value, str):
        value = '"%s"' % value
    elif value is None:
        value = 'null'
    elif isinstance(value, bool):
        if value:
            value = 'true'
        else:
            value = 'false'
    end = ''
    if not is_last:
        end = ', '
    return '"%s": %s%s' % (key, value, end)


class HPROFArgumentPair:

    def __init__(self):
        self.process_name = ''
        self.include_subprocesses = False


class DumpsysServiceArgumentPair:

    def __init__(self):
        self.service_name = ''
        self.arguments = ''


class ProfilingConfiguration:

    def __init__(self):
        self.cpu_load = True
        self.cpu_states = True
        self.power_measure = True
        self.battery_stats = True
        self.frame_stats = False
        self.cpu_load_per_core = False
        self.netstats = False
        self.disk_usage = False
        self.thermal = True
        self.gpu_load = False
        self.gpu_states = False
        self.light_memory_profiling = True
        self.process_memory_profiling = False
        self.system_memory_profiling = False
        self.slabs = False
        self.device_log = True
        self.device2_log = False
        self.events = True
        self.resets = True
        self.dump_heaps = []
        self.dump_services = []
        self.device_settings = {}

    def add_device_setting(self, device_id, key, value):
        if not self.device_settings.__contains__(device_id):
            self.device_settings[device_id] = {}
        self.device_settings[device_id][key] = value

    def add_dumpsys_service(self, service_name, arguments):
        item = DumpsysServiceArgumentPair()
        item.service_name = service_name
        item.arguments = arguments
        self.dump_services.append(item)

    def add_dumpheap_process(self, process_name, include_subprocesses):
        item = HPROFArgumentPair()
        item.process_name = process_name
        item.include_subprocesses = include_subprocesses
        self.dump_heaps.append(item)

    def to_json(self):
        bob = '{ '
        bob += to_json_value("cpuLoad", self.cpu_load, False)
        bob += to_json_value("cpuStates", self.cpu_states, False)
        bob += to_json_value("powerMeasure", self.power_measure, False)
        bob += to_json_value("batteryStats", self.battery_stats, False)
        bob += to_json_value("frameStats", self.frame_stats, False)
        bob += to_json_value("cpuLoadPerCore", self.cpu_load_per_core, False)
        bob += to_json_value("netstats", self.netstats, False)
        bob += to_json_value("diskUsage", self.disk_usage, False)
        bob += to_json_value("thermal", self.thermal, False)
        bob += to_json_value("gpuLoad", self.gpu_load, False)
        bob += to_json_value("gpuStates", self.gpu_states, False)
        bob += to_json_value("lightMemoryProfiling", self.light_memory_profiling, False)
        bob += to_json_value("processMemoryProfiling", self.process_memory_profiling, False)
        bob += to_json_value("systemMemoryProfiling", self.system_memory_profiling, False)
        bob += to_json_value("slabs", self.slabs, False)
        bob += to_json_value("deviceLog", self.device_log, False)
        bob += to_json_value("device2Log", self.device2_log, False)
        bob += to_json_value("events", self.events, False)
        bob += to_json_value("resets", self.resets, False)

        dumpsys_bob = '"dumpServices" : [ '

        for dp in self.dump_services:
            dumpsys_bob += '{ '
            dumpsys_bob += to_json_value("service", dp.service_name, False)
            dumpsys_bob += to_json_value("arguments", dp.arguments, True)
            dumpsys_bob += ' }'
            if self.dump_services.index(dp) < len(self.dump_services) - 1:
                dumpsys_bob += ', '
        dumpsys_bob += ' ], '
        bob += dumpsys_bob

        hprof_bob = '"dumpHeaps" : [ '
        for hp in self.dump_heaps:
            hprof_bob += '{ '
            hprof_bob + to_json_value("processName", hp.process_name, False)
            hprof_bob += to_json_value("includeSubprocesses", hp.include_subprocesses, True)
            hprof_bob += ' }'
            if self.dump_heaps.index(hp) < len(self.dump_heaps) - 1:
                hprof_bob += ', '
        hprof_bob += ' ], '
        bob += hprof_bob

        dev_bob = '"deviceSettings" : { '

        i = 0

        for device_id in self.device_settings.keys():
            dev_bob += '"%s" : {' % device_id
            props = self.device_settings.get(device_id)
            if props:
                j = 0
                for key in props.keys():
                    dev_bob += to_json_value(key, props.get(key), j == len(props) - 1)
                    j += 1
            dev_bob += ' }'
            if i < len(self.device_settings) - 1:
                dev_bob += ', '
            i += 1

        dev_bob += ' }'
        bob += dev_bob + ' }'
        return bob
