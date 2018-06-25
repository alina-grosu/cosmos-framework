package com.cosmos.cucumber.em.steps;

import com.cosmos.cucumber.context.ITestResourceContext;
import com.cosmos.cucumber.context.ITestUiContext;
import com.cosmos.pageobject.em.manager.PageObjectManager;
import com.cosmos.resource.TestResourcesEnum;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.cosmos.util.AshotUtils.getScreenshot;
import static com.cosmos.util.AshotUtils.loadScreenshot;
import static com.cosmos.util.ImageUtils.*;
import static org.junit.Assert.assertTrue;


public class EmPostLogoutPageSteps extends EmStepsBase {
	
	private static final Logger logger = LogManager.getLogger();
	
	private final ITestResourceContext resourceContext;
	private final ITestUiContext<PageObjectManager> uiContext;	

	public EmPostLogoutPageSteps(ITestUiContext<PageObjectManager> uiContext, ITestResourceContext resourceContext)
	{
		this.uiContext = uiContext;	
		this.resourceContext = resourceContext;	
		logger.debug(String
						.format("Instantiated %s using ui context %s and resource context %s",
								this.toString(),
								uiContext.toString(),
								resourceContext.toString()
								));
	}

	@Then("^PostLogout page shows$")
	public void postlogoff_page_shows() throws Exception {
		System.out.println(uiContext.getPageObjectManager().getPostLogoutPage().isAt());
	}

	@When("^user clicks Login Screen button$")
	public void user_clicks_Login_Screen_button() throws Exception {
		uiContext.getPageObjectManager().getPostLogoutPage().navigateToLoginScreen();
	}
	
	@When("^user clicks Go Back button$")
	public void user_clicks_Go_Back_button() throws Exception {
		uiContext.getPageObjectManager().getPostLogoutPage().navigateToLoginScreen();
	}
	
	@When("^user clicks Home button$")
	public void user_clicks_Home_button() throws Exception {
		uiContext.getPageObjectManager().getPostLogoutPage().navigateToLoginScreen();
	}
	
	@And("^PostLogout page looks like \"([^\"]*)\"$")
	public void postlogout_page_looks_like(String baseScreenshotName) throws Exception {
				
		WebDriver webDriver = uiContext.getDriverManager().getDriver();		
		List<WebElement> elementsToIgnore = uiContext.getPageObjectManager().getPostLogoutPage().getElementsToIgnore();					
		
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
