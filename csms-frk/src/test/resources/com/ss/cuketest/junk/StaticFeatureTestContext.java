package com.cosmos.webdriver.context;

import java.util.Random;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.config.IConfigurationFactory;
import com.cosmos.webdriver.config.impl.ConfigurationFactory;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.manager.IDriverManagerFactory;
import com.cosmos.webdriver.manager.impl.DefaultDriverManagerFactory;

public class StaticFeatureTestContext {
		
	private static ThreadLocal<StaticFeatureTestContext> threadLocalInstance = new ThreadLocal<>();
	private final IConfigurationFactory configFactory;
	private final IDriverManagerFactory driverFactory;
	private final IConfiguration config;
	private final IDriverManager driverManager;
	private final int r;
	
	private StaticFeatureTestContext()
	{
		System.out.println("Static test context instantiating from " + Thread.currentThread().getName() + 
				" " + Thread.currentThread().getId());
		System.out.println("Instance " + this.toString());

		configFactory = new ConfigurationFactory(null);
		driverFactory = new DefaultDriverManagerFactory(null);
		config = configFactory.getConfiguration();
		driverManager = driverFactory.getManager();
		r = new Random().nextInt(10000);
		System.out.println(r);
		
	}
	
	public static StaticFeatureTestContext getInstance()
	{	
		if (threadLocalInstance.get() == null)
		{			
			threadLocalInstance.set(new StaticFeatureTestContext());
		}
		
		System.out.println("Returning static context instance: " + threadLocalInstance.get().toString());
		System.out.println("Random: " + threadLocalInstance.get().r);
		return threadLocalInstance.get();
	}
	
	public IDriverManager getDriverManager()
	{		
		return threadLocalInstance.get().driverManager;	
	}
}
