package com.CL.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.CL.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

import pages.fulfllmnt;

public class ClFulfillment extends SooperTestNG

{
	@BeforeTest
	public void startreport()
	{	
		super.initPath("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\CLFulfillment.html");			
	}
	
	@Test
	public void testfulfillment()
	{
		File file = new File("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CLTestCase.xlsx");
		
		try
		{
			test=extent.startTest("Fulfillment");
			driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
			FileInputStream fis = new FileInputStream("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CreateMember.xlsx"); 
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet("Fulfillment");
			Sheet sheet1 = workbook.getSheet("LoginPage");
			FileInputStream fisnew = new FileInputStream(file);			
			XSSFWorkbook wbnew = new XSSFWorkbook(fisnew);
			XSSFSheet shnew = wbnew.getSheet("Fulfillment");			
			fulfllmnt Clfulfllmnt = new fulfllmnt(driver);
			
			shnew.getRow(1).getCell(2).setCellValue("Login with valid Username and Password");
			LoginPage LP = new LoginPage(driver);
			String UserName = sheet1.getRow(1).getCell(0).getStringCellValue();
			String Password = sheet1.getRow(1).getCell(1).getStringCellValue();
			LP.UN(UserName, Password);
			Thread.sleep(10000);
			if(driver.findElements(By.linkText("Members")).size()!=0)
			{
				test.log(LogStatus.PASS, "Home Page displayed successfully");
				super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\", "HomepagePASS.png");
				shnew.getRow(1).getCell(3).setCellValue("Home Page should get display");
				shnew.getRow(1).getCell(4).setCellValue("P");
				
				WebElement moveelement = driver.findElement(By.linkText("Members"));
				
				Actions actions = new Actions(driver);			
				actions.moveToElement(moveelement).perform();
				Thread.sleep(2000);
				shnew.getRow(2).getCell(2).setCellValue("1. Mouse Over on Members menu 2. Click on Member Search");
				driver.findElement(By.xpath("//div[@id='Members']/span[1]")).click();
				Thread.sleep(5000);
				shnew.getRow(2).getCell(3).setCellValue("Member search screen should get display with Member details");
				shnew.getRow(2).getCell(4).setCellValue("P");
				
				shnew.getRow(3).getCell(2).setCellValue("1. Enter FirstName and Last Name 2. Click on search button");
				String MbrId = sheet.getRow(1).getCell(4).getStringCellValue();
				Clfulfllmnt.txtMbrberIdTB(MbrId);				
				driver.findElement(By.id("btnSearch")).click();
				
				Thread.sleep(10000);
				shnew.getRow(3).getCell(3).setCellValue("Search result should get display");
				shnew.getRow(3).getCell(4).setCellValue("P");
				
				shnew.getRow(4).getCell(2).setCellValue("Click on MEMBER name link");
				String MemberName = sheet.getRow(1).getCell(3).getStringCellValue();
				driver.findElement(By.xpath("//table[@id='dgdMbrSrch']/tbody/tr/td/a[contains(text(),'"+MemberName+"')]/ancestor::tr/td[2]/a")).click();
				Thread.sleep(10000);
				shnew.getRow(4).getCell(3).setCellValue("Member Enquiry screen should get display with member details");
				shnew.getRow(4).getCell(4).setCellValue("P");
				shnew.getRow(4).getCell(2).setCellValue("1. Enter No. of records needed for the fulfillment type");
				String Number = sheet.getRow(1).getCell(0).getStringCellValue();
				Clfulfllmnt.FulfilmntNo(Number);
				shnew.getRow(5).getCell(2).setCellValue("1. Slect Fulfillment requirement type");
				String SelReType = sheet.getRow(1).getCell(1).getStringCellValue();
				Clfulfllmnt.SelFLReqType(SelReType);
				shnew.getRow(5).getCell(2).setCellValue("1. Slect Fulfillment type from a drop list");
				String SelFLType = sheet.getRow(1).getCell(2).getStringCellValue();
				Clfulfllmnt.SelFLmntType(SelFLType);
				shnew.getRow(5).getCell(2).setCellValue("1. Click on Submit button");
				Clfulfllmnt.lnkSubmit();
				Thread.sleep(10000);
				String FullmntSuccmsg = driver.findElement(By.id("lblError")).getText();
				
				if(FullmntSuccmsg.contains("Record updated successfully"))
				{
					test.log(LogStatus.PASS, FullmntSuccmsg);
					super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\", "FulfillmentPASS.png");
					shnew.getRow(5).getCell(3).setCellValue("A success message should get display with Record updated successfully");
					shnew.getRow(5).getCell(4).setCellValue("P");
				}
				else
				{
					test.log(LogStatus.FAIL, FullmntSuccmsg);
					super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\", "FulfillmentFAIL.png");
					shnew.getRow(5).getCell(3).setCellValue("Fulfillment not submitted successfully.");
					shnew.getRow(5).getCell(4).setCellValue("P");
				}
			}
			else
			{
				shnew.getRow(1).getCell(3).setCellValue("Home Page not displayed successfully");
				test.log(LogStatus.FAIL, "Home Page not displayed successfully");
				super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\", "HomepageFAIL.png");
				shnew.getRow(1).getCell(4).setCellValue("O");				
			}						
			
			FileOutputStream fos = new FileOutputStream(file);
			wbnew.write(fos);
			extent.endTest(test);
			
		}
		catch(Exception ex)
		{
			
		}
	}


}
