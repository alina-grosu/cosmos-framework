package com.cosmos.webdriver.uicomparison.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.uicomparison.IUiComparator;
import com.cosmos.webdriver.uicomparison.IUiComparisonIgnorableElementsAware;
import com.cosmos.webdriver.uicomparison.IUiComparisonResult;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.Coords;

import static com.cosmos.webdriver.uicomparison.IUiComparisonResult.UiComparisonStatusEnum;

public class AShotUiComparator implements IUiComparator {

	private static final Logger logger = LogManager.getLogger();	
	private final WebDriver driver;
	
	public AShotUiComparator(WebDriver driver)
	{
		this.driver = driver;
	}
				
	@Override
	public IUiComparisonResult compare(BufferedImage baseScreenshot)
	{
		return compare(baseScreenshot, new LinkedList<WebElement>());
	}	

	@Override
	public IUiComparisonResult compare(BufferedImage baseScreenshot, List<WebElement> ignoredElements)
	{
		logger.info(String
				.format("Attempting to compare screenshot [%s] to current UI state", baseScreenshot.toString()));
		
		
		
		
		BufferedImage baseImg = null;
		BufferedImage sampleImg = null;
		BufferedImage diffImg = null;					
		
		try 
		{
			logger.info("Reading base screenshot...");
			baseImg = ImageIO.read(baseScreenshot.toFile());
		}
		catch (IOException e)
		{
			logger.error(String.format("Unable to read base screenshot file from path [%s]", baseScreenshot.toString()), e);
			throw new RuntimeException("Unable to read image.", e);
		}				
		
		Screenshot actual = new AShot()
						.ignoredAreas(toAreas(ignoredElements))
						.takeScreenshot(driver);
		Screenshot expected = new Screenshot(baseImg);
		
		ImageDiff diff = new ImageDiffer().makeDiff(expected, actual);
		
		baseImg = expected.getImage();
		sampleImg = actual.getImage();
		diffImg = diff.getMarkedImage();
		UiComparisonStatusEnum status = diff.hasDiff() 
				? UiComparisonStatusEnum.FAIL
				: UiComparisonStatusEnum.PASS; 				
		
		Path diffLocation = baseScreenshot.getParent().getParent().resolve("result").resolve("diff_" + baseScreenshot.getFileName());
		try 
		{						
			logger.info(
					String.format(
							"Trying to preserve diff image to [%s]...", 
							diffLocation));
			File diffFile = diffLocation.toFile();
			diffFile.mkdirs();
			ImageIO.write(diffImg, "png", diffFile);
		}
		catch (IOException e)
		{
			logger.error(String.format("Unable to preserve diff screenshot file to path [%s]", diffLocation.toString()), e);
			throw new RuntimeException("Unable to store image.", e);
		}
		
		return new DefaultUiComparisonResult(status, baseImg, sampleImg, diffImg);
	}
	
	private Set<Coords> toAreas(List<WebElement> elementsToIgnore)
	{
		
		return elementsToIgnore
			.stream()
			.map(
					webElem -> 
						new Coords(webElem.getRect().x, webElem.getRect().y, webElem.getRect().width, webElem.getRect().height)
				)
			.collect(Collectors.toSet())
			;
	}
}
