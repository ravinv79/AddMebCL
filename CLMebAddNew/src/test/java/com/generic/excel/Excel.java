package com.generic.excel;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel 
{
	public static String getCellData(String xlPath,String sheetName,int rowNum, int cellNum) 
	{		
		try 
		{
			 FileInputStream fis = new FileInputStream(xlPath);
			 Workbook wb = WorkbookFactory.create(fis);
			 Sheet s = wb.getSheet(sheetName);
			 String v = s.getRow(rowNum).getCell(cellNum).getStringCellValue();
			
			return v;
		}
			
		catch(Exception e)
		{
			return "";
		}
		
	}

}
