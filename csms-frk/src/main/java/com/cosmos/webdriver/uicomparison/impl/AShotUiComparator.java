package com.cosmos.webdriver.uicomparison.impl;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cosmos.webdriver.uicomparison.IUiComparator;
import com.cosmos.webdriver.uicomparison.IUiComparisonResult;
import com.cosmos.webdriver.uicomparison.IUiComparisonResult.UiComparisonStatusEnum;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class AShotUiComparator implements IUiComparator {

	private static final Logger logger = LogManager.getLogger();			
			
	@Override
	public IUiComparisonResult compare(BufferedImage baseScreenshot, WebDriver actualScreenshotProvider)
	{
		return compare(baseScreenshot, actualScreenshotProvider, Collections.emptyList());
	}	

	@Override
	public IUiComparisonResult compare(
			BufferedImage baseScreenshot, 
			WebDriver actualScreenshotProvider, 
			List<WebElement> ignoredElements)
	{
		logger.info("Attempting to compare screenshots...");						
									
		Screenshot actual = new AShot()
						.coordsProvider(new WebDriverCoordsProvider())
						.ignoredAreas(toAreas(ignoredElements))	
						//.shootingStrategy(ShootingStrategies.viewportPasting(100))
						.takeScreenshot(actualScreenshotProvider);
		Screenshot expected = new Screenshot(baseScreenshot);
		expected.setIgnoredAreas(actual.getIgnoredAreas());
		ImageDiff diff = new ImageDiffer().makeDiff(expected, actual);
				
		BufferedImage sampleImg = actual.getImage();
		BufferedImage diffImg = diff.getMarkedImage();
		UiComparisonStatusEnum status = diff.hasDiff() 
				? UiComparisonStatusEnum.FAIL
				: UiComparisonStatusEnum.PASS; 								
		
		return new DefaultUiComparisonResult(status, baseScreenshot, sampleImg, diffImg);
	}
	
	private Set<Coords> toAreas(List<WebElement> elementsToIgnore)
	{
		
		return 
			elementsToIgnore
			.stream()
			.map(
					webElem -> 
					new Coords(webElem.getRect().x, webElem.getRect().y, webElem.getRect().width, webElem.getRect().height)
				)
			.collect(Collectors.toSet())
			;
	}
}
