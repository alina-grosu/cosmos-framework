package com.cosmos.pageobject.em.pages.pagecomponents.commons;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.cosmos.pageobject.em.pages.pagecomponents.IWebDriverAware;
import com.cosmos.util.WaitUtils;

import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class CheckedDropList extends HtmlElement
							implements IWebDriverAware {
	
	@FindBy(how = How.XPATH, using = ".//li")
	private List<CheckedListItem> dropListItemsAll;
	@FindBy(how = How.XPATH, using = ".//li[@class = 'active']")
	private List<CheckedListItem> dropListItemsChecked;
	@FindBy(how = How.XPATH, using = ".//li[not(@class = 'active')]")
	private List<CheckedListItem> dropListItemsUnchecked;
	private WebDriver driver;
	
	public void checkItemWithText(String text)
	{
		dropListItemsAll	
			.stream()
			.filter((item) -> text.equals(item.getText().trim()))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(String.format("List item with text [%s] seems to be absent in the list", text)))
			.select();				
	}

	public void uncheckAll()
	{		
		for (int i = 0; i < dropListItemsChecked.size(); i++ )
		{
			CheckedListItem checkedListItem = dropListItemsChecked.get(i);
			checkedListItem.uncheck();
			System.out.println(driver.toString());
			WaitUtils.waitUntilElementStaleness(checkedListItem, driver);
		}
	}

	@Override
	public void setWebDriver(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
}
