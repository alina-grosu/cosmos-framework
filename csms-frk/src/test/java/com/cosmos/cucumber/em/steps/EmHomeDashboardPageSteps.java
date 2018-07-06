package com.cosmos.cucumber.em.steps;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.cosmos.cucumber.context.ITestUiContext;
import com.cosmos.pageobject.em.manager.PageObjectManager;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EmHomeDashboardPageSteps extends EmStepsBase {

	private final ITestUiContext<PageObjectManager> uiContext;
	private final PageObjectManager pageObjectManager;		
	private final Map<String, Consumer<PageObjectManager>> views = new HashMap<>();
	{
		views.put("Users, Groups and Permissions", (pom) -> pom
															.getHomeDashboard()															
															.selectUsersGroupsPermissions());
	}
	
	
	public EmHomeDashboardPageSteps (ITestUiContext<PageObjectManager> uiContext)
	{
		this.uiContext = uiContext;
		this.pageObjectManager = uiContext.getPageObjectManager();
	}	
	
	@Then("^Dashboard page shows$")
	public void dashboard_page_shows() throws Exception 
	{
		pageObjectManager.getHomeDashboard().isAt();
	}
	
	@Then("^user logs off from Dashboard$")
	public void user_logs_off_from_Dashboard() throws Exception {
		pageObjectManager.getHomeDashboard().logout();
	}
	
	@Then("^User navigates to \"([^\"]*)\" view$")
	public void user_navigates_to_view(String viewName) throws Exception 
	{
		views.get(viewName).accept(pageObjectManager);
	}
	
	@When("^user selects Engagement Manager$")
	public void user_selects_Engagement_Manager() throws Exception {
	   pageObjectManager.getHomeDashboard().enterEngagementManager();
	}
	
}
