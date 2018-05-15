package com.cosmos.webdriver.util;

import static com.cosmos.webdriver.util.AllureUtils.attachScreenshot;
import static com.cosmos.webdriver.util.AllureUtils.toByteArray;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.cosmos.webdriver.uicomparison.IUiComparisonResult;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Label;

public class AllureUtils {
	
	private static final Logger logger = LogManager.getLogger();
	
	public static void attachUiComparisonResults(IUiComparisonResult uiComparisonResult)
	{		
		Allure.addLabels(new Label().withName("testType").withValue("screenshotDiff"));		
		attachScreenshot("actual", toByteArray(uiComparisonResult.getSampleImage()));
		attachScreenshot("expected", toByteArray(uiComparisonResult.getBaseImage()));
		attachScreenshot("diff", toByteArray(uiComparisonResult.getDiffImage()));
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
	
	public static byte[] toByteArray(final BufferedImage image) {
	    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
	        ImageIO.write(image, "png", out);
	        return out.toByteArray();
	    } catch (IOException ignored) {
	        return new byte[0];
	    }
	}	
	
}
