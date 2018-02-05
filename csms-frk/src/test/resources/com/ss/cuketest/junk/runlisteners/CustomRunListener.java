package com.ss.cuketest.junit.runlisteners;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import com.cosmos.webdriver.context.StaticFeatureTestContext;

public class CustomRunListener extends RunListener {

	@Override
	public void testRunFinished(Result result) throws Exception
	{
		System.out.println("test run finished");
//		StaticFeatureTestContext.getInstance().getDriverManager().getDriver().quit();
	}

	@Override
	public void testRunStarted(Description description) throws Exception
	{
		System.out.println("test run started");
	}

}
