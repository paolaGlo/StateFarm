/*
 * Copyright (c) PaolaGloria. All rights reserved.
 * Paola PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.statefarm.utilities;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.statefarm.data.loader.TestScenarioLoader;

/**
 * 
 * @author paola Gloria
 *
 */
public class BaseTest {

	TestScenarioLoader loader = new TestScenarioLoader();

	public void reportSetUp() {

	}

	@BeforeMethod
	public void testSetUP() throws Exception {
		Driver.setUp();
		Driver.getDriver().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		Driver.getDriver().manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() {
		Driver.closeDriver();
	}

	public void reportFlush() {

	}

	public void loadDataSet(String dataSetName) throws Exception {
		loader.loadData(dataSetName);
	}
}
