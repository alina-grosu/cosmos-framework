package com.cosmos.webdriver.pageobject.manager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.pageobject.pages.HomeDashboardPage;
import com.cosmos.webdriver.pageobject.pages.LoginPage;
import com.cosmos.webdriver.pageobject.pages.PostLogoutPage;

public class PageObjectManager {
	
	private LoginPage loginPage;
	private HomeDashboardPage homeDashboardPage;
	private PostLogoutPage postLogoutPage;
	private final IDriverManager driverManager;
	
	public PageObjectManager(IDriverManager driverManager)
	{
		this.driverManager = driverManager;
	}
	
	public LoginPage getLoginPage()
	{
		if (loginPage == null)
		{
			loginPage = new LoginPage(driverManager);
		}
		return loginPage;
	}
	
	public HomeDashboardPage getHomeDashboard()
	{
		if (homeDashboardPage == null)
		{
			homeDashboardPage = new HomeDashboardPage(driverManager);
		}
		return homeDashboardPage;
	}
	
	public PostLogoutPage getPostLogoutPage()
	{
		if (postLogoutPage == null)
		{
			postLogoutPage = new PostLogoutPage(driverManager);
		}
		return postLogoutPage;
	}
	
	public void quit()
	{
		driverManager.quit();
	}
	
	public void quitDriver()
	{
		driverManager.quitDriver();
	}
	
	public void get(String url)
	{		
		driverManager.getDriver().get(url);
	}
	
	public <T> T getScreenshotAs (OutputType<T> type)
	{
		return ((TakesScreenshot)driverManager.getDriver()).getScreenshotAs(type);
	}
	
	

}
