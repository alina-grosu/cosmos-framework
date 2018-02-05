package com.cosmos.webdriver.config;

import java.io.File;
import java.net.URL;

import com.cosmos.webdriver.manager.Browsers;
import com.cosmos.webdriver.manager.ExecutionTypes;

public interface IConfiguration {

	Browsers getBrowser();
	ExecutionTypes getExecutionType();
	URL getRemoteGridHubUrl();
	void setBrowser(Browsers browser);
	void setExecutionType(ExecutionTypes executionType);
	void setRemoteGridHubUrl(URL gridHubUrl);
	File getDriverExecutableLocation();
	void setDriverExecutableLocation(File executable);
	
}
