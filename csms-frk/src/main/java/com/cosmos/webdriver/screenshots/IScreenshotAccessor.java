package com.cosmos.webdriver.screenshots;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;

import com.cosmos.webdriver.screenshots.impl.DefaultScreenshotAccessor;
import com.cosmos.webdriver.screenshots.impl.InputFileTypeGuardingScreenshotAccessorDecorator;
import com.cosmos.webdriver.screenshots.impl.ProposingIfAbsentScreenshotAccessorDecorator;

public interface IScreenshotAccessor {

	BufferedImage readScreenshot(Path pathTofile) throws IOException;
	void persistScreenshot(Path path, BufferedImage data) throws IOException;
	static ScreenshotAccessorBuilder builder()
	{
		return new ScreenshotAccessorBuilder();
	}
	
	static class ScreenshotAccessorBuilder
	{
		private boolean inputGuard;
		private boolean proposeIfAbsent;		
		private IScreenshotAccessor instance;
		private Supplier<byte[]> proposedSupplier;
		
		private ScreenshotAccessorBuilder() {};
		
		public ScreenshotAccessorBuilder inputFileGuarding() 
		{
			inputGuard = true;
			return this;
		};
		
		public ScreenshotAccessorBuilder proposingIfAbsent(Supplier<byte[]> proposedSupplier)
		{
			proposeIfAbsent = true;
			this.proposedSupplier = proposedSupplier;
			return this;
		};
		
		public IScreenshotAccessor build()
		{
			instance = new DefaultScreenshotAccessor();
			
			if(proposeIfAbsent) 
				instance = new ProposingIfAbsentScreenshotAccessorDecorator(instance, proposedSupplier);
			if(inputGuard)
				instance = new InputFileTypeGuardingScreenshotAccessorDecorator(instance);
			
			return instance;
		}
		
	}
	
}
