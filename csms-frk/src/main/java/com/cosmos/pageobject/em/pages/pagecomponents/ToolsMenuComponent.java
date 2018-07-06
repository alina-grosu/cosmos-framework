package com.cosmos.pageobject.em.pages.pagecomponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cosmos.util.WaitUtils;

import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class ToolsMenuComponent extends HtmlElement
								implements IWebDriverAware{

	@FindBy(how = How.XPATH, using = "//a[@href='/tools/']")
	private WebElement link;
	@FindBy(how = How.XPATH, using = "//ul[@class='dropdown-menu']")
	private WebElement menu;
	@FindBy(how = How.XPATH, using = "//a[contains(@href, 'user-group-permissions')]")
	private WebElement usersGroupsPermissions;
	private WebDriver driver;
	
	@Override
	public void setWebDriver(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void click()
	{
		link.click();
	};
	
	public void selectUsersGroupsPermissions()
	{
		WaitUtils.waitUntilElementVisible(menu, driver);
		usersGroupsPermissions.click();
	}
	
}
