package com.cosmos.webdriver.spring.registry.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cosmos.webdriver.spring.registry.IDisposable;
import com.cosmos.webdriver.spring.registry.IDisposableRegistry;

public class DefaultDisposableRegistry implements IDisposableRegistry {
	
	private static final Logger logger = LogManager.getLogger();
	
	private final List<IDisposable> contexts = new ArrayList<>();
	
	@Override
	public void registerDisposable(IDisposable disposable)
	{
		logger.info(String.format("Registering disposable : %s", disposable.toString()));
		contexts.add(disposable);		
	}

	@Override
	public void disposeAll()
	{
		System.out.println("-------- CALL DISPOSE ALL ---------" + contexts.size());
		contexts.forEach(IDisposable::dispose);
	}

}
