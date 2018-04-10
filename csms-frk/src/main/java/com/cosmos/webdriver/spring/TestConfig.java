package com.cosmos.webdriver.spring;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.service.DriverService;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.cosmos.log4j.Log4JThreadBoundLogNameManager;
import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.config.IConfigurationBuilder;
import com.cosmos.webdriver.config.impl.ConfigurationFactory;
import com.cosmos.webdriver.config.impl.EnvironmentBasedConfigurationBuilder;
import com.cosmos.webdriver.config.impl.PropertiesBasedConfigurationBuilder;
import com.cosmos.webdriver.context.IStepsContext;
import com.cosmos.webdriver.context.impl.DefaultStepsContext;
import com.cosmos.webdriver.manager.BrowsersEnum;
import com.cosmos.webdriver.manager.ExecutionTypesEnum;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.manager.IDriverServiceManager;
import com.cosmos.webdriver.manager.StepContextScopesEnum;
import com.cosmos.webdriver.manager.impl.DefaultDriverManagerFactory;
import com.cosmos.webdriver.manager.impl.LocalDriverServiceManager;
import com.cosmos.webdriver.manager.impl.RemoteDriverServiceManager;
import com.cosmos.webdriver.pageobject.manager.PageObjectManager;
import com.cosmos.webdriver.spring.scope.GlueCodeScope;


@Configuration
public class TestConfig {
	
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
	
	@Bean
	public ChromeDriverService chromeDriverService()
	{
		return new ChromeDriverService.Builder()
				.usingDriverExecutable(configuration().getDriverExecutableLocation())
				.usingAnyFreePort()
				.build();			
	}
	
	@Bean 
	public InternetExplorerDriverService ieDriverService()
	{
		//TODO
		return null;
	}
	
	@Bean
	public Map<BrowsersEnum, DriverService> driverServices()
	{
		Map<BrowsersEnum, DriverService> driverServices = new EnumMap<>(BrowsersEnum.class);
		driverServices.put(BrowsersEnum.CHROME, chromeDriverService());
		driverServices.put(BrowsersEnum.IE, ieDriverService());
		return driverServices;
	}
	
	@Bean(destroyMethod="stopDriverService")
	public IDriverServiceManager localDriverServiceManager()
	{
		return new LocalDriverServiceManager(Optional.ofNullable(driverServices().get(configuration().getBrowser())));
	}	
	
	@Bean
	public IDriverServiceManager remoteDriverServiceManager()
	{
		return new RemoteDriverServiceManager(configuration());
	}
	
	@Bean
	public IDriverManager singletonDriverManager() 
	{
		return new DefaultDriverManagerFactory().newManager
						(
							configuration(), 
							configuration().getExecutionType() == ExecutionTypesEnum.REMOTE
									? remoteDriverServiceManager() 
									: localDriverServiceManager()
						);
	}
	
	@Bean
	@Scope("cucumber-glue")
	public IDriverManager glueCodeScopedDriverManager() 
	{
		return new DefaultDriverManagerFactory().newManager
				(
					configuration(), 
					configuration().getExecutionType() == ExecutionTypesEnum.REMOTE 
							? remoteDriverServiceManager()
							: localDriverServiceManager()
				);
	}	
	
	@Bean
	@Scope("prototype")
	public PageObjectManager pageObjectManager()
	{
		return StepContextScopesEnum.SCENARIO.equals(configuration().getStepsContextScope())
				? new PageObjectManager(glueCodeScopedDriverManager())
				: new PageObjectManager(singletonDriverManager());
	}
		
	@Bean
	@Scope("prototype")
	public IStepsContext stepsContext()
	{
		IStepsContext context = new DefaultStepsContext(pageObjectManager());		
		return context;
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
