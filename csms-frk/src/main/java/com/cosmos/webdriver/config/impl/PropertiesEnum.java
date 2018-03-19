package com.cosmos.webdriver.config.impl;

enum PropertiesEnum {
	
	EXECUTION_TYPE_ENVIRO_KEY("com.cosmos.execution.type", "local"),
	BROWSER_ENVIRO_KEY("com.cosmos.driver.browser", "chrome"),
	GRID_URL_KEY("com.cosmos.grid.hub.url", "http://localhost:4444/wd/hub"),
	CHROMEDRIVER_LOCATION_KEY("com.cosmos.driver.chrome.executable.location", "chromedriver.exe"),
	STEPS_CONTEXT_SCOPE_KEY("com.cosmos.steps.context.scope", "scenario"),
	AUT_URL_KEY("com.cosmos.aut.url", "https://login.tst-us-east.medispend.com/dashboard/login");
	

	private final String key;
	private final String defaultValue;	
	
	PropertiesEnum (String key, String defaultValue)
	{
		this.key = key;
		this.defaultValue = defaultValue;
	}
	
	String get()
	{		
		return key;
	}
	
	String defaultValue()
	{	
		return defaultValue;
	}		
}
