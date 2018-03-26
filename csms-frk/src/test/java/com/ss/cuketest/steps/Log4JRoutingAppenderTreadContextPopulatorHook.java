package com.ss.cuketest.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import com.cosmos.log4j.Log4JThreadContextKeysEnum;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class Log4JRoutingAppenderTreadContextPopulatorHook {
	
	private static final Logger logger = LogManager.getLogger();
	//private static boolean isSet = false;
	private static ThreadLocal<Boolean> isSet = ThreadLocal.withInitial(() -> false );

	@Before
	public void setUp(Scenario scenario)
	{		
		if (!isSet.get())
		{
			String featureName = getFeatureName(scenario);
			String currentThreadBoundLogName = ThreadContext.get(Log4JThreadContextKeysEnum.LOG_NAME_KEY.get());
			String newThreadBoundLogName = String.format("%s@%s", 
					featureName, 
					currentThreadBoundLogName);
			
			logger.info(String.format("Updating thread bound log name from %s to %s.",
										currentThreadBoundLogName, 
										newThreadBoundLogName));			
			
			ThreadContext.put(Log4JThreadContextKeysEnum.LOG_NAME_KEY.get(),
					newThreadBoundLogName);
			isSet.set(true);
		}
		
	}
	
	private String getFeatureName(Scenario scenario)
	{			
		return scenario.getUri().substring(scenario.getUri().lastIndexOf("/") + 1);
	}

}
