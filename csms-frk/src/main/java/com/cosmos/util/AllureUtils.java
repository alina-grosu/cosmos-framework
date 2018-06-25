package com.cosmos.util;

import static com.cosmos.util.ImageUtils.loadImage;
import static com.cosmos.util.ImageUtils.toByteArray;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Label;

public class AllureUtils {			
	
	public static void attachUiComparisonResults(BufferedImage actual, BufferedImage expected, BufferedImage diff) throws IOException
	{		
		Allure.addLabels(new Label().withName("testType").withValue("screenshotDiff"));		
		attachScreenshot("actual", toByteArray(actual));
		attachScreenshot("expected", toByteArray(expected));
		attachScreenshot("diff", toByteArray(diff));
	}
	
	public static void attachUiComparisonResults(Path actual, Path expected, Path diff) throws IOException
	{		
		Allure.addLabels(new Label().withName("testType").withValue("screenshotDiff"));		
		attachScreenshot("actual", toByteArray(loadImage(actual)));
		attachScreenshot("expected", toByteArray(loadImage(expected)));
		attachScreenshot("diff", toByteArray(loadImage(diff)));
	}

	@Attachment(value = "{name}", type = "image/png")
	public static byte[] attachScreenshot(String name, WebDriver driver)
	{
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
		
	@Attachment(value = "{name}", type = "image/png")
	public static byte[] attachScreenshot(String name, byte[] data) {
	    return data;
	}	
	
}
