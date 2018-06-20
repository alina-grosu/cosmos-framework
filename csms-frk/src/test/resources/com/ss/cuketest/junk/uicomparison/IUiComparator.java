package com.cosmos.webdriver.uicomparison;

import java.awt.image.BufferedImage;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface IUiComparator {
	
	IUiComparisonResult compare (
			BufferedImage baseScreenshot, WebDriver actualScreenshotProvider);	
	
	IUiComparisonResult compare (
			BufferedImage baseScreenshot, WebDriver actualScreenshotProvider, List<WebElement> ignoredElements);
	
	BufferedImage getComparatorConfigurationDependentScreenshot(WebDriver actualScreenshotProvider);
	
	BufferedImage getComparatorConfigurationDependentScreenshot(WebDriver actualScreenshotProvider, ScreenshotTakingStrategyEnum strategy);
}
