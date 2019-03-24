package com.statefarm.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.statefarm.utilities.Driver;

public class HomePage {

	public HomePage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(id = "twotabsearchtextbox")
	public WebElement searchBar;

	@FindBy(xpath = "//*[@id='nav-search']//input[@type='submit']")
	public WebElement submitSearch;

	@FindBy(xpath = "//div[@class='s-result-list sg-row']//h5")
	public List<WebElement> items;
	
	@FindBy(xpath = "//span[@class='a-price']")
	public List<WebElement> itemsPrice;
	
	@FindBy(id="file-upload")
	public WebElement uploadFile;
	
	@FindBy(id="file-submit")
	public WebElement submitFile;
	
	@FindBy(id="uploaded-files")
	public WebElement fileSuccesfullyUploaded;
}
