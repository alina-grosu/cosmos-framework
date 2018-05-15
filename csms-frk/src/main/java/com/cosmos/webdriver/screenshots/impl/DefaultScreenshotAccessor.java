package com.cosmos.webdriver.screenshots.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cosmos.webdriver.screenshots.IScreenshotAccessor;

public class DefaultScreenshotAccessor implements IScreenshotAccessor {

	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public BufferedImage readScreenshot(Path pathTofile) throws IOException
	{
		logger.info(String.format("Attempting to read image from path [%s]", pathTofile.toString()));
		return ImageIO.read(pathTofile.toFile());		
	}

	@Override
	public void persistScreenshot(Path path, BufferedImage data) throws IOException
	{
		logger.info(String.format("Attempting to write image to path [%s]", path.toString()));
		File file = path.toFile();
		file.mkdirs();
		ImageIO.write(data, "png", file);
	}

}
