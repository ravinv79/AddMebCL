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

import pages.AddDep;
import pages.Addmember;

public class AddDependent extends SooperTestNG

{
	@BeforeTest
	public void startreport()
	{	
		super.initPath("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\AddDependent.html");			
	}	
	
	@Test
	public void testadddep()
	{
		File file = new File("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CLTestCase.xlsx");
		try
		{
			test=extent.startTest("Add Dependent");
			driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
			FileInputStream fis = new FileInputStream("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CreateMember.xlsx"); 
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet("Add Dependent");
			Sheet sheet1 = workbook.getSheet("LoginPage");
			FileInputStream fisnew = new FileInputStream(file);			
			XSSFWorkbook wbnew = new XSSFWorkbook(fisnew);
			XSSFSheet shnew = wbnew.getSheet("AddDependent");
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
				shnew.getRow(2).getCell(3).setCellValue("Member search screen should get display with Member details");
				shnew.getRow(2).getCell(4).setCellValue("P");
				
				shnew.getRow(3).getCell(2).setCellValue("1. Enter FirstName and Last Name 2. Click on search button");
				
				driver.findElement(By.id("txtMbrFName")).sendKeys("PPDCMEB");				
				driver.findElement(By.id("txtMbrLName")).sendKeys("TWO UPDATE");				
				driver.findElement(By.id("btnSearch")).click();
				
				Thread.sleep(10000);
				shnew.getRow(3).getCell(3).setCellValue("Search result should get display");
				shnew.getRow(3).getCell(4).setCellValue("P");
				
				shnew.getRow(4).getCell(2).setCellValue("Click on Edit link");
				
				driver.findElement(By.xpath("//table[@id='dgdMbrSrch']/tbody/tr/td/a[contains(text(),'PPDCMEB TWO UPDATE')]/ancestor::tr/td/a")).click();
				Thread.sleep(10000);
				shnew.getRow(4).getCell(3).setCellValue("Edit screen should get display with member details");
				shnew.getRow(4).getCell(4).setCellValue("P");
				
				shnew.getRow(5).getCell(2).setCellValue("Enter all the required data for to add a dependent");
				AddDep dPage = new AddDep(driver);
				String DFname = sheet.getRow(1).getCell(0).getStringCellValue();
				dPage.DepFNameTB(DFname);
				String DLname = sheet.getRow(1).getCell(1).getStringCellValue();
				dPage.DepLNameTB(DLname);
				String DepDOB = sheet.getRow(1).getCell(2).getStringCellValue();
				dPage.DepDOBTB(DepDOB);
				String DepmebStartDate = sheet.getRow(1).getCell(3).getStringCellValue();
				dPage.DepMebshipStartDate(DepmebStartDate);
				String DepGender = sheet.getRow(1).getCell(4).getStringCellValue();
				dPage.selgender(DepGender);
				dPage.DepSavebtn();	
				Thread.sleep(10000);
				String AddDepSuccmsg = driver.findElement(By.id("lblError")).getText();
				
				if(AddDepSuccmsg.contains("Member dependent details Added successfully."))
				{
					test.log(LogStatus.PASS, AddDepSuccmsg);
					super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\", "AddDependentPASS.png");
					shnew.getRow(5).getCell(3).setCellValue("Dependent should get create with success message like Member dependent details Added successfully.");
					shnew.getRow(5).getCell(4).setCellValue("P");
				}
				else
				{
					test.log(LogStatus.FAIL, AddDepSuccmsg);
					super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\", "ApprovalPASS.png");
					shnew.getRow(5).getCell(3).setCellValue("Dependent should get add with success message like Member dependent details Added successfully.");
					shnew.getRow(5).getCell(4).setCellValue("P");
				}
				
				String MbrID = driver.findElement(By.id("MbrHdr_lblMemberId")).getText();
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				System.out.println("Driver Loaded");			
				 			 
				String connectionURL = "jdbc:sqlserver://10.74.11.173:1433;user=carelynx;password=CAREington;databaseName=CARELynx";
				Connection con1 = DriverManager.getConnection(connectionURL);			 			 
				System.out.println("SQl server connected");	
				Statement smt = con1.createStatement();
				int size = 1;
				while(true)
				{
					Thread.sleep(120000);
					ResultSet rs = smt.executeQuery("select * from memberdiffsenttovendor where mbrid='"+MbrID+"'");
					rs.next();
					while(rs.next())
					{
						size = rs.getRow();					
					}
					if(size==2)
					{
						System.out.println("Method Type ADDDEPENDENT has been updated in Diffgram - " +size);	
						break;	
					}							
					System.out.println("Method Type ADDDEPENDENT is not updated in Diffgram");
				}
				
				ResultSet rs = smt.executeQuery("select * from memberdiffsenttovendor where mbrid='"+MbrID+"'");			   			   
				String MbrRecId= "";
				while(rs.next())
				{
					MbrRecId = rs.getString("MbrRecordID");					
					System.out.println("Member Record Id - "+MbrRecId);	
					test.log(LogStatus.INFO, "Member Record Id - "+MbrRecId);
					String MbrApiStatus = rs.getString("ApiStatus");	
					System.out.println("Member API Status - "+MbrApiStatus);
					test.log(LogStatus.INFO, "Member API Status - "+MbrApiStatus);
					String MbrApires = rs.getString("ApiResponse");	
					System.out.println("Member API Response - "+MbrApires);
					test.log(LogStatus.INFO, "Member API Response - "+MbrApires);
					String MType = rs.getString("MethodType");	
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
				
			else
			{
				shnew.getRow(1).getCell(3).setCellValue("Home Page should get display");
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
