package com.statefarm.tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.statefarm.utilities.BaseTest;
import com.statefarm.utilities.Driver;

public class InternetHerokuappTest extends BaseTest {

	@Test(priority = 1)
	public void uploadFile() {
		testLogger = report.createTest("uploadFile");
		testLogger.info("Navigate to internet hero upload page");
		Driver.getDriver().get("http://the-internet.herokuapp.com/upload");
		testLogger.info("upload and submit file");
		page.herokuapp().uploadFile
				.sendKeys(System.getProperty("user.dir") + "\\src\\test\\resources\\Locators_table.pdf");
		page.herokuapp().submitFile.click();
		String expectedFile = "Locators_table.pdf";
		String actualFile = page.herokuapp().fileSuccesfullyUploaded.getText().trim();
		System.out.println(actualFile);
		assertEquals(actualFile, expectedFile, "file name: ");
	}

	@Test (priority = 2)
	public void alertHandle() throws InterruptedException {
		testLogger = report.createTest("alertHandle");
		testLogger.info("Navigate to internet hero javascript_alerts page");
		Driver.getDriver().get("http://the-internet.herokuapp.com/javascript_alerts");
		testLogger.info("Click jsAlert button");
		WebElement jsAlertBtn = page.herokuapp().jsAlert;
		WebElement jsConfirmBtn = page.herokuapp().jsConfirm;
		WebElement jsPromptBtn = page.herokuapp().jsPrompt;

		try {
			jsAlertBtn.click();
			//has to be called after alert is present else will throw exception
			alert = Driver.getDriver().switchTo().alert();
			String actualJsAlertText = alert.getText();
			String expectedJsAlertText = "I am a JS Alert";
			alert.accept();
			softAssert.assertEquals(actualJsAlertText, expectedJsAlertText, "JSAlert text: ");

			jsConfirmBtn.click();
			alert = Driver.getDriver().switchTo().alert();
			String actualJsConfirmText = alert.getText();
			String expectedJsConfirmText = "I am a JS Confirm";
			alert.dismiss();
			softAssert.assertEquals(actualJsConfirmText, expectedJsConfirmText, "JSConfirm text: ");

			jsPromptBtn.click();
			alert = Driver.getDriver().switchTo().alert();
			alert.sendKeys("testing alert");
			String actualJsPromptText = alert.getText();
			String expectedJsPromptText = "I am a JS prompt";
			alert.accept();
			softAssert.assertEquals(actualJsPromptText, expectedJsPromptText, "promp text: ");
			
			softAssert.assertAll();

		} catch (NoAlertPresentException e) {
			e.getStackTrace();
		}

	}

}
