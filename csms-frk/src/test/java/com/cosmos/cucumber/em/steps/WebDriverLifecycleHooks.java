package com.cosmos.cucumber.em.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cosmos.cucumber.config.WebDriverLifecycleEnum;
import com.cosmos.cucumber.context.ITestConfigurationContext;
import com.cosmos.cucumber.context.ITestUiContext;
import com.cosmos.pageobject.em.manager.PageObjectManager;

import cucumber.api.java.After;

public class WebDriverLifecycleHooks {
	
	private static final Logger logger = LogManager.getLogger();
	private final ITestConfigurationContext configurationContext;
	private final ITestUiContext<PageObjectManager> uiContext;

	public WebDriverLifecycleHooks(ITestUiContext<PageObjectManager> uiContext, ITestConfigurationContext configurationContext)
	{
		this.uiContext = uiContext;
		this.configurationContext = configurationContext;
	}
	
	@After
	public void restartBrowser()
	{
		if (configurationContext.getTestConfig().getWebDriverScope().equals(WebDriverLifecycleEnum.SCENARIO))
		{
			logger.info("About to close current browser...");
			uiContext.getDriverManager().quitDriver();
		}
	}
	
}
