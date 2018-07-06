package com.cosmos.pageobject.em.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cosmos.pageobject.em.pages.pagecomponents.commons.MultiSelectCheckedDropBox;
import com.cosmos.util.WaitUtils;
import com.cosmos.webdriver.manager.IDriverManager;

import ru.yandex.qatools.htmlelements.element.Select;

public class NeedsAssessmentsEditorPage extends BasePage {

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'contentBody')]//div[@class = 'needsAssessmentFormWrapper']")
	private WebElement thisPage;
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'globalOverlay ')]")
	private WebElement overlay;		
	@FindBy(how = How.XPATH, using = "//input[contains(@id, '-titleField')]")
	private WebElement title;	
	@FindBy(how = How.XPATH, using = "//body[div[@id = 'root']/div[@data-reactroot]/span[not(@*) and not(*)]]")
	private WebElement noOverlay;	
	@FindBy(how = How.XPATH, using = "//button[contains(@class, 'em-clearButton')]")
	private WebElement clearButton;
	@FindBy(how = How.XPATH, using = "//span[select[@id = 'needsAssessments-editor-businessUnitField']]")
	private MultiSelectCheckedDropBox bussinessUnitSelector;
	
	
	
	public NeedsAssessmentsEditorPage(IDriverManager driverManager)
	{
		super(driverManager);		
	}

	@Override
	protected WebElement getPagePresenceValidatingWebElement()
	{		
		return thisPage;
	}

	@Override
	public boolean isAt()
	{
		waitPageToLoad();
		return super.isAt();
	}
	
	public NeedsAssessmentsEditorPage enterTitle(String title)
	{
		this.title.sendKeys(title);
		return this;
	}
	
	public NeedsAssessmentsEditorPage selectBusinessUnit(String bu)
	{		
		bussinessUnitSelector.clear();
		bussinessUnitSelector.selectByText(bu);				
		return this;
	}
	
	private void waitPageToLoad()
	{
		WaitUtils.waitUntilElementVisible(noOverlay, driverManager.getDriver());
	}

	public NeedsAssessmentsEditorPage clear()
	{
		clearButton.click();
		return this;
	}

	public NeedsAssessmentsEditorPage selectBusinessUnits(List<String> bus)
	{
		bussinessUnitSelector.clear();
		bussinessUnitSelector.selectByText(bus);				
		return this;
	}

}