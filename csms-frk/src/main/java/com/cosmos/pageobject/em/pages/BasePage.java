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
		
}
