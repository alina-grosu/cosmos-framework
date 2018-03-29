package com.ss.cuketest.steps;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cosmos.log4j.Log4JThreadBoundLogNameManager;
import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class Log4JRoutingAppenderTreadContextPopulatorHook {		
	
	private static final Logger logger = LogManager.getLogger();
	private static final String asterisks = "*";
	private static final String ASTERISK = "*";
	private static final String BLANK = "-";
	private static final String DOLLAR = "$";
	
	
//	@Before
	/*public void logFeatureName(Scenario scenario)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int amount = 0; amount < 128; amount++) sb.append(ASTERISK);
		logger.info(sb);
		
		
	}
	*/
	@Before
	public void setUp(Scenario scenario)
	{				
		Log4JThreadBoundLogNameManager.updateFeatureName(getFeatureName(scenario));
	}
	
	
	
	private String getFeatureName(Scenario scenario)
	{			
		return scenario.getUri().substring(scenario.getUri().lastIndexOf("/") + 1);
	}
	
	public static void main (String[] args) throws InterruptedException
	{
		int length = 132;
		int width = 6;				
		int total = length * width;
		int borderLength = 2;
		String message = "VeryLongfoooLongFeatureNameWithNewLinefeed\nfoo";
		int messageStartPoint = width / 2 * length - length/2 - message.length()/2;
		
		if (message.contains("\n"))
		{
			message = message.replace("\n", "");
		}
		
		
		/*for (int current = 0, nextLeftBorder = 0, nextRightBorder = length - 1; current < total; current++)
		{						
			if (current == nextLeftBorder)
			{				
				System.out.print(DOLLAR);
				nextLeftBorder += length;
			}			
			else if (current == nextRightBorder)
			{
				System.out.println(BLANK);
				nextRightBorder += length;
			}
			else if (current == messageStartPoint)
			{
				System.out.print(message);
				current += message.length() - 1;
			}
			else if (current > length && current < total - length)
			{
				System.out.print("?");
			}
			else
			{
				System.out.print(ASTERISK);
			}
		}*/
		
		
		int frameSize = 5;
		
		/*Function<Integer, String> bf = (n) -> 
											{
												String r = ""; 
												for (int i = 0; i < n; i++) r += "\b\r"; 
												return r;
											};*/ 
		/*String removeFrame =  bf.apply(frameSize);*/
		/*String removeFrame =  "\r\b\r\b";*/
		/*System.out.println(removeFrame.length());*/
		System.out.println("122");
		System.out.println("123");
		System.out.print("124");
		Thread.sleep(1000);
		System.out.print("\r\b\r\b\r");
		
		System.out.println("125");
		System.out.println("126");
		System.out.println("127");
		
			
		String spaceX12 = "            ";
		/*String spaceX6 = "      ";*/
		/*for (int i = 0; i < 20; i++)
		{
			System.out.print(String.format("X   X%s\n X X %s\n  X  %s\n X X %s\nX   X%s", spaceX12, spaceX12, spaceX12, spaceX12, spaceX12));
			Thread.sleep(500);
			System.out.print(removeFrame);
			
			System.out.print("X   X X   X\n X X   X X \n  X     X  \n X X   X   \nX   X X    ");
			Thread.sleep(500);
			System.out.print(removeFrame);
			
			System.out.print("X   X X   X X - X\n X X   X X  X  XX\n  X     X   X X X\n X X   X    XX  X\nX   X X     X   X");
			Thread.sleep(500);
			System.out.print(removeFrame);
			
		}*/
				
		
	}

}
