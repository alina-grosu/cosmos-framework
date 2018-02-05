package com.cosmos.webdriver.spring.registry.impl;

import java.util.ArrayList;
import java.util.List;

import com.cosmos.webdriver.spring.registry.IDisposable;
import com.cosmos.webdriver.spring.registry.IDisposableRegistry;

public class DefaultDisposableRegistry implements IDisposableRegistry {
	
		
	private final List<IDisposable> contexts = new ArrayList<>();
	
	@Override
	public void registerDisposable(IDisposable disposable)
	{
		contexts.add(disposable);		
	}

	@Override
	public void disposeAll()
	{
		contexts.forEach(IDisposable::dispose);
	}

}
