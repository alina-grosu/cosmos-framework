package com.cosmos.webdriver.screenshots.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import com.cosmos.webdriver.screenshots.IScreenshotAccessor;

public class InputFileTypeGuardingScreenshotAccessorDecorator 
				implements IScreenshotAccessor {

	private final IScreenshotAccessor delegate;

	public InputFileTypeGuardingScreenshotAccessorDecorator(IScreenshotAccessor delegate)
	{
		this.delegate = delegate;		
	}
	
	@Override
	public BufferedImage readScreenshot(Path pathToFile) throws IOException
	{
		guard(pathToFile);		
		return delegate.readScreenshot(pathToFile);
	}
	
	@Override
	public void persistScreenshot(Path pathToFile, BufferedImage data) throws IOException
	{
		guard(pathToFile);		
		delegate.persistScreenshot(pathToFile, data);
	}
	
	private void guard(Path pathToFile)
	{
		final String fileName = pathToFile.getFileName().toString();						
		if (!fileName.endsWith(".png"))
		{
			throw new IllegalArgumentException(
					String.format("Path [%s] is expected to point to a file *.png!", pathToFile.toString())); 
		}
	}	
}
