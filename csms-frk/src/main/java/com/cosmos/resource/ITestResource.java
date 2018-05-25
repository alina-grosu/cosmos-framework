package com.cosmos.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ITestResource<T> {
		
	T load(Path path) throws IOException;
	void save(Path path, T resource) throws IOException;
	
	ITestResource<BufferedImage> IMAGE = new ITestResource<BufferedImage>()
	{
		private final Logger logger = LogManager.getLogger();
		
		@Override
		public BufferedImage load(Path path) throws IOException
		{
			logger.info(String.format("Attempting to read image from path [%s]", path.toString()));			
			return ImageIO.read(path.toFile());
		}

		@Override
		public void save(Path path, BufferedImage resource) throws IOException
		{
			logger.info(String.format("Attempting to write image to path [%s]", path.toString()));
			File file = path.toFile();
			file.mkdirs();
			ImageIO.write(resource, "png", file);
			
		}
	};
}
