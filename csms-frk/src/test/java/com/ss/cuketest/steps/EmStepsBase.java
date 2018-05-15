package com.ss.cuketest.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cosmos.webdriver.context.IUiDrivingStepContext;
import com.cosmos.webdriver.pageobject.manager.PageObjectManager;

public abstract class EmStepsBase {
	
	private static final Logger logger = LogManager.getLogger();
	protected IUiDrivingStepContext uiDrivingContext;
	protected PageObjectManager pageObjectManager;
	
	public EmStepsBase()
	{
		
	}
	
	public EmStepsBase (IUiDrivingStepContext context)
	{
		this.uiDrivingContext = context;
		this.pageObjectManager = context.getPageObjectManager();
		logger.debug(String.format("Instantiated %s using context %s and pageObjectManager %s"
				, this.toString()
				, context.toString()
				, pageObjectManager.toString()));
	}
			
}
