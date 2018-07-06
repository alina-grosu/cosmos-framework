package com.cosmos.pageobject.em.pages.pagecomponents.commons;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class Calendar extends HtmlElement {

	@FindBy(how = How.XPATH, using = ".//input")
	private WebElement input;
	@FindBy(how = How.XPATH, using = ".//span[@class = 'input-group-addon']")
	private WebElement button;
	@FindBy(how = How.XPATH, using = ".//div[contains(@class, 'bootstrap-datetimepicker-widget') and contains(@style, 'display: block')]")
	private CalendarDatePicker datePicker;
	
	
	
}
