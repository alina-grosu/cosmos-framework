package com.cosmos.webdriver.config;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;

import com.cosmos.webdriver.manager.BrowsersEnum;
import com.cosmos.webdriver.manager.ExecutionTypesEnum;
import com.cosmos.webdriver.manager.StepContextScopesEnum;
import com.cosmos.webdriver.uicomparison.UiComparatorTypesEnum;

public interface IConfiguration {

	BrowsersEnum getBrowser();
	ExecutionTypesEnum getExecutionType();
	URL getRemoteGridHubUrl();
	void setDesiredBrowser(BrowsersEnum browser);
	void setExecutionType(ExecutionTypesEnum executionType);
	void setRemoteGridHubUrl(URL gridHubUrl);
	File getDriverExecutableLocation();
	void setChromeDriverExecutableLocation(File executable);
	URL getAppUnderTestUrl();
	void setAppUnderTestUrl(URL autUrl);
	StepContextScopesEnum getStepsContextScope();
	void setStepsContextScope(StepContextScopesEnum scope);
	Capabilities getDesiredCapabilities();
	void setDesiredCapabilities(Capabilities caps);
	String getDriverManagerHint();
	void setDriverManagerHint(String hint);
	Dimension getBrowserWindowDimension();
	void setBrowserWindowDimension(Dimension dimension);
	UiComparatorTypesEnum getUiComparatorType();
	void setUiComparatorType(UiComparatorTypesEnum type);
	
}
