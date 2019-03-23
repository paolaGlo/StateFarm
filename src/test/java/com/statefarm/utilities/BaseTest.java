/*
 * Copyright (c) PaolaGloria. All rights reserved.
 * Paola PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.statefarm.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.DateFormatManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
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

	protected static final Logger LOGGER = LogManager.getLogger(BaseTest.class);
	protected TestScenarioLoader loader = new TestScenarioLoader();
	protected ExtentTest testLogger;
	protected ExtentReports report;
	protected ExtentHtmlReporter htmlReport;

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
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshot = getScreenshot(result.getName());
			testLogger.addScreenCaptureFromPath(screenshot);
			testLogger.fail(result.getName() + " : Failed");
			testLogger.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			testLogger.skip("Test skipped: " + result.getName());
		}
		Driver.closeDriver();
	}

	@AfterTest(alwaysRun = true)
	public void reportFlush() {
		report.flush();
	}

	public void loadDataSet(String dataSetName) throws Exception {
		loader.loadData(dataSetName);
	}

	public String getScreenshot(String testResultName) {
		String time = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
		File source = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
		String target = System.getProperty("user.dir") + "test-output/screenshots/" + time + testResultName + ".png";
		File destFile = new File(target);
		try {
			FileUtils.copyFile(source, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return target;
	}
}
