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

import pages.Addmember;

public class UpdateMember extends SooperTestNG
{
	@BeforeTest
	public void startreport()
	{	
		super.initPath("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\UpdateMember.html");			
	}	
	
	@Test
	public void testUpdatemember()
	{
		File file = new File("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CLTestCase.xlsx");
		try
		{		
			test=extent.startTest("Update Member");
			driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			FileInputStream fis = new FileInputStream("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CreateMember.xlsx"); 
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet("Update Dependent");
			Sheet sheet1 = workbook.getSheet("LoginPage");
			FileInputStream fisnew = new FileInputStream(file);			
			XSSFWorkbook wbnew = new XSSFWorkbook(fisnew);
			XSSFSheet shnew = wbnew.getSheet("Update Member");
			Addmember cMember = new Addmember(driver);
			
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
				
				driver.findElement(By.id("txtMbrFName")).sendKeys("PPMEB");			
				driver.findElement(By.id("txtMbrLName")).sendKeys("THREE UPDATE");
				
				driver.findElement(By.id("btnSearch")).click();				
				Thread.sleep(10000);
				shnew.getRow(3).getCell(3).setCellValue("Respective serach result should get display");
				shnew.getRow(3).getCell(4).setCellValue("P");
				
				shnew.getRow(4).getCell(2).setCellValue("Click on Edit link");
				driver.findElement(By.xpath("//table[@id='dgdMbrSrch']/tbody/tr/td/a[contains(text(),'PPMEB THREE UPDATE')]/ancestor::tr/td/a")).click();
				Thread.sleep(10000);
				shnew.getRow(4).getCell(3).setCellValue("Member details screen should get display");
				shnew.getRow(4).getCell(4).setCellValue("P");
				
				shnew.getRow(4).getCell(2).setCellValue("Edit the required data to update the member details in respective fields and click on save button");
				/*cMember.TBFNameClear();
				String FirstName = sheet.getRow(1).getCell(0).getStringCellValue();
				cMember.TBFName(FirstName);*/
				cMember.TBLNameClear();
				String LastName = sheet.getRow(1).getCell(1).getStringCellValue();
				cMember.TBLName(LastName);	
				/*cMember.TBAdd1Clear();
				String Address1 = sheet.getRow(1).getCell(2).getStringCellValue();
				cMember.TBAdd1(Address1);
				cMember.TBcityClear();
				String City = sheet.getRow(1).getCell(4).getStringCellValue();
				cMember.TBcity(City);
				cMember.TBZipClear();
				String ZipCode = sheet.getRow(1).getCell(6).getStringCellValue();
				cMember.TBZip(ZipCode);
				cMember.TBHomephoneClear();			
				String HomePhone = sheet.getRow(1).getCell(7).getStringCellValue();
				cMember.TBHomephone(HomePhone);
				cMember.TBMphoneClear();
				String MobilePhone = sheet.getRow(1).getCell(8).getStringCellValue();
				cMember.TBMphone(MobilePhone);
				cMember.TBEmailClear();
				String Email = sheet.getRow(1).getCell(9).getStringCellValue();
				cMember.TBEmail(Email);
				cMember.TBDOBClear();
				String DOB = sheet.getRow(1).getCell(10).getStringCellValue();
				cMember.TBDOB(DOB);
				String MaritalStatus = sheet.getRow(1).getCell(11).getStringCellValue();
				cMember.SelMaritalStatus(MaritalStatus);			
				String Gender = sheet.getRow(1).getCell(13).getStringCellValue();
				cMember.SelGender(Gender);
				cMember.TBEffectiveDateClear();
				String EffectiveDate = sheet.getRow(1).getCell(12).getStringCellValue();
				cMember.TBEffectiveDate(EffectiveDate);*/
				
				cMember.Save();
				
				Thread.sleep(20000);
							
				String MebRegsucess = driver.findElement(By.id("lblError")).getText();
				
				if(MebRegsucess.contains("Record updated successfully."))			
				{
					test.log(LogStatus.PASS, MebRegsucess);
					super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\", "UpdateMemberDetailsPASS.png");					
					shnew.getRow(5).getCell(3).setCellValue("Member details details should get update with success message Record updated successfully.");
					shnew.getRow(5).getCell(4).setCellValue("P");
				}
				
				String MbrId = driver.findElement(By.id("MbrHdr_lblMemberId")).getText();
				
				test.log(LogStatus.INFO, "Member ID - "+MbrId);
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				System.out.println("Driver Loaded");			
				 			 
				String connectionURL = "jdbc:sqlserver://10.74.11.173:1433;user=carelynx;password=CAREington;databaseName=CARELynx";
				Connection con1 = DriverManager.getConnection(connectionURL);			 			 
				System.out.println("SQl server connected");				
				Statement smt = con1.createStatement();
				String MType = null;		
				                
				ResultSet rs = smt.executeQuery("select * from member where mbrid='"+MbrId+"'");
				while(rs.next())
				{					
					MType = rs.getString("MethodType");	
					System.out.println("Method Type - "+MType);	
					test.log(LogStatus.INFO, "Method Type - "+MType);
					String MEmail = rs.getString("Email");	
					System.out.println("Email - "+MEmail);
					test.log(LogStatus.INFO, "Email - "+MEmail);
					String Dob = rs.getString("DOB");	
					System.out.println("Date of Birth - "+Dob);
					test.log(LogStatus.INFO, "Date of Birth - "+Dob);
				}				
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
