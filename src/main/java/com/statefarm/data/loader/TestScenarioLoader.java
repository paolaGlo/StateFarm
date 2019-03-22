/*
 * Copyright (c) PaolaGloria. All rights reserved.
 * Paola PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.statefarm.data.loader;

import java.util.HashMap;
import java.util.Map;

import com.statefarm.data.dao.AutomationDataDao;

/**
 * Class loads automation properties. connects and load data from database.
 * 
 * @author paola Gloria
 *
 */
public class TestScenarioLoader {

	private Map<String, String> dataMap = new HashMap<>();
	private Map<String, String> localDataMap = new HashMap<>();
	
	public TestScenarioLoader() {
		
	}
	
	public void loadData(String dataSetName) throws Exception {
		final AutomationDataDao dataDao = new AutomationDataDaoImpl(dataSetName);
		dataMap = dataDao.loadScenarioData();
	}
	
	public void setLocalData(String key, String value) {
		localDataMap.put(key, value);
	}
	
	public String getLocalDAta(String key) {
		return localDataMap.get(key);		
	}
	
	public void clearLocalData() {
		localDataMap.clear();
	}
	
	public String getDataFieldValue(String fieldName) {
		String data = dataMap.get(fieldName);
		if(data != null) {
			data = data.trim();
		}
		return data;
	}
}
