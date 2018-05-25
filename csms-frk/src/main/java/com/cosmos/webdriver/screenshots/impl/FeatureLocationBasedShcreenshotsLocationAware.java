package com.cosmos.webdriver.screenshots.impl;

import java.nio.file.Path;
import com.cosmos.cucumber.ThreadLocalBasedFeatureTracker;
import com.cosmos.webdriver.screenshots.IScreenshotsLocationAware;

public class FeatureLocationBasedShcreenshotsLocationAware 
				implements IScreenshotsLocationAware {
			
	
	@Override
	public Path getBaseScreenshotsLocation()
	{
		return getScreenshotsDir()
				.resolve(ThreadLocalBasedFeatureTracker.getCurrentScenarioName())
				.resolve("base");
	}
	
	@Override
	public Path getResultScreenshotsLocation()
	{
		return getScreenshotsDir()
				.resolve(ThreadLocalBasedFeatureTracker.getCurrentScenarioName())
				.resolve("result");
	}

	@Override
	public Path getFailureScreenshotsLocation()
	{
		return getScreenshotsDir()
				.resolve(ThreadLocalBasedFeatureTracker.getCurrentScenarioName())
				.resolve("fail");
	}
	
	private Path getScreenshotsDir()
	{
		return ThreadLocalBasedFeatureTracker
				.getCurrentFeatureDir()				
				.resolve("screenshots");				
	}

}
