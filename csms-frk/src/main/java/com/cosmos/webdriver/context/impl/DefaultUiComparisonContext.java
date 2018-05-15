package com.cosmos.webdriver.context.impl;

import com.cosmos.webdriver.context.IUiComparisonContext;
import com.cosmos.webdriver.uicomparison.IUiComparator;
import com.cosmos.webdriver.uicomparison.IUiComparisonResult;

public class DefaultUiComparisonContext implements IUiComparisonContext {

	private IUiComparator comparator;
	private IUiComparisonResult latestFailure;
	
	public DefaultUiComparisonContext (IUiComparator comparator)
	{
		this.comparator = comparator;
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

}
