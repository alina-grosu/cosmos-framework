package com.cosmos.webdriver.context;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.pageobject.manager.PageObjectManager;

public interface IStepsContext {
	
	PageObjectManager getPageObjectManager();
	IConfiguration getConfiguration();
}
