package com.cosmos.cucumber.context;

import com.cosmos.resource.ITestResourceLocator;

public interface ITestResourceContext extends ICucumberTestContext {

	ITestResourceLocator getResourceLocator();
	
}
