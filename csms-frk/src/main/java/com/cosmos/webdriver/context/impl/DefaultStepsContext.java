package com.cosmos.webdriver.context.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.context.IStepsContext;
import com.cosmos.webdriver.pageobject.manager.PageObjectManager;

public class DefaultStepsContext implements IStepsContext {
	
	private final PageObjectManager pageObjectManager;
	private IConfiguration config;
	private static final Logger logger = LogManager.getLogger();
	
	public DefaultStepsContext (PageObjectManager pageObjectManager, IConfiguration config)
	{
		this.pageObjectManager = pageObjectManager;
		this.config = config;
	}

	@Override
	public PageObjectManager getPageObjectManager()
	{
		logger.debug(String.format("Returning page object manager %s", pageObjectManager.toString()));
		return pageObjectManager;
	}

	@Override
	public IConfiguration getConfiguration()
	{		
		return config;
	}

}
