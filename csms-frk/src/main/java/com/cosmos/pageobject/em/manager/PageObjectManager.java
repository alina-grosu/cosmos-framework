package com.cosmos.pageobject.em.manager;

import com.cosmos.pageobject.em.pages.NeedsAssessmentsEditorPage;
import com.cosmos.pageobject.em.pages.EngagementManagerMainMenuPage;
import com.cosmos.pageobject.em.pages.HomeDashboardPage;
import com.cosmos.pageobject.em.pages.LoginPage;
import com.cosmos.pageobject.em.pages.PostLogoutPage;
import com.cosmos.pageobject.em.pages.UsersGroupsPermissionsPage;
import com.cosmos.webdriver.manager.IDriverManager;

public class PageObjectManager {
	
	private LoginPage loginPage;
	private HomeDashboardPage homeDashboardPage;
	private PostLogoutPage postLogoutPage;
	private UsersGroupsPermissionsPage usersGroupsPermissionsPage;
	private final IDriverManager driverManager;
	private EngagementManagerMainMenuPage engagementManagerMainMenuPage;
	private NeedsAssessmentsEditorPage editNeedsAssessmentsPage;
	
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

	public UsersGroupsPermissionsPage getUsersGroupsPermissionsPage()
	{
		if (usersGroupsPermissionsPage == null)
		{
			usersGroupsPermissionsPage = new UsersGroupsPermissionsPage(driverManager);
		}
		return usersGroupsPermissionsPage;
	}		
	
	public EngagementManagerMainMenuPage getEngagementManagerMainMenuPage()
	{
		if (engagementManagerMainMenuPage == null)
		{
			engagementManagerMainMenuPage = new EngagementManagerMainMenuPage(driverManager);
		}
		return engagementManagerMainMenuPage;
	}
	
	public NeedsAssessmentsEditorPage getNeedsAssessmentsEditorPage()
	{
		if (editNeedsAssessmentsPage == null)
		{
			editNeedsAssessmentsPage = new NeedsAssessmentsEditorPage(driverManager);
		}
		return editNeedsAssessmentsPage;
	}
}
