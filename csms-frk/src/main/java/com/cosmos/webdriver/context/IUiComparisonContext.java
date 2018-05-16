package com.cosmos.webdriver.context;

import com.cosmos.webdriver.screenshots.IScreenshotsLocationAware;
import com.cosmos.webdriver.uicomparison.IUiComparator;
import com.cosmos.webdriver.uicomparison.IUiComparisonResult;

public interface IUiComparisonContext extends ICucumberStepContext {
	
	IUiComparator getUiComparator();
	IScreenshotsLocationAware getScreenshotsLocationAware();
	IUiComparisonResult getLatestFailure();
	void setLatestFailure(IUiComparisonResult failure);
}
