package com.cosmos.webdriver.context;

import com.cosmos.resource.ITestResourceLocator;

public interface ITestResourceContext extends ICucumberStepContext {

	ITestResourceLocator getResourceLocator();
	
}
