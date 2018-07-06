package com.cosmos.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.cosmos.pageobject.em.pages.pagecomponents.commons.CheckedListItem;

public class WaitUtils {

	public static WebElement waitUntilElementVisible(WebElement target, WebDriver driver)
	{
		return waitUntilElementVisible(target, driver, 5);
	}
	
	public static WebElement waitUntilElementVisible(WebElement target, WebDriver driver, long timeout)
	{
		return new FluentWait<WebDriver>(driver)
					.pollingEvery(50, TimeUnit.MILLISECONDS)
					.withTimeout(timeout, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOf(target))
					;
	}

	public static boolean waitUntilElementInvisible(WebElement target, WebDriver driver)
	{
		return waitUntilElementInvisible(target, driver, 5);
	}
	
	public static boolean waitUntilElementInvisible(WebElement target, WebDriver driver, long timeout)
	{
				
		return new FluentWait<WebDriver>(driver)
				.pollingEvery(50, TimeUnit.MILLISECONDS)
				.withTimeout(timeout, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.invisibilityOf(target))
				;
	}

	public static boolean waitUntilElementStaleness(WebElement target, WebDriver driver)
	{
		return waitUntilElementStaleness(target, driver, 5);
	}

	private static boolean waitUntilElementStaleness(WebElement target, WebDriver driver, int timeout)
	{
		return new FluentWait<WebDriver>(driver)
				.pollingEvery(50, TimeUnit.MILLISECONDS)
				.withTimeout(timeout, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.stalenessOf(target))
				;
	}
	
}
