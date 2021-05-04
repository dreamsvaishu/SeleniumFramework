package com.qa.orangeHRM.testcases;

import org.testng.annotations.Test;

import com.qa.orangeHRM.common.base;
import com.qa.orangeHRM.dataprovider.DataProviders;
import com.qa.orangeHRM.pages.AddEmp;
import com.qa.orangeHRM.pages.LoginPage;
import com.qa.orangeHRM.pages.PayGrades;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

public class AddEmpTest extends base {
	
	public static XSSFWorkbook book;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	String empId;
	
	LoginPage loginpage;
	AddEmp emp;
	
	
	
	
  @Test(dataProviderClass =DataProviders.class, dataProvider ="EmpDetails", description="It performs add new employee operation")
  public void AddEmp_DataProvider(String fname, String midName, String lastName) throws Exception {
	 
	try {  
	  	//Login into the application
			loginpage = new LoginPage(driver,test);

			try {
				loginpage.Login();
				System.out.println("HomePpage method");
				loginpage.VerifyHomePage();
			}catch(Exception e) {
				loginpage.reportStatus("FAIL", "User is not logged in");
			}	

			try {
				//Navigate to PIM-- Add Emp page
				System.out.println("EmpName: "+fname+" "+" "+midName+" "+lastName);
				
				emp=new AddEmp(driver, test);	
				emp.Navigate_To_AddEmp();
				emp.Enter_FirstName(fname);
				emp.Enter_MidName(midName);
				emp.Enter_LastName(lastName);
				empId=emp.Get_EmpId();
				emp.ClickOnSaveBtn();
				//emp.ClickOn_EditBtn();
				
							
		}catch(Exception e1) {
				emp.reportStatus("FAIL", "User is not able to add employee");
			}
			
			try {
				//Navigate to PIM-- Search Emp page
				emp.EmpSearchPage();
				Thread.sleep(2000);
				emp.Enter_EmpId(empId);
				emp.ClickOn_SearchBtn();
				emp.Verify_Emp(empId);
				
			}catch(Exception e) {
				emp.reportStatus("FAIL", "User is not able to verify employee");
			}
	}catch(Exception e) {
		loginpage.reportStatus("FAIL", "User is unable to login or add employee or verify employee");
		throw new Exception("User is unable to login or add or verify employee");
	}
  }

 /* @DataProvider
	public Object[][] EmpDetails() throws IOException {
	
		//Read the data from Excel
		File file= new File("C:\\Users\\Vijayshri\\git\\SeleniumFramework\\SeleniumFramework_19Apr\\TestData\\EmpData.xlsx");
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
	} */
  }

