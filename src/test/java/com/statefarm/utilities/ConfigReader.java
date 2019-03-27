package com.statefarm.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties configFile;
	
	static {
		try {
			FileInputStream fis = new FileInputStream("/automation.properties");
			configFile = new Properties();
			configFile.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return configFile.getProperty(key);
	}
}
