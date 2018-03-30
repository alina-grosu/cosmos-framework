package com.cosmos.webdriver.manager.impl;

import java.net.URL;

import com.cosmos.webdriver.manager.BrowsersEnum;

public class DefaultRemoteDriverManager extends AbstractRemoteDriverManager{

	public DefaultRemoteDriverManager(URL hubUrl, BrowsersEnum browser)
	{
		super(hubUrl, browser);
	}

}
