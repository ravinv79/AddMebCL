package com.CL.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CancelMemberPage 
{
	@FindBy(id="MbrTabs_lnkCnclltn")
	private WebElement CancellationTab;
	
	@FindBy(id="txtCanReqDt")
	private WebElement CancelReqDate;
	
	@FindBy(id="txtCEffDt")
	private WebElement CancelEffDate;
	
	@FindBy(id="ddlCancellationVia")
	private WebElement CancellationVia;
	
	@FindBy(id="cboReason")
	private WebElement CancellationReason;
	
	@FindBy(id="txtNotes")
	private WebElement CancellationNotes;
	
	@FindBy(id="txtCAppBy")
	private WebElement CancellationApprvdBy;
	
	@FindBy(id="txtAppDt")
	private WebElement CancellationApprDate;
	
	@FindBy(id="btnSave")
	private WebElement SaveBtn;
	
	@FindBy(id="btnReAct")
	private WebElement ReactivateBtn;
	
	public CancelMemberPage(WebDriver driver)
	{
		super();
		PageFactory.initElements(driver, this);
	}
	
	public void CancellationTab()
	{
		CancellationTab.click();
	}
	
	public void CancelReqDateTB(String ReqDate)
	{
		CancelReqDate.sendKeys(ReqDate);
	}
	
	public void CancelEffDateTB(String EffDate)
	{
		CancelEffDate.sendKeys(EffDate);
	}

	public void SelCancelEffDate(String SelCancellationVia)
	{
		Select SelCancelEffDate = new Select(CancellationVia);
		SelCancelEffDate.selectByVisibleText(SelCancellationVia);
	}
	
	public void SelCancellationReason(String Creason)
	{
		Select SelCancellationReason = new Select(CancellationReason);
		SelCancellationReason.selectByVisibleText(Creason);
	}
	
	public void CancellationNotesTB(String Notes)
	{
		CancellationNotes.sendKeys(Notes);
	}
	
	public void CancellationApprvdByTB(String ApprovedBy)
	{
		CancellationApprvdBy.sendKeys(ApprovedBy);
	}
	
	public void CancellationApprDateTB(String ApprovedDate)
	{
		CancellationApprDate.sendKeys(ApprovedDate);
	}
	
	public void SaveBtn()
	{
		SaveBtn.click();
	}
	
	public void ReactivateBtn()
	{
		ReactivateBtn.click();
	}
	


}
