package com.ss.cuketest.steps;

import org.springframework.beans.factory.annotation.Autowired;

import com.cosmos.webdriver.context.IStepsContext;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MedispendPostLogoutPageSteps extends MedispendStepsBase{

	public MedispendPostLogoutPageSteps(@Autowired IStepsContext context)
	{
		super(context);		
	}

	@Then("^PostLogout page shows$")
	public void postlogoff_page_shows() throws Exception {
		System.out.println(pageObjectManager.getPostLogoutPage().isAt());
	}

	@When("^user clicks Login Screen button$")
	public void user_clicks_Login_Screen_button() throws Exception {
		pageObjectManager.getPostLogoutPage().navigateToLoginScreen();
	}
	
	@When("^user clicks Go Back button$")
	public void user_clicks_Go_Back_button() throws Exception {
		pageObjectManager.getPostLogoutPage().navigateToLoginScreen();
	}
	
	@When("^user clicks Home button$")
	public void user_clicks_Home_button() throws Exception {
		pageObjectManager.getPostLogoutPage().navigateToLoginScreen();
	}
	
}
