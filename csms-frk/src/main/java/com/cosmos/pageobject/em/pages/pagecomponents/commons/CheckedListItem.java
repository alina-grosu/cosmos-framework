package com.cosmos.pageobject.em.pages.pagecomponents.commons;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class CheckedListItem extends HtmlElement {	

	@FindBy(how = How.XPATH, using = ".//input[@type = 'checkbox']")
	private CheckBox checkbox;
	
	public void select() 
	{
		checkbox.select();		
	}

	public void uncheck()
	{
		checkbox.deselect();
	}

}
