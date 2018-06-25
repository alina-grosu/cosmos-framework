package com.ss.cuketest.steps;

import com.cosmos.cucumber.feature.ThreadLocalBasedFeatureTracker;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class FeatureTrackingHooks {

	@Before
	public void updateFeatureInfo(Scenario scenario)
	{				
		ThreadLocalBasedFeatureTracker.setCurrentScenario(scenario);
	}
	
}
