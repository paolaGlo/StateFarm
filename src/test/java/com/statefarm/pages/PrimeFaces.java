package com.statefarm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.statefarm.utilities.Driver;

public class PrimeFaces {

	public PrimeFaces() throws Exception {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(id="puff")
	public WebElement puff;
}
