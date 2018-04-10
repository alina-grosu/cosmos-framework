package com.cosmos.webdriver.manager.impl;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.manager.IDriverServiceManager;

public class ChromeDriverManager extends AbstractLocalDriverManager {

	public ChromeDriverManager(IConfiguration config, IDriverServiceManager driverServiceManager)
	{
		super(config, driverServiceManager);		
	}
	
	/*private static final Logger logger = LogManager.getLogger();
	
	public ChromeDriverManager(File driverExecutable)
	{
		super(driverExecutable);		
	}

	private ChromeDriverService driverService;	

	@Override
	protected void startService()
	{
		if(null == driverService)
		{
			try
			{
				logger.debug(String.format("Attempting to start ChromeDriverService using executable %s from thread %s %s"
									, driverExecutable.getAbsolutePath()
									, Thread.currentThread().getId()
									, Thread.currentThread().getName()));
				
				driverService = new ChromeDriverService.Builder()
						.usingDriverExecutable(driverExecutable)
						.usingAnyFreePort()
						.build();
				driverService.start();
				
				logger.debug("ChromeDriverService has been started successfully...");
			}
			catch (Exception e)
			{
				logger.error("ChromeDriverService creation failed.");
				logger.error(e);
			}
		}
	}	

	@Override
	protected void createDriver()
	{
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        driver = new RemoteWebDriver(driverService.getUrl(), options);
        logger.debug(String.format("Created WebDriver instance %s in thread %s %s"
        					, driver.toString()
        					, Thread.currentThread().getId()
        					, Thread.currentThread().getName()));
	}

	@Override
	protected DriverService getService()
	{		
		return driverService;
	}*/

}
