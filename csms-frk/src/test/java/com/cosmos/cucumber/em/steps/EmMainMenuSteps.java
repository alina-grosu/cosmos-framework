package com.cosmos.cucumber.em.steps;

import com.cosmos.cucumber.context.ITestUiContext;
import com.cosmos.pageobject.em.manager.PageObjectManager;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EmMainMenuSteps {

	private final ITestUiContext<PageObjectManager> uiContext;

	public EmMainMenuSteps (ITestUiContext<PageObjectManager> uiContext)
	{
		this.uiContext = uiContext;
	}
	
	@Then("^Engagement Manager shows$")
	public void engagement_Manager_shows() throws Exception {	    
	   uiContext.getPageObjectManager().getEngagementManagerMainMenuPage().isAt();
	}
	
	@When("^User initiates new Needs Assessment$")
	public void user_initiates_new_Needs_Assessment() throws Exception {
		 uiContext.getPageObjectManager().getEngagementManagerMainMenuPage().startNewNeedsAssessment();
	}
	
}
