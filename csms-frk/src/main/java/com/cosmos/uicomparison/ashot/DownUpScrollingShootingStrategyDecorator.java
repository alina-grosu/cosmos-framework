package com.cosmos.uicomparison.ashot;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.coordinates.Coords;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;
import ru.yandex.qatools.ashot.shooting.ViewportPastingDecorator;

public class DownUpScrollingShootingStrategyDecorator 
				extends ViewportPastingDecorator			
				implements ShootingStrategy {
	
	private static final long serialVersionUID = 1L;
	private Coords shootingArea;
	private static final Logger logger = LogManager.getLogger();

	public DownUpScrollingShootingStrategyDecorator(ShootingStrategy strategy)
	{
		super(strategy);
	}
	
	@Override
	public BufferedImage getScreenshot(WebDriver wd, Set<Coords> coordsSet)
	{
		JavascriptExecutor js = (JavascriptExecutor) wd;		
		int pageHeight = getFullHeight(wd);
		int pageWidth = getFullWidth(wd);
		int viewportHeight = getWindowHeight(wd);
		
		logger.debug(String.format("Page height: [%s], Page width: [%s], Viewport height: [%s]", 
				pageHeight, pageWidth, viewportHeight));
		
		shootingArea = getShootingCoords(coordsSet, pageWidth, pageHeight, viewportHeight);
					
		BufferedImage finalImage = new BufferedImage(pageWidth, shootingArea.height, BufferedImage.TYPE_3BYTE_BGR);		
		
		Graphics2D graphics = finalImage.createGraphics();					
		
		int scrolled = 0;		
		int startingPoint = shootingArea.y + shootingArea.height;
		
		do
		{					
			scrollVertically(js, startingPoint - scrolled);									
			waitForScrolling();	
			
			logger.debug(String.format("Scrolled: [%s], Starting point: [%s], Current scroll: [%s], Shooting area Y: [%s]", 
					scrolled, startingPoint, getCurrentScrollY(js),  shootingArea.y));
			
			BufferedImage part = getShootingStrategy().getScreenshot(wd);
			graphics.drawImage(part, 0, getCurrentScrollY(js) - shootingArea.y, null);							
			scrolled += 0.75 * viewportHeight;										
		}
		while (getCurrentScrollY(js) > shootingArea.y);
				
		graphics.dispose();			
		return finalImage;		
	}
			
	/*
		IE11 compatible.	
	*/	
	@Override	
	protected int getCurrentScrollY(JavascriptExecutor js)
	{		
		return ((Number) js.executeScript("var scrY = window.pageYOffset;"
        		+ "if(scrY){return scrY;} else {return 0;}")).intValue();
	}

	private Coords getShootingCoords(Set<Coords> coords, int pageWidth, int pageHeight, int viewPortHeight)
	{
		if (coords == null || coords.isEmpty())
		{
			return new Coords(0, 0, pageWidth, pageHeight);
		}
		return extendShootingArea(Coords.unity(coords), viewPortHeight, pageHeight);
	}

	private Coords extendShootingArea(Coords shootingCoords, int viewportHeight, int pageHeight)
	{
		int halfViewport = viewportHeight / 2;
		shootingCoords.y = Math.max(shootingCoords.y - halfViewport / 2, 0);
		shootingCoords.height = Math.min(shootingCoords.height + halfViewport, pageHeight);
		return shootingCoords;
	}

	private void waitForScrolling()
	{
		try
		{
			Thread.sleep(scrollTimeout);
		} catch (InterruptedException e)
		{
			throw new IllegalStateException("Exception while waiting for scrolling", e);
		}
	}

}
