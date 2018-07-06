package com.cosmos.cucumber.config;

import java.net.URL;

public interface ITestConfiguration {

	URL getAppUnderTestUrl();
	void setAppUnderTestUrl(URL autUrl);
	WebDriverLifecycleEnum getWebDriverScope();
	void setWebDriverScope(WebDriverLifecycleEnum scope);
	void setShouldRecordVideo(boolean record);
	boolean getShouldRecordVideo();
				
}
