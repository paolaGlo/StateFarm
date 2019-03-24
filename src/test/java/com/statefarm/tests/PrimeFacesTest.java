package com.statefarm.tests;

import org.testng.annotations.Test;

import com.statefarm.utilities.BaseTest;
import com.statefarm.utilities.Driver;

public class PrimeFacesTest extends BaseTest {

	@Test
	public void clickElementsPracticeActions() {
		testLogger = report.createTest("clickElementsPracticeActions");
		testLogger.info("navigate to prime faces");
		Driver.getDriver().get("https://www.primefaces.org/showcase/ui/misc/effect.xhtml");
		testLogger.info("practice clicking");
	}
}
