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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.Addmember;
import pages.ManualletterPage;

public class CLManualLetter extends SooperTestNG
{
	@BeforeTest
	public void startreport()
	{	
		super.initPath("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Screenshots\\CarelynxMember.html");			
	}	
	
	@Test
	public void testManualLetter()
	{
		File file = new File("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CLTestCase.xlsx");
		
		try 
		{
			test=extent.startTest("Manual Letter");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			FileInputStream fis = new FileInputStream("C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\CreateMember.xlsx"); 
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet("Manual Letter");
			FileInputStream fisnew = new FileInputStream(file);			
			XSSFWorkbook wbnew = new XSSFWorkbook(fisnew);
			XSSFSheet shnew = wbnew.getSheet("Manual Letter");
			Addmember cMember = new Addmember(driver);
			ManualletterPage CLManualLetter = new ManualletterPage(driver);
						
			shnew.getRow(1).getCell(2).setCellValue("Login with valid Username and Password");
			String UN = sheet.getRow(1).getCell(0).getStringCellValue();
			CLManualLetter.UserNameTB(UN);				
			Thread.sleep(2000);	
			CLManualLetter.PasswordTB();
			Thread.sleep(2000);
			String PWD = sheet.getRow(1).getCell(1).getStringCellValue();
			CLManualLetter.PasswordTB(PWD);			
			Thread.sleep(2000);			
			CLManualLetter.Loginbtn();			
			Thread.sleep(10000);
			if(driver.findElements(By.linkText("Members")).size()!=0)
			{
				test.log(LogStatus.PASS, "Home Page displayed successfully");
				super.passfail("E:\\Enrollment\\DCLatest\\TestReport\\", "HomepagePASS.png");
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
				String MbrId = sheet.getRow(1).getCell(2).getStringCellValue();
				CLManualLetter.MemberIDTB(MbrId);					
				CLManualLetter.Searchbtn();				
				Thread.sleep(10000);
				shnew.getRow(3).getCell(3).setCellValue("Search result should get display");
				shnew.getRow(3).getCell(4).setCellValue("P");
				
				shnew.getRow(4).getCell(2).setCellValue("Click on Edit link");
				
				driver.findElement(By.linkText("Edit")).click();
				
				//driver.findElement(By.xpath("//table[@id='dgdMbrSrch']/tbody/tr/td/a[contains(text(),'"+TESTMIDDIR MEBONE+"')]/ancestor::tr/td/a")).click();
				Thread.sleep(10000);
				shnew.getRow(4).getCell(3).setCellValue("Edit screen should get display with member details");
				shnew.getRow(4).getCell(4).setCellValue("P");
				shnew.getRow(5).getCell(2).setCellValue("Click on Manual Letter tab");
				CLManualLetter.ManualLetterTab();	
				Thread.sleep(5000);
				shnew.getRow(5).getCell(3).setCellValue("Manual Letter screen should get display");
				shnew.getRow(5).getCell(4).setCellValue("P");
				super.passfail("E:\\Enrollment\\DCLatest\\TestReport\\", "ManualLetter Screen1.png");
				
				WebElement ltrDb = driver.findElement(By.id("ddlLetterCode"));
				Select dd1 = new Select(ltrDb);
		        Integer itemSize1 =dd1.getOptions().size();
		        //second list
		        WebElement reqTypDb = driver.findElement(By.id("ddlRequestType"));
		        Select dd2 = new Select(reqTypDb);
		        Integer itemSize2 = dd2.getOptions().size();	        
		        
		        System.out.println("Num of manual letters: "+itemSize1.toString());
		        System.out.println("Num of Req Type: "+itemSize2.toString());
		        //select manual letters one by one
		        for(int i = 0; i < itemSize1; i++)
		        {		        	
		        	dd1 = new Select(driver.findElement(By.id("ddlLetterCode")));
		        	Thread.sleep(2000);
		        	dd1.selectByIndex(i);
		        	
		        	System.out.println("i :"+String.valueOf(i));
		            Thread.sleep(5000);
		            //for each manual letter send a request by paper and email
		            for(int j = 0; j<itemSize2 ; j++)
		            {
		            	dd2 = new Select(driver.findElement(By.id("ddlRequestType")));		            	
		            	Thread.sleep(2000);
		            	dd2.selectByIndex(j);
		            	System.out.println("j :"+String.valueOf(j));
		            	Thread.sleep(5000);
		            	//create a request
		            	CLManualLetter.CreaterRequestbtn();
		            	Thread.sleep(5000);
		            	super.passfail("E:\\Enrollment\\DCLatest\\TestReport\\", "ManualLetter Sucess.png");
		            }	
		            
		        }
		        
		        FileOutputStream fos = new FileOutputStream(file);
				wbnew.write(fos);
				extent.endTest(test);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

}
