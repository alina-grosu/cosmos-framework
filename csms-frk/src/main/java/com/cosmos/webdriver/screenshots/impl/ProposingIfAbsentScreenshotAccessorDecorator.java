package com.cosmos.webdriver.screenshots.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cosmos.webdriver.screenshots.IScreenshotAccessor;

public class ProposingIfAbsentScreenshotAccessorDecorator
				implements IScreenshotAccessor {

	private static final Logger logger = LogManager.getLogger();
	private final IScreenshotAccessor delegate;	
	private Supplier<byte[]> proposedSupplier;
			
	public ProposingIfAbsentScreenshotAccessorDecorator
	(IScreenshotAccessor decoratable, Supplier<byte[]> proposedSupplier)
	{
		this.delegate = decoratable;
		this.proposedSupplier = proposedSupplier;
	}
	
	@Override
	public BufferedImage readScreenshot(Path pathTofile) throws IOException
	{				
		if (!pathTofile.toFile().exists())
		{
			Path newPath = pathTofile.getParent().resolve("proposed_" + pathTofile.getFileName());
			logger.warn(String.format
					("Reading image from path [%s] has failed. Proposed sample is about to be stored to [%s].",
					pathTofile, newPath));
			
			
			BufferedImage proposedImage = ImageIO.read(new ByteArrayInputStream(proposedSupplier.get()));			
			persistScreenshot(newPath, proposedImage);																		
			
			throw new IOException("Image file was not found!");
		}
		
		return delegate.readScreenshot(pathTofile);
	}

	@Override
	public void persistScreenshot(Path path, BufferedImage data) throws IOException
	{
		delegate.persistScreenshot(path, data);		
	}

}
