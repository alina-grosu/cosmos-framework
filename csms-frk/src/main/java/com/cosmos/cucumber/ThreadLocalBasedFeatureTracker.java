package com.cosmos.cucumber;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ThreadLocalBasedFeatureTracker {

	private static final ThreadLocal<String> currentFeatureUri = ThreadLocal.withInitial(() -> "");	
	
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
