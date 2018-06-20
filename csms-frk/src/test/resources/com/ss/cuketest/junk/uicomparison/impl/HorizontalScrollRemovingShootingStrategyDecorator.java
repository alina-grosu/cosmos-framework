package com.cosmos.webdriver.uicomparison.impl;

import java.awt.image.BufferedImage;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

public class HorizontalScrollRemovingShootingStrategyDecorator implements ShootingStrategy {

	private static final long serialVersionUID = 1L;
	private ShootingStrategy decoratable;

	public HorizontalScrollRemovingShootingStrategyDecorator (ShootingStrategy decoratable)
	{
		this.decoratable = decoratable;
	}
	
	@Override
	public BufferedImage getScreenshot(WebDriver wd)
	{
		BufferedImage sample = decoratable.getScreenshot(wd);
		int width = wd.findElement(By.xpath("//html")).getSize().width;
		return sample.getSubimage(0, 0, width, sample.getHeight());
		
	}

	@Override
	public BufferedImage getScreenshot(WebDriver wd, Set<Coords> coords)
	{		
		return getScreenshot(wd);
	}

	@Override
	public Set<Coords> prepareCoords(Set<Coords> coordsSet)
	{
		  return coordsSet;
	}

}
