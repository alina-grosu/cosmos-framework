package com.cosmos.webdriver.util;

import java.util.UUID;

public class UuidSingleton {

	private static final UUID instance = UUID.randomUUID();
	
	public static UUID get()
	{
		return instance;
	}
	
}
