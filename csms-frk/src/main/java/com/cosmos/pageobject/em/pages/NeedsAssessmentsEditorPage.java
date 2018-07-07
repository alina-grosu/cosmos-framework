package com.cosmos.pageobject.em.pages;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cosmos.pageobject.em.pages.pagecomponents.commons.Calendar;
import com.cosmos.pageobject.em.pages.pagecomponents.commons.MultiSelectCheckedDropBox;
import com.cosmos.util.WaitUtils;
import com.cosmos.webdriver.manager.IDriverManager;

import ru.yandex.qatools.htmlelements.element.Select;

public class NeedsAssessmentsEditorPage extends BasePage {

	private static final Map<String, BiConsumer<NeedsAssessmentsEditorPage, String>> inputActions = new HashMap<>();
	static 
	{
		inputActions.put("Title", (page, title) -> page.enterTitle(title));
		inputActions.put("Description", (page, desc) -> page.enterDescription(desc));
		inputActions.put("Justification", (page, just) -> page.enterJustification(just));
		inputActions.put("Effective Date", (page, date) -> page.setEffectiveDate(LocalDate.parse(date)));
		inputActions.put("Expiration Date", (page, date) -> page.setExpirationDate(LocalDate.parse(date)));
		inputActions.put("Year", (page, year) -> page.selectYear(year));
	}
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'contentBody')]//div[@class = 'needsAssessmentFormWrapper']")
	private WebElement thisPage;
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'globalOverlay ')]")
	private WebElement overlay;		
	@FindBy(how = How.XPATH, using = ".//input[contains(@id, '-titleField')]")
	private WebElement title;	
	@FindBy(how = How.XPATH, using = "//body[div[@id = 'root']/div[@data-reactroot]/span[not(@*) and not(*)]]")
	private WebElement noOverlay;	
	@FindBy(how = How.XPATH, using = ".//button[contains(@class, 'em-clearButton')]")
	private WebElement clearButton;
	@FindBy(how = How.XPATH, using = ".//span[select[@id = 'needsAssessments-editor-regionField']]")
	private MultiSelectCheckedDropBox regionSelector;
	@FindBy(how = How.XPATH, using = ".//span[select[@id = 'needsAssessments-editor-businessUnitField']]")
	private MultiSelectCheckedDropBox bussinessUnitSelector;
	@FindBy(how = How.XPATH, using = ".//button[contains(@class, 'em-saveButton')]")
	private WebElement saveButton;
	@FindBy(how = How.XPATH, using = ".//textarea[contains(@id, '-descriptionField')]")
	private WebElement description;
	@FindBy(how = How.XPATH, using = ".//textarea[contains(@id, '-justificationField')]")
	private WebElement justification;
	@FindBy(how = How.XPATH, using = ".//input[contains(@id, '-rootIdField')]")
	private WebElement originalId;
	@FindBy(how = How.XPATH, using = ".//input[contains(@id, '-idField')]")
	private WebElement id;
	@FindBy(how = How.XPATH, using = ".//input[contains(@id, '-versionIndexField')]")
	private WebElement version;
	@FindBy(how = How.XPATH, using = ".//select[@id = 'needsAssessments-editor-functionalAreaField']")
	private Select functionalAreaSelector;
	@FindBy(how = How.XPATH, using = ".//div[@class = 'reactBootstrapDatePickerFront' and .//input[contains(@id, '-effectiveDateField')]]/parent::div")
	private Calendar effectiveDate;
	@FindBy(how = How.XPATH, using = ".//div[@class = 'reactBootstrapDatePickerFront' and .//input[contains(@id, '-expirationDateField')]]/parent::div")
	private Calendar expirationDate;
	@FindBy(how = How.XPATH, using = ".//select[@id='needsAssessments-editor-yearField']")
	private Select year;
	
	public NeedsAssessmentsEditorPage(IDriverManager driverManager)
	{
		super(driverManager);		
	}

	public NeedsAssessmentsEditorPage selectYear(String year)
	{
		this.year.selectByVisibleText(year);
		return this;
	}

	public NeedsAssessmentsEditorPage enterJustification(String just)
	{
		justification.sendKeys(just);
		return this;
	}

	public NeedsAssessmentsEditorPage enterDescription(String desc)
	{
		description.sendKeys(desc);
		return this;
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

	public NeedsAssessmentsEditorPage setExpirationDate(LocalDate date)
	{
		expirationDate.setDate(date);	
		return this;
	}

	public NeedsAssessmentsEditorPage setEffectiveDate(LocalDate date)
	{
		effectiveDate.setDate(date);	
		return this;		
	}

	public void setData(Map<String, String> form)
	{
		for (String key : form.keySet())
		{
			inputActions.get(key).accept(this, form.get(key));
		}				
	}

}
