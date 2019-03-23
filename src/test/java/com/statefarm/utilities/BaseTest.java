/*
 * Copyright (c) PaolaGloria. All rights reserved.
 * Paola PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.statefarm.utilities;

import com.statefarm.data.loader.TestScenarioLoader;
/**
 * 
 * @author paola Gloria
 *
 */
public class BaseTest {

	TestScenarioLoader loader = new TestScenarioLoader();
	
	public void loadDataSet(String dataSetName) throws Exception {
		loader.loadData(dataSetName);
	}
}
