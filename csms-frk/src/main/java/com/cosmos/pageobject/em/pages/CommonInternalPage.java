package com.cosmos.pageobject.em.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cosmos.webdriver.manager.IDriverManager;

public abstract class CommonInternalPage extends BasePage {

	@FindBy(how = How.XPATH, using = "//a[contains(@href, 'logOutUser()')][@class]")
	private WebElement logout;
	
	public CommonInternalPage(IDriverManager driverManager)
	{
		super(driverManager);		
	}
	
	public void logout()
	{
		logout.click();		
	}

	

}
