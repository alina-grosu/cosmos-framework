package com.cosmos.webdriver.manager.impl;

import com.cosmos.webdriver.manager.ExecutionTypesEnum;
import com.cosmos.webdriver.manager.IDriverManagerConfiguration;
import com.cosmos.webdriver.manager.IDriverServiceManager;
import com.cosmos.webdriver.manager.IDriverServiceManagerFactory;

public class DefaultDriverServiceManagerFactory implements IDriverServiceManagerFactory {

	private final IDriverManagerConfiguration config;

	public DefaultDriverServiceManagerFactory(IDriverManagerConfiguration config)
	{
		this.config = config;
	}
	
	@Override
	public IDriverServiceManager getDriverServiceManager()
	{
		return config.getExecutionType().equals(ExecutionTypesEnum.REMOTE)
				? new RemoteDriverServiceManager(config)
				: new LocalDriverServiceManager(new DefaultDriverServiceFactory(config).newDriverService())
				;
	}

}
