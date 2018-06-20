package com.cosmos.webdriver.context;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.pageobject.manager.PageObjectManager;

public interface ITestUiContext extends ICucumberTestContext {
	
	PageObjectManager getPageObjectManager();
	IDriverManager getDriverManager();	
	IConfiguration getConfiguration();	
}
