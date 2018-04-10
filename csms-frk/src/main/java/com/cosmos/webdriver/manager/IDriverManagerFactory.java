package com.cosmos.webdriver.manager;

import com.cosmos.webdriver.config.IConfiguration;

public interface IDriverManagerFactory {

	IDriverManager newManager(IConfiguration config, IDriverServiceManager driverServiceManager);
	
}
