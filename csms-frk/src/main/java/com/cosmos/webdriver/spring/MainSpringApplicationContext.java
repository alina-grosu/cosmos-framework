package com.cosmos.webdriver.spring;

import java.util.HashMap;
import java.util.Map;
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
import com.cosmos.webdriver.context.IUiComparisonContext;
import com.cosmos.webdriver.context.IUiDrivingStepContext;
import com.cosmos.webdriver.context.impl.DefaultUiComparisonContext;
import com.cosmos.webdriver.context.impl.DefaultUiDrivingStepContext;
import com.cosmos.webdriver.manager.ExecutionTypesEnum;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.manager.IDriverServiceManager;
import com.cosmos.webdriver.manager.StepContextScopesEnum;
import com.cosmos.webdriver.manager.impl.DefaultDriverManagerFactory;
import com.cosmos.webdriver.manager.impl.DefaultDriverServiceFactory;
import com.cosmos.webdriver.manager.impl.LocalDriverServiceManager;
import com.cosmos.webdriver.manager.impl.RemoteDriverServiceManager;
import com.cosmos.webdriver.pageobject.manager.PageObjectManager;
import com.cosmos.webdriver.screenshots.IScreenshotsLocationAware;
import com.cosmos.webdriver.screenshots.impl.FeatureLocationBasedShcreenshotsLocationAware;
import com.cosmos.webdriver.spring.scope.GlueCodeScope;
import com.cosmos.webdriver.uicomparison.IUiComparator;
import com.cosmos.webdriver.uicomparison.impl.DefaultUiComparatorFactory;


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
	
	@Bean(destroyMethod="stopDriverService")
	public IDriverServiceManager driverServiceManager()
	{
		return configuration().getExecutionType().equals(ExecutionTypesEnum.REMOTE)
				? new RemoteDriverServiceManager(configuration())
				: new LocalDriverServiceManager(new DefaultDriverServiceFactory().newDriverService(configuration()))
				;
	}
	
	@Bean(destroyMethod="quitDriver")
	public IDriverManager singletonDriverManager() 
	{
		return new DefaultDriverManagerFactory().newManager(configuration(), driverServiceManager());
	}
	
	@Bean(destroyMethod="quitDriver")
	@Scope("cucumber-glue")
	public IDriverManager glueCodeScopedDriverManager() 
	{
		return new DefaultDriverManagerFactory().newManager(configuration(), driverServiceManager());
	}	
			
	@Bean
	@Scope("prototype")
	public PageObjectManager pageObjectManager()
	{
		return new PageObjectManager(driverManager());
	}

	private IDriverManager driverManager()
	{
		return StepContextScopesEnum.SCENARIO.equals(configuration().getStepsContextScope())
				? glueCodeScopedDriverManager()
				: singletonDriverManager();
	}
		
	@Bean
	@Scope("prototype")
	public IUiDrivingStepContext stepsContext()
	{
		return new DefaultUiDrivingStepContext(pageObjectManager(), configuration(), driverManager());				
	}	
	
	@Bean
	@Scope("cucumber-glue")
	public IUiComparisonContext uiComparisonContext() 
	{
		IUiComparator comparator = new DefaultUiComparatorFactory(configuration()).getUiComparator();
		IScreenshotsLocationAware screenshotsLocationAware = new FeatureLocationBasedShcreenshotsLocationAware();
		DefaultUiComparisonContext defaultIUiComparisonContext = 
				new DefaultUiComparisonContext(comparator, screenshotsLocationAware);
		System.out.println("RETURNING INSTANCE" + defaultIUiComparisonContext );
		return defaultIUiComparisonContext;
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
