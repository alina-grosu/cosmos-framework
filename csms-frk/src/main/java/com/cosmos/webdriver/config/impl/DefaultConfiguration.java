package com.cosmos.webdriver.config.impl;

import java.io.File;
import java.net.URL;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.manager.Browsers;
import com.cosmos.webdriver.manager.ExecutionTypes;

public class DefaultConfiguration implements IConfiguration {

	private Browsers browser;
	private ExecutionTypes executionType;
	private URL gridHubUrl;
	private File executable;
	private URL autUrl;

	@Override
	public Browsers getBrowser()
	{
		return browser;
	}

	@Override
	public ExecutionTypes getExecutionType()
	{
		return executionType;
	}

	@Override
	public URL getRemoteGridHubUrl()
	{
		return gridHubUrl;
	}

	@Override
	public void setDesiredBrowser(Browsers browser)
	{
		this.browser = browser;
	}

	@Override
	public void setExecutionType(ExecutionTypes executionType)
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

}
