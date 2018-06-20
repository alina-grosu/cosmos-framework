package com.cosmos.webdriver.context;

import com.cosmos.resource.ITestResourceLocator;

public interface ITestResourceContext extends ICucumberTestContext {

	ITestResourceLocator getResourceLocator();
	
}
