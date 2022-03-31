package com.CL.scripts;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import generic.Excel;

public class SooperTestNG 
{
	public WebDriver driver;		
	ExtentReports extent;
	ExtentTest test;
	
	protected void initPath(String path) 
	{
		extent = new ExtentReports(path,true);
		extent.addSystemInfo("Environment", "Pre-Prod Environment").addSystemInfo("Browser", "IE 11");
		extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
	}	
	
	@BeforeClass
	public void precondition()
	{				
		 String xlPath = "C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\Excelfiles\\Config.xlsx";
		 String sheetName = "Sheet1";
		 String browser = Excel.getCellData(xlPath, sheetName, 0, 1);
		 String url = Excel.getCellData(xlPath, sheetName, 1, 1);
		if (browser.equals("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\exefiles\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browser.equals("IE"))
		{							
			System.setProperty("webdriver.ie.driver", "C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\exefiles\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else
		{
			System.setProperty("webdriver.gecko.driver","C:\\Users\\raviv\\git\\AddMebCL\\CLMebAddNew\\exefiles\\geckodriver.exe");
			driver=new FirefoxDriver();	
		}
		
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	protected void passfail(String path, String fileName) 
	{
		try
		{
			String dateTime=new Date().toString().replace(":", "_");
			String screenshotpath=path+fileName+dateTime+".png";
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(screenshotpath));
			test.log(LogStatus.INFO, "Snapshot below: " + test.addScreenCapture(screenshotpath));
		}
		catch(Exception ex)
		{
			
		}
		
	}
	
	@AfterMethod
	public void getResult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		extent.endTest(test);
	}
	
	@AfterTest
	public void endReport()
	{
		extent.flush();
		extent.close();
		
	}	
	

}
