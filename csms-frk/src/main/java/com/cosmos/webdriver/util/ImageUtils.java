package com.cosmos.webdriver.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.cosmos.resource.ITestResource;

public class ImageUtils {
	
	private static final Logger logger = LogManager.getLogger();
	
	public static BufferedImage loadImage (Path from) throws IOException
	{
		return ITestResource.IMAGE.load(from);
	}
	
	public static void persistImage(Path where, BufferedImage image) throws IOException
	{
		ITestResource.IMAGE.save(where, image);
	}
	
	public static BufferedImage toBufferedImage(byte[] image) throws IOException
	{
		return ImageIO.read(new ByteArrayInputStream(image));
	}
	
	public static void saveCurrentIfExpectedAbsent(Path expected, WebDriver currentProvider) throws IOException
	{		
		saveCurrentIfExpectedAbsent(expected, toBufferedImage(((TakesScreenshot)currentProvider).getScreenshotAs(OutputType.BYTES)));	
	}
	
	public static void saveCurrentIfExpectedAbsent(Path expected, BufferedImage current) throws IOException
	{
		if (!expected.toFile().exists())
		{
			Path proposedPath = expected.getParent().resolve("proposed_" + expected.getFileName());
			logger.error(String.format
					("Reading image from path [%s] has failed. Proposed sample is about to be stored to [%s].",
					expected, proposedPath));
			
			persistImage(proposedPath, current);
			
			throw new IOException(String.format("Expected file [%s] does not exist!", expected.toString()));
		}
	}
			
	public static void preserveUiComparisonResults(Path failureScreenshotsLocation, BufferedImage actual, BufferedImage expected, BufferedImage diff) throws IOException
	{			
		persistImage(failureScreenshotsLocation.resolve("actual.png"), actual);
		persistImage(failureScreenshotsLocation.resolve("expected.png"), expected);
		persistImage(failureScreenshotsLocation.resolve("diff.png"), diff);			
	}
	
	public static byte[] toByteArray(BufferedImage image) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ImageIO.write(image, "png", out);
	    return out.toByteArray();	    
	}	

}
