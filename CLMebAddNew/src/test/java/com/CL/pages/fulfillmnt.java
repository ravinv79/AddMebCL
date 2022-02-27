package com.CL.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class fulfillmnt 
{
	@FindBy(id="txtMbrberId")
	private WebElement txtMbrberIdTB;
	
	@FindBy(id="txtNoOfCards")
	private WebElement fllmntNoTB;
	
	@FindBy(id="cboFlFlReqType")
	private WebElement fllmntReqTypeDD;
	
	@FindBy(id="cboFlflType")
	private WebElement cboFlflTypeDD;
	
	@FindBy(id="lnkSubmit")
	private WebElement lnkSubmit;
	
	public fulfllmnt(WebDriver driver)
	{
		super();
		PageFactory.initElements(driver, this);
	}
	
	public void txtMbrberIdTB(String MbrId)
	{
		txtMbrberIdTB.sendKeys(MbrId);
	}
	
	public void FulfilmntNo(String Number)
	{
		fllmntNoTB.sendKeys(Number);
	}
	
	public void SelFLReqType(String SelReType)
	{
		Select fullflmntretype = new Select(fllmntReqTypeDD);
		fullflmntretype.selectByVisibleText(SelReType);
	}

	public void SelFLmntType(String SelFLType)
	{
		Select FlflTypeDD = new Select(cboFlflTypeDD);
		FlflTypeDD.selectByVisibleText(SelFLType);
	}
	
	public void lnkSubmit()
	{
		lnkSubmit.click();
	}


}
