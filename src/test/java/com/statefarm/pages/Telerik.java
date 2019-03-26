package com.statefarm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.statefarm.utilities.Driver;

public class Telerik {

	public Telerik() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(id="draggable")
	public WebElement dragabbleCircle;
	
	@FindBy(id="droptarget")
	public WebElement dropTarget;
	
	@FindBy(id="qualaroo_dnt_frame")
	public WebElement iframe;
	
	@FindBy(xpath="//div[@class='optanon-alert-box-wrapper  ']//div[@class='optanon-alert-box-button optanon-button-allow']")
	public WebElement AcceptCookiesBtn;
}
