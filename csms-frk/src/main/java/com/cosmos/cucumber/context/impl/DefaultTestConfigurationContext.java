package com.cosmos.cucumber.context.impl;

import com.cosmos.cucumber.config.ITestConfiguration;
import com.cosmos.cucumber.context.ITestConfigurationContext;

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
