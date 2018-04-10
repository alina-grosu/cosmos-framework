package com.cosmos.webdriver.manager.impl;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.service.DriverService;

import com.cosmos.webdriver.manager.IDriverServiceManager;

public class LocalDriverServiceManager implements IDriverServiceManager {
	
	private static final Logger logger = LogManager.getLogger();
	private Optional<DriverService> driverService;
	
	public LocalDriverServiceManager(Optional<DriverService> driverService)
	{
		this.driverService = driverService;
	}

	@Override
	public void startDriverService() throws IOException
	{		
		if (driverService.isPresent() && driverService.get().isRunning())
		{
			logger.warn(String.format("DriverService %s with URL %s is already running, nothing is to be started.",
								driverService.get(),
								driverService.get().getUrl().toString()));			
		}		
		else 
		{			
			logger.info("Attempting to start DriverService locally.");
			driverService.orElseThrow(() -> new RuntimeException("DriverService may not be null.")).start();
			logger.info(
					String.format("DriverService with URL: %s was started successfully",
							driverService.get().getUrl().toString()));
		}
	}

	@Override
	public void stopDriverService()
	{				
		logger.info(String.format("Attempting to stop DriverService with URL: %s", getDriverServiceUrl().toString()));
		
		driverService
		.orElseThrow(() -> new RuntimeException("Attempted to stop DriverService which is null."))
		.stop();
		
		logger.info(String.format("DriverService %s was stopped.", driverService.get()));
	}

	@Override
	public URL getDriverServiceUrl()
	{		
		return driverService
				.orElseThrow(() -> new RuntimeException("Attempted to get URL from DriverService which is null."))
				.getUrl();
	}

	@Override
	public boolean isDriverServiceRunning()
	{		
		return driverService.isPresent() ? driverService.get().isRunning() : false;	
	}

	@Override
	public void restartDriverService() throws IOException
	{
		stopDriverService();
		startDriverService();		
	}	

}
