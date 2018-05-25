package com.ss.cuketest.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cosmos.resource.TestResourcesEnum;
import com.cosmos.webdriver.context.ITestResourceContext;
import com.cosmos.webdriver.context.IUiDrivingStepContext;

import cucumber.api.Scenario;
import cucumber.api.java.After;

import static com.cosmos.webdriver.util.ImageUtils.*;

import java.io.IOException;
import java.nio.file.Path;

public class ScreenshotingHooks {
	
	private static final Logger logger = LogManager.getLogger();
	private final IUiDrivingStepContext uiContext;
	private final ITestResourceContext resourceContext;
	
	public ScreenshotingHooks (IUiDrivingStepContext context, ITestResourceContext resourceContext)
	{
		this.uiContext = context;	
		this.resourceContext = resourceContext;
	}
	
	@After
	public void saveScreenshotOnFailure(Scenario scenario)
	{		
		if (scenario.isFailed())
		{
					
			byte[] failureScreenshot = 
					((TakesScreenshot) uiContext.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES);
			Path failureDir = 
					resourceContext.getResourceLocator().getResourcePath(TestResourcesEnum.FAILURE_SCREENSHOTS_DIR);						
			
			logger
				.info(
					String.format("Current scenario has failed. Trying to preserve screenshot to filesystem [%s]...",
									failureDir.toString()));	
			
			try
			{
				persistImage(failureDir.resolve("failure.png"), toBufferedImage(failureScreenshot));
			}
			catch (IOException e)
			{
				logger.error("Screenshot preservation has failed!", e);
				throw new RuntimeException("Screenshoting hook has failed!");
			}						
		}									
	}		
	
}
