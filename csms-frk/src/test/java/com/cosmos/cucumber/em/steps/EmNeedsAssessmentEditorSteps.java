package com.cosmos.cucumber.em.steps;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cosmos.cucumber.context.ITestResourceContext;
import com.cosmos.cucumber.context.ITestUiContext;
import com.cosmos.pageobject.em.manager.PageObjectManager;
import com.cosmos.resource.TestResourcesEnum;
import com.cosmos.uicomparison.ashot.DownUpScrollingShootingStrategyDecorator;
import com.cosmos.uicomparison.ashot.HorizontalScrollRemovingShootingStrategyDecorator;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

import static com.cosmos.util.AshotUtils.getScreenshot;
import static com.cosmos.util.AshotUtils.loadScreenshot;
import static com.cosmos.util.ImageUtils.preserveUiComparisonResults;
import static com.cosmos.util.ImageUtils.saveCurrentIfExpectedAbsent;
import static org.junit.Assert.*;

public class EmNeedsAssessmentEditorSteps {

	private final ITestUiContext<PageObjectManager> uiContext;
	private ITestResourceContext resourceContext;

	public EmNeedsAssessmentEditorSteps (ITestUiContext<PageObjectManager> uiContext, ITestResourceContext resourceContext)
	{
		this.uiContext = uiContext;
		this.resourceContext = resourceContext;
	}
	
	@Then("^Needs Assessment shows$")
	public void needs_Assessment_shows() throws Exception {
	   uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().isAt();	  
	}
	
	@Then("^User clears Needs Assessments Editor$")
	public void user_clears_Needs_Assessments_Editor() throws Exception {
	   uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().clear();	  
	}
	
	@Then("^User selects Business Unit \"([^\"]*)\"$")
	public void user_selects_Business_Unit(String bu) throws Exception {
		uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().selectBusinessUnit(bu);
	}
	
	
	@Then("^User selects next Business Units$")
	public void user_selects_next_Business_Units(List<String> bus) throws Exception {
		uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().selectBusinessUnits(bus);
	}
	
	@And("^User picks Expiration Date as \"([^\"]*)\"$")
	public void user_picks_Expiration_Date_as(String date) throws Exception {
	    uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().setExpirationDate(LocalDate.parse(date));
	}
	
	@And("^User picks Effective Date as \"([^\"]*)\"$")
	public void user_picks_Effective_Date_as(String date) throws Exception {
	    uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().setEffectiveDate(LocalDate.parse(date));
	}
	
	@And("^User adds next data to Needs Assessent$")
	public void user_adds_next_data_to_Needs_Assessent(Map<String, String> form) throws Exception 
	{
		uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().setData(form);
	}
	
	@Then("^User saves Needs Assessment$")
	public void user_saves_Needs_Assessment() throws Exception {
		uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().save();
	}
	
	@Then("^Needs Assessment is filled with next data$")
	public void needs_Assessment_is_filled_with_next_data(Map<String, String> data) throws Exception {
		assertTrue(uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().checkData(data));
	}
	
	@Then("^User adds next Business Units$")
	public void user_adds_next_Business_Units(List<String> bus) throws Exception {
		uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().addBusinessUnits(bus);
	}
	
	@Then("^Business Units has next items selected$")
	public void business_Units_has_next_items_selected(List<String> bus) throws Exception {
		Set<String> actualBus= uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().getCurrentBusinessUnits();
		assertEquals(actualBus, new HashSet<String> (bus));
	}
	
	@And("^Needs Assessments Editor looks like \"([^\"]*)\"$")
	public void needs_Assessments_Editor_looks_like(String baseScreenshotName) throws Exception 
	{
				
		WebDriver webDriver = uiContext.getDriverManager().getDriver();		
		List<WebElement> elementsToIgnore = uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().getElementsToIgnore();			
		ShootingStrategy shootingStrategy = ShootingStrategies.simple();		
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
		}
		assertFalse("Ui comparison has failed!", diff.hasDiff());
	}		
	
}
