package com.cosmos.webdriver.config;

import java.io.File;
import java.net.URL;

import com.cosmos.webdriver.manager.Browsers;
import com.cosmos.webdriver.manager.ExecutionTypes;
import com.cosmos.webdriver.manager.StepContextScopes;

public interface IConfiguration {

	Browsers getBrowser();
	ExecutionTypes getExecutionType();
	URL getRemoteGridHubUrl();
	void setDesiredBrowser(Browsers browser);
	void setExecutionType(ExecutionTypes executionType);
	void setRemoteGridHubUrl(URL gridHubUrl);
	File getDriverExecutableLocation();
	void setChromeDriverExecutableLocation(File executable);
	URL getAppUnderTestUrl();
	void setAppUnderTestUrl(URL autUrl);
	StepContextScopes getStepsContextScope();
	void setStepsContextScope(StepContextScopes scope);
	
}
