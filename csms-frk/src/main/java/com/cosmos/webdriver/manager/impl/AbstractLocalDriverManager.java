package com.cosmos.webdriver.manager.impl;

import java.io.File;

import org.openqa.selenium.WebDriver;

import com.cosmos.webdriver.manager.IDriverManager;

public abstract class AbstractLocalDriverManager implements IDriverManager {

	protected WebDriver driver;
	protected File driverExecutable;	
    protected abstract void startService();
    protected abstract void stopService();
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
            driver.quit();
            driver = null;           
        }

	}

}
