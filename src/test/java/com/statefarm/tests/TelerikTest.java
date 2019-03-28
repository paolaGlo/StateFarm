package com.statefarm.tests;

import static org.testng.Assert.assertEquals;

import java.awt.Window;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.statefarm.utilities.BaseTest;
import com.statefarm.utilities.Driver;

public class TelerikTest extends BaseTest {

	@Test
	public void dragAndDrop() throws Exception {
		testLogger = report.createTest("dragAndDrop");
		testLogger.info("Navigat to Telerik");
		Driver.getDriver().get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		WebElement source = page.telerick().dragabbleCircle;
		WebElement target = page.telerick().dropTarget;
		String expected = "You did great!";
		actions.dragAndDrop(source, target).perform();
		String actual = target.getText();
		System.out.println(actual);
		assertEquals(actual, expected, "drop messasge: ");
	}

	@Test
	public void iframeCookies() throws Exception {
		testLogger = report.createTest("iframeCookies");
		testLogger.info("Navigat to Telerik");
		Driver.getDriver().get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		WebElement cookiesBtn = page.telerick().AcceptCookiesBtn;
		wait.until(ExpectedConditions.visibilityOf(cookiesBtn));
		cookiesBtn.click();
	}
}
