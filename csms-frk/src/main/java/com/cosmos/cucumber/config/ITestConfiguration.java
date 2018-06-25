package com.cosmos.cucumber.config;

import java.net.URL;

import com.cosmos.webdriver.manager.TestContextScopesEnum;

public interface ITestConfiguration {

	URL getAppUnderTestUrl();
	void setAppUnderTestUrl(URL autUrl);
	TestContextScopesEnum getStepsContextScope();
	void setStepsContextScope(TestContextScopesEnum scope);	
				
}
