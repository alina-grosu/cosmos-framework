package com.cosmos.pageobject.em.pages.pagecomponents.commons;



import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.cosmos.pageobject.em.pages.pagecomponents.IWebDriverAware;
import com.cosmos.util.WaitUtils;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class MultiSelectCheckedDropBox 	extends HtmlElement
										implements IWebDriverAware{
	
	@FindBy(how = How.XPATH, using = ".//button")
	private WebElement button;
	@FindBy(how = How.XPATH, using = ".//ul[@class = 'multiselect-container dropdown-menu' and li]")
	private CheckedDropList dropList;		
	private WebDriver driver;
	
	
	@Override
	public void setWebDriver(WebDriver driver)
	{
		this.driver = driver;		
	}	
	
	@Override
	public void clear()
	{
		invokeDropList();
		dropList.uncheckAll();
		clickDropList();
	}

	public void selectByText(String itemValue)
	{
		invokeDropList();
		dropList.checkItemWithText(itemValue);	
		clickDropList();
	}
	
	private void invokeDropList()
	{
		clickDropList();
		WaitUtils.waitUntilElementVisible(dropList, driver);
	}
	
	private void clickDropList()
	{
		this.click();
	}

	public void selectByText(List<String> itemValues)
	{
		invokeDropList();
		itemValues.forEach((itemValue) -> dropList.checkItemWithText(itemValue));
		clickDropList();
	}
	
}
