package com.cosmos.pageobject.em.pages.pagecomponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;

public class SingleSelectBox extends HtmlElement 
							implements IWebDriverAware{
	
	@FindBy(how = How.XPATH, using = "//select[@id = 'securityProfile_object_id'][ancestor::div[@class = 'panel panel-default']]")
	private Select select;
	private WebDriver driver;
	

	@Override
	public void setWebDriver(WebDriver driver)
	{
		this.driver = driver;		
	}
	
	public void selectItemByText(String text)
	{
		select.selectByValue(text);
	}
	
}
