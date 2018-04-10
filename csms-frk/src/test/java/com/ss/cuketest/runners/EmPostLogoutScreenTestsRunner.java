package com.ss.cuketest.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
(
		features="src/test/resources/com/ss/cuketest/features/EmPostLogoutScreen.feature"
		, 	glue="com/ss/cuketest/steps"
		, 	plugin= {"io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm"}
)
public class EmPostLogoutScreenTestsRunner {

}
