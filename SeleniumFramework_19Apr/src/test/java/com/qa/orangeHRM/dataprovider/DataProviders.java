package com.qa.orangeHRM.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.qa.orangeHRM.config.gblConstants;

public class DataProviders {
	
	public static XSSFWorkbook book;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	@DataProvider
	public Object[][] EmpDetails() throws IOException {
	
		//Read the data from Excel
		File file= new File(gblConstants.URL);
		FileInputStream fis = new FileInputStream(file);
		
		book = new XSSFWorkbook(fis);
		sheet = book.getSheet("Data");
		
		int rowcount = sheet.getLastRowNum();
		int colcount = sheet.getRow(1).getLastCellNum();
		Object[][] empdata = new Object[rowcount][colcount];
				
		for(int i=1;i<=rowcount;i++) {
			row = sheet.getRow(i);
			for(int j=0;j<colcount;j++) {
				cell =row.getCell(j);
				empdata[i-1][j]= cell.getStringCellValue();
			}
		}
		return empdata;
	}

}
