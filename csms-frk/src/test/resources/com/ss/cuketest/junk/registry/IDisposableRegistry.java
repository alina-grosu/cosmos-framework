package com.cosmos.webdriver.spring.registry;

public interface IDisposableRegistry {
	
	void registerDisposable(IDisposable disposable);
	
	void disposeAll();
	
}
