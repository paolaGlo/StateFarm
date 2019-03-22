/*
 * Copyright (c) PaolaGloria. All rights reserved.
 * Paola PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.statefarm.data.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.statefarm.automation.exception.AutomationException;
import com.statefarm.data.dao.AutomationDataDao;

/**
 * Class loads automation properties. connects and load data from database.
 * 
 * @author paola Gloria
 *
 */
public class AutomationDataDaoImpl implements AutomationDataDao {
	private static final Logger LOGGER = LogManager.getLogger(AutomationDataDaoImpl.class);
	private Properties prop = null;
	private String dataColumnName;

	/**
	 * Constructs properties from automation.properties file
	 */

	public AutomationDataDaoImpl(String dataSetName) {

		InputStream is = null;
		try {
			this.prop = new Properties();
			is = this.getClass().getResourceAsStream("/automation.properties");
			prop.load(is);
			this.dataColumnName = dataSetName;
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
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
	}

	/**
	 * Gets value of key from automation properties file.
	 * @param key
	 * @return value of key
	 */
	public String getPropertyValue(String key) {
		return this.prop.getProperty(key);
	}

	/**
	 *implementation of method from AutomationDataDao interface. loads data from database
	 * @return map of resultSets
	 */
	@Override
	public Map<String, String> loadScenarioData() throws AutomationException {
		ResultSet rs = null;
		final Map<String, String> dataMap = new HashMap<>();
		try (Connection con = getConnection(); Statement stmt = con.createStatement();) {
			rs = stmt.executeQuery("");
			while (rs.next()) {
				dataMap.put(rs.getString(1), rs.getString(2));
			}
			LOGGER.info("Retrieved " + dataMap.size() + " items from table");
		} catch (Exception e) {
			throw new AutomationException(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
		return dataMap;
	}

	/**
	 * Connects to jdbc
	 * @return connection to db
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		con = DriverManager.getConnection(getPropertyValue("jdbc.url"), getPropertyValue("jdbc.username"),
				getPropertyValue("jdbc.password"));
		return con;
	}

}
