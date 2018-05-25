package com.cosmos.cucumber;

import java.nio.file.Path;
import java.nio.file.Paths;

import cucumber.api.Scenario;

public class ThreadLocalBasedFeatureTracker {
	
	private static final ThreadLocal<Scenario> currentScenario = new ThreadLocal<>();
	
	public static void setCurrentScenario(Scenario scenario)
	{
		currentScenario.set(scenario);
	}
					
	public static String getCurrentFeatureUri()
	{
		return currentScenario.get().getUri();
	}		
	
	public static Path getCurrentFeatureDir()
	{
		return Paths.get(currentScenario.get().getUri()).getParent();
	}
	
	public static String getCurrentScenarioName()
	{
		return currentScenario.get().getName(); 
	}
	
	public static String getCurrentFeatureName()
	{
		return getCurrentFeatureDir().getFileName().toString(); 
	}
	
}
