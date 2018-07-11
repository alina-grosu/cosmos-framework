package com.cosmos.pageobject.em.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cosmos.util.WaitUtils;
import com.cosmos.webdriver.manager.IDriverManager;

import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.Table;

public class UsersGroupsPermissionsPage extends BasePage {
	
	@FindBy(how = How.XPATH, using = "//body[@class = '']")
	private WebElement thisPage;	
	@FindBy(how = How.XPATH, using = "//body[//div[@class='modal' and contains(@style, 'display: none')]]")
	private WebElement noOverlay;
	@FindBy(how = How.XPATH, using = "//input[@type = 'emailAddress']")	
	private WebElement email;
	@FindBy(how = How.XPATH, using = "//select[@id = 'securityProfile_object_id'][ancestor::div[@class = 'panel panel-default']]")
	private Select securityProfileName;
	@FindBy(how = How.XPATH, using = "//button[@id = 'btnSearchUser']")	
	private WebElement search;
	@FindBy(how = How.XPATH, using = "//button[@id = 'buttonChange']")	
	private WebElement changeDomain;
	@FindBy(how = How.XPATH, using = "//div[@id = 'subDomainSelectorModal']//select[@id = 'clientSelector']")	
	private Select clientSelector;
	@FindBy(how = How.XPATH, using = "//div[@class = 'modal-footer']//button[contains(@class, 'btn-success')]")	
	private WebElement apply;	
	@FindBy(how = How.XPATH, using = "//div[@id = 'subDomainSelectorModal']")	
	private WebElement domainPopup;
	@FindBy(how = How.XPATH, using = "//div[@id = 'userRoleTable_wrapper']//div[@class = 'dataTables_scroll']")	
	private Table usersTable;
	@FindBy(how = How.XPATH, using = "//div[@id = 'myDropdown']/ul/li/a[@class = 'buttonCloakAsUser']")	
	private WebElement cloakAs;
	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'bootbox modal')]//div[@class = 'modal-content']//button[@class = 'btn btn-danger']")	
	private WebElement cloakAsConfirm;
//	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'bootbox modal')]//div[@class = 'modal-content']")	
//	private WebElement cloakAsConfirmPopup;	
	
								
	private static final Map<String, String> roles = new HashMap<>();
	static 
	{
		roles.put("EM Client Admin", "alesia.rippy@zimmerbiomet.com");
	}
	
	public UsersGroupsPermissionsPage(IDriverManager driverManager)
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

	public void cloakAsRole(String role)
	{
		changeDomain.click();
		WaitUtils.waitUntilElementVisible(clientSelector, driverManager.getDriver());
		clientSelector.selectByVisibleText("ZimmerBiomet");
		apply.click();
		
		waitPageToLoad();
		
		email.sendKeys(roles.get(role));		
		securityProfileName.click();
		securityProfileName.selectByVisibleText(role);
		WebElement currentSearchResults = ((WrapsElement)usersTable.getWrappedElement()).getWrappedElement();		
		search.click();
		WaitUtils.waitUntilElementStaleness(currentSearchResults, driverManager.getDriver(), 10);		
		usersTable.getCellAt(0, 0).findElement(By.xpath("//div[@id = 'myDropdown']/button")).click();
		cloakAs.click();
		cloakAsConfirm.click();
	}		
	
	private void waitPageToLoad()
	{
		WaitUtils.waitUntilElementVisible(noOverlay, driverManager.getDriver(), 50);
	}		
}
