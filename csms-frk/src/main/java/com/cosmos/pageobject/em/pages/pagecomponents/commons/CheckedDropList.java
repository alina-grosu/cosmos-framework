package com.cosmos.pageobject.em.pages.pagecomponents.commons;

import java.util.List;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class CheckedDropList extends HtmlElement{
	
	@FindBy(how = How.XPATH, using = ".//li")
	private List<CheckedListItem> dropListItemsAll;	
	@FindBy(how = How.XPATH, using = ".//li[@class = 'active']")
	@Timeout(0)
	private List<CheckedListItem> dropListItemsChecked;
	@FindBy(how = How.XPATH, using = ".//li[not(@class = 'active')]")
	private List<CheckedListItem> dropListItemsUnchecked;	
	
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
		while (dropListItemsChecked.size() != 0)
		{
			CheckedListItem checkedListItem = dropListItemsChecked.iterator().next();
			checkedListItem.uncheck();		
		}		
	}

}
