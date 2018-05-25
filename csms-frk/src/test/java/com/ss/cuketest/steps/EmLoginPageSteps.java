package com.ss.cuketest.steps;

import com.cosmos.resource.ITestResource;
import com.cosmos.resource.TestResourcesEnum;
import com.cosmos.webdriver.context.ITestResourceContext;
import com.cosmos.webdriver.context.IUiComparisonContext;
import com.cosmos.webdriver.context.IUiDrivingStepContext;
import com.cosmos.webdriver.uicomparison.IUiComparisonResult;
import com.cosmos.webdriver.uicomparison.IUiComparisonResult.UiComparisonStatusEnum;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.cosmos.webdriver.util.ImageUtils.preserveUiComparisonResults;
import static com.cosmos.webdriver.util.ImageUtils.saveCurrentIfExpectedAbsent;
import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class EmLoginPageSteps extends EmStepsBase{
	private static final Logger logger = LogManager.getLogger();
	private ITestResourceContext resourceContext;
	private IUiComparisonContext uiComparisonContext;
		
	public EmLoginPageSteps(IUiDrivingStepContext context, ITestResourceContext resourceContext, IUiComparisonContext uiComparisonContext)
	{
		super(context);		
		this.resourceContext = resourceContext;
		this.uiComparisonContext = uiComparisonContext;
	}
	
	@Given("^user navigates to login page$")
	public void user_navigates_to_login_page() throws Exception
	{		
		pageObjectManager.get(uiDrivingContext.getConfiguration().getAppUnderTestUrl().toString());
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
	   String loginError = pageObjectManager.getLoginPage().getLoginErrorMessage();
	   logger.debug(String.format("message: %s ; loginError: %s ;", message, loginError));
	   assertTrue(loginError.equalsIgnoreCase(message));
	}
	
	@And("^Login page looks like \"([^\"]*)\"$")
	public void login_page_looks_like(String baseScreenshotName) throws Exception {
				
		WebDriver actualScreenshotProvider = uiDrivingContext.getDriverManager().getDriver();		
	
		Path baseScreenshotLocation = 
				resourceContext
					.getResourceLocator()
					.getResourcePath(baseScreenshotName, TestResourcesEnum.EXPECTED_SCREENSHOTS_DIR);
		
		saveCurrentIfExpectedAbsent(baseScreenshotLocation, actualScreenshotProvider);
			
		BufferedImage baseScreenshot = ITestResource.IMAGE.load(baseScreenshotLocation);	
		
		IUiComparisonResult uiComparisonResult = uiComparisonContext
						.getUiComparator()
						.compare(baseScreenshot, 
								actualScreenshotProvider, 
								uiDrivingContext.getPageObjectManager().getLoginPage().getElementsToIgnore());
		
		if (uiComparisonResult.getUiComparisonStatus() == UiComparisonStatusEnum.FAIL)
		{						
			Path failureScreenshotsLocation = 
					resourceContext
						.getResourceLocator()
						.getResourcePath(TestResourcesEnum.FAILURE_SCREENSHOTS_DIR);
			preserveUiComparisonResults(failureScreenshotsLocation, uiComparisonResult);
		}
	}
	
}
