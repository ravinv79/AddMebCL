package com.CL.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManualletterPage 
{
	@FindBy(id="TxtUserName")
	private WebElement UserNameTB;
	
	@FindBy(id="TxtPassword")
	private WebElement PasswordTB;
	
	@FindBy(id="btnLogin")
	private WebElement Loginbtn;
	
	@FindBy(id="txtMbrberId")
	private WebElement MemberIDTB;
	
	@FindBy(id="btnSearch")
	private WebElement Searchbtn;
	
	@FindBy(linkText="Manual Letters")
	private WebElement ManualLetterTab;
	
	@FindBy(id="btnCreateRqt")
	private WebElement CreaterRequestbtn;
	
	public ManualletterPage(WebDriver driver)
	{
		super();
		PageFactory.initElements(driver, this);
	}
	
	public void UserNameTB(String UN)
	{
		UserNameTB.sendKeys(UN);
	}
	
	public void PasswordTB()
	{
		PasswordTB.click();
		PasswordTB.clear();		
	}
	
	public void PasswordTB(String PWD)
	{		
		PasswordTB.sendKeys(PWD);
	}
	
	public void Loginbtn()
	{
		Loginbtn.click();
	}
	
	public void MemberIDTB(String MbrId)
	{
		MemberIDTB.sendKeys(MbrId);
	}
	
	public void Searchbtn()
	{
		Searchbtn.click();
	}
	
	public void ManualLetterTab()
	{
		ManualLetterTab.click();
	}
	
	public void CreaterRequestbtn()
	{
		CreaterRequestbtn.click();
	}

}
