package com.cosmos.webdriver.config.impl;

import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.config.IConfigurationBuilder;
import com.cosmos.webdriver.config.IConfigurationFactory;

public class ConfigurationFactory implements IConfigurationFactory {

	private final Map<String, IConfigurationBuilder> builders;	
	private static final Logger logger = LogManager.getLogger(); 
	
	public ConfigurationFactory(Map<String, IConfigurationBuilder> builders)
	{
		this.builders = builders;
	}
	
	public IConfiguration getConfiguration() 
	{
		String configStrategy = System.getProperty("com.cosmos.configuration.strategy");		
		IConfigurationBuilder builder = Optional.ofNullable(builders.get(configStrategy))
												.orElse(new PropertiesBasedConfigurationBuilder());
		logger.info(String.format("Using Configuration Builder of type: %s", builder.getClass()));												
		return 	builder.build();
				
	}
	
	

}
