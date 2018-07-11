package com.cosmos.pageobject.em.pages.pagecomponents;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;

public class SingleSelectBox extends HtmlElement{
	
	@FindBy(how = How.XPATH, using = "//select[@id = 'securityProfile_object_id'][ancestor::div[@class = 'panel panel-default']]")
	private Select select;			
	
	public void selectItemByText(String text)
	{
		select.selectByValue(text);
	}
	
}
