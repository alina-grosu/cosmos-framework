package com.ss.cuketest.steps;

import org.springframework.beans.factory.annotation.Autowired;
import com.cosmos.webdriver.context.IStepsContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MedispendLoginPageSteps extends MedispendStepsBase{
	private static final Logger logger = LogManager.getLogger();
		
	public MedispendLoginPageSteps(@Autowired IStepsContext context)
	{
		super(context);							
	}
	
	@Given("^user navigates to login page$")
	public void user_navigates_to_login_page() throws Exception
	{

		System.out.println(1);
		System.out.println("Started in thread " + Thread.currentThread().getId());
		pageObjectManager.get("https://login.tst-us-east.medispend.com/dashboard/login");
		assertTrue(pageObjectManager.getLoginPage().isAt());
		Thread.sleep(5000);

	}
	
	@When("^user inputs \"([^\"]*)\" as login and \"([^\"]*)\" as password$")
	public void user_inputs_as_login_and_as_password(String login, String password) throws Exception {		
		pageObjectManager.getLoginPage().inputCredentials(login, password);		
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
