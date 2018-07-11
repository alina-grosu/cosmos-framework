package com.cosmos.webdriver.manager.impl;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.manager.IDriverManagerConfiguration;
import com.cosmos.webdriver.manager.IDriverServiceManager;

public class DefaultRemotingDriverManager implements IDriverManager {

	private static final Logger logger = LogManager.getLogger();	
	private final IDriverServiceManager driverServiceManager;
	private final IDriverManagerConfiguration dmConfig;
	private WebDriver driver;	
	
	public DefaultRemotingDriverManager(IDriverManagerConfiguration config, IDriverServiceManager driverServiceManager)
	{
		this.dmConfig = config;
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
			driver = null;
		}
		else
		{
			logger.warn("Attempted to quit WebDriver which was null");
		}
				
	}

	@Override
	public void quitDriverAndDriverService()
	{		
		quitDriver();
		driverServiceManager.stopDriverService();
	}	
	
	private RemoteWebDriver configureRemoteWebDriver()
	{
		RemoteWebDriver remoteWebDriver = 
				new RemoteWebDriver(driverServiceManager.getDriverServiceUrl(), dmConfig.getDesiredCapabilities());
						
		if (dmConfig.getBrowserWindowDimension() == null)
		{
			remoteWebDriver.manage().window().maximize();
		}
		else
		{
			remoteWebDriver.manage().window().setSize(dmConfig.getBrowserWindowDimension());
		}
			
		return remoteWebDriver;
	}
	
}
