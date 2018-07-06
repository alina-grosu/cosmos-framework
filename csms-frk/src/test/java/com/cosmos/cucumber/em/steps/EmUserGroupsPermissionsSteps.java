package com.cosmos.cucumber.em.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cosmos.cucumber.context.ITestConfigurationContext;
import com.cosmos.cucumber.context.ITestResourceContext;
import com.cosmos.cucumber.context.ITestUiContext;
import com.cosmos.pageobject.em.manager.PageObjectManager;

import cucumber.api.java.en.Then;

public class EmUserGroupsPermissionsSteps {

	private static final Logger logger = LogManager.getLogger();
	private final ITestUiContext<PageObjectManager> uiContext;
	private final ITestResourceContext resourceContext;	
	
	public EmUserGroupsPermissionsSteps(
			ITestUiContext<PageObjectManager> uiContext,
			ITestResourceContext resourceContext			
			)
	{
		this.uiContext = uiContext;		
		this.resourceContext = resourceContext;				

		logger.debug(String
				.format("Instantiated [%s] using ui context [%s], resource context [%s]",
						this.toString(),
						uiContext.toString(),
						resourceContext.toString()						
						));
	}
	
	@Then("^User cloaks as role \"([^\"]*)\"$")
	public void user_cloaks_as_role(String role) throws Exception {
		uiContext
			.getPageObjectManager()
			.getUsersGroupsPermissionsPage()
			.cloakAsRole(role);
	}
	
	@Then("^Users, Groups and Permissions view shows$")
	public void users_Groups_and_Permissions_view_shows() throws Exception {
	    uiContext.getPageObjectManager().getUsersGroupsPermissionsPage().isAt();
	}
	
}
