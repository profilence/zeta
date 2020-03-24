__copyright__ = "Copyright 2020 Profilence"
__license__ = "Apache License, Version 2.0"


def enum(**enums):
    return type('Enum', (), enums)


ResetType = enum(HARD_RESET=1,
                 SOFT_RESET=2,
                 REQUESTED_RESET=4,
                 MODEM_HIDDEN=8)

EventType = enum(WARNING=1,
                 NATIVE_CRASH=2,
                 STRICT_MODE_VIOLATION=4,
                 TERRIBLE_FAILURE=8,
                 LOW_MEMORY=16,
                 APP_NATIVE_CRASH=32,
                 APP_CRASH=64,
                 APP_NOT_RESPONDING=128,
                 WATCHDOG=256,
                 KERNEL_PANIC=16384,
                 CONSOLE_RAMOOPS=262144,
                 OTHER=2147483647)

LogPriority = enum(NOT_AVAILABLE=0,
                   UNKNOWN=1,
                   KERNEL_EMERGENCY=2,
                   KERNEL_ALERT=3,
                   KERNEL_CRITICAL=4,
                   KERNEL_ERROR=5,
                   KERNEL_WARNING=6,
                   KERNEL_NOTICE=7,
                   KERNEL_INFO=8,
                   KERNEL_DEBUG=9,
                   DEFAULT=10,
                   VERBOSE=11,
                   DEBUG=12,
                   INFO=13,
                   WARNING=14,
                   ERROR=15,
                   FATAL=16,
                   SILENT=17)

SourceBuffer = enum(MAIN=1,
                    RADIO=2,
                    EVENTS=4,
                    SYSTEM=8,
                    KERNEL=16,
                    MARKER=32)

SeriesType = enum(SINGLE_SERIES=0,
                  STACKED_SERIES=1)

PingResponseType = enum(FAILED=0,
                        OK=1)
