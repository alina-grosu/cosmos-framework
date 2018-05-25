package com.cosmos.resource;

import java.nio.file.Path;

public interface ITestResourceLocator {
	
	Path getResourcePath(TestResourcesEnum of);
	Path getResourcePath(String name, TestResourcesEnum of);
	
}
