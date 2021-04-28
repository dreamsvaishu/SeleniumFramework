package com.qa.orangeHRM.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.qa.orangeHRM.common.base;
import com.qa.orangeHRM.pages.EmployeeImport;
import com.qa.orangeHRM.pages.LoginPage;
import com.qa.orangeHRM.pages.PayGrades;

public class EmpFileUpload extends base {
	LoginPage loginpage;
	EmployeeImport ei;
	
	@Test
	public void EmpFileUpload() throws IOException {
	
	
	try {
		//Login into the application
		loginpage = new LoginPage(driver,test);

		try {
			loginpage.Login();
			loginpage.VerifyHomePage();
		}catch(Exception e) {
			loginpage.reportStatus("FAIL", "User is not logged in");
		}	

		try {	
			//Navigate to PIM --> Config --> import Data
			ei= new EmployeeImport(driver,test);
			ei.NavigatetoconfigPage();
			ei.uploadEmpFile("C:\\Users\\Vijayshri\\git\\SeleniumFramework\\SeleniumFramework_19Apr\\TestData\\importDataEmp.csv");
			ei.clickonUploadBtn();
			Thread.sleep(8000);
			ei.NavigateToEmpListPage();
			ei.verifyEmployee("1005");
			
		}catch(Exception e1) {
			ei.reportStatus("FAIL", "User is unable to upload File");
		}

	}catch(Exception e) {
		ei.reportStatus("FAIL", "User is either not able to login or unable to upload File");
	}
	}
}
