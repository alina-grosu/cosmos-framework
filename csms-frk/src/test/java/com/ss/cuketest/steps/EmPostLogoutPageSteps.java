package com.ss.cuketest.steps;

import com.cosmos.resource.ITestResource;
import com.cosmos.resource.TestResourcesEnum;
import com.cosmos.webdriver.context.ITestResourceContext;
import com.cosmos.webdriver.context.IUiComparisonContext;
import com.cosmos.webdriver.context.IUiDrivingStepContext;
import com.cosmos.webdriver.uicomparison.IUiComparisonResult;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static com.cosmos.webdriver.uicomparison.IUiComparisonResult.UiComparisonStatusEnum;
import static com.cosmos.webdriver.util.ImageUtils.*;


public class EmPostLogoutPageSteps extends EmStepsBase {
	
	private static final Logger logger = LogManager.getLogger();
	private final IUiComparisonContext uiComparisonContext;
	private ITestResourceContext resourceContext;

	public EmPostLogoutPageSteps(IUiDrivingStepContext uiDrivingContext, IUiComparisonContext uiComparisonContext, ITestResourceContext resourceContext)
	{
		this.uiDrivingContext = uiDrivingContext;
		this.uiComparisonContext = uiComparisonContext;
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
								uiDrivingContext.getPageObjectManager().getPostLogoutPage().getElementsToIgnore());
		
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
