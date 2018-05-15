package com.cosmos.webdriver.pageobject.pages.pagecomponents;

import static com.cosmos.webdriver.util.WaitUtils.waitUntilElementVisible;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cosmos.webdriver.uicomparison.IUiComparisonIgnorableElementsAware;

import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class LoginFormComponent 
			extends HtmlElement 
			implements IUiComparisonIgnorableElementsAware, IWebDriverAware {

	@FindBy(how = How.XPATH, using = "//input[@type='email']")
	private WebElement login;
	@FindBy(how = How.XPATH, using = "//input[@type='password']")
	private WebElement password;
	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private Button loginButton;
	@FindBy(how = How.XPATH, using = "//span[parent::div[contains(@class, 'auth0-global-message-error')]]")
	private WebElement errorMessage;
	private WebDriver driver;
	
	@Override
	public List<WebElement> getElementsToIgnore()
	{		
		return Arrays.asList(login);
	}
	
	public void inputCredentials(String login, String password)
	{
		this.login.sendKeys(login);
		this.password.sendKeys(password);					
	}

	public void clickLogin()
	{
		loginButton.click();		
	}

	public String getErrorMessage()
	{
		return waitUntilElementVisible(errorMessage, driver).getText().trim();
	}

	@Override
	public void setWebDriver(WebDriver driver)
	{
		this.driver = driver;	
	}

}
