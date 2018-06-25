package com.cosmos.cucumber.config;

import java.net.URL;

public interface ITestConfiguration {

	URL getAppUnderTestUrl();
	void setAppUnderTestUrl(URL autUrl);
	WebDriverLifecycleEnum getStepsContextScope();
	void setStepsContextScope(WebDriverLifecycleEnum scope);	
				
}
