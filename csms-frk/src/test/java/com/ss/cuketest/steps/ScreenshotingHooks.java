package com.ss.cuketest.steps;

import static com.cosmos.webdriver.util.AllureUtils.attachScreenshot;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cosmos.webdriver.context.IUiComparisonContext;
import com.cosmos.webdriver.context.IUiDrivingStepContext;
import com.cosmos.webdriver.screenshots.IScreenshotAccessor;

import cucumber.api.Scenario;
import cucumber.api.java.After;

import static com.cosmos.webdriver.util.AllureUtils.*;

public class ScreenshotingHooks {

	private static final Logger logger = LogManager.getLogger();
	private IUiDrivingStepContext context;
	private IUiComparisonContext uiComparisonContext;
	private IScreenshotAccessor screenshotAccessor = IScreenshotAccessor.builder()
														.inputFileGuarding().build();

	public ScreenshotingHooks (IUiDrivingStepContext context, IUiComparisonContext uiComparisonContext)
	{
		this.context = context;
		this.uiComparisonContext = uiComparisonContext;
	}
	
	@After
	public void embedScreenshotOnFailure(Scenario scenario)
	{		
		if (scenario.isFailed())
		{
			logger.info("Current scenario has failed. Embedding screenshot to Allure report...");			
			byte[] failureScreenshot = attachScreenshot("Failure screenshot", context.getDriverManager().getDriver());
			
			try
			{
				Path failureScreenshotLocation = uiComparisonContext
					.getScreenshotsLocationAware()
					.getFailureScreenshotsLocation()
					.resolve(context.getConfiguration().getBrowser().toString().toLowerCase())
					.resolve("failure.png");
				logger.info(
						String.format(
								"Trying to preserve screenshot in filesystem at location [%s]",
								failureScreenshotLocation.toString()));
				
				screenshotAccessor
					.persistScreenshot(failureScreenshotLocation, ImageIO.read(new ByteArrayInputStream(failureScreenshot)));
			}
			catch (IOException e)
			{
				logger.error("Screenshot preservation has failed!", e);
				throw new RuntimeException("Screenshoting hook has failed!");
			}						
		}									
	}		
	
	@After
	public void embedScreenshotsOnComparisonFailure(Scenario scenario)
	{
		if (scenario.isFailed() && uiComparisonContext.getLatestFailure() != null)
		{
			logger.info("UI comparison results seem to be available, trying to embed to Allure report...");
			attachUiComparisonResults(uiComparisonContext.getLatestFailure());
						
			try
			{
				Path diffScreenshotLocation = uiComparisonContext
						.getScreenshotsLocationAware()
						.getResultScreenshotsLocation()
						.resolve(context.getConfiguration().getBrowser().toString().toLowerCase())
						.resolve("diff.png");
				
				logger.info(
						String.format(
								"Trying to preserve diff image to filesystem at location [%s]",
								diffScreenshotLocation));
				
				screenshotAccessor
					.persistScreenshot(
							diffScreenshotLocation, 
							uiComparisonContext.getLatestFailure().getDiffImage());
			} 
			catch (IOException e)
			{
				logger.error("Screenshot preservation has failed!", e);
				throw new RuntimeException("Screenshoting hook has failed!");
			}
			
		}
	}
}
