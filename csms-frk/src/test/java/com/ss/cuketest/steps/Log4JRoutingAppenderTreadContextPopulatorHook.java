package com.ss.cuketest.steps;

import com.cosmos.log4j.Log4JThreadBoundLogNameManager;
import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class Log4JRoutingAppenderTreadContextPopulatorHook {		
	
	@Before
	public void setUp(Scenario scenario)
	{				
		Log4JThreadBoundLogNameManager.updateFeatureName(getFeatureName(scenario));
	}
	
	private String getFeatureName(Scenario scenario)
	{			
		return scenario.getUri().substring(scenario.getUri().lastIndexOf("/") + 1);
	}

}
