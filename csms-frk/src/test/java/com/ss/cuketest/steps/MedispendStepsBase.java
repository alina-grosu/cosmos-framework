package com.ss.cuketest.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cosmos.webdriver.context.IStepsContext;
import com.cosmos.webdriver.pageobject.manager.PageObjectManager;

public abstract class MedispendStepsBase {
	
	private static final Logger logger = LogManager.getLogger();
	protected IStepsContext context;
	protected final PageObjectManager pageObjectManager;
	
	public MedispendStepsBase (IStepsContext context)
	{
		this.context = context;
		this.pageObjectManager = context.getPageObjectManager();
		logger.debug(String.format("Instantiated %s using context %s and pageObjectManager %s"
				, this.toString()
				, context.toString()
				, pageObjectManager.toString()));
	}			
	
}
