package com.CL.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.CL.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

import pages.Addmember;

public class Carelynxmeb extends SooperTestNG
{
	@BeforeTest
	public void startreport()
	{	
		super.initPath("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\CarelynxMember.html");			
	}	

	@Test
	public void testcarelynxmeb()
	{
		File file = new File("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CLTestCase.xlsx");
		
		try
		{
			test=extent.startTest("Member Create");
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			FileInputStream fis = new FileInputStream("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CreateMember.xlsx"); 
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet("MemberRegistration");
			Sheet sheet1 = workbook.getSheet("LoginPage");
			FileInputStream fisnew = new FileInputStream(file);			
			XSSFWorkbook wbnew = new XSSFWorkbook(fisnew);
			XSSFSheet shnew = wbnew.getSheet("CreateMember");	
			Addmember cMember = new Addmember(driver);
			String getOldWindow=driver.getWindowHandle();
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
				shnew.getRow(2).getCell(2).setCellValue("1. Mouse Over on Members menu 2. Click on Add member");
				driver.findElement(By.xpath("//div[@id='Members']/span[2]")).click();
				Thread.sleep(5000);
				shnew.getRow(2).getCell(3).setCellValue("Add member screen should get display");
				shnew.getRow(2).getCell(4).setCellValue("P");
							
				String PolicyNumber = sheet.getRow(1).getCell(0).getStringCellValue();
				cMember.policyno(PolicyNumber);
				String FirstName = sheet.getRow(1).getCell(1).getStringCellValue();
				cMember.TBFName(FirstName);
				String LastName = sheet.getRow(1).getCell(2).getStringCellValue();
				cMember.TBLName(LastName);
				String Address1 = sheet.getRow(1).getCell(3).getStringCellValue();
				cMember.TBAdd1(Address1);
				String City = sheet.getRow(1).getCell(5).getStringCellValue();
				cMember.TBcity(City);
				String State = sheet.getRow(1).getCell(6).getStringCellValue();
				cMember.selState(State);
				String ZipCode = sheet.getRow(1).getCell(7).getStringCellValue();
				cMember.TBZip(ZipCode);
				String HomePhone = sheet.getRow(1).getCell(8).getStringCellValue();
				cMember.TBHomephone(HomePhone);
				String MobilePhone = sheet.getRow(1).getCell(9).getStringCellValue();
				cMember.TBMphone(MobilePhone);
				String Email = sheet.getRow(1).getCell(10).getStringCellValue();
				cMember.TBEmail(Email);
				String DOB = sheet.getRow(1).getCell(11).getStringCellValue();
				cMember.TBDOB(DOB);
				String MaritalStatus = sheet.getRow(1).getCell(12).getStringCellValue();
				cMember.SelMaritalStatus(MaritalStatus);			
				String Gender = sheet.getRow(1).getCell(14).getStringCellValue();
				cMember.SelGender(Gender);
				String EffectiveDate = sheet.getRow(1).getCell(13).getStringCellValue();
				cMember.TBEffectiveDate(EffectiveDate);
				shnew.getRow(3).getCell(2).setCellValue("1. Enter all the required details and click on Save button");
				driver.findElement(By.xpath("//td[@class='padlefttop']/table/tbody/tr/td[2]/input")).click();
				
				Thread.sleep(20000);
				String MebRegsucess = driver.findElement(By.id("lblError")).getText();
				
				if(MebRegsucess.contains("Member details updated successfully."))			
				{
					test.log(LogStatus.PASS, MebRegsucess);
					super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "MemberUpdatePASS.png");	
					shnew.getRow(3).getCell(3).setCellValue("Member should get create with success message like Member details updated successfully.");
					shnew.getRow(3).getCell(4).setCellValue("P");
				}
				
				else
				{					
					Set<String> newWindow=driver.getWindowHandles();					
					Iterator<String> ite = newWindow.iterator();
					
					while(ite.hasNext())
					{
					    String popupHandle=ite.next().toString();
					    if(!popupHandle.contains(getOldWindow))
					    {
					        driver.switchTo().window(popupHandle);				        
					        driver.close();
					        driver.switchTo().window(getOldWindow);
					    }
					}			
					
					Thread.sleep(10000);
					driver.findElement(By.xpath("//td[@class='padlefttop']/table/tbody/tr/td[2]/input")).click();
					
					MebRegsucess = driver.findElement(By.id("lblError")).getText();	
					
					if(MebRegsucess.contains("Member details updated successfully."))			
					{
						test.log(LogStatus.PASS, MebRegsucess);
						super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "MemberUpdatePASS.png");	
						shnew.getRow(3).getCell(3).setCellValue("Member should get create and success message should get display like Member details updated successfully.");
						shnew.getRow(3).getCell(4).setCellValue("P");
					}
					else
					{
						test.log(LogStatus.FAIL, MebRegsucess);
						super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots","MemberUpdateFAIL.png");	
						shnew.getRow(3).getCell(3).setCellValue("Member should get create and success message should get display like Member details updated successfully.");
						shnew.getRow(3).getCell(4).setCellValue("O");
					}
				}
				
				shnew.getRow(4).getCell(2).setCellValue("1. Click on Group Agent tab");			
				driver.findElement(By.linkText("Group / Agent")).click();		
				Thread.sleep(7000);
				super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "GroupsCREEN1.png");
				shnew.getRow(4).getCell(3).setCellValue("Group Agent screen should get display successfully");
				shnew.getRow(4).getCell(4).setCellValue("P");
				String GrpCode = sheet.getRow(1).getCell(15).getStringCellValue();
				
				driver.findElement(By.id("txtGrpCode")).sendKeys(GrpCode);		
				Thread.sleep(5000);
				driver.findElement(By.id("txtMktPromoCode")).click();			
				Thread.sleep(10000);
				driver.findElement(By.linkText("Select Agent Structure")).click();			
				Thread.sleep(20000);
				
				getOldWindow = driver.getWindowHandle();
				Set<String> newWindow = driver.getWindowHandles();
				for(String str:newWindow)
				{
					driver.switchTo().window(str);				
				}
				Thread.sleep(5000);
				super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "GroupsCREEN2.png");
				driver.findElement(By.id("dgdSrchAgntStrctr_ctl03_lnkSelect")).click();
						
				driver.switchTo().window(getOldWindow);
				Thread.sleep(5000);
				shnew.getRow(5).getCell(2).setCellValue("1. Click on Save button to update group details");
				driver.findElement(By.id("btnSave")).click();			
				Thread.sleep(10000);
				String GrpUpdateMsg = driver.findElement(By.id("lblError")).getText();
				if(GrpUpdateMsg.contains("Record updated successfully"))
				{
					test.log(LogStatus.PASS, GrpUpdateMsg);
					super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "GroupUpdatePASS.png");	
					shnew.getRow(5).getCell(3).setCellValue("Group details should get update with success message like Record updated successfully");
					shnew.getRow(5).getCell(4).setCellValue("P");
				}
				else
				{
					test.log(LogStatus.FAIL, GrpUpdateMsg);
					super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "GroupUpdateFAIL.png");	
					shnew.getRow(5).getCell(3).setCellValue("Group details should get update with success message like Record updated successfully");
					shnew.getRow(5).getCell(4).setCellValue("O");
				}		
				
				driver.findElement(By.linkText("Payment")).click();
				Thread.sleep(7000);
				super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "PaymentScreen1.png");
				driver.findElement(By.id("chkDisclaimer")).click();
				Thread.sleep(10000);
				newWindow=driver.getWindowHandles();					
				Iterator<String> ite = newWindow.iterator();
				
				while(ite.hasNext())
				{
				    String popupHandle=ite.next().toString();
				    if(!popupHandle.contains(getOldWindow))
				    {
				        driver.switchTo().window(popupHandle);	
				        driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td/input")).click();
				        //driver.close();
				        super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "PaymentAcceptance.png");
				        driver.switchTo().window(getOldWindow);
				    }
				}	
				
				Thread.sleep(5000);
				String PaymentMethod = sheet.getRow(1).getCell(16).getStringCellValue();
				if(PaymentMethod.contains("Credit Card/Bank Card"))
				{
					//table[@id='tblCREDITCARD']/tbody/tr/td/span/label[contains(text(),'Yes')]/parent::span/input
				}
				
				driver.findElement(By.xpath("//*[text()='"+PaymentMethod+"']/parent::tr/td[3]/input")).click();
				
				String FinsName = sheet.getRow(1).getCell(17).getStringCellValue();
				driver.findElement(By.id("Payment1_txtFinInsName")).sendKeys(FinsName);
				String RoutingNo = sheet.getRow(1).getCell(18).getStringCellValue();
				driver.findElement(By.id("Payment1_txtRoutingTransitNo")).sendKeys(RoutingNo);
				String AccNo = sheet.getRow(1).getCell(19).getStringCellValue();
				driver.findElement(By.id("Payment1_txtAchAccNo")).sendKeys(AccNo);
				
				driver.findElement(By.id("Payment1_rbgCheckAcc")).click();
				
				driver.findElement(By.id("btnSave")).click();
				Thread.sleep(10000);
				String PaymentSuccMsg = driver.findElement(By.id("lblError")).getText();
				if(PaymentSuccMsg.contains("Record updated successfully"))
				{
					test.log(LogStatus.PASS, PaymentSuccMsg);
					super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "PaymentPASS.png");	
					shnew.getRow(5).getCell(3).setCellValue("Payment details should get update with success message like Record updated successfully");
					shnew.getRow(5).getCell(4).setCellValue("P");
				}
				else
				{
					test.log(LogStatus.FAIL, PaymentSuccMsg);
					super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "PaymentFAIL.png");	
					shnew.getRow(5).getCell(3).setCellValue("Payment details should get update with success message like Record updated successfully");
					shnew.getRow(5).getCell(4).setCellValue("O");
				}
				
			}
			else
			{
				shnew.getRow(1).getCell(3).setCellValue("Home Page should get display");
				test.log(LogStatus.FAIL, "Home Page not displayed successfully");
				super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "HomepageFAIL.png");
				shnew.getRow(1).getCell(4).setCellValue("O");				
			}	
				
			driver.findElement(By.linkText("Approval")).click();
			shnew.getRow(6).getCell(3).setCellValue("Approval screen should get display successfully");
			shnew.getRow(6).getCell(4).setCellValue("P");
			driver.findElement(By.id("rdoMbrStatus_0")).click();			
			shnew.getRow(7).getCell(2).setCellValue("1. Click on Save button");
			driver.findElement(By.id("btnSave")).click();
			Thread.sleep(10000);		
			
			String ApprovalMsg = driver.findElement(By.id("lblError")).getText();
			if(ApprovalMsg.contains("Record updated successfully"))
			{
				test.log(LogStatus.PASS, ApprovalMsg);
				super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "ApprovalPASS.png");	
				shnew.getRow(7).getCell(3).setCellValue("Member status should get update to Active from Prospect");
				shnew.getRow(7).getCell(4).setCellValue("P");
			}
			else
			{
				test.log(LogStatus.FAIL, ApprovalMsg);
				super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "ApprovalFAIL.png");	
				shnew.getRow(7).getCell(3).setCellValue("Member status should get update to Active from Prospect");
				shnew.getRow(7).getCell(4).setCellValue("O");
			}
			
			driver.findElement(By.linkText("Approval")).click();
			Thread.sleep(5000);
			String MbrId = driver.findElement(By.id("MbrHdr_lblMemberId")).getText();
			
			test.log(LogStatus.INFO, "Member ID - "+MbrId);
			
			driver.findElement(By.linkText("Payment")).click();
			Thread.sleep(5000);
			driver.findElement(By.linkText("Post Payment")).click();
			Thread.sleep(10000);
			getOldWindow = driver.getWindowHandle();
			Set<String> newWindow = driver.getWindowHandles();
			for(String str:newWindow)
			{
				driver.switchTo().window(str);				
			}
			Thread.sleep(10000);
			WebElement pReason = driver.findElement(By.id("cboMbrPaymRsn"));
			
			Select selPaymentReason = new Select(pReason);
			selPaymentReason.selectByVisibleText("REGULAR PAYMENT");
			Thread.sleep(5000);
			driver.findElement(By.linkText("Process Payment")).click();
			Thread.sleep(5000);
			//super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots", "PostPaymentScreen1.png");
			driver.switchTo().alert().accept();
			Thread.sleep(5000);		
			driver.switchTo().alert().accept();
			Thread.sleep(5000);			
			driver.close();
			driver.switchTo().window(getOldWindow);
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver Loaded");			
			 			 
			String connectionURL = "jdbc:sqlserver://10.74.11.173:1433;user=carelynx;password=CAREington;databaseName=CARELynx";
			Connection con1 = DriverManager.getConnection(connectionURL);			 			 
			System.out.println("SQl server connected");	
			Statement smt = con1.createStatement();
			
			ResultSet rs = smt.executeQuery("select * from member where mbrid='"+MbrId+"'");			   			   
			
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
			
			extent.endTest(test);
			
		}
		catch(Exception ex)
		{
			
		}
	}

}
