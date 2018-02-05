package com.cosmos.webdriver.config.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.config.IConfigurationBuilder;
import com.cosmos.webdriver.config.IConfigurationFactory;

public class ConfigurationFactory implements IConfigurationFactory{

//	private final Map<String, IConfigurationBuilder> builders = new HashMap<>();
	private final Map<String, IConfigurationBuilder> builders;
	
//	public ConfigurationFactory()
//	{
//		builders.put("env", new EnvironmentBasedConfigurationBuilder());
//	}
	
	public ConfigurationFactory(Map<String, IConfigurationBuilder> builders)
	{
		this.builders = builders;
	}
	
	public IConfiguration getConfiguration() 
	{
		String configStrategy = System.getProperty("com.cosmos.configuration.strategy");
//		return builders.get(configStrategy) != null 
//				? builders.get(configStrategy).build()
//				: new EnvironmentBasedConfigurationBuilder().build();
		return 	Optional.ofNullable(builders.get(configStrategy))
						.orElse(new EnvironmentBasedConfigurationBuilder()).build();
				
	}
	
	

}
