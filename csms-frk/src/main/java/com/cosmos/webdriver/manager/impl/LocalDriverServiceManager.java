package com.cosmos.webdriver.manager.impl;

import java.io.IOException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.service.DriverService;

import com.cosmos.webdriver.manager.IDriverServiceManager;

public class LocalDriverServiceManager implements IDriverServiceManager {
	
	private static final Logger logger = LogManager.getLogger();
	private DriverService driverService;
	
	public LocalDriverServiceManager(DriverService driverService)
	{
		if (driverService == null)
		{
			throw new IllegalArgumentException("DriverService may not be null.");
		}
		this.driverService = driverService;
	}

	@Override
	public void startDriverService() throws IOException
	{		
		if (driverService.isRunning())
		{
			logger.warn(String.format("DriverService %s with URL %s is already running, nothing is to be started.",
								driverService,
								driverService.getUrl().toString()));			
		}		
		else 
		{			
			logger.info("Attempting to start DriverService locally.");
			driverService.start();
			logger.info(
					String.format("DriverService with URL: %s was started successfully",
							driverService.getUrl().toString()));
		}
	}

	@Override
	public void stopDriverService()
	{				
		logger.info(String.format("Attempting to stop DriverService with URL: %s", getDriverServiceUrl().toString()));
		
		driverService.stop();
		
		logger.info(String.format("DriverService %s was stopped.", driverService.toString()));
	}

	@Override
	public URL getDriverServiceUrl()
	{		
		return driverService.getUrl();
	}

	@Override
	public boolean isDriverServiceRunning()
	{		
		return driverService.isRunning();	
	}

	@Override
	public void restartDriverService() throws IOException
	{
		stopDriverService();
		startDriverService();		
	}	

}
