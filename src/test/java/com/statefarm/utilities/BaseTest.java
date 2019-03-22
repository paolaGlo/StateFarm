package com.statefarm.utilities;

import com.statefarm.data.loader.TestScenarioLoader;

public class BaseTest {

	TestScenarioLoader loader = new TestScenarioLoader();
	
	public void loadDataSet(String dataSetName) throws Exception {
		loader.loadData(dataSetName);
	}
}
