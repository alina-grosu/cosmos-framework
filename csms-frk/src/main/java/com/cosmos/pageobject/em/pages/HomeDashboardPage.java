package com.cosmos.pageobject.em.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cosmos.pageobject.em.pages.pagecomponents.ToolsMenuComponent;
import com.cosmos.util.WaitUtils;
import com.cosmos.webdriver.manager.IDriverManager;

public class HomeDashboardPage 	extends CommonInternalPage {
	
	@FindBy(how = How.XPATH, using = "//div[@class = 'navbar-collapse collapse']")
	private WebElement thisPage;
	@FindBy(how = How.XPATH, using = "//li[a[@href = '/tools/']]")
	private ToolsMenuComponent tools; 
	@FindBy(how = How.XPATH, using = "//div[@class='modal in']")
	private WebElement overlay;
	@FindBy(how = How.XPATH, using = "//body[//div[@class='modal' and contains(@style, 'display: none')]]")
	private WebElement noOverlay;
	@FindBy(how = How.XPATH, using = "	//div[@id = 'engagmentManagerLink']")
	private WebElement engagementManager;	
	
	public HomeDashboardPage(IDriverManager driverManager)
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
	
	public void selectUsersGroupsPermissions()
	{
		tools.click();
		tools.selectUsersGroupsPermissions();		
	}

	public void enterEngagementManager()
	{		
		engagementManager.click();
	}	
	
	private void waitPageToLoad()
	{
		WaitUtils.waitUntilElementVisible(noOverlay, driverManager.getDriver());
	}
}
