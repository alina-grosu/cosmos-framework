package com.ss.cuketest.steps;

import com.cosmos.webdriver.context.ITestUiContext;
import com.cosmos.webdriver.manager.StepContextScopesEnum;

import cucumber.api.java.After;

public class WebDriverLifecycleHooks {

	private ITestUiContext uiContext;

	public WebDriverLifecycleHooks(ITestUiContext uiContext)
	{
		this.uiContext = uiContext;
	}
	
	@After
	public void restartBrowser()
	{
		if (uiContext.getConfiguration().getStepsContextScope().equals(StepContextScopesEnum.SCENARIO))
		{
			uiContext.getDriverManager().quitDriver();
		}
	}
	
}
