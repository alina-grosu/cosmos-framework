package com.cosmos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Foo {
	
	static String foo;
	static {
		
	}
	
	static {
		
	}
	
	{
		
	}
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
	
//	private static Logger logger = LogManager.getLogger();
	
	public Foo ()
	{
		System.out.println(this.toString());
	}

	public static void main (String[] args)
	{
		Parent p = new Child();
		Child c = new Child();
		try
		{
			p.foo();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.foo();
	}
}

class Parent {
	
	public Parent()
	{
		System.out.println("p creates foo!");
	}
	
	 void foq()
	{
		System.out.println("foq!");
	}
	
	Object foo() throws IOException
	{
		System.out.println("p foo");
		return null;
	}
}

class Child extends Parent{
	
	public Child()
	{
		System.out.println("c creates foo!");						
	}
	
	Object foo()
	{
		byte b = 127;
		System.out.println("c foo");
		float d = (float) 4.d;
		char c = 'a';
		System.out.println("foq! " + d + " " + c + " " + (int)c);
		System.out.println(4.0f == 4d);
		
		Parent p = new Parent();
		p.foq();
		Parent pp = new Child();
		pp.foq();
		
		return null;
	}	
	
	public static void main (String[] args) throws InterruptedException
	{
		int length = 132;
		int width = 6;				
		int total = length * width;		
		String message = "VeryLongfoooLongFeatureNameWithNewLinefeed\nfoo";
		int messageStartPoint = width / 2 * length - length/2 - message.length()/2;
		
		if (message.contains("\n"))
		{
			message = message.replace("\n", "");
		}
		
		
		for (int current = 0, nextLeftBorder = 0, nextRightBorder = length - 1; current < total; current++)
		{						
			if (current == nextLeftBorder)
			{				
				System.out.print("$");
				nextLeftBorder += length;
			}			
			else if (current == nextRightBorder)
			{
				System.out.println(" ");
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
				System.out.print("*");
			}
		}						
		
	}
}
