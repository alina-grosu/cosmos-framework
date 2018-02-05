package com.cosmos.webdriver.config.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.config.IConfigurationBuilder;
import com.cosmos.webdriver.manager.Browsers;
import com.cosmos.webdriver.manager.ExecutionTypes;

public class EnvironmentBasedConfigurationBuilder implements IConfigurationBuilder {
	
	private static final String EXECUTION_TYPE_ENVIRO_KEY = "com.cosmos.execution.type";
	private static final String BROWSER_ENVIRO_KEY = "com.cosmos.driver.browser"; 
	
	@Override
	public IConfiguration build() {
		
		String browser = System.getProperty(BROWSER_ENVIRO_KEY);
		String executionStrategy = System.getProperty(EXECUTION_TYPE_ENVIRO_KEY);
		String hub = System.getProperty("com.cosmos.grid.hub.url");
		String driverExecutableLocation = System.getProperty("com.cosmos.driver.executable.location");
		if (StringUtils.isEmpty(browser) || StringUtils.isEmpty(executionStrategy))
		{
			throw new RuntimeException("Neither " + BROWSER_ENVIRO_KEY + 
						" nor " + EXECUTION_TYPE_ENVIRO_KEY + " may be undefined.");
		}
		
		IConfiguration config = new DefaultConfiguration();
		config.setBrowser(Browsers.valueOf(browser.toUpperCase()));
		config.setExecutionType(ExecutionTypes.valueOf(executionStrategy.toUpperCase()));
		URL hubUrl;
		try
		{
			hubUrl = new URL(hub);
		}
		catch (MalformedURLException e)
		{
			throw new RuntimeException("Selenium Grid Hub URL " + hub + " is malformed");
		}
		config.setRemoteGridHubUrl(hubUrl);
		config.setDriverExecutableLocation(new File(driverExecutableLocation));
		
		return config;
	}

}
