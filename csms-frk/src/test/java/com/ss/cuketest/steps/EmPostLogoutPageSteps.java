package com.ss.cuketest.steps;

import com.cosmos.webdriver.context.IUiComparisonContext;
import com.cosmos.webdriver.context.IUiDrivingStepContext;
import com.cosmos.webdriver.screenshots.IScreenshotAccessor;
import com.cosmos.webdriver.uicomparison.IUiComparisonResult;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import io.qameta.allure.cucumber2jvm.CucumberSourceUtils;

import static com.cosmos.cucumber.uicomparison.FeatureLocationAwareUiComparisonHelper.*;
import static com.cosmos.webdriver.uicomparison.IUiComparisonResult.UiComparisonStatusEnum;
import static com.cosmos.webdriver.util.AllureUtils.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class EmPostLogoutPageSteps extends EmStepsBase {
	
	private static final Logger logger = LogManager.getLogger();
	private final IUiComparisonContext uiComparisonContext;

	public EmPostLogoutPageSteps(IUiDrivingStepContext uiDrivingContext, IUiComparisonContext uiComparisonContext)
	{
		this.uiDrivingContext = uiDrivingContext;
		this.uiComparisonContext = uiComparisonContext;
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
	
	@Then("^PostLogout page looks like \"([^\"]*)\"$")
	public void postlogout_page_looks_like(String baseScreenshotName) throws Exception {
				
		WebDriver actualScreenshotProvider =  uiDrivingContext.getDriverManager().getDriver();		
		IScreenshotAccessor screenshotAccessor = 
				IScreenshotAccessor
					.builder()
						.inputFileGuarding()
						.proposingIfAbsent(() -> ((TakesScreenshot) actualScreenshotProvider).getScreenshotAs(OutputType.BYTES))
						.build();
		
		Path baseScreenshotLocation = 
				uiComparisonContext
					.getScreenshotsLocationAware()
					.getBaseScreenshotsLocation()
					.resolve(uiDrivingContext.getConfiguration().getBrowser().toString().toLowerCase())
					.resolve(baseScreenshotName);
		
		BufferedImage baseScreenshot = screenshotAccessor.readScreenshot(baseScreenshotLocation);		
		
		IUiComparisonResult uiComparisonResult = uiComparisonContext
						.getUiComparator()
						.compare(baseScreenshot, 
								actualScreenshotProvider, 
								uiDrivingContext.getPageObjectManager().getPostLogoutPage().getElementsToIgnore());
		
		if (uiComparisonResult.getUiComparisonStatus() == UiComparisonStatusEnum.FAIL)
		{			
			uiComparisonContext.setLatestFailure(uiComparisonResult);			
			throw new RuntimeException("UI comparison has failed!");
		}
	}
	
}
