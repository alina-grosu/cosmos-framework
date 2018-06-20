package com.ss.cuketest.steps;

import com.cosmos.webdriver.context.ITestUiContext;
import cucumber.api.java.en.Then;

public class EmHomeDashboardPageSteps extends EmStepsBase {

	public EmHomeDashboardPageSteps (ITestUiContext context)
	{
		super(context);
	}	
	
	@Then("^Dashboard page shows$")
	public void dashboard_page_shows() throws Exception {
		System.out.println(pageObjectManager.getHomeDashboard().isAt());
	}
	
	@Then("^user logs off from Dashboard$")
	public void user_logs_off_from_Dashboard() throws Exception {
		pageObjectManager.getHomeDashboard().logout();
	}
	
}
