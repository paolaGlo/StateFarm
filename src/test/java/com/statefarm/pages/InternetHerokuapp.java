package com.statefarm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.statefarm.utilities.Driver;

public class InternetHerokuapp {

	public InternetHerokuapp() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(id="file-upload")
	public WebElement uploadFile;
	
	@FindBy(id="file-submit")
	public WebElement submitFile;
	
	@FindBy(id="uploaded-files")
	public WebElement fileSuccesfullyUploaded;
}
