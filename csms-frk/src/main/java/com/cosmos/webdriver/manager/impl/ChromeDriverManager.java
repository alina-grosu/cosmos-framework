package com.cosmos.webdriver.manager.impl;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends AbstractLocalDriverManager {
	
	private static final Logger logger = LogManager.getLogger();
	
	public ChromeDriverManager(File driverExecutable)
	{
		super(driverExecutable);		
	}

	private ChromeDriverService cs;	

	@Override
	protected void startService()
	{
		if(null == cs)
		{
			try
			{
				logger.debug(String.format("Attempting to start ChromeDriverService using executable %s from thread %s %s"
									, driverExecutable.getAbsolutePath()
									, Thread.currentThread().getId()
									, Thread.currentThread().getName()));
				
				cs = new ChromeDriverService.Builder()
						.usingDriverExecutable(driverExecutable)
						.usingAnyFreePort()
						.build();
				
				logger.debug("ChromeDriverService has been started successfully...");
			}
			catch (Exception e)
			{
				logger.error(e);
			}
		}

	}

	@Override
	protected void stopService()
	{
		if (null != cs && cs.isRunning())
            cs.stop();
	}

	@Override
	protected void createDriver()
	{
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        driver = new ChromeDriver(cs, options);
        logger.debug(String.format("Created WebDriver instance %s in thread %s %s"
        					, driver.toString()
        					, Thread.currentThread().getId()
        					, Thread.currentThread().getName()));
	}

}
