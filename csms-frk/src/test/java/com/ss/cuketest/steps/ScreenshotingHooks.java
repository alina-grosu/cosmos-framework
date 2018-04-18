package com.ss.cuketest.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.springframework.beans.factory.annotation.Autowired;

import com.cosmos.webdriver.context.IStepsContext;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class ScreenshotingHooks {

	private static final Logger logger = LogManager.getLogger();
	private IStepsContext context;

	public ScreenshotingHooks (IStepsContext context)
	{
		this.context = context;
	}
	
	@After
	public void embedScreenshotOnFailure(Scenario scenario)
	{		
		if (scenario.isFailed())
		{
			logger.info("Current scenario has failed. Embedding screenshot to Allure report");			
			getScreenshotAsBytes();
		}
	}
	
	@Attachment(value = "Failure screenshot", type = "image/png")
	private byte[] getScreenshotAsBytes()
	{
		return context.getPageObjectManager().getScreenshotAs(OutputType.BYTES);
	}
}
