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
	
	
	public EmUserGroupsPermissionsSteps(
			ITestUiContext<PageObjectManager> uiContext				
			)
	{
		this.uiContext = uiContext;				

		logger.debug(String
				.format("Instantiated [%s] using ui context [%s]",
						this.toString(),
						uiContext.toString()										
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
