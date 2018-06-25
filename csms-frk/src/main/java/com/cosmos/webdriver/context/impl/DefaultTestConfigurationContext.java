package com.cosmos.webdriver.context.impl;

import com.cosmos.cucumber.config.ITestConfiguration;
import com.cosmos.webdriver.context.ITestConfigurationContext;

public class DefaultTestConfigurationContext implements ITestConfigurationContext {

	private final ITestConfiguration config;

	public DefaultTestConfigurationContext(ITestConfiguration config)
	{
		this.config = config;
	}
	
	@Override
	public ITestConfiguration getTestConfig()
	{		
		return config;
	}

}
