package com.cosmos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Foo {
	
//	private static Foo foo;
//	
//	private Foo()
//	{
//		System.out.println("Foo!");
//	}
//	
//	public static Foo getInstance()
//	{
//		
//		if (null == foo)
//		{
//			foo = new Foo();
//		}
//		return foo; 
//	}
	
	private static Logger logger = LogManager.getLogger();
	
	public Foo ()
	{
		System.out.println(this.toString());
	}

	public static void main (String[] args)
	{
		logger.error(new RuntimeException());		
	}
}
