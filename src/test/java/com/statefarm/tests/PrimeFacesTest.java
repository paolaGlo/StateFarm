package com.statefarm.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.statefarm.utilities.BaseTest;
import com.statefarm.utilities.Driver;

public class PrimeFacesTest extends BaseTest {

	@Test
	public void doubleClickOnElement(){
		testLogger = report.createTest("clickElementsPracticeActions");
		testLogger.info("navigate to prime faces");
		Driver.getDriver().get("https://www.primefaces.org/showcase/ui/misc/effect.xhtml");
		testLogger.info("practice clicking");
		WebElement puff = page.primeFaces().puff;
		actions.doubleClick(puff).perform();
		String expected = "in progress";
		String actual = "";
		System.out.println(actual);
		assertEquals("in progress", expected, "float: ");
	}
}
