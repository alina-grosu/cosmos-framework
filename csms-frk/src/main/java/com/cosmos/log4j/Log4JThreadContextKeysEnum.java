package com.cosmos.log4j;

public enum Log4JThreadContextKeysEnum {

	LOG_NAME_KEY("logfileName");
	
	private final String key;
	
	private Log4JThreadContextKeysEnum(String key)
	{
		this.key = key;
	}
	
	public String get()
	{
		return key;
	}
	
}
