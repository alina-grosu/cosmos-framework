package com.cosmos.webdriver.manager.impl;

import java.net.URL;

import com.cosmos.webdriver.manager.Browsers;

public class DefaultRemoteDriverManager extends AbstractRemoteDriverManager{

	public DefaultRemoteDriverManager(URL hubUrl, Browsers browser)
	{
		super(hubUrl, browser);
	}

}
