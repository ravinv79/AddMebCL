package com.CL.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
import pages.CancelMemberPage;

public class CancelMember extends SooperTestNG
{
	@BeforeTest
	public void startreport()
	{	
		super.initPath("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\CancelMember.html");			
	}	
	
	String MbrId=null;	
		
	@Test
	public void testMmbercancel()
	{
		File file = new File("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CLTestCase.xlsx");
		try
		{
			test=extent.startTest("Cancel Member");
			driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			FileInputStream fis = new FileInputStream("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CreateMember.xlsx"); 
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet("Cancellation");
			Sheet sheet1 = workbook.getSheet("LoginPage");
			FileInputStream fisnew = new FileInputStream(file);			
			XSSFWorkbook wbnew = new XSSFWorkbook(fisnew);
			XSSFSheet shnew = wbnew.getSheet("Update Member");
			
			CancelMemberPage CancelMeb = new CancelMemberPage(driver);
			
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
				shnew.getRow(2).getCell(3).setCellValue("Member search screen should get display with search criteria");
				shnew.getRow(2).getCell(4).setCellValue("P");
				
				shnew.getRow(3).getCell(2).setCellValue("1. Enter FirstName and Last Name 2. Click on search button");
				
				driver.findElement(By.id("txtMbrFName")).sendKeys("PPAMEB");			
				driver.findElement(By.id("txtMbrLName")).sendKeys("FOUR UPDATE");
				
				driver.findElement(By.id("btnSearch")).click();				
				Thread.sleep(10000);
				shnew.getRow(3).getCell(3).setCellValue("Respective serach result should get display");
				shnew.getRow(3).getCell(4).setCellValue("P");
				
				shnew.getRow(4).getCell(2).setCellValue("Click on Edit link");
				driver.findElement(By.xpath("//table[@id='dgdMbrSrch']/tbody/tr/td/a[contains(text(),'PPAMEB FOUR UPDATE')]/ancestor::tr/td/a")).click();
				Thread.sleep(10000);
				shnew.getRow(4).getCell(3).setCellValue("Member details screen should get display");
				shnew.getRow(4).getCell(4).setCellValue("P");
				
				CancelMeb.CancellationTab();
				Thread.sleep(10000);
				String ReqDate = sheet.getRow(1).getCell(0).getStringCellValue();
				CancelMeb.CancelReqDateTB(ReqDate);
				String EffDate = sheet.getRow(1).getCell(1).getStringCellValue();
				CancelMeb.CancelEffDateTB(EffDate);
				String SelCancellationVia = sheet.getRow(1).getCell(2).getStringCellValue();
				CancelMeb.SelCancelEffDate(SelCancellationVia);
				String Creason = sheet.getRow(1).getCell(3).getStringCellValue();
				CancelMeb.SelCancellationReason(Creason);
				String Notes = sheet.getRow(1).getCell(4).getStringCellValue();
				CancelMeb.CancellationNotesTB(Notes);
				String ApprovedBy = sheet.getRow(1).getCell(5).getStringCellValue();
				CancelMeb.CancellationApprvdByTB(ApprovedBy);
				String ApprovedDate = sheet.getRow(1).getCell(6).getStringCellValue();
				CancelMeb.CancellationApprDateTB(ApprovedDate);
				CancelMeb.SaveBtn();
				Thread.sleep(10000);
				String MebRegsucess = driver.findElement(By.id("lblError")).getText();
				
				if(MebRegsucess.contains("Record updated successfully."))			
				{
					test.log(LogStatus.PASS, MebRegsucess);
					super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\", "MemberCancellationPASS.png");	
					shnew.getRow(3).getCell(3).setCellValue("Member should get cancel with success message like Record updated successfully.");
					shnew.getRow(3).getCell(4).setCellValue("P");
				}
			}
			
			else
			{
				shnew.getRow(1).getCell(3).setCellValue("Home Page should get display");
				test.log(LogStatus.FAIL, "Home Page not displayed successfully");
				super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\", "HomepageFAIL.png");
				shnew.getRow(1).getCell(4).setCellValue("O");				
			}						
			
			String MbrID = driver.findElement(By.id("MbrHdr_lblMemberId")).getText();
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver Loaded");			
				 			 
			String connectionURL = "jdbc:sqlserver://10.74.11.173:1433;user=carelynx;password=CAREington;databaseName=CARELynx";
			Connection con1 = DriverManager.getConnection(connectionURL);			 			 
			System.out.println("SQl server connected");	
			Statement smt = con1.createStatement();
							
			ResultSet rs = smt.executeQuery("select * from member where mbrid='"+MbrID+"'");			   			   
					
			while(rs.next())
			{								
				String MEmail = rs.getString("Email");	
				System.out.println("Email - "+MEmail);	
				test.log(LogStatus.INFO, "Email - "+MEmail);
				String Dob = rs.getString("DOB");	
				System.out.println("Date of Birth - "+Dob);
				test.log(LogStatus.INFO, "Date of Birth - "+Dob);
			}	
							
			//driver.switchTo().window(getOldWindow);	
			FileOutputStream fos = new FileOutputStream(file);
			wbnew.write(fos);
			extent.endTest(test);
		}
		catch(Exception ex)
		{
		
		}
	}


}
