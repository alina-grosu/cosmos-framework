package com.ss.cuketest.steps;

import org.springframework.beans.factory.annotation.Autowired;

import com.cosmos.webdriver.context.IStepsContext;
import cucumber.api.java.en.Then;

public class MedispendHomeDashboardPageSteps extends MedispendStepsBase {

	public MedispendHomeDashboardPageSteps (@Autowired IStepsContext context)
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
