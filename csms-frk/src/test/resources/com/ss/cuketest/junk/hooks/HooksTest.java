package com.ss.cuketest.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class HooksTest {

	@Before
	public void setUp()
	{
		System.out.println("setup");
	}
	
	@After
	public void tearDown()
	{
		System.out.println("tear down");
	}
	
}
