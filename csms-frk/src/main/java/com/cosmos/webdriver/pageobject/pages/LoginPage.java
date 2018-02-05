package com.cosmos.webdriver.pageobject.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.cosmos.webdriver.manager.IDriverManager;

public class LoginPage extends BasePage {

	@FindBy(how = How.XPATH, using = "//body[//div[@id='login-container']]")
	private WebElement thisPage;
	@FindBy(how = How.XPATH, using = "//input[@type='email']")
	private WebElement login;
	@FindBy(how = How.XPATH, using = "//input[@type='password']")
	private WebElement password;
	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement loginButton;
	@FindBy(how = How.XPATH, using = "//span[parent::div[contains(@class, 'auth0-global-message-error')]]")
	private WebElement errorMessage;
	
	
	public LoginPage(IDriverManager driverManager)
	{
		super(driverManager);	
	}
	
	@Override
	protected WebElement getPagePresenceValidatingWebElement()
	{
		return thisPage;
	}
	
	public LoginPage inputCredentials(String login, String password) throws InterruptedException
	{
		this.login.sendKeys(login);
		this.password.sendKeys(password);		
		return this;		
	}
	
	public void login()
	{
		loginButton.click();		
	}
	
	public String getLoginError()
	{
		return waitUntilElementVisible(errorMessage).getText().trim();
	}

}

