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

public class UpdateDependent extends SooperTestNG
{
	@BeforeTest
	public void startreport()
	{	
		super.initPath("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\UpdateDependent.html");				
	}
		
	@Test
	public void testupdatedep()
	{
		File file = new File("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CLTestCase.xlsx");
		
		try
		{
			test=extent.startTest("Update Dependent");
			driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
			FileInputStream fis = new FileInputStream("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CreateMember.xlsx");  
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet("Update Dependent");
			Sheet sheet1 = workbook.getSheet("LoginPage");
			FileInputStream fisnew = new FileInputStream(file);			
			XSSFWorkbook wbnew = new XSSFWorkbook(fisnew);
			XSSFSheet shnew = wbnew.getSheet("Update Member");
			Addmember cMember = new Addmember(driver);
			String getOldWindow = driver.getWindowHandle();
			LoginPage LP = new LoginPage(driver);
			String UserName = sheet1.getRow(1).getCell(0).getStringCellValue();
			String Password = sheet1.getRow(1).getCell(1).getStringCellValue();
			LP.UN(UserName, Password);	
			Thread.sleep(10000);
				
			WebElement moveelement = driver.findElement(By.linkText("Members"));
			AddDep dPage = new AddDep(driver);
			Actions actions = new Actions(driver);			
			actions.moveToElement(moveelement).perform();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='Members']/span[1]")).click();
			Thread.sleep(5000);
			String mFN = sheet.getRow(1).getCell(6).getStringCellValue();
			driver.findElement(By.id("txtMbrFName")).sendKeys(mFN);	
			String mLN = sheet.getRow(1).getCell(7).getStringCellValue();
			driver.findElement(By.id("txtMbrLName")).sendKeys(mLN);			
			driver.findElement(By.id("btnSearch")).click();
			
			Thread.sleep(10000);
			String mName = sheet.getRow(1).getCell(8).getStringCellValue();
			driver.findElement(By.xpath("//table[@id='dgdMbrSrch']/tbody/tr/td/a[contains(text(),'"+mName+"')]/ancestor::tr/td/a")).click();
			Thread.sleep(10000);				
			driver.findElement(By.xpath("//table[@id='dgdDependList']/tbody/tr[2]/td[1]")).click();					
			Thread.sleep(10000);
			
			driver.findElement(By.id("txtDependLastName")).clear();		
			String DLname = sheet.getRow(1).getCell(1).getStringCellValue();
			driver.findElement(By.id("txtDependLastName")).sendKeys(DLname);
			
			String DepEmail = sheet.getRow(1).getCell(5).getStringCellValue();
			dPage.DepEmailTB(DepEmail);
			
			/*dPage.DepDOBClear();
			String DepDOB = sheet.getRow(1).getCell(2).getStringCellValue();
			dPage.DepDOBTB(DepDOB);*/
			
			dPage.DepSavebtn();			
			Thread.sleep(20000);
			String MebRegsucess = driver.findElement(By.id("lblError")).getText();
			
			if(MebRegsucess.contains("Member dependent details updated successfully."))			
			{
				test.log(LogStatus.PASS, MebRegsucess);					
				super.passfail("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\", "UpdateDepDetailsPASS.png");
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
			int size = 1;
			
			ResultSet rs = smt.executeQuery("select * from memberdiffsenttovendor where mbrid='"+MbrId+"'");			   			   
			
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
			
			driver.switchTo().window(getOldWindow);			
			FileOutputStream fos = new FileOutputStream(file);
			wbnew.write(fos);
			extent.endTest(test);				
						
		}
		catch(Exception ex)
		{
			
		}
	}

}
