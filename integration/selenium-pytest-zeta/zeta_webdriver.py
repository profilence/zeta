import unittest
import sys
import inspect
import webhook_listener
from selenium import webdriver
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities

class ZetaWebDriver():

    def __init__(self, zeta=None, browser=None):
        self.browser = browser
        if zeta is None:
            print('Zeta was not initialized!')
        else:
            self.zeta = zeta

    def Connect(self):
        if self.browser == None:
            print('No browser given!')
            return None
        elif self.browser == 'Chrome':
            capabilities = DesiredCapabilities.CHROME.copy()
            capabilities['loggingPrefs'] = { 'browser':'ALL', 'performance':'ALL'}
            options = webdriver.ChromeOptions()
            options.add_argument("--enable-precise-memory-info")
            options.add_experimental_option('w3c', False)##disable w3c compliancy to get more data
            self.driver = webdriver.Chrome(zeta=self.zeta, desired_capabilities=capabilities, options=options)
            print("Driver PID: " + str(self.driver.service.process.pid))
        elif self.browser == 'Firefox':
            self.driver = webdriver.Firefox(zeta=self.zeta)
        return self.driver




