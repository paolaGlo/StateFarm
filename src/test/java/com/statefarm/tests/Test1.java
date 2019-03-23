package com.statefarm.tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.statefarm.utilities.BaseTest;
import com.statefarm.utilities.Driver;

public class Test1 extends BaseTest {

	
	@Test
	public void testing() {
		testLogger = report.createTest("testing");
		testLogger.info("login to google");
		Driver.getDriver().get("http://www.google.com");
		testLogger.info("get tilte");
		String title = Driver.getDriver().getTitle();
		System.out.println(title);
		assertTrue(title.equals("paola"),"title: ");
	}
}
