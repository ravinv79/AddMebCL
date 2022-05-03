package com.CL.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	@FindBy(id="TxtUserName")
	private WebElement UNTB;
	
	@FindBy(id="TxtPassword")
	private WebElement PWDTB;
	
	@FindBy(id="btnLogin")
	private WebElement Loginbtn;
	
	public LoginPage(WebDriver driver)
	{
		super();
		PageFactory.initElements(driver, this);
	}
	
	public void UN (String UserName, String Password)
	{
		UNTB.sendKeys(UserName);		
		PWDTB.click();
		PWDTB.clear();
		PWDTB.sendKeys(Password);
		Loginbtn.click();
	}
	
	public void PWDClear ()
	{
				
	}
	
	public void Login (String Password)
	{	
		
	}
	
	

}
