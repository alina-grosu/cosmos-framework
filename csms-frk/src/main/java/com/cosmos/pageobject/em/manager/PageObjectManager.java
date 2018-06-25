package com.cosmos.pageobject.em.manager;

import com.cosmos.pageobject.em.pages.HomeDashboardPage;
import com.cosmos.pageobject.em.pages.LoginPage;
import com.cosmos.pageobject.em.pages.PostLogoutPage;
import com.cosmos.webdriver.manager.IDriverManager;

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
}
