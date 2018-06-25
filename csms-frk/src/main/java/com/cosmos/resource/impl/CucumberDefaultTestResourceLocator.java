package com.cosmos.resource.impl;

import java.awt.Toolkit;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import com.cosmos.cucumber.config.IConfiguration;
import com.cosmos.cucumber.feature.ThreadLocalBasedFeatureTracker;
import com.cosmos.resource.ITestResourceLocator;
import com.cosmos.resource.TestResourcesEnum;
import com.cosmos.webdriver.util.UuidSingleton;

public class CucumberDefaultTestResourceLocator 
		implements ITestResourceLocator {
	
	private static final String SCREENSHOTS = "screenshots";
	private final IConfiguration config;
	private final Map<TestResourcesEnum, Supplier<Path>> locations = new EnumMap<>(TestResourcesEnum.class);  
	
	{
		locations.put(TestResourcesEnum.BASE_DIR, () -> getBaseDir());
		locations.put(TestResourcesEnum.RESULTS_DIR, () -> getResultsDir());
		locations.put(TestResourcesEnum.EXPECTED_SCREENSHOTS_DIR, () -> getExpectedScreenshotsDir());
		locations.put(TestResourcesEnum.FAILURE_SCREENSHOTS_DIR, () -> getFailureScreenshotsDir());
	}
	

	public CucumberDefaultTestResourceLocator(IConfiguration config)
	{
		this.config = config; 
	}

	@Override
	public Path getResourcePath(TestResourcesEnum of)
	{			
		return Optional.ofNullable(locations.get(of))
				.orElseThrow(() -> new IllegalArgumentException(String.format("Resource type [%s] is not supported", of.toString())))
				.get();		
	}
	
	@Override
	public Path getResourcePath(String name, TestResourcesEnum as)
	{		
		return getResourcePath(as).resolve(name);
	}		

	private Path getFailureScreenshotsDir()
	{
		return getScreenshotResultDir()
				.resolve(ThreadLocalBasedFeatureTracker.getCurrentFeatureName())
				.resolve(ThreadLocalBasedFeatureTracker.getCurrentScenarioName())
				.resolve("failure");
	}

	private Path getExpectedScreenshotsDir()
	{
		String screenResolution = String.format("%sx%s", getScreenWidth(), getScreenHeight());
		return getScreenshotsBaseDir()
				.resolve(config.getBrowser().toString().toLowerCase())
				.resolve(screenResolution);
	}			
	
	private Path getScreenshotResultDir()
	{
		return getResultsDir()
				.resolve(SCREENSHOTS);
	}

	
	private Path getScreenshotsBaseDir()
	{
		return getBaseDir()
				.resolve(SCREENSHOTS);
	}

	
	private int getScreenHeight()
	{
		double screenHeight = config.getBrowserWindowDimension() == null 
						? Toolkit.getDefaultToolkit().getScreenSize().getHeight()
						: config.getBrowserWindowDimension().getHeight();
		return (int)screenHeight;
	}

	
	private int getScreenWidth()
	{
		double screenWidth = config.getBrowserWindowDimension() == null 
				? Toolkit.getDefaultToolkit().getScreenSize().getWidth()
				: config.getBrowserWindowDimension().getWidth();
		return (int) screenWidth;
	}

	
	private Path getResultsDir()
	{
		return Paths
				.get("testruns")
				.resolve(UuidSingleton.get().toString());
	}

	
	private Path getBaseDir()
	{
		return ThreadLocalBasedFeatureTracker
				.getCurrentFeatureDir();
	}
		
}
