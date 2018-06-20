package com.cosmos.webdriver.context;

import com.cosmos.webdriver.uicomparison.IUiComparator;

public interface IUiComparisonContext extends ICucumberStepContext {
	
	IUiComparator getUiComparator();
	
}
