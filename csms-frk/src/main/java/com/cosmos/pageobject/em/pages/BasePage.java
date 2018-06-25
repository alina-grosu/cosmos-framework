package com.cosmos.pageobject.em.pages;

import static com.cosmos.util.WaitUtils.waitUntilElementVisible;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.cosmos.pageobject.em.pages.pagefactory.WebDriverAwareDecorator;
import com.cosmos.webdriver.manager.IDriverManager;

import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class BasePage {
			
	protected final IDriverManager driverManager;

	public BasePage(IDriverManager driverManager)
	{
		this.driverManager = driverManager;
		//PageFactory.initElements(driverManager.getDriver(), this);
		PageFactory
			.initElements(new WebDriverAwareDecorator
								(new HtmlElementLocatorFactory(driverManager.getDriver()), driverManager.getDriver()), this);
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
			waitUntilElementVisible(getPagePresenceValidatingWebElement(), driverManager.getDriver(), timeout);				
			isAt = true;
		} 
		catch (TimeoutException exception)
		{
			isAt = false;
		}
		
		return isAt;
	}			
	
	protected abstract WebElement getPagePresenceValidatingWebElement();
	
	/*protected WebElement waitUntilElementVisible(WebElement target)
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
	}*/
	
		
		
}
