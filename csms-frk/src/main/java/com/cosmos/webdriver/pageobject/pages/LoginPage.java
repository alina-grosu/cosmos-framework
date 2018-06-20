package com.cosmos.webdriver.pageobject.pages;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.pageobject.pages.pagecomponents.LoginFormComponent;
import com.cosmos.webdriver.uicomparison.IUiComparisonIgnorableElementsAware;

public class LoginPage 
			extends BasePage
			implements IUiComparisonIgnorableElementsAware {

	@FindBy(how = How.XPATH, using = "//body[//div[@id='login-container']]")
	private WebElement thisPage;	
	@FindBy(how = How.XPATH, using = "//div[@class = 'auth0-lock-center']")
	private LoginFormComponent loginForm;	
	
	public LoginPage(IDriverManager driverManager)
	{
		super(driverManager);	
	}
	
	@Override
	protected WebElement getPagePresenceValidatingWebElement()
	{
		return thisPage;
	}
	
	public LoginPage inputCredentials(String login, String password)
	{
		loginForm.inputCredentials(login, password);		
		return this;		
	}
	
	public void login()
	{		
		loginForm.clickLogin();
	}
	
	public String getLoginErrorMessage()
	{
		return loginForm.getErrorMessage();		
	}

	@Override
	public List<WebElement> getElementsToIgnore()
	{
		List<WebElement> elementsToIgnore = new LinkedList<>();
		elementsToIgnore.addAll(loginForm.getElementsToIgnore());				
		return elementsToIgnore;
	}	

}

