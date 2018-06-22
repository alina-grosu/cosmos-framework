package com.cosmos.webdriver.manager;

import org.openqa.selenium.remote.service.DriverService;

public interface IDriverServiceFactory {

	DriverService newDriverService();
	
}
