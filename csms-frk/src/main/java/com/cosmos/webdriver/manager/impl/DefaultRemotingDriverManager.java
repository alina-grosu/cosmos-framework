package com.cosmos.webdriver.manager.impl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.manager.IDriverServiceManager;

public class DefaultRemotingDriverManager implements IDriverManager {

	private static final Logger logger = LogManager.getLogger();
	private final IConfiguration config;
	private final IDriverServiceManager driverServiceManager;
	private WebDriver driver;	

	public DefaultRemotingDriverManager(IConfiguration config, IDriverServiceManager driverServiceManager)
	{
		this.config = config;
		this.driverServiceManager = driverServiceManager;		
	}
	
	@Override
	public WebDriver getDriver()
	{
		if (null == driver) 
		{						
			logger.info("About to create new WebDriver instance...");
			if (!driverServiceManager.isDriverServiceRunning())
			{
				try
				{					
					driverServiceManager.startDriverService();					
				} 
				catch (IOException e)
				{
					logger.error("DriverService startup failed.", e);									
				}
			}
			driver = configureRemoteWebDriver();
			logger.info(String.format("WebDriver instance %s was created...", driver.toString()));
        }
		return driver;
	}	

	@Override
	public void quitDriver()
	{				
		if (driver != null)
		{
			logger.info(String.format("Quiting WebDriver instance : %s", driver.toString()));
			driver.quit();			
		}
		else
		{
			logger.warn("Attempted to quit WebDriver which was null");
		}
				
	}

	@Override
	public void quit()
	{
		logger.info(String.format("Quiting WebDriver instance %s and corresponding DriverService..."), driver.toString());
		quitDriver();
		driverServiceManager.stopDriverService();
	}

	private RemoteWebDriver configureRemoteWebDriver()
	{
		RemoteWebDriver remoteWebDriver = 
				new RemoteWebDriver(driverServiceManager.getDriverServiceUrl(), config.getDesiredCapabilities());
		
		remoteWebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if (config.getBrowserWindowDimension() == null)
		{
			remoteWebDriver.manage().window().maximize();
		}
		else
		{
			remoteWebDriver.manage().window().setSize(config.getBrowserWindowDimension());
		}
			
		return remoteWebDriver;
	}
}
