package com.cosmos.webdriver.context;

import com.cosmos.cucumber.config.ITestConfiguration;

public interface ITestConfigurationContext extends ICucumberTestContext {
	
	ITestConfiguration getTestConfig();
	
}
