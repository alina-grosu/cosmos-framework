package com.cosmos.cucumber.config.impl;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

import com.cosmos.webdriver.manager.BrowsersEnum;

class CapabilitiesStore {
	
	private static final String DEFAULT = "default";
	private static final Map<BrowsersEnum, Map<String, Capabilities>> allCaps = new EnumMap<>(BrowsersEnum.class);
	static
	{
		//Chrome
		Map<String, Capabilities> chromeCapsMap = new HashMap<>();
		//default
		chromeCapsMap.put(DEFAULT, getChromeDefault());
		allCaps.put(BrowsersEnum.CHROME, chromeCapsMap);
		
		//IE
		Map<String, Capabilities> ieCapsMap = new HashMap<>();
		//default
		ieCapsMap.put(DEFAULT, getIeDefault());
		allCaps.put(BrowsersEnum.IE, ieCapsMap);
		
	}

	public static Capabilities get(BrowsersEnum desiredBrowser, String key)
	{		
		return Optional.ofNullable
				(
					Optional.ofNullable(allCaps.get(desiredBrowser))
							.orElseThrow(() -> new RuntimeException(String.format("DesiredCapabilies for browser %s are not available", desiredBrowser)))
							.get(key)
				).orElse(allCaps.get(desiredBrowser).get(DEFAULT));
	}

	private static Capabilities getChromeDefault()
	{
		ChromeOptions chromeDefault = new ChromeOptions();
		chromeDefault.addArguments("start-maximized");
		return chromeDefault;
	}
			
	private static Capabilities getIeDefault()
	{
		InternetExplorerOptions ieDefault = new InternetExplorerOptions()
				.destructivelyEnsureCleanSession();
		return ieDefault;
	}

}
