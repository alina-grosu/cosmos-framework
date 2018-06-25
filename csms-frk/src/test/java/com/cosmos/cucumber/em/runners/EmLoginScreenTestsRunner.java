package com.cosmos.cucumber.em.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
(
		features="src/test/resources/com/cosmos/cucumber/em/features/EmLoginScreen/EmLoginScreen.feature"
		, 	glue="com/cosmos/cucumber/em/steps"
		, 	plugin= {"io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm"}
)
public class EmLoginScreenTestsRunner {

}
