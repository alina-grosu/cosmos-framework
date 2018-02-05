package com.cosmos.webdriver.manager.impl;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.manager.IDriverManager;
import com.cosmos.webdriver.manager.IDriverManagerFactory;

public class DefaultDriverManagerFactory implements IDriverManagerFactory {

	private final IConfiguration config;

	public DefaultDriverManagerFactory(IConfiguration config)
	{
		this.config = config;
	}
	
	@Override
	public IDriverManager getManager()
	{
		IDriverManager mgr;
		switch (config.getExecutionType())
		{
			case LOCAL:
				switch (config.getBrowser())
				{
					case CHROME:
						mgr = new ChromeDriverManager(config.getDriverExecutableLocation());
						break;
					default:
						throw new RuntimeException("Browser is temporarily unsupported");
				}
				break;
			case REMOTE:
				default:
					mgr = new DefaultRemoteDriverManager(config.getRemoteGridHubUrl(), config.getBrowser());
		}
		return mgr;
	}

}
