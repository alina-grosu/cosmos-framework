package com.cosmos.webdriver.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.config.IConfigurationBuilder;
import com.cosmos.webdriver.config.impl.ConfigurationFactory;
import com.cosmos.webdriver.config.impl.EnvironmentBasedConfigurationBuilder;
import com.cosmos.webdriver.config.impl.PropertiesBasedConfigurationBuilder;
import com.cosmos.webdriver.context.IStepsContext;
import com.cosmos.webdriver.context.impl.DefaultStepsContext;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.manager.impl.DefaultDriverManagerFactory;
import com.cosmos.webdriver.pageobject.manager.PageObjectManager;
import com.cosmos.webdriver.spring.registry.IDisposableRegistry;
import com.cosmos.webdriver.spring.registry.impl.DefaultDisposableRegistry;
import com.cosmos.webdriver.spring.scope.GlueCodeScope;

@Configuration
public class TestConfig {
	
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
	
	@Bean
	public IDriverManager singletonDriverManager() 
	{
		return new DefaultDriverManagerFactory(configuration()).getManager();
	}
	
	@Bean
	@Scope("cucumber-glue")
	public IDriverManager glueCodeScopedDriverManager() 
	{
		return new DefaultDriverManagerFactory(configuration()).getManager();
	}	
	
	@Bean
	@Scope("prototype")
	public PageObjectManager pageObjectManager()
	{
		return "scenario".equals(System.getProperty("com.cosmos.steps.context.scope"))
				? new PageObjectManager(glueCodeScopedDriverManager())
				: new PageObjectManager(singletonDriverManager());
	}
	
	@Bean(destroyMethod="disposeAll")
	public IDisposableRegistry registry()
	{
		return new DefaultDisposableRegistry();
	}
	
	@Bean
	@Scope("prototype")
	public IStepsContext stepsContext()
	{
		IStepsContext context = new DefaultStepsContext(pageObjectManager());
		registry().registerDisposable(context);
		return context;
	}	
		
	/*
	Workaround of a Cucumber issue: https://github.com/cucumber/cucumber-jvm/issues/965
	GlueCodeScope is a direct copy of package private class from respective Cucumber package
	which is unaccessible directly
	*/
	@Bean
    public static CustomScopeConfigurer glueScopeConfigurer(){
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();

        configurer.addScope("cucumber-glue", new GlueCodeScope());

        return configurer;
    }

}
