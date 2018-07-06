package com.cosmos.cucumber.config.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;

import com.cosmos.cucumber.config.IConfiguration;
import com.cosmos.cucumber.config.IConfigurationBuilder;
import com.cosmos.cucumber.config.WebDriverLifecycleEnum;
import com.cosmos.webdriver.manager.BrowsersEnum;
import com.cosmos.webdriver.manager.ExecutionTypesEnum;


public class PropertiesBasedConfigurationBuilder implements IConfigurationBuilder {
	
	private static final Logger logger = LogManager.getLogger(); 
	
	@Override
	public IConfiguration build()
	{
		Properties props = getProperties();
		IConfiguration config = new DefaultConfiguration();
		
		BrowsersEnum desiredBrowser = BrowsersEnum.valueOf(getProperty(props, PropertiesEnum.BROWSER_ENVIRO_KEY).toUpperCase());
		config.setDesiredBrowser(desiredBrowser);
		
		config.setExecutionType(
				ExecutionTypesEnum.valueOf(getProperty(props, PropertiesEnum.EXECUTION_TYPE_ENVIRO_KEY).toUpperCase()));
		
		config.setChromeDriverExecutableLocation(new File(getProperty(props, PropertiesEnum.WEBDRIVER_LOCATION_KEY)));
		
		config.setRemoteGridHubUrl(
				toUrl(getProperty(props, PropertiesEnum.GRID_URL_KEY), "Selenium Grid Hub URL seems to be malformed"));
		
		config.setAppUnderTestUrl(
				toUrl(getProperty(props, PropertiesEnum.AUT_URL_KEY), "Application Under Test URL seems to be malformed"));			
		
		config.setWebDriverScope(
				WebDriverLifecycleEnum.valueOf(getProperty(props, PropertiesEnum.STEPS_CONTEXT_SCOPE_KEY).toUpperCase()));
		
		config.setDesiredCapabilities(CapabilitiesStore.get(desiredBrowser, getProperty(props, PropertiesEnum.CAPS_ADDITIONAL_KEY)));
		
		config.setDriverManagerHint(getProperty(props, PropertiesEnum.DRIVER_MANAGER_HINT).toUpperCase());
		
		config.setBrowserWindowDimension(getBrowserWindowDimension(props));				
		
		config.setShouldRecordVideo(Boolean.parseBoolean(getProperty(props, PropertiesEnum.RECORD_VIDEO)));
		
		return config;
	}
	
	private Dimension getBrowserWindowDimension(Properties props)
	{
		String height = getProperty(props, PropertiesEnum.BROWSER_HEIGHT);
		String width = getProperty(props, PropertiesEnum.BROWSER_WIDTH);
		if ("".equals(height) || "".equals(width))
		{
			return null;
		}
		
		try
		{
			return new Dimension(Integer.parseInt(width), Integer.parseInt(height));
		}
		catch (NumberFormatException e)
		{
			String format = "Unable to parse desired browser dimensions: height=%s, width=%s. Values must be ints.";
			logger.error(String.format(format, height, width), e);			
			throw new RuntimeException(e);
		}			
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
			logger.error(String.format("Failed to read properties file '%s' from classpath.", configName), e);
			throw new RuntimeException(e);
		}
		
		StringBuilder sb = new StringBuilder("Loaded properties: \n");
		p.forEach((key, value) -> sb.append(String.format("%s=%s \n", key, value)));
		logger.info(sb.toString());
		
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
			logger.error(String.format("Failed to parse URL: %s", url), e);			
			throw new RuntimeException(failMessage, e);			
		}
		return toUrl;
	}
}
