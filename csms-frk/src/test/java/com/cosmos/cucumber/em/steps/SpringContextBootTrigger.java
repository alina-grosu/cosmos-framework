package com.cosmos.cucumber.em.steps;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.cosmos.cucumber.spring.CucumberSpringApplicationContext;

import cucumber.api.java.en.Given;

@ContextConfiguration(classes=CucumberSpringApplicationContext.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class SpringContextBootTrigger {
		
	@Given("^Necessary for triggering Spring context boot$")
	public void bootSpringContext() throws Exception
	{	

	}
	
}
