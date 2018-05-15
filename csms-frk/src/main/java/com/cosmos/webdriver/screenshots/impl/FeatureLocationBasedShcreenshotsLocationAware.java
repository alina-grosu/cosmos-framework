package com.cosmos.webdriver.screenshots.impl;

import java.nio.file.Path;

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
				.getCurrentFeatureDir()
				.resolve("screenshots");
	}

}
