package com.cosmos.webdriver.config;

import java.net.URL;

import com.cosmos.webdriver.manager.IDriverManagerConfiguration;
import com.cosmos.webdriver.manager.StepContextScopesEnum;

public interface IConfiguration extends IDriverManagerConfiguration {
		
	URL getAppUnderTestUrl();
	void setAppUnderTestUrl(URL autUrl);
	StepContextScopesEnum getStepsContextScope();
	void setStepsContextScope(StepContextScopesEnum scope);	
}
