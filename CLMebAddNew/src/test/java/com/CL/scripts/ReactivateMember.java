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

import com.relevantcodes.extentreports.LogStatus;

import pages.CancelMemberPage;

public class ReactivateMember extends SooperTestNG
{
	@BeforeTest
	public void startreport()
	{	
		super.initPath("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\ReactivateMember.html");			
	}	
	
	@Test
	public void testActivateMeb()
	{
		File file = new File("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CLTestCase.xlsx");
		try
		{
			test=extent.startTest("Reactivate Member");
			driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			FileInputStream fis = new FileInputStream("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CreateMember.xlsx"); 
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet("Cancellation");
			FileInputStream fisnew = new FileInputStream(file);			
			XSSFWorkbook wbnew = new XSSFWorkbook(fisnew);
			XSSFSheet shnew = wbnew.getSheet("Update Member");
			
			CancelMemberPage CancelMeb = new CancelMemberPage(driver);
			
			shnew.getRow(1).getCell(2).setCellValue("Login with valid Username and Password");
			driver.findElement(By.id("TxtUserName")).sendKeys("itqaops");	
			Thread.sleep(2000);	
			driver.findElement(By.id("TxtPassword")).sendKeys("");
			Thread.sleep(2000);			
			driver.findElement(By.id("btnLogin")).click();
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
				
				driver.findElement(By.id("txtMbrFName")).sendKeys("TESTMIDDIR");			
				driver.findElement(By.id("txtMbrLName")).sendKeys("MEBTHREE");
				
				driver.findElement(By.id("btnSearch")).click();				
				Thread.sleep(10000);
				shnew.getRow(3).getCell(3).setCellValue("Respective serach result should get display");
				shnew.getRow(3).getCell(4).setCellValue("P");
				
				shnew.getRow(4).getCell(2).setCellValue("Click on Edit link");
				driver.findElement(By.xpath("//table[@id='dgdMbrSrch']/tbody/tr/td/a[contains(text(),'TESTMIDDIR MEBTHREE')]/ancestor::tr/td/a")).click();
				Thread.sleep(10000);
				shnew.getRow(4).getCell(3).setCellValue("Member details screen should get display");
				shnew.getRow(4).getCell(4).setCellValue("P");
				
				CancelMeb.CancellationTab();
				Thread.sleep(10000);
				CancelMeb.ReactivateBtn();
				
				String MebRegsucess = driver.findElement(By.id("lblError")).getText();
				
				if(MebRegsucess.contains("Member re-activated sucessfully."))			
				{
					test.log(LogStatus.PASS, MebRegsucess);
					super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\", "MemberCancellationPASS.png");	
					shnew.getRow(3).getCell(3).setCellValue("Member should get cancel with success message like Member re-activated sucessfully.");
					shnew.getRow(3).getCell(4).setCellValue("P");
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
					String CvgStDt = rs.getString("CvgStartDt");	
					System.out.println("Coverage Start Date - "+CvgStDt);
					test.log(LogStatus.INFO, "Coverage Start Date - "+CvgStDt);		
					super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "Database.png");
				}					
				//driver.switchTo().window(getOldWindow);	
				FileOutputStream fos = new FileOutputStream(file);
				wbnew.write(fos);	
				extent.endTest(test);		
				
			}
		}
		catch(Exception ex)
		{
			
		}
	}
}
