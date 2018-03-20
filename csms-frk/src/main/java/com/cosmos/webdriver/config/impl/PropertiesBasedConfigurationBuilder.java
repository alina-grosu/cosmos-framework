package com.cosmos.webdriver.config.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.config.IConfigurationBuilder;
import com.cosmos.webdriver.manager.Browsers;
import com.cosmos.webdriver.manager.ExecutionTypes;
import com.cosmos.webdriver.manager.StepContextScopes;

public class PropertiesBasedConfigurationBuilder implements IConfigurationBuilder {
	
	private static final Logger logger = LogManager.getLogger(); 
	
	@Override
	public IConfiguration build()
	{
		Properties props = getProperties();
		IConfiguration config = new DefaultConfiguration();
		
		config.setDesiredBrowser(
				Browsers.valueOf(getProperty(props, PropertiesEnum.BROWSER_ENVIRO_KEY).toUpperCase()));
		
		config.setExecutionType(
				ExecutionTypes.valueOf(getProperty(props, PropertiesEnum.EXECUTION_TYPE_ENVIRO_KEY).toUpperCase()));
		
		config.setChromeDriverExecutableLocation(new File(getProperty(props, PropertiesEnum.CHROMEDRIVER_LOCATION_KEY)));
		
		config.setRemoteGridHubUrl(
				toUrl(getProperty(props, PropertiesEnum.GRID_URL_KEY), "Selenium Grid Hub URL seems to be malformed"));
		
		config.setAppUnderTestUrl(
				toUrl(getProperty(props, PropertiesEnum.AUT_URL_KEY), "Application Under Test URL seems to be malformed"));			
		
		config.setStepsContextScope(
				StepContextScopes.valueOf(getProperty(props, PropertiesEnum.STEPS_CONTEXT_SCOPE_KEY).toUpperCase()));
		
		return config;
	}
	
	private Properties getProperties()
	{
		Properties p = new Properties();
		String configName = "cosmos.configuration.properties";
		try
		{
			
			p.load(new FileInputStream(this.getClass().getClassLoader().getResource(configName).getFile()));
		} 
		catch (IOException e)
		{
			logger.error(String.format("Failed to read properties file '%s' from classpath.", configName));
			throw new RuntimeException(e);
		}
		logger.info("Loaded properties:");
		p.forEach((key, value) -> logger.info(String.format("%s=%s", key, value)));
		
		return p;
	}
	
	private String getProperty(Properties props, PropertiesEnum propsEnum)
	{
		return props.getProperty(propsEnum.get(), propsEnum.defaultValue());
	}
	
	private URL toUrl (String url, String failMessage)
	{
		URL toUrl;
		try
		{
			toUrl = new URL(url);
		}
		catch (MalformedURLException e)
		{
			logger.error(String.format("Failed to parse URL: %s", url));			
			throw new RuntimeException(failMessage, e);			
		}
		return toUrl;
	}
}
