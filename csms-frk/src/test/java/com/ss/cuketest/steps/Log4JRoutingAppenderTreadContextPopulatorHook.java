package com.ss.cuketest.steps;

import org.apache.logging.log4j.ThreadContext;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class Log4JRoutingAppenderTreadContextPopulatorHook {
	
	private static final String FEATURE_BOUND_LOG_FILE_NAME_KEY = "featureLogName";

	@Before
	public void setUp(Scenario scenario)
	{		
		if (!ThreadContext.containsKey(FEATURE_BOUND_LOG_FILE_NAME_KEY))
		{
			ThreadContext.put(FEATURE_BOUND_LOG_FILE_NAME_KEY,
					String.format("%s@%s-%s", getFeatureName(scenario), Thread.currentThread().getName(), Thread.currentThread().getId()));
		}
		
	}
	
	private String getFeatureName(Scenario scenario)
	{			
		return scenario.getUri().substring(scenario.getUri().lastIndexOf("/") + 1);
	}

}
