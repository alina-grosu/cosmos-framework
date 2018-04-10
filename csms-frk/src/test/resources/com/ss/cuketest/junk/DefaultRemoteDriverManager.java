package com.cosmos.webdriver.manager.impl;

import java.net.URL;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.manager.BrowsersEnum;
import com.cosmos.webdriver.manager.IDriverServiceManager;

public class DefaultRemoteDriverManager extends AbstractRemoteDriverManager{

	public DefaultRemoteDriverManager(IConfiguration config, IDriverServiceManager driverServiceManager)
	{
		super(config, driverServiceManager);
	}

}
