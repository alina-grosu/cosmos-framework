package com.cosmos.video;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.io.File;
import java.io.IOException;
import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.screenrecorder.ScreenRecorder;

public class CustomScreenRecorder extends ScreenRecorder {
	
	private final String fileName;
	
	public CustomScreenRecorder(GraphicsConfiguration cfg,            
            					Format fileFormat,
            					Format screenFormat,
            					Format mouseFormat,            
            					File movieFolder,
            					String fileName) 	throws Exception, AWTException
	{
		super (cfg, null, fileFormat, screenFormat, mouseFormat, null, movieFolder);
		this.fileName = fileName;
	}

	@Override
	protected File createMovieFile(Format fileFormat) throws IOException
	{
		if (!movieFolder.exists()) 
		{
            movieFolder.mkdirs();
        } 
		else if (!movieFolder.isDirectory()) 
		{
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }        

        return new File(movieFolder, fileName + "." + Registry.getInstance().getExtension(fileFormat));       	
	}
	
	
}
