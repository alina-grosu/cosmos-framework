package com.cosmos.webdriver.context.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cosmos.webdriver.context.ITestUiContext;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.pageobject.manager.PageObjectManager;

public class DefaultTestUiContext implements ITestUiContext<PageObjectManager> {
	
	private final PageObjectManager pageObjectManager;	
	private IDriverManager driverManager;	
	private static final Logger logger = LogManager.getLogger();
	
	public DefaultTestUiContext (PageObjectManager pageObjectManager,								
								IDriverManager driverManager)
	{
		this.pageObjectManager = pageObjectManager;		
		this.driverManager = driverManager;		
	}

	@Override
	public PageObjectManager getPageObjectManager()
	{
		logger.debug(String.format("Returning page object manager %s", pageObjectManager.toString()));
		return pageObjectManager;
	}	

	@Override 
	public IDriverManager getDriverManager()
	{		
		return driverManager;
	}

}
