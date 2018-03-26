package com.cosmos.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;


public class Log4JThreadBoundLogNameManager {
	
	private static final String LOG_NAME_KEY = "logfileName";	
	private static final Logger logger = LogManager.getLogger();
	private static final ThreadLocal<LogFileName> logName = ThreadLocal.withInitial(() -> new LogFileName());
	
	
	public static void init()
	{			
		ThreadContext.put(LOG_NAME_KEY, logName.get().get());
		
		logger.info(
				String.format("Set initial value for key '%s' in thread '%s' to '%s'", 
							LOG_NAME_KEY, 
							Thread.currentThread().getName(), 
							logName.get().get()));				
	}
	
	public static void updateFeatureName(String featureName)
	{
		if (!logName.get().featureName.equals(featureName))
		{
			setFeatureName(featureName);
		}
	}
	
	public static void setFeatureName(String featureName)
	{		
		String currentLogFileName = ThreadContext.get(LOG_NAME_KEY);
		logName.get().setFeatureName(featureName);
		String newLogFileName = logName.get().get();		
		
		ThreadContext.put(LOG_NAME_KEY, logName.get().get());
		
		logger.info(
				String.format("Set value for key '%s' in thread '%s' from '%s' to '%s'", 
							LOG_NAME_KEY, 
							Thread.currentThread().getName(), 
							currentLogFileName,
							newLogFileName));
	}	
			
	private static class LogFileName {
		
		private static final String LOG_NAME_PATTERN = "log-%s@%s.txt";		
		private String featureName = "featureless";
		private String threadId = String.format("%s-%s", 
							Thread.currentThread().getName(), 
							Thread.currentThread().getId());
		String get()
		{
			return String.format(LOG_NAME_PATTERN, featureName, threadId);
		}
		
		void setFeatureName (String featureName)
		{
			this.featureName = featureName;
		}				
	}
	
	
}
