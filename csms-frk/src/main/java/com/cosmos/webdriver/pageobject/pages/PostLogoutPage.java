package com.cosmos.webdriver.pageobject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cosmos.webdriver.manager.IDriverManager;

public class PostLogoutPage extends BasePage {

	@FindBy(how = How.XPATH, using = "//div[h3/span[contains(@class, 'glyphicon-log-out')]]")
	private WebElement thisPage;
	@FindBy(how = How.XPATH, using = "//button[contains(@onclick, 'document.referrer')]")
	private WebElement goBack;
	@FindBy(how = How.XPATH, using = "//button[contains(@onclick, '/dashboard/home')][not(contains(@onclick, 'domain'))]")
	private WebElement home;
	@FindBy(how = How.XPATH, using = "//button[contains(@onclick, '/dashboard/home')][contains(@onclick, 'domain')]")
	private WebElement loginScreen;
	
	
	public PostLogoutPage(IDriverManager driverManager)
	{
		super(driverManager);		
	}

	public void navigateToLoginScreen()
	{
		loginScreen.click();	
	}
	
	public void navigateBack()
	{
		goBack.click();
	}
	
	public void navigateHome()
	{
		home.click();
	}
	
	@Override
	protected WebElement getPagePresenceValidatingWebElement()
	{
		return thisPage;
	}

}
