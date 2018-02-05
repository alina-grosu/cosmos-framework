package com.cosmos.webdriver.manager.impl;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.cosmos.webdriver.manager.Browsers;
import com.cosmos.webdriver.manager.IDriverManager;

public class AbstractRemoteDriverManager implements IDriverManager{
		
	protected WebDriver driver;
	protected URL hubUrl;
	protected Browsers browser;	
	private final Map<Browsers, Supplier<Capabilities>> lamCapsMap = new EnumMap<>(Browsers.class);
	
	public AbstractRemoteDriverManager(URL hubUrl, Browsers browser)
	{
		this.hubUrl = hubUrl;
		this.browser = browser;
		lamCapsMap.put(Browsers.CHROME, ChromeOptions::new);
		lamCapsMap.put(Browsers.IE, InternetExplorerOptions::new);
	}
	
	@Override
	public WebDriver getDriver()
	{
		if (null == driver) {			
			Capabilities caps = Optional.ofNullable(lamCapsMap.get(browser))
								.orElseThrow(() -> new RuntimeException("Browser " + browser + " is not supported for remote execution."))
								.get()
								;
			driver = new RemoteWebDriver(hubUrl, caps);
        }
		return driver;
	}

	@Override
	public void quitDriver()
	{
		if (null != driver) {
            driver.quit();
            driver = null;
        }	
	}

}
