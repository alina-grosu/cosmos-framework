package com.cosmos.webdriver.manager.impl;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.manager.IDriverServiceManager;

public abstract class AbstractLocalDriverManager extends DefaultRemotingDriverManager {

	public AbstractLocalDriverManager(IConfiguration config, IDriverServiceManager driverServiceManager)
	{
		super(config, driverServiceManager);		
	}
/*
	private static final Logger logger = LogManager.getLogger();
	protected WebDriver driver;
	protected File driverExecutable;		
    protected abstract void startService();
    protected abstract DriverService getService();
    protected abstract void createDriver();
        
    public AbstractLocalDriverManager(File driverExecutable)
	{
    	this.driverExecutable = driverExecutable;
	}
	
	@Override
	public WebDriver getDriver()
	{
		if (null == driver) {
            startService();
            createDriver();
        }
        return driver;
	}

	@Override
	public void quitDriver()
	{
		if (null != driver) {
			logger.info(String.format("Quiting WebDriver instance: %s", driver.toString())); 
			System.out.println(String.format("Quiting WebDriver instance: %s , Thread : %s", driver.toString(), Thread.currentThread().getName()));
			driver.quit();
            driver = null;           
        }
	}	
		
	@Override
	public void quit()
	{	
		quitDriver();
		stopService();
	}
	
	private void stopService()
	{
		DriverService driverService = getService();
		if (null != driverService && driverService.isRunning())
		{
			logger.info(String.format("Stopping DriverService instance: %s", driverService.toString()));
			System.out.println(String.format("Stopping DriverService instance: %s", driverService.toString()));
			driverService.stop();
		}            
	};*/

}
