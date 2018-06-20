package com.cosmos.webdriver.uicomparison.impl;

import java.awt.image.BufferedImage;

import com.cosmos.webdriver.uicomparison.IUiComparisonResult;

public class DefaultUiComparisonResult implements IUiComparisonResult {
	
	private UiComparisonStatusEnum status;	
	private BufferedImage baseImage;
	private BufferedImage sampleImage;
	private BufferedImage diffImage;
	
	public DefaultUiComparisonResult
			(
					UiComparisonStatusEnum status,
					BufferedImage baseImage,
					BufferedImage sampleImage,
					BufferedImage diffImage
			)
	{		
		this.status = status;
		this.baseImage = baseImage;
		this.sampleImage = sampleImage;
		this.diffImage = diffImage;
	}

	@Override
	public UiComparisonStatusEnum getUiComparisonStatus()
	{		
		return status;
	}

	@Override
	public BufferedImage getBaseImage()
	{
		return baseImage;
	}

	@Override
	public BufferedImage getSampleImage()
	{
		return sampleImage;
	}

	@Override
	public BufferedImage getDiffImage()
	{	
		return diffImage;
	}

}
