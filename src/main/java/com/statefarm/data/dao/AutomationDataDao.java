/*
 * Copyright (c) PaolaGloria. All rights reserved.
 * Paola PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.statefarm.data.dao;

import java.util.Map;
/**
 * 
 * @author paola Gloria
 *
 */
public interface AutomationDataDao {

	public Map<String, String> loadScenarioData() throws Exception;
}
