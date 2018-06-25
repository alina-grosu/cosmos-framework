package com.cosmos.cucumber.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.cosmos.cucumber.config.IConfiguration;
import com.cosmos.cucumber.config.IConfigurationBuilder;
import com.cosmos.cucumber.config.impl.ConfigurationFactory;
import com.cosmos.cucumber.config.impl.EnvironmentBasedConfigurationBuilder;
import com.cosmos.cucumber.config.impl.PropertiesBasedConfigurationBuilder;
import com.cosmos.cucumber.context.ITestConfigurationContext;
import com.cosmos.cucumber.context.ITestResourceContext;
import com.cosmos.cucumber.context.ITestUiContext;
import com.cosmos.cucumber.context.impl.DefaultTestConfigurationContext;
import com.cosmos.cucumber.context.impl.DefaultTestResourceContext;
import com.cosmos.cucumber.context.impl.DefaultTestUiContext;
import com.cosmos.cucumber.log4j.Log4JThreadBoundLogNameManager;
import com.cosmos.cucumber.resource.impl.CucumberDefaultTestResourceLocator;
import com.cosmos.cucumber.spring.scope.GlueCodeScope;
import com.cosmos.pageobject.em.manager.PageObjectManager;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.manager.impl.DefaultDriverManagerFactory;


@Configuration
public class MainSpringApplicationContext {
	
	/*
	Dummy bean main intent of which is to configure an initial thread local key value pair
	which is used by log4j's RoutingAppender in order to write traces
	for each thread/feature separately.
	*/		
	@Bean
    public Object log4jInitialSetup() 
	{			
		Log4JThreadBoundLogNameManager.init();		
		return new Object();
    }	
	
	@Bean
	public Map<String, IConfigurationBuilder> configurationBuilders()
	{
		Map<String, IConfigurationBuilder> builders = new HashMap<>();
		builders.put("env", new EnvironmentBasedConfigurationBuilder());
		builders.put("prop", new PropertiesBasedConfigurationBuilder());
		
		return builders;
	}			
	
	@Bean
	public IConfiguration configuration()
	{
		return new ConfigurationFactory(configurationBuilders()).getConfiguration();
	}		
			
	@Bean(destroyMethod="quitDriverAndDriverService")
	public IDriverManager driverManager() 
	{
		return new DefaultDriverManagerFactory(configuration()).newManager();
	}				
		
	@Bean
	@Scope("cucumber-glue")
	public ITestUiContext<PageObjectManager> stepsContext()
	{
		return new DefaultTestUiContext(new PageObjectManager(driverManager()), driverManager());				
	}			
	
	@Bean
	@Scope("cucumber-glue")
	public ITestResourceContext testResourceContext() 
	{		
		return new DefaultTestResourceContext(new CucumberDefaultTestResourceLocator(configuration()));
	}	
		
	@Bean
	@Scope("cucumber-glue")
	public ITestConfigurationContext testConfigurationContext() 
	{		
		return new DefaultTestConfigurationContext(configuration());
	}
	
	/*
	Workaround of a Cucumber issue: https://github.com/cucumber/cucumber-jvm/issues/965
	GlueCodeScope is a direct copy of package private class from respective Cucumber package
	which is unaccessible directly
	*/
	@Bean
    public static CustomScopeConfigurer glueScopeConfigurer()
	{
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.addScope("cucumber-glue", new GlueCodeScope());
        return configurer;
    }		
	
}
