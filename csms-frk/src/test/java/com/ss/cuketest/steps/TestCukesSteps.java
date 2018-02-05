package com.ss.cuketest.steps;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.cosmos.webdriver.context.IStepsContext;
import com.cosmos.webdriver.pageobject.pages.LoginPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestCukesSteps{

	// private final IStepsContext stepcontext;
	// private final WebDriver driver;

	// public TestCukesSteps (IStepsContext stepsContext)
	// {
	// System.out.println("TestCukesSteps constructor call");
	// this.stepcontext = stepsContext;
	// this.driver = stepsContext.getDriverManager().getDriver();
	//// System.out.println(context.getConfiguration().getBrowser());
	//// System.out.println(context.getConfiguration().getDriverExecutableLocation());
	//// System.out.println(context.getConfiguration().getExecutionType());
	//// System.out.println(context.getConfiguration().getRemoteGridHubUrl());
	// }
	private final IStepsContext context;

	public TestCukesSteps(@Autowired IStepsContext context)
	{	
		this.context = context;
		if (null != context)
		{
			System.out.println(context);
//			System.out.println(context.getDriverManager());
		}
		else 
		{
			System.out.println("NULL");
		}
	}

//	@Given("^user navigates to login page$")
//	public void user_navigates_to_login_page() throws Exception
//	{
//
//		System.out.println(1);
//		System.out.println("Started in thread " + Thread.currentThread().getId());
//		WebDriver driver = context.getDriverManager().getDriver();
//		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
////		driver.get("https://www.google.com.ua");
//		driver.get("https://alpha.em.tst-us-east.medispend.com/login");		
////		driver.findElement(By.xpath("//input[@name = 'q']")).sendKeys("Foo");
////		driver.findElement(By.xpath("//input[@name = 'q']")).sendKeys(" ");
////		driver.findElement(By.xpath("//input[@name = 'q']")).sendKeys("Foo");
////		driver.findElement(By.xpath("//input[@name = 'q']")).sendKeys(" ");
////		driver.findElement(By.xpath("//input[@name = 'q']")).sendKeys(Thread.currentThread().getName());
////		driver.findElement(By.xpath("//input[@name = 'btnK']")).sendKeys(Keys.ENTER);
//				 		
//		System.out.println(new LoginPage(context.getDriverManager()).isAt());
//		Thread.sleep(5000);
//
//	}

	@And("^user logs in as \"([^\"]*)\" \"([^\"]*)\"$")
	public void user_logs_in_as(String arg1, String arg2) throws Exception
	{
		System.out.println(2);
		// Thread.sleep(5000);
	}

	@When("^some page is available$")
	public void some_page_is_available() throws Exception
	{
		System.out.println(3);
	}

}
