package com.cosmos.webdriver.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cosmos.resource.ITestResource;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

public class AshotUtils {
	
	public static Screenshot loadScreenshot(Path location) throws IOException
	{
		return new Screenshot(ITestResource.IMAGE.load(location));
	}
	
	public static Screenshot getScreenshot(WebDriver webDriver, List<WebElement> elementsToIgnore, ShootingStrategy shootingStrategy)
	{
		return new AShot()
				.coordsProvider(new WebDriverCoordsProvider())
				.shootingStrategy(shootingStrategy)
				.ignoredAreas(toAreas(elementsToIgnore))
				.takeScreenshot(webDriver)
				;	
	}
	
	public static Set<Coords> toAreas(List<WebElement> elementsToIgnore)
	{
		
		return 
			elementsToIgnore
			.stream()
			.map(
					webElem -> 
					new Coords(webElem.getLocation().x, webElem.getLocation().y, webElem.getSize().width, webElem.getSize().height)
				)
			.collect(Collectors.toSet())
			;
	}
	
}
