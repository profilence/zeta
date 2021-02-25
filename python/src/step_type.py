from enum import Enum

"""Enum for Step Types

    When adding these to manualtest_settings.json, they're case sensitive!

"""
class StepType(Enum):
    Idle = 0
    Running = 1
    Paused = 2
    NotStarted = 3
    Setup = 4
    TearDown = 5
    Finished = 6
    Aborted = 7
    Faulted = 8
