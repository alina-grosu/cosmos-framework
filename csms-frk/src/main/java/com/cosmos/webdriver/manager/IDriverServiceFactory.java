package com.cosmos.webdriver.manager;

import org.openqa.selenium.remote.service.DriverService;

import com.cosmos.webdriver.config.IConfiguration;

public interface IDriverServiceFactory {

	DriverService newDriverService(IConfiguration config);
	
}
