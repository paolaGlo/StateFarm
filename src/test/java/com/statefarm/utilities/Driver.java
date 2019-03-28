/*
 * Copyright (c) PaolaGloria. All rights reserved.
 * Paola PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.statefarm.utilities;

import java.io.IOException;
import java.net.URL;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author paola Gloria
 *
 */
public class Driver {

	private static final Logger LOGGER = LogManager.getLogger(Driver.class);
	private static WebDriver driver;

	private Driver() {
	}

	public static WebDriver getDriver() throws Exception {
		if (driver == null) {
			String targetDriver = System.getProperty("driver");
			if (targetDriver == null || targetDriver.trim().isEmpty()) {
				// should be saucelabs
				targetDriver = "chrome";
			}
			switch (targetDriver) {
			case "sauceFirefox":
				setSauseChromeConfig();
				break;
			case "firefox":
				setFirefox();
				break;
			case "ie":
				setInternetExplorer();
				break;
			case "hubChrome":
				setHubChromeConfig();
				break;
			case "chrome":
			default:
				setChrome();
			}
		}
		return driver;
	}

	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static void setFirefox() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

	public static void setInternetExplorer() {
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
	}

	public static void setChrome() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

//	public void setHeadless() {
//		driver = new HtmlUnitDriver();
//		((HtmlUnitDriver)driver).setJavaScriptEnabled(true);
//	}

	public static void setHubChromeConfig() throws IOException {
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setPlatform(Platform.ANY);
		//final String URL = ConfigReader.getProperty("hubUrl");

		driver = new RemoteWebDriver(new URL("http://13.52.184.173:4444/wd/hub"), caps);
		((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());

	}

	public static void setSauseChromeConfig() throws IOException {
		final DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability("platform", "windows 7");
		caps.setCapability("browser", "chrome");
		caps.setCapability("version", "latest");
		caps.setCapability("parentTunnel", "sauce_admin");
		caps.setCapability("tunnelIdentifier", "CompanySharedTunnel-Stg");
		caps.setAcceptInsecureCerts(true);
		caps.setCapability("screen-resolution", "1024x768");
		caps.setCapability("timeZone", "central");

		final String URL = getSauceLabURL();

		driver = new RemoteWebDriver(new URL(URL), caps);
		// to allow testing of file uploads using files present in local environment
		((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
	}

	public static String getSauceLabURL() {

		String url = "";
		final String username = ConfigReader.getProperty("saucelab.username");
		final String accesKey = ConfigReader.getProperty("saucelab.key");
		url = "https://" + username + ":" + accesKey + "@ondemand.saucelabs.com:441/wd/hub";
		return url;
	}

}
