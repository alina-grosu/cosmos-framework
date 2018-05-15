package com.cosmos.webdriver.uicomparison;

import java.awt.image.BufferedImage;

public interface IUiComparisonResult {

	enum UiComparisonStatusEnum {PASS, FAIL};
	
	UiComparisonStatusEnum getUiComparisonStatus();
	BufferedImage getBaseImage();
	BufferedImage getSampleImage();
	BufferedImage getDiffImage();	
		
}
