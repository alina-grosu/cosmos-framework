package com.cosmos.webdriver.screenshots.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.cosmos.cucumber.ThreadLocalBasedFeatureTracker;
import com.cosmos.webdriver.screenshots.IScreenshotsLocationAware;

public class FeatureLocationBasedShcreenshotsLocationAware implements IScreenshotsLocationAware {
			
	
	@Override
	public Path getBaseScreenshotsLocation()
	{
		return getScreenshotsDir().resolve("base");
	}
	
	@Override
	public Path getResultScreenshotsLocation()
	{
		return getScreenshotsDir().resolve("result");
	}

	@Override
	public Path getFailureScreenshotsLocation()
	{
		return getScreenshotsDir().resolve("fail");
	}
	
	private Path getScreenshotsDir()
	{
		return ThreadLocalBasedFeatureTracker
				.getCurrentScenarioDir()				
				.resolve("screenshots");				
	}

}
