package com.cosmos.cucumber.uicomparison;

import java.nio.file.Path;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cosmos.cucumber.ThreadLocalBasedFeatureTracker;
import com.cosmos.webdriver.uicomparison.IUiComparator;
import com.cosmos.webdriver.uicomparison.IUiComparisonResult;
import com.cosmos.webdriver.uicomparison.impl.AShotUiComparator;

public class FeatureLocationAwareUiComparisonHelper {	
	
	public static IUiComparisonResult compare(String baseScreenshotName, WebDriver driver)
	{
		Path baseScreenshot = getBaseScreenshotLocation(baseScreenshotName);				
		IUiComparator uiComparator = new AShotUiComparator(driver);				
		return uiComparator.compare(baseScreenshot);
	}
	
	public static IUiComparisonResult compare(String baseScreenshotName, WebDriver driver, List<WebElement> toBeIgnored)
	{
		Path baseScreenshot = getBaseScreenshotLocation(baseScreenshotName);				
		IUiComparator uiComparator = new AShotUiComparator(driver);				
		return uiComparator.compare(baseScreenshot, toBeIgnored);
	}

	private static Path getBaseScreenshotLocation(String baseScreenshotName)
	{
		Path currentScreenshotsLocation = ThreadLocalBasedFeatureTracker
				.getCurrentFeatureDir()
				.resolve("screenshots");

		Path base = currentScreenshotsLocation.resolve("base");
		Path baseScreenshot = base.resolve(baseScreenshotName);
		return baseScreenshot;
	}
	
}
