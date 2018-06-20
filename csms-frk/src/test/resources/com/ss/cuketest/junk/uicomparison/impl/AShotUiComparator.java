package com.cosmos.webdriver.uicomparison.impl;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cosmos.webdriver.uicomparison.IUiComparator;
import com.cosmos.webdriver.uicomparison.IUiComparisonResult;
import com.cosmos.webdriver.uicomparison.IUiComparisonResult.UiComparisonStatusEnum;
import com.cosmos.webdriver.uicomparison.ScreenshotTakingStrategyEnum;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;
import ru.yandex.qatools.ashot.shooting.cutter.CutStrategy;

public class AShotUiComparator implements IUiComparator {

	private static final Logger logger = LogManager.getLogger();
	private static final Map<ScreenshotTakingStrategyEnum, ShootingStrategy> strategies = 
			new EnumMap<>(ScreenshotTakingStrategyEnum.class);
	static 
	{
		strategies.put(ScreenshotTakingStrategyEnum.SIMPLE, ShootingStrategies.simple());
		strategies.put(ScreenshotTakingStrategyEnum.SCROLL_UP_DOWN, ShootingStrategies.viewportPasting(50));
		strategies.put(ScreenshotTakingStrategyEnum.SCROLL_DOWN_UP, 
						new DownUpScrollingShootingStrategyDecorator(ShootingStrategies.simple()));
	}
			
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
									
		Screenshot actual = getAshot(ignoredElements)
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
	
	@Override
	public BufferedImage getComparatorConfigurationDependentScreenshot(WebDriver actualScreenshotProvider)
	{		
		return 
				getComparatorConfigurationDependentScreenshot(actualScreenshotProvider, ScreenshotTakingStrategyEnum.SIMPLE);
	}
	
	@Override
	public BufferedImage getComparatorConfigurationDependentScreenshot(WebDriver actualScreenshotProvider,
			ScreenshotTakingStrategyEnum strategy)
	{
		return 
				getAshot(Collections.emptyList())
				.shootingStrategy(strategies.get(strategy))
				.takeScreenshot(actualScreenshotProvider)
				.getImage();
	}
	
	private AShot getAshot(List<WebElement> ignoredElements)
	{		
		return new AShot()
						.coordsProvider(new WebDriverCoordsProvider())
						.ignoredAreas(toAreas(ignoredElements))	
						;
		
	}
		
	public Set<Coords> toAreas(List<WebElement> elementsToIgnore)
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
