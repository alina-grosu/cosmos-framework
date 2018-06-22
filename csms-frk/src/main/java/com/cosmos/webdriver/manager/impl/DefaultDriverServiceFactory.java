package com.cosmos.webdriver.manager.impl;

import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.service.DriverService;

import com.cosmos.webdriver.manager.BrowsersEnum;
import com.cosmos.webdriver.manager.IDriverManagerConfiguration;
import com.cosmos.webdriver.manager.IDriverServiceFactory;

public class DefaultDriverServiceFactory implements IDriverServiceFactory {

	private final IDriverManagerConfiguration config;

	public DefaultDriverServiceFactory(IDriverManagerConfiguration config)
	{
		this.config = config;
	}
	
	@Override
	public DriverService newDriverService()
	{		
		DriverService driverService = null;
		
		if(config.getBrowser().equals(BrowsersEnum.CHROME))
			driverService = new ChromeDriverService.Builder()
					.usingDriverExecutable(config.getDriverExecutableLocation())
					.usingAnyFreePort()
					.build();
		else if (config.getBrowser().equals(BrowsersEnum.IE))
			driverService = new InternetExplorerDriverService.Builder()
					.usingDriverExecutable(config.getDriverExecutableLocation())
					.usingAnyFreePort()
					.build();
		
		return Optional.ofNullable(driverService)
					.orElseThrow(() -> new RuntimeException(
							String.format("Browser %s seems not to be supported.", config.getBrowser().toString())));
	}

}
