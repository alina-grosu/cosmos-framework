package com.ss.cuketest.steps;

import static com.cosmos.webdriver.util.AshotUtils.*;
import static com.cosmos.webdriver.util.ImageUtils.preserveUiComparisonResults;
import static com.cosmos.webdriver.util.ImageUtils.saveCurrentIfExpectedAbsent;
import static org.junit.Assert.assertTrue;
import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cosmos.resource.TestResourcesEnum;
import com.cosmos.webdriver.context.ITestConfigurationContext;
import com.cosmos.webdriver.context.ITestResourceContext;
import com.cosmos.webdriver.context.ITestUiContext;
import com.cosmos.webdriver.pageobject.manager.PageObjectManager;
import com.cosmos.webdriver.uicomparison.ashot.DownUpScrollingShootingStrategyDecorator;
import com.cosmos.webdriver.uicomparison.ashot.HorizontalScrollRemovingShootingStrategyDecorator;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

public class EmLoginPageSteps extends EmStepsBase {
	
	private static final Logger logger = LogManager.getLogger();
	private final ITestResourceContext resourceContext;
	private final ITestUiContext<PageObjectManager> uiContext;
	private final ITestConfigurationContext configurationContext;	
	private final PageObjectManager pageObjectManager;
		
	public EmLoginPageSteps(
							ITestUiContext<PageObjectManager> uiContext,
							ITestResourceContext resourceContext,
							ITestConfigurationContext configurationContext
							)
	{
		this.uiContext = uiContext;		
		this.resourceContext = resourceContext;
		this.configurationContext = configurationContext;
		this.pageObjectManager = uiContext.getPageObjectManager();
		
		logger.debug(String
				.format("Instantiated [%s] using ui context [%s], resource context [%s], configuration context [%s]",
						this.toString(),
						uiContext.toString(),
						resourceContext.toString(),
						configurationContext.toString()
						));
	}
	
	@Given("^user navigates to login page$")
	public void user_navigates_to_login_page() throws Exception
	{		
		uiContext
			.getDriverManager()
			.getDriver()
			.get(configurationContext.getTestConfig().getAppUnderTestUrl().toString());
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
				
		WebDriver webDriver = uiContext.getDriverManager().getDriver();		
		List<WebElement> elementsToIgnore = uiContext.getPageObjectManager().getLoginPage().getElementsToIgnore();			
		ShootingStrategy shootingStrategy = new HorizontalScrollRemovingShootingStrategyDecorator(
				new DownUpScrollingShootingStrategyDecorator(ShootingStrategies.simple()));
		
		Screenshot actualScreen = getScreenshot(webDriver, elementsToIgnore, shootingStrategy);
		
		Path baseScreenshotLocation = 
				resourceContext
					.getResourceLocator()
					.getResourcePath(baseScreenshotName, TestResourcesEnum.EXPECTED_SCREENSHOTS_DIR);				
							
		saveCurrentIfExpectedAbsent(baseScreenshotLocation, actualScreen.getImage());
		
		Screenshot expected = loadScreenshot(baseScreenshotLocation);
		expected.setIgnoredAreas(actualScreen.getIgnoredAreas());
		ImageDiff diff = new ImageDiffer().makeDiff(expected, actualScreen);							
		
		if (diff.hasDiff())
		{						
			Path failureScreenshotsLocation = 
					resourceContext
						.getResourceLocator()
						.getResourcePath(TestResourcesEnum.FAILURE_SCREENSHOTS_DIR);
			
			preserveUiComparisonResults(failureScreenshotsLocation, 
					actualScreen.getImage(), 
					expected.getImage(), 
					diff.getMarkedImage());
			
			assertTrue("Ui comparison has failed!", false);
		}
	}			
}
