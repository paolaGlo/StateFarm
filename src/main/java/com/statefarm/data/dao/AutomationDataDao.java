package com.statefarm.data.dao;

import java.util.Map;

public interface AutomationDataDao {

	public Map<String, String> loadScenarioData() throws Exception;
}
