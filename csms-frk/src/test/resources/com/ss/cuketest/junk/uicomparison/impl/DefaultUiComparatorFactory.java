package com.cosmos.webdriver.uicomparison.impl;

import java.util.Optional;

import com.cosmos.webdriver.config.IConfiguration;
import com.cosmos.webdriver.uicomparison.IUiComparator;
import com.cosmos.webdriver.uicomparison.IUiComparatorFactory;
import com.cosmos.webdriver.uicomparison.UiComparatorTypesEnum;

public class DefaultUiComparatorFactory implements IUiComparatorFactory {
		
	private final IConfiguration config;

	public DefaultUiComparatorFactory(IConfiguration config)
	{
		this.config = config;
	}
	
	@Override
	public IUiComparator getUiComparator()
	{
		IUiComparator comparator = null;
		if (config.getUiComparatorType() == UiComparatorTypesEnum.ASHOT)
		{
			comparator = new AShotUiComparator();
		}
		
		return Optional
					.ofNullable(comparator)
				   	.orElseThrow(
				   			() -> new RuntimeException(
				   					String.format("Unable to determine screenshot comparator using key [%s].", 
								   	config.getUiComparatorType().toString())));		
		
	}

}
