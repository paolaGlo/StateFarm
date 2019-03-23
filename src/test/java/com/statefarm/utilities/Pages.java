package com.statefarm.utilities;

import com.statefarm.pages.HomePage;

public class Pages {

	private HomePage homePage;

	public HomePage home() {
		if (homePage == null) {
			homePage = new HomePage();
		}
		return homePage;
	}
}
