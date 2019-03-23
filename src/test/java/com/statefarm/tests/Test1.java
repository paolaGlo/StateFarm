package com.statefarm.tests;

import org.testng.annotations.Test;

import com.statefarm.utilities.BaseTest;
import com.statefarm.utilities.Driver;

public class Test1 extends BaseTest {

	@Test
	public void testing() {
		Driver.getDriver().get("http://www.google.com");
	}
}
