package com.ss.cuketest.steps;

import com.cosmos.resource.TestResourcesEnum;
import com.cosmos.webdriver.context.ITestResourceContext;
import com.cosmos.webdriver.context.ITestUiContext;
import com.cosmos.webdriver.uicomparison.ashot.DownUpScrollingShootingStrategyDecorator;
import com.cosmos.webdriver.uicomparison.ashot.HorizontalScrollRemovingShootingStrategyDecorator;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.cosmos.webdriver.util.AshotUtils.getScreenshot;
import static com.cosmos.webdriver.util.AshotUtils.loadScreenshot;
import static com.cosmos.webdriver.util.ImageUtils.*;
import static org.junit.Assert.assertTrue;


public class EmPostLogoutPageSteps extends EmStepsBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private ITestResourceContext resourceContext;

	public EmPostLogoutPageSteps(ITestUiContext uiDrivingContext, ITestResourceContext resourceContext)
	{
		this.uiDrivingContext = uiDrivingContext;		
		this.resourceContext = resourceContext;
	}

	@Then("^PostLogout page shows$")
	public void postlogoff_page_shows() throws Exception {
		System.out.println(uiDrivingContext.getPageObjectManager().getPostLogoutPage().isAt());
	}

	@When("^user clicks Login Screen button$")
	public void user_clicks_Login_Screen_button() throws Exception {
		uiDrivingContext.getPageObjectManager().getPostLogoutPage().navigateToLoginScreen();
	}
	
	@When("^user clicks Go Back button$")
	public void user_clicks_Go_Back_button() throws Exception {
		uiDrivingContext.getPageObjectManager().getPostLogoutPage().navigateToLoginScreen();
	}
	
	@When("^user clicks Home button$")
	public void user_clicks_Home_button() throws Exception {
		uiDrivingContext.getPageObjectManager().getPostLogoutPage().navigateToLoginScreen();
	}
	
	@And("^PostLogout page looks like \"([^\"]*)\"$")
	public void postlogout_page_looks_like(String baseScreenshotName) throws Exception {
				
		WebDriver webDriver = uiDrivingContext.getDriverManager().getDriver();		
		List<WebElement> elementsToIgnore = uiDrivingContext.getPageObjectManager().getPostLogoutPage().getElementsToIgnore();					
		
		Screenshot actualScreen = getScreenshot(webDriver, elementsToIgnore, ShootingStrategies.simple());
		
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
