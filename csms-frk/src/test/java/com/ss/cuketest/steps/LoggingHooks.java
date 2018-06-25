package com.ss.cuketest.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cosmos.cucumber.log4j.Log4JThreadBoundLogNameManager;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class LoggingHooks {		
	
	private static final Logger logger = LogManager.getLogger();	
	private static final String ASTERISK = "*";
	private static final String BLANK = " ";	
	private static final String LINE_SEPARATOR = "\n";
	
	@Before
	public void updateLogFileName(Scenario scenario)
	{				
		Log4JThreadBoundLogNameManager.updateFeatureName(getFeatureName(scenario));
		logFeatureAndScenarioName(scenario);
	}
		
	public void logFeatureAndScenarioName(Scenario scenario)
	{
		StringBuilder sb = new StringBuilder(LINE_SEPARATOR);
							
		int length = 164;
		int width = 3;
		int total = length * width;
		String featureName = String.format("feature: %s  scenario: %s", getFeatureName(scenario), scenario.getName());
		int messageStartPoint = (width / 2 == 1 ? width / 2 + 1 : width / 2) * length 
								- length/2 
								- featureName.length()/2;					
		
		for (int current = 0, nextLeftBorder = 0, nextRightBorder = length - 1; current < total; current++)
		{						
			if (current == nextLeftBorder)
			{				
				sb.append(ASTERISK);
				nextLeftBorder += length;
			}			
			else if (current == nextRightBorder)
			{
				sb.append(ASTERISK).append(LINE_SEPARATOR);
				nextRightBorder += length;
			}
			else if (current == messageStartPoint)
			{
				sb.append(featureName);
				current += featureName.length() - 1;
			}
			else if (current > length && current < total - length)
			{
				sb.append(BLANK);
			}
			else
			{
				sb.append(ASTERISK);
			}
		}			
		logger.info(sb);
	}			
			
	private String getFeatureName(Scenario scenario)
	{			
		return scenario.getUri().substring(scenario.getUri().lastIndexOf("/") + 1);
	}			

}
