package com.cosmos.webdriver.uicomparison;

import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebElement;

public interface IUiComparisonIgnorableElementsAware {
		
	List<WebElement> getElementsToIgnore();
	
	default List<WebElement> getElementsToIgnore(String hint)
	{
		return Collections.emptyList();
	};
}
