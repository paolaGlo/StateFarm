/*
 * Copyright (c) PaolaGloria. All rights reserved.
 * Paola PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.statefarm.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.statefarm.data.loader.AutomationDataDaoImpl;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author paola Gloria
 *
 */
public class Driver {

	private static final Logger LOGGER = LogManager.getLogger(AutomationDataDaoImpl.class);
	private static WebDriver driver;
	private Properties automationProperties;

	private Driver() {
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setUp() throws Exception {
		Driver driver = new Driver();
		driver.setDriverUp();
	}

	public void setDriverUp() throws Exception {
		if (driver == null) {
			String targetDriver = System.getProperty("driver");
			loadAutomationProperties();

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
			case "chrome":
			default:
				setChrome();
			}
		}
	}

	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public void setFirefox() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

	public void setInternetExplorer() {
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
	}

	public void setChrome() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

//	public void setHeadless() {
//		driver = new HtmlUnitDriver();
//		((HtmlUnitDriver)driver).setJavaScriptEnabled(true);
//	}

	public void setSauseChromeConfig() throws IOException {
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
		((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
	}

	public String getSauceLabURL() {

		String url = "";
		if (automationProperties != null) {
			final String username = automationProperties.getProperty("saucelab.username");
			final String accesKey = automationProperties.getProperty("saucelab.key");
			url = "https://" + username + ":" + accesKey + "@ondemand.saucelabs.com:441/wd/hub";
		}
		return url;

	}

	public Properties loadAutomationProperties() {
		automationProperties = new Properties();
		InputStream is = this.getClass().getResourceAsStream("/automation.properties");
		try {
			automationProperties.load(is);
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return automationProperties;
	}

}
