package com.cosmos.webdriver.uicomparison;

import java.awt.image.BufferedImage;
import java.util.List;
import org.openqa.selenium.WebElement;

public interface IUiComparator {
	
	IUiComparisonResult compare (BufferedImage baseScreenshot);	
	IUiComparisonResult compare (BufferedImage baseScreenshot, List<WebElement> ignoredElements);
}
