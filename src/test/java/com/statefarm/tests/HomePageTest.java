package com.statefarm.tests;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.statefarm.utilities.BaseTest;
import com.statefarm.utilities.Driver;

public class HomePageTest extends BaseTest {

	//@Test
	public void counItemsAmazon() {
		testLogger = report.createTest("testing");
		testLogger.info("navigate to amazon");
		Driver.getDriver().get("https://www.amazon.com/");
		testLogger.info("get tilte");
		page.home().searchBar.sendKeys("table spoon");
		page.home().submitSearch.click();
		List<WebElement> items = page.home().items;
		List<WebElement> itemPrice = page.home().itemsPrice;
		int itemCount = items.size();
		int priceCount = itemPrice.size();
		softAssert.assertTrue(itemCount < 10, "itemCount<10: ");//fail on purpose
		softAssert.assertTrue(priceCount > 10, "priceCount>10: ");
		softAssert.assertAll();
	}
	
	//@Test
	public void radScoresFromMyOwnHtmlTable(){
		testLogger = report.createTest("radScores from table created by me");
		testLogger.info("navigate to table");
		Driver.getDriver().get("file:///C:/Users/paoh4/ws/statefarm/StateFarm/src/test/resources/webtable.html");
		testLogger.info("get Mexico score");
		List<WebElement> rows = Driver.getDriver().findElements(By.xpath("//html/body/table/tbody/tr"));
		String actualScore = Driver.getDriver().findElement(By.xpath("(html/body/table/tbody//td)[5]")).getText();
		assertEquals(actualScore, "0-3", "score of Mexico game: ");		
	}
	
	@Test
	public void uploadFile(){
		testLogger = report.createTest("uploadFile");
		testLogger.info("Navigate to internet hero page");
		Driver.getDriver().get("http://the-internet.herokuapp.com/upload");
		testLogger.info("upload and submit file");
		page.home().uploadFile.sendKeys(System.getProperty("user.dir") + "\\src\\test\\resources\\Locators_table.pdf");
		page.home().submitFile.click();
		String expectedFile = "Locators_table.pdf";
		String actualFile = page.home().fileSuccesfullyUploaded.getText().trim();
		System.out.println(actualFile);
		assertEquals(actualFile, expectedFile, "file name: ");
	}
	
	
}
