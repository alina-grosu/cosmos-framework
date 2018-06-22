package com.cosmos.webdriver.config.impl;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.manager.BrowsersEnum;
import com.cosmos.webdriver.manager.ExecutionTypesEnum;
import com.cosmos.webdriver.manager.IDriverManagerConfiguration;
import com.cosmos.webdriver.manager.StepContextScopesEnum;


public class DefaultConfiguration implements IConfiguration {

	private BrowsersEnum browser;
	private ExecutionTypesEnum executionType;
	private URL gridHubUrl;
	private File executable;
	private URL autUrl;
	private StepContextScopesEnum scope;
	private Capabilities caps;
	private String driverManagerHint;
	private Dimension dimension;	

	@Override
	public BrowsersEnum getBrowser()
	{
		return browser;
	}

	@Override
	public ExecutionTypesEnum getExecutionType()
	{
		return executionType;
	}

	@Override
	public URL getRemoteGridHubUrl()
	{
		return gridHubUrl;
	}

	@Override
	public void setDesiredBrowser(BrowsersEnum browser)
	{
		this.browser = browser;
	}

	@Override
	public void setExecutionType(ExecutionTypesEnum executionType)
	{
		this.executionType = executionType;
	}

	@Override
	public void setRemoteGridHubUrl(URL gridHubUrl)
	{
		this.gridHubUrl = gridHubUrl;
	}

	@Override
	public File getDriverExecutableLocation()
	{
		return executable;
	}

	@Override
	public void setChromeDriverExecutableLocation(File executable)
	{
		this.executable = executable;
	}

	@Override
	public URL getAppUnderTestUrl()
	{
		return autUrl;
	}

	@Override
	public void setAppUnderTestUrl(URL autUrl)
	{
		this.autUrl = autUrl;
	}

	@Override
	public StepContextScopesEnum getStepsContextScope()
	{		
		return scope;
	}

	@Override
	public void setStepsContextScope(StepContextScopesEnum scope)
	{
		this.scope = scope;
	}

	@Override
	public Capabilities getDesiredCapabilities()
	{		
		return caps;
	}

	@Override
	public void setDesiredCapabilities(Capabilities caps)
	{
		this.caps = caps;
	}

	@Override
	public String getDriverManagerHint()
	{		
		return driverManagerHint;
	}

	@Override
	public void setDriverManagerHint(String driverManagerHint)
	{
		this.driverManagerHint = driverManagerHint;		
	}

	@Override
	public Dimension getBrowserWindowDimension()
	{
		return dimension;
	}

	@Override
	public void setBrowserWindowDimension(Dimension dimension)
	{
		this.dimension = dimension;
	}	

}
