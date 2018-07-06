package com.cosmos.pageobject.em.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cosmos.util.WaitUtils;
import com.cosmos.webdriver.manager.IDriverManager;

public class EngagementManagerMainMenuPage extends BasePage {
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'contentBody') and //div[@class = 'dashboard-item']]")
	private WebElement thisPage;
	@FindBy(how = How.XPATH, using = "//a[@class and @href = '/needs-assessments/editor']")
	private WebElement newNeedsAssessmentsButton;
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'globalOverlay ')]")
	private WebElement overlay;
	@FindBy(how = How.XPATH, using = "//body[div[@id = 'root']/div[@data-reactroot]/span[not(@*) and not(*)]]")
	private WebElement noOverlay;
	
	
	
	public EngagementManagerMainMenuPage(IDriverManager driverManager)
	{
		super(driverManager);		
	}

	@Override
	protected WebElement getPagePresenceValidatingWebElement()
	{		
		return thisPage;
	}
	
	public void startNewNeedsAssessment()
	{
		newNeedsAssessmentsButton.click();
	}

	@Override
	public boolean isAt()
	{
		waitPageToLoad();
		return super.isAt();
	}
	
	private void waitPageToLoad()
	{
		WaitUtils.waitUntilElementVisible(noOverlay, driverManager.getDriver());
	}

}
