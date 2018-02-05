package com.cosmos.webdriver.context;

import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.pageobject.manager.PageObjectManager;
import com.cosmos.webdriver.spring.registry.IDisposable;

public interface IStepsContext extends IDisposable {
	
//	IDriverManager getDriverManager();
	PageObjectManager getPageObjectManager();
}
