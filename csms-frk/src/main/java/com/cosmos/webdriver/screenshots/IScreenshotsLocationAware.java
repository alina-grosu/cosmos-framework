package com.cosmos.webdriver.screenshots;

import java.nio.file.Path;

public interface IScreenshotsLocationAware {

	Path getBaseScreenshotsLocation();
	Path getResultScreenshotsLocation();
	Path getFailureScreenshotsLocation();
	
}
