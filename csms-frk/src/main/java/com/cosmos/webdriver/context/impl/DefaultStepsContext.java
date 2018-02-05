package com.cosmos.webdriver.context.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cosmos.webdriver.context.IStepsContext;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.pageobject.manager.PageObjectManager;

public class DefaultStepsContext implements IStepsContext {

	/*private final IDriverManager mgr;*/
	private final PageObjectManager pageObjectManager;
	private static final Logger logger = LogManager.getLogger();
	
	public DefaultStepsContext (PageObjectManager pageObjectManager)
	{
		this.pageObjectManager = pageObjectManager;
	}
	
	/*public DefaultStepsContext (IDriverManager mgr)
	{
		logger.trace(String.format("Creating steps context %s", this.toString()));
		this.mgr = mgr;
	}*/	
	
	/*@Override
	public IDriverManager getDriverManager()
	{	
		logger.trace(String.format("Returning driver manager %s", mgr.toString()));
		return mgr;
	}*/

	@Override
	public void dispose()
	{		
		logger.trace(String.format("Disposing steps context %s", this.toString()));
		pageObjectManager.quit();		
	}

	@Override
	public PageObjectManager getPageObjectManager()
	{
		logger.trace(String.format("Returning page object manager %s", pageObjectManager.toString()));
		return pageObjectManager;
	}

}
