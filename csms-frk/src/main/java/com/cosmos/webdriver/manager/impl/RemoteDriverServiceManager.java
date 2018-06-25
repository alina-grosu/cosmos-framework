package com.cosmos.webdriver.manager.impl;

import java.io.IOException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cosmos.cucumber.config.IConfiguration;
import com.cosmos.webdriver.manager.IDriverManagerConfiguration;
import com.cosmos.webdriver.manager.IDriverServiceManager;

public class RemoteDriverServiceManager implements IDriverServiceManager {
				
	private static final Logger logger = LogManager.getLogger();
	private final IDriverManagerConfiguration config;
	
	public RemoteDriverServiceManager(IDriverManagerConfiguration config)
	{
		this.config = config;
	}
	
	@Override
	public void startDriverService() throws IOException
	{
		logger.warn("Test execution is remote, nothing is about to be started");
	}

	@Override
	public void stopDriverService()
	{
		logger.warn("Test execution is remote, nothing is about to be stopped");
	}

	@Override
	public void restartDriverService() throws IOException
	{
		logger.warn("Test execution is remote, nothing is about to be restarted");
	}

	@Override
	public URL getDriverServiceUrl()
	{
		return config.getRemoteGridHubUrl();
	}

	@Override
	public boolean isDriverServiceRunning()
	{
		// TODO Implement check whether remote hub is running
		return true;
	}

}
