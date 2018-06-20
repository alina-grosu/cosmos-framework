package com.cosmos.webdriver.context.impl;

import com.cosmos.resource.ITestResourceLocator;
import com.cosmos.webdriver.context.ITestResourceContext;

public class DefaultTestResourceContext implements ITestResourceContext {

	private final ITestResourceLocator resourceLocator;

	public DefaultTestResourceContext(ITestResourceLocator resourceLocator)
	{
		this.resourceLocator = resourceLocator;
	}
	
	@Override
	public ITestResourceLocator getResourceLocator()
	{
		return resourceLocator;
	}

}
