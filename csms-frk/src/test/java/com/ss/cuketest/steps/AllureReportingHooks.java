package com.ss.cuketest.steps;

import static com.cosmos.util.AllureUtils.*;
import static com.cosmos.util.ImageUtils.*;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cosmos.cucumber.context.ITestResourceContext;
import com.cosmos.cucumber.context.ITestUiContext;
import com.cosmos.resource.TestResourcesEnum;

import cucumber.api.Scenario;
import cucumber.api.java.After;

public class AllureReportingHooks {

	private static final Logger logger = LogManager.getLogger();	
	private ITestResourceContext resourceContext;

	public AllureReportingHooks (ITestUiContext uiContext, ITestResourceContext resourceContext)
	{		
		this.resourceContext = resourceContext;
	}
	
	@After(order = 5000)
	public void embedFailureScreenshot(Scenario scenario)
	{		
		if (scenario.isFailed())
		{
			logger.info("Current scenario has failed. Trying to embed screenshot to Allure report...");
			Path failureScreenshot = 
					resourceContext
						.getResourceLocator()
						.getResourcePath(TestResourcesEnum.FAILURE_SCREENSHOTS_DIR)
						.resolve("failure.png");
			
			try
			{
				logger
					.info(
						String.format("Trying to load failure screenshot from location [%s]...",
										failureScreenshot.toString()));
				
				attachScreenshot("failure", toByteArray(loadImage(failureScreenshot)));
				logger.info("Embedded...");
			} 
			catch (IOException e)
			{
				logger.warn("Failure screenshot was not found...");
			}										
		}									
	}		
	
	@After(order = 5000)
	public void embedUiComparisonFailureScreenshots(Scenario scenario)
	{
		Path actual = 
				resourceContext
					.getResourceLocator()
					.getResourcePath(TestResourcesEnum.FAILURE_SCREENSHOTS_DIR)
					.resolve("actual.png");
		Path expected = 
				resourceContext
					.getResourceLocator()
					.getResourcePath(TestResourcesEnum.FAILURE_SCREENSHOTS_DIR)
					.resolve("expected.png");
		Path diff = 
				resourceContext
					.getResourceLocator()
					.getResourcePath(TestResourcesEnum.FAILURE_SCREENSHOTS_DIR)
					.resolve("diff.png");
		
		if (scenario.isFailed() && actual.toFile().exists() && expected.toFile().exists() && diff.toFile().exists())
		{
			logger.info("UI comparison results seem to be available, trying to embed to Allure report...");
			
			try
			{
				attachUiComparisonResults(actual, expected, diff);
				logger.info("Embedded...");
			}
			catch (IOException e)
			{
				String message = "Embedding UI comparison results has failed!";
				logger.error(message, e);
				throw new RuntimeException(message);
			}
			
		}
	}
}
