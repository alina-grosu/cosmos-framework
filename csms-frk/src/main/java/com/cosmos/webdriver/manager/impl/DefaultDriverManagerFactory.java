package com.cosmos.webdriver.manager.impl;

import java.util.Optional;
import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.manager.IDriverManagerFactory;
import com.cosmos.webdriver.manager.IDriverServiceManager;


public class DefaultDriverManagerFactory implements IDriverManagerFactory {	
	
	@Override
	public IDriverManager newManager(IConfiguration config, IDriverServiceManager driverServiceManager)
	{			
		IDriverManager driverManager = null;
		
		if ("REMOTING".equals(config.getDriverManagerHint()))
			driverManager = new DefaultRemotingDriverManager(config, driverServiceManager);
				
		return Optional.ofNullable(driverManager)
					   .orElseThrow(
							   () -> new RuntimeException(
									   		String.format("Unable to determine DriverManager using hint %s.", 
									   		config.getDriverManagerHint()
						)));
	}
}
