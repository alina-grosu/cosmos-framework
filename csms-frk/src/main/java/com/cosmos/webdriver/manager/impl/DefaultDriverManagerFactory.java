package com.cosmos.webdriver.manager.impl;

import java.util.Optional;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.manager.IDriverManagerConfiguration;
import com.cosmos.webdriver.manager.IDriverManagerFactory;


public class DefaultDriverManagerFactory 
			implements 	IDriverManagerFactory {
	
	private final IDriverManagerConfiguration config;
	
	public DefaultDriverManagerFactory(IDriverManagerConfiguration config)
	{		
		this.config = config;
	};
	
	@Override
	public IDriverManager newManager()
	{
		IDriverManager driverManager = null;
		
		if ("REMOTING".equals(config.getDriverManagerHint()))
			driverManager = 
				new DefaultRemotingDriverManager(
						config, 
						new DefaultDriverServiceManagerFactory(config)
							.getDriverServiceManager());
				
		return Optional.ofNullable(driverManager)
					   .orElseThrow(
							   () -> new RuntimeException(
									   		String.format("Unable to determine DriverManager using hint %s.", 
									   		config.getDriverManagerHint()
						)));
	}	
}
