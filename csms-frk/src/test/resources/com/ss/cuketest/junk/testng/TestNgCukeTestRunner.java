/*package com.ss.cuketest.testng;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions
(
		features="src/test/resources/com/ss/cuketest/features"
	, 	glue="com/ss/cuketest/steps"	
)
public class TestNgCukeTestRunner extends AbstractTestNGCucumberTests {

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite()
	{
		System.out.println("TestNg before suite");
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite()
	{
		System.out.println("TestNg after suite");
	}
	
}
*/