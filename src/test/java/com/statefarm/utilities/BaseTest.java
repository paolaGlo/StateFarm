/*
 * Copyright (c) PaolaGloria. All rights reserved.
 * Paola PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.statefarm.utilities;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.statefarm.data.loader.TestScenarioLoader;

/**
 * 
 * @author paola Gloria
 *
 */
public class BaseTest {

	private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);
	private TestScenarioLoader loader = new TestScenarioLoader();
	private ExtentTest test;
	private ExtentReports report;
	private ExtentHtmlReporter htmlReport;

	@BeforeTest(alwaysRun = true)
	public void reportSetUp() {
		String reportPath = System.getProperty("user.dir") + "/test-output/report.html";

		htmlReport = new ExtentHtmlReporter(reportPath);
		htmlReport.config().setDocumentTitle("StateFarm Automation Report");
		htmlReport.config().setReportName("Automated Regression");
		report = new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("environment", "test");
		report.setSystemInfo("browser", "b");
		report.setSystemInfo("OS", System.getProperty("os.name"));
	}

	@BeforeMethod(alwaysRun = true)
	public void testSetUP() throws Exception {
		Driver.setUp();
		Driver.getDriver().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		Driver.getDriver().manage().window().maximize();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		Driver.closeDriver();
	}

	public void reportFlush() {

	}

	public void loadDataSet(String dataSetName) throws Exception {
		loader.loadData(dataSetName);
	}
}
