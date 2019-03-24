package com.statefarm.utilities;

import com.statefarm.pages.AmazonHomePage;
import com.statefarm.pages.InternetHerokuapp;
import com.statefarm.pages.PrimeFaces;

public class Pages {

	private AmazonHomePage homePage;
	private InternetHerokuapp herokuapp;
	private PrimeFaces primeFaces;

	public PrimeFaces primeFaces() {
		if (primeFaces == null) {
			primeFaces = new PrimeFaces();
		}
		return primeFaces;
	}

	public InternetHerokuapp herokuapp() {
		if (herokuapp == null) {
			herokuapp = new InternetHerokuapp();
		}
		return herokuapp;
	}

	public AmazonHomePage home() {
		if (homePage == null) {
			homePage = new AmazonHomePage();
		}
		return homePage;
	}

}
