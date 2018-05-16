package com.cosmos.cucumber;

import java.nio.file.Path;
import java.nio.file.Paths;

import cucumber.api.Scenario;

public class ThreadLocalBasedFeatureTracker {

	private static final ThreadLocal<String> currentFeatureUri = ThreadLocal.withInitial(() -> "");	
	private static final ThreadLocal<Scenario> currentScenario = new ThreadLocal<>();
	
	public static void setCurrentScenario(Scenario scenario)
	{
		currentScenario.set(scenario);
	}
	
	public static Path getCurrentScenarioDir()
	{
		return Paths.get(currentScenario.get().getUri()).getParent().resolve(currentScenario.get().getName());
	}
	
	public static void updateCurrentFeatureUri(String uri)
	{
		if (!currentFeatureUri.get().equals(uri))
		{
			currentFeatureUri.set(uri);
		}
	}
	
	public static String getCurrentFeatureUri()
	{
		return currentFeatureUri.get();
	}		
	
	public static Path getCurrentFeatureDir()
	{
		return Paths.get(currentFeatureUri.get()).getParent();
	}
	
}
