import unittest
import sys
import inspect
import webhook_listener
from appium import webdriver

class ZetaWebDriver():

    def __init__(self, zeta=None):  
        if zeta is None:
            print('Zeta was not initialized!')
        else:
            self.zeta = zeta

    def Connect(self):
        desired_caps = {}
        desired_caps['platformName'] = 'Android'
        desired_caps['platformVersion'] = '11'
        desired_caps['udid'] = 'emulator-5554'
        desired_caps['automationName'] = 'UiAutomator2'
        desired_caps['skipLogcatCapture'] = 'true'
        self.driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps, zeta=self.zeta)
        return self.driver




