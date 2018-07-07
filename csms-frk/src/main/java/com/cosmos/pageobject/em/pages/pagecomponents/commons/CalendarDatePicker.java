package com.cosmos.pageobject.em.pages.pagecomponents.commons;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class CalendarDatePicker extends HtmlElement {

	private static final String quotes = "\"";
	private static final String space = " ";
	
	@FindBy(how = How.XPATH, using = ".//th[@class = 'prev']")
	private WebElement previousButton;
	@FindBy(how = How.XPATH, using = ".//th[@class = 'next']")
	private WebElement nextButton;
	@FindBy(how = How.XPATH, using = ".//td[@class = 'day']")
	private List<WebElement> days;
	@FindBy(how = How.XPATH, using = ".//th[@class = 'switch']")
	private WebElement monthAndYearSwitch;
	@FindBy(how = How.XPATH, using = ".//span[@class = 'month']")
	private List<WebElement> months;
	@FindBy(how = How.XPATH, using = ".//span[@class = 'year']")
	private List<WebElement> years;
	
	public void setDate(LocalDate date)
	{
		if (date.getYear() != getCurrentYear())
		{
			pickYearAndMonth(date);
		}
		else if (!date.getMonth().equals(getCurrentMonth()))
		{
			pickMonth(date);
		}
		
		pickDayOfMonth(date);
		
	}

	private void pickDayOfMonth(LocalDate date)
	{		
		days
			.stream()
			.filter((day) -> Integer.parseInt(day.getText()) == date.getDayOfMonth())
			.findFirst()
			.get()
			.click();
	}

	private void pickMonth(LocalDate date)
	{
		monthAndYearSwitch.click();
		selectMonth(date);
		
	}
	
	private void selectMonth(LocalDate date)
	{
		months
			.stream()
			.filter((month) -> date.getMonth().toString().startsWith(month.getText().trim().toUpperCase()))
			.findFirst()
			.get()
			.click();
	}

	private void pickYearAndMonth(LocalDate date)
	{
		monthAndYearSwitch.click();
		monthAndYearSwitch.click();
		years
			.stream()
			.filter((year) -> Integer.parseInt(year.getText()) == date.getYear())
			.findFirst()
			.get()
			.click();
		selectMonth(date);
	}

	private Month getCurrentMonth()
	{
		return Month.valueOf(getMonthAndYear()[0].toUpperCase());
	}

	private int getCurrentYear()
	{
		return Integer.parseInt(getMonthAndYear()[1]);				
	}
	
	private String[] getMonthAndYear()
	{
		return monthAndYearSwitch.getText().split(space);				
	}	
	
	
	
}
