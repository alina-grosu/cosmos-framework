package com.cosmos.cucumber.em.steps;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.cosmos.cucumber.context.ITestUiContext;
import com.cosmos.pageobject.em.manager.PageObjectManager;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class EmNeedsAssessmentEditorSteps {

	private final ITestUiContext<PageObjectManager> uiContext;

	public EmNeedsAssessmentEditorSteps (ITestUiContext<PageObjectManager> uiContext)
	{
		this.uiContext = uiContext;
	}
	
	@Then("^Needs Assessment shows$")
	public void needs_Assessment_shows() throws Exception {
	   uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().isAt();	  
	}
	
	@Then("^User clears Needs Assessments Editor$")
	public void user_clears_Needs_Assessments_Editor() throws Exception {
	   uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().clear();	  
	}
	
	@Then("^User selects Bussiness Unit \"([^\"]*)\"$")
	public void user_selects_Bussiness_Unit(String bu) throws Exception {
		uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().selectBusinessUnit(bu);
	}
	
	
	@Then("^User selects next Bussiness Units$")
	public void user_selects_next_Bussiness_Units(List<String> bus) throws Exception {
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
	
	@And("^User add next data to Needs Assessent$")
	public void user_add_next_data_to_Needs_Assessent(Map<String, String> form) throws Exception 
	{
		uiContext.getPageObjectManager().getNeedsAssessmentsEditorPage().setData(form);
	}
}
