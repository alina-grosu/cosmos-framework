package com.cosmos.webdriver.context.impl;

import java.util.Random;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.config.IConfigurationFactory;
import com.cosmos.webdriver.config.impl.ConfigurationFactory;
import com.cosmos.webdriver.context.IStepsContext;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.manager.IDriverManagerFactory;
import com.cosmos.webdriver.manager.impl.DefaultDriverManagerFactory;

public class FeatureWideStepsContext {

	/*private static ThreadLocal<FeatureWideStepsContext> threadLocalInstance = new ThreadLocal<>();
	private final IConfigurationFactory configFactory;
	private final IDriverManagerFactory driverFactory;
	private final IConfiguration config;
	private final IDriverManager driverManager;
	private final int r;*/

//	public FeatureWideStepsContext()
//	{
//		System.out.println(String.format("Static test context instantiating from %s %s"
//										, Thread.currentThread().getName()
//										, Thread.currentThread().getId()));
//		System.out.println("Instance " + this.toString());
//
//		configFactory = new ConfigurationFactory();
//		driverFactory = new DefaultDriverManagerFactory();
//		config = configFactory.getConfiguration();
//		driverManager = driverFactory.getManager(config);
//		r = new Random().nextInt(10000);
//		System.out.println(r);
//		
//	}

//	@Override
//	public IDriverManager getDriverManager()
//	{
////		return getInstance().driverManager;
//		return null;
//	}
	
//	private FeatureWideStepsContext getInstance()
//	{	
//		if (threadLocalInstance.get() == null)
//		{			
//			threadLocalInstance.set(this);
//		}
//		
//		System.out.println("Returning static context instance: " + threadLocalInstance.get().toString());
//		System.out.println("Random: " + threadLocalInstance.get().r);
//		return threadLocalInstance.get();
//	}
 
}
