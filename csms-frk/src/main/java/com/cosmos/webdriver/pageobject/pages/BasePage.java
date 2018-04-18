package com.cosmos.webdriver.pageobject.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.cosmos.webdriver.manager.IDriverManager;

public abstract class BasePage {
			
	protected final IDriverManager driverManager;

	public BasePage(IDriverManager driverManager)
	{
		this.driverManager = driverManager;
		PageFactory.initElements(driverManager.getDriver(), this);
	}
	
	public boolean isAt()
	{
		return isAt(5);
	}
	
	public boolean isAt(long timeout)
	{
		boolean isAt;
		
		try
		{
			waitUntilElementVisible(getPagePresenceValidatingWebElement(), timeout);				
			isAt = true;
		} 
		catch (TimeoutException exception)
		{
			isAt = false;
		}
		
		return isAt;
	}			
	
	protected WebElement waitUntilElementVisible(WebElement target)
	{
		return waitUntilElementVisible(target, 5);
	}
	
	protected WebElement waitUntilElementVisible(WebElement target, long timeout)
	{
		return new FluentWait<WebDriver>(driverManager.getDriver())
					.pollingEvery(50, TimeUnit.MILLISECONDS)
					.withTimeout(timeout, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOf(target))
					;
	}
	
	protected abstract WebElement getPagePresenceValidatingWebElement();	
		
}
