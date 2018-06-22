package com.cosmos.webdriver.manager;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;

public interface IDriverManagerConfiguration {

	BrowsersEnum getBrowser();
	ExecutionTypesEnum getExecutionType();
	URL getRemoteGridHubUrl();
	void setDesiredBrowser(BrowsersEnum browser);
	void setExecutionType(ExecutionTypesEnum executionType);
	void setRemoteGridHubUrl(URL gridHubUrl);
	File getDriverExecutableLocation();
	void setChromeDriverExecutableLocation(File executable);
	Capabilities getDesiredCapabilities();
	void setDesiredCapabilities(Capabilities caps);
	String getDriverManagerHint();
	void setDriverManagerHint(String hint);
	Dimension getBrowserWindowDimension();
	void setBrowserWindowDimension(Dimension dimension);
}
