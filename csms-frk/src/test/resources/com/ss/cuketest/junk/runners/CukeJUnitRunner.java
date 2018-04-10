package com.ss.cuketest.runners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
(
		features="src/test/resources/com/ss/cuketest/features/TestCukes2.feature"
		, 	glue="com/ss/cuketest/steps"
)
public class CukeJUnitRunner {
	
}
