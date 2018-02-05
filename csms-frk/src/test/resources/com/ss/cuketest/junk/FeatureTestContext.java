package com.cosmos.webdriver.context;

import org.openqa.selenium.WebDriver;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.config.IConfigurationFactory;
import com.cosmos.webdriver.config.impl.ConfigurationFactory;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.manager.IDriverManagerFactory;
import com.cosmos.webdriver.manager.impl.DefaultDriverManagerFactory;

public class FeatureTestContext {
	
	private final IConfigurationFactory configFactory;
	private final IDriverManagerFactory driverFactory;
	private final IConfiguration config;
	private final IDriverManager driverManager;
	

	public FeatureTestContext()
	{
		
		System.out.println("Test context instantiating from " + Thread.currentThread().getName() + 
							" " + Thread.currentThread().getId());
		System.out.println("Instance " + this.toString());
		
		configFactory = new ConfigurationFactory(null);
		driverFactory = new DefaultDriverManagerFactory(null);
		config = configFactory.getConfiguration();
		driverManager = driverFactory.getManager();
	}
	
	public IConfiguration getConfiguration()
	{
		return config;
	}
	
	public IDriverManager getDriverManager()
	{
		return driverManager;
	}

}
