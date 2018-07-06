package com.cosmos.cucumber.em.steps;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import com.cosmos.cucumber.context.ITestConfigurationContext;
import com.cosmos.cucumber.context.ITestResourceContext;

import com.cosmos.resource.TestResourcesEnum;
import com.cosmos.video.CustomScreenRecorder;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class VideoHooks {

	private static final Logger logger = LogManager.getLogger();
	private CustomScreenRecorder screenRecorder;
	private final ITestResourceContext resourceContext;
	private final ITestConfigurationContext configContext;
	
	public VideoHooks(ITestResourceContext resourceContext, ITestConfigurationContext configContext) throws Exception
	{
		this.resourceContext = resourceContext;			
		this.configContext = configContext;
	}

	@Before
	public void startRecord(Scenario scenario) throws Exception
	{
		if (configContext.getTestConfig().getShouldRecordVideo())
		{
			this.screenRecorder = defaultRecorder(scenario.getName());			
			screenRecorder.start();
			logger.info("Started recording video...");
		}
	}
	
	@After
	public void stopVideo() throws Exception
	{
		if (screenRecorder != null && screenRecorder.getState() == ScreenRecorder.State.RECORDING)
		{
			screenRecorder.stop();
			logger.info("Video record is stopped.");
			
			screenRecorder
				.getCreatedMovieFiles()
				.forEach((movie) -> 
					logger.info(String.format("Recorded video: [%s]", movie.getAbsolutePath())));
		}
	}
	
	private CustomScreenRecorder defaultRecorder(String fileName)  throws Exception
	{
		GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();
       
        return new CustomScreenRecorder(gc,
                	new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                	new Format(MediaTypeKey, MediaType.VIDEO, 
                		EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, (int)24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, (int) (15 * 60)),
                	new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,"black",
                        FrameRateKey, Rational.valueOf(30)),
                	resourceContext.getResourceLocator().getResourcePath(TestResourcesEnum.VIDEO_DIR).toFile(),
                	fileName
                );                
	}	
	
}
