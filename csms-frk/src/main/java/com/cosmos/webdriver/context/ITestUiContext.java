package com.cosmos.webdriver.context;

import com.cosmos.webdriver.manager.IDriverManager;

public interface ITestUiContext<T> extends ICucumberTestContext {
	
	T getPageObjectManager();
	IDriverManager getDriverManager();		
}
