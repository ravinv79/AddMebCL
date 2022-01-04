package com.CL.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Addmember 
{
	
	@FindBy(id="txtPolicyNo")
	private WebElement policyno;
	
	@FindBy(id="txtFirstName")
	private WebElement TBFName;
	
	@FindBy(id="txtLastName")
	private WebElement TBLName;
	
	@FindBy(id="txtAddr1")
	private WebElement TBAdd1;
	
	@FindBy(id="txtAddr2")
	private WebElement TBAdd2;
	
	@FindBy(id="txtCity")
	private WebElement TBcity;
	
	@FindBy(id="cboState")
	private WebElement DDState;
	
	@FindBy(id="txtZip")
	private WebElement TBZip;
	
	@FindBy(id="txtHPhone")
	private WebElement TBHomephone;
	
	@FindBy(id="txtMPhone")
	private WebElement TBMphone;
	
	@FindBy(id="txtEmail")
	private WebElement TBEmail;
	
	@FindBy(id="txtDOB")
	private WebElement TBDOB;
	
	@FindBy(id="cboMStatus")
	private WebElement DDmaritalStatus;
	
	@FindBy(id="txtCovEffDt")
	private WebElement TBEffectiveDate;
	
	@FindBy(id="cboGender")
	private WebElement DBGender;
	
	@FindBy(id="btnSave")
	private WebElement Save;
	
	@FindBy(id="Payment1_cboAcTpe")
	private WebElement AccTyp;
	
	@FindBy(id="Payment1_cboCardType")
	private WebElement CardTyp;
	
	@FindBy(id="Payment1_txtCardNo")
	private WebElement CardNo;
	
	@FindBy(id="Payment1_txtCardExpiry")
	private WebElement CardExpiry;
	
	@FindBy(id="Payment1_txtCardName")
	private WebElement NameonCard;
	
	@FindBy(id="Payment1_txtCity")
	private WebElement pCity;
	
	@FindBy(id="Payment1_cboState")
	private WebElement pState;
	
	@FindBy(id="Payment1_txtZip")
	private WebElement pZipcode;
	
	
	public Addmember(WebDriver driver)
	{
		super();
		PageFactory.initElements(driver, this);
	}

	public void policyno(String PolicyNumber)
	{
		policyno.sendKeys(PolicyNumber);
	}
	
	public void TBFName(String FirstName)
	{
		TBFName.sendKeys(FirstName);
	}
	
	public void TBFNameClear()
	{
		TBFName.clear();
	}
	
	public void TBLName(String LastName)
	{
		TBLName.sendKeys(LastName);
	}
	
	public void TBLNameClear()
	{
		TBLName.clear();
	}
	
	public void TBAdd1(String Address1)
	{
		TBAdd1.sendKeys(Address1);
	}
	
	public void TBAdd1Clear()
	{
		TBAdd1.clear();
	}
	
	public void TBAdd2(String Address2)
	{
		TBAdd2.sendKeys(Address2);
	}
	
	public void TBAdd2Clear()
	{
		TBAdd2.clear();
	}
	
	public void TBcity(String City)
	{
		TBcity.sendKeys(City);
	}
	
	public void TBcityClear()
	{
		TBcity.clear();
	}
	
	public void selState(String State)
	{
		Select selState = new Select(DDState);
		selState.selectByVisibleText(State);		
	}
	
	public void TBZip(String ZipCode)
	{
		TBZip.sendKeys(ZipCode);
	}
	
	public void TBZipClear()
	{
		TBZip.clear();
	}
	
	public void TBHomephoneClear()
	{
		TBHomephone.clear();
	}	
	
	public void TBHomephone(String HomePhone)
	{
		TBHomephone.sendKeys(HomePhone);
	}
	
	public void TBMphone(String MobilePhone)
	{
		TBMphone.sendKeys(MobilePhone);
	}
	
	public void TBMphoneClear()
	{
		TBMphone.clear();
	}
	
	public void TBEmail(String Email)
	{
		TBEmail.sendKeys(Email);
	}
	
	public void TBEmailClear()
	{
		TBEmail.clear();
	}
	
	public void TBDOB(String DOB)
	{
		TBDOB.sendKeys(DOB);
	}
	
	public void TBDOBClear()
	{
		TBDOB.clear();
	}
	
	public void SelMaritalStatus(String MaritalStatus)
	{
		Select SelMaritalStatus = new Select(DDmaritalStatus);
		SelMaritalStatus.selectByVisibleText(MaritalStatus);	
	}
	
	public void TBEffectiveDate(String EffectiveDate)
	{
		TBEffectiveDate.sendKeys(EffectiveDate);
	}
	
	public void TBEffectiveDateClear()
	{
		TBEffectiveDate.clear();
	}
	
	public void SelGender(String Gender)
	{
		Select SelGender = new Select(DBGender);
		SelGender.selectByVisibleText(Gender);		
	}
	
	public void Save()
	{
		Save.click();
	}
	

}
