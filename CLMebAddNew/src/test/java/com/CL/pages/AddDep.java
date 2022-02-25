package com.CL.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddDep 
{
	@FindBy(id="txtDependFirstName")
	private WebElement DepFNameTB;
	
	@FindBy(id="txtDependLastName")
	private WebElement DepLNameTB;
	
	@FindBy(id="txtDependDOB")
	private WebElement DepDOBTB;
	
	@FindBy(id="txtDepCovStDt")
	private WebElement DepMebshipStartDate;
	
	@FindBy(id="cboDependGender")
	private WebElement DrpDwnGender;
	
	@FindBy(id="txtDepEmail")
	private WebElement DepEmailTB;
	
	@FindBy(id="btnSaveDepend")
	private WebElement DepSavebtn;
	
	public AddDep(WebDriver driver)
	{
		super();
		PageFactory.initElements(driver, this);
	}
	
	public void DepFNameTB(String DFname)
	{
		DepFNameTB.sendKeys(DFname);
	}
	
	public void DepLNameTB(String DLname)
	{
		DepLNameTB.sendKeys(DLname);
	}
	
	public void DepDOBClear()
	{
		DepDOBTB.clear();
	}
	
	public void DepDOBTB(String DepDOB)
	{
		DepDOBTB.sendKeys(DepDOB);
	}
	
	public void DepMebshipStartDate(String DepmebStartDate)
	{
		DepMebshipStartDate.sendKeys(DepmebStartDate);
	}
	
	public void selgender(String DepGender)
	{
		Select selgender = new Select(DrpDwnGender);
		selgender.selectByVisibleText(DepGender);
	}
	
	public void DepEmailTB(String DepEmail)
	{
		DepEmailTB.sendKeys(DepEmail);
	}
	
	public void DepSavebtn()
	{
		DepSavebtn.click();
	}
	


}
