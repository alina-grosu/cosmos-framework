package com.cosmos.webdriver.context.impl;

import com.cosmos.resource.ITestResourceLocator;
import com.cosmos.webdriver.context.ITestResourceContext;

public class DefaultResourceContext implements ITestResourceContext {

	private final ITestResourceLocator resourceLocator;

	public DefaultResourceContext(ITestResourceLocator resourceLocator)
	{
		this.resourceLocator = resourceLocator;
	}
	
	@Override
	public ITestResourceLocator getResourceLocator()
	{
		return resourceLocator;
	}

}
