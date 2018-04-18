package com.ss.cuketest.steps;

import com.cosmos.webdriver.context.IStepsContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmLoginPageSteps extends EmStepsBase{
	private static final Logger logger = LogManager.getLogger();
		
	public EmLoginPageSteps(IStepsContext context)
	{
		super(context);							
	}
	
	@Given("^user navigates to login page$")
	public void user_navigates_to_login_page() throws Exception
	{		
		pageObjectManager.get(context.getConfiguration().getAppUnderTestUrl().toString());
		assertTrue(pageObjectManager.getLoginPage().isAt());		
	}
	
	@When("^user inputs \"([^\"]*)\" as login and \"([^\"]*)\" as password$")
	public void user_inputs_as_login_and_as_password(String login, String password) throws Exception {		
		pageObjectManager.getLoginPage().inputCredentials(login, password);	
		//assertTrue(false);
	}

	@And("^clicks Login button$")
	public void clicks_Login_button() throws Exception {
		pageObjectManager.getLoginPage().login();		
	}			

	@Then("^LoginPage shows$")
	public void loginpage_shows() throws Exception {
		assertTrue(pageObjectManager.getLoginPage().isAt());
	}
	
	@Then("^error with text \"([^\"]*)\" appears$")
	public void error_with_text_appears(String message) throws Exception {
	   String loginError = pageObjectManager.getLoginPage().getLoginError();
	   logger.debug(String.format("message: %s ; loginError: %s ;", message, loginError));
	   assertTrue(loginError.equalsIgnoreCase(message));
	}
	
}
