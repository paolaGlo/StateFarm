package com.statefarm.tests;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.statefarm.utilities.BaseTest;
import com.statefarm.utilities.Driver;

public class HomePageTest extends BaseTest {

	@Test
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
}
