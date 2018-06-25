package com.cosmos.cucumber.context.impl;

import com.cosmos.cucumber.context.ITestResourceContext;
import com.cosmos.resource.ITestResourceLocator;

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
