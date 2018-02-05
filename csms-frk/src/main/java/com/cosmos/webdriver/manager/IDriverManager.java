package com.cosmos.webdriver.manager;

import org.openqa.selenium.WebDriver;

public interface IDriverManager {

	WebDriver getDriver();
	void quitDriver();
	
}
