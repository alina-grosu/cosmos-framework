package com.cosmos.webdriver.pageobject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.cosmos.webdriver.manager.IDriverManager;

public class HomeDashboardPage extends CommonInternalPage {
	
	@FindBy(how = How.XPATH, using = "//body[//div[@class = 'col-md-12']/div[@id = 'circleContent']]")
	private WebElement thisPage;
	
	public HomeDashboardPage(IDriverManager driverManager)
	{
		super(driverManager);			
	}	

	@Override
	protected WebElement getPagePresenceValidatingWebElement()
	{		
		return thisPage;
	}

}
