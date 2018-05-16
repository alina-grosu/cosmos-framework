package com.cosmos.webdriver.context.impl;

import com.cosmos.webdriver.context.IUiComparisonContext;
import com.cosmos.webdriver.screenshots.IScreenshotsLocationAware;
import com.cosmos.webdriver.uicomparison.IUiComparator;
import com.cosmos.webdriver.uicomparison.IUiComparisonResult;

public class DefaultUiComparisonContext implements IUiComparisonContext {

	private final IUiComparator comparator;
	private final IScreenshotsLocationAware screenshotsLocationAware;
	private IUiComparisonResult latestFailure;
				
	public DefaultUiComparisonContext (IUiComparator comparator, IScreenshotsLocationAware screenshotsLocationAware)
	{
		this.comparator = comparator;
		this.screenshotsLocationAware = screenshotsLocationAware;
	}
	
	@Override
	public IUiComparator getUiComparator()
	{
		return comparator;
	}

	@Override
	public IUiComparisonResult getLatestFailure()
	{
		return latestFailure;
	}

	@Override
	public void setLatestFailure(IUiComparisonResult failure)
	{
		this.latestFailure = failure;
	}

	@Override
	public IScreenshotsLocationAware getScreenshotsLocationAware()
	{	
		return screenshotsLocationAware;
	}

}
