package com.statefarm.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.statefarm.utilities.BaseTest;
import com.statefarm.utilities.Driver;

public class InternetHerokuappTest extends BaseTest{

	@Test
	public void uploadFile(){
		testLogger = report.createTest("uploadFile");
		testLogger.info("Navigate to internet hero page");
		Driver.getDriver().get("http://the-internet.herokuapp.com/upload");
		testLogger.info("upload and submit file");
		page.herokuapp().uploadFile.sendKeys(System.getProperty("user.dir") + "\\src\\test\\resources\\Locators_table.pdf");
		page.herokuapp().submitFile.click();
		String expectedFile = "Locators_table.pdf";
		String actualFile = page.herokuapp().fileSuccesfullyUploaded.getText().trim();
		System.out.println(actualFile);
		assertEquals(actualFile, expectedFile, "file name: ");
	}
}
