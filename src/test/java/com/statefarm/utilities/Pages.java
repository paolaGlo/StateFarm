package com.statefarm.utilities;

import com.statefarm.pages.AmazonHomePage;
import com.statefarm.pages.InternetHerokuapp;
import com.statefarm.pages.PrimeFaces;
import com.statefarm.pages.Telerik;

public class Pages {

	private AmazonHomePage homePage;
	private InternetHerokuapp herokuapp;
	private PrimeFaces primeFaces;
	private Telerik telerik;

	public PrimeFaces primeFaces() throws Exception {
		if (primeFaces == null) {
			primeFaces = new PrimeFaces();
		}
		return primeFaces;
	}

	public InternetHerokuapp herokuapp() throws Exception {
		if (herokuapp == null) {
			herokuapp = new InternetHerokuapp();
		}
		return herokuapp;
	}

	public AmazonHomePage home() throws Exception {
		if (homePage == null) {
			homePage = new AmazonHomePage();
		}
		return homePage;
	}
	
	public Telerik telerick() throws Exception {
		if(telerik == null) {
			telerik = new Telerik();
		}
		return telerik;		
	}

}
