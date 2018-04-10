package com.cosmos.webdriver.manager;

import java.io.IOException;
import java.net.URL;

public interface IDriverServiceManager {

	void startDriverService() throws IOException;
	void stopDriverService();
	void restartDriverService() throws IOException;
	URL getDriverServiceUrl();
	boolean isDriverServiceRunning();
	
}
