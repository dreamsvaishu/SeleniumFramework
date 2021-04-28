package com.qa.orangeHRM.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.qa.orangeHRM.common.base;
import com.qa.orangeHRM.pages.EmpReports;
import com.qa.orangeHRM.pages.LoginPage;
import com.qa.orangeHRM.pages.PayGrades;

public class EmpRptTest extends base{
	LoginPage loginpage;
	EmpReports rpt;
  
	@Test
  public void VerifyEmpRpt() throws IOException {
		
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
				//Navigate to PIM --> Reports
				rpt= new EmpReports(driver,test);
				rpt.NavigateToReportPage();
				rpt.EnterReportName("Employee Job Details");
				rpt.ClickOnSearchBtn();
				rpt.verifyReportName();
				
						
			}catch(Exception e1) {
				rpt.reportStatus("FAIL", "User is unable to search employee Report");
			}

		}catch(Exception e) {
			rpt.reportStatus("FAIL", "User is either not able to login or unable to search employee report");
		}
		}	
		
  }

