package com.cosmos.cucumber.context;

import com.cosmos.cucumber.config.ITestConfiguration;

public interface ITestConfigurationContext extends ICucumberTestContext {
	
	ITestConfiguration getTestConfig();
	
}
