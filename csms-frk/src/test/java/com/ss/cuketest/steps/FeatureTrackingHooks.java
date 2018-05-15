package com.ss.cuketest.steps;

import com.cosmos.cucumber.ThreadLocalBasedFeatureTracker;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class FeatureTrackingHooks {

	@Before
	public void updateFeatureInfo(Scenario scenario)
	{	
		System.out.println(scenario.getUri());
		ThreadLocalBasedFeatureTracker.updateCurrentFeatureUri(scenario.getUri());
	}
	
}
