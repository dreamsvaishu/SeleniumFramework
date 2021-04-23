package com.qa.orangeHRM.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.qa.orangeHRM.report.Report;
import com.relevantcodes.extentreports.ExtentTest;

public class EmpReports extends Report{

	public WebDriver driver;
	public ExtentTest test;
	public static Logger log=LogManager.getLogger(EmpReports.class);
	public String RptNameExpected;
	
	@FindBy(id="menu_pim_viewPimModule")
	WebElement PIM;
	
	@FindBy(id="menu_core_viewDefinedPredefinedReports")
	WebElement ReportMenu;
	
	@FindBy(xpath="//div[@class='ac_results']/ul/li")
	List<WebElement> RptNames;
	
	@FindBy(id="search_search")
	 WebElement ReportName;
	
	@FindBy(name="_search")
	WebElement	SearchBtn;
	
	@FindBy(id="resultTable")
	WebElement resultTable;
	
	@FindBy(xpath="//table[@id='resultTable']/tbody/tr/td[1]/input")
	WebElement ChkBox;
	
	@FindBy(xpath="//table[@id='resultTable']/tbody/tr/td[3]/a")
	WebElement RptRun;
	
	public EmpReports(WebDriver driver, ExtentTest test)
	{
		setDriver(driver);
		setLog(log);
		setTest(test);
		this.driver=driver;
		this.test=test;
		PageFactory.initElements(driver, this);
		
	}
	
	public void NavigateToReportPage(){
		PIM.click();
		Actions a =new Actions(driver);
		a.moveToElement(ReportMenu).click().build().perform();
  }
	//Select Reportname
	public void EnterReportName(String RptNameActual)
	{
		ReportName.sendKeys(RptNameActual);
		RptNameExpected=RptNameActual;
		System.out.println("RptNameExpected: "+ RptNameExpected);
		
		for(WebElement eachRptName: RptNames) {
			String text = eachRptName.findElement(By.tagName("strong")).getText();
			
			System.out.println("Names "+text);
			if(text.contains(RptNameActual))
			{
				ReportName.click();
				reportStatus("PASS","ReportName is selected successfully");
			}
	}
}		
		//Enter report name
		public void ClickOnSearchBtn(){
			SearchBtn.click();
		}
	
  //Verify report name
	public void verifyReportName() throws Exception {
	try {
		//rows
		int rowcount = resultTable.findElements(By.tagName("tr")).size();
		int colcount = resultTable.findElements(By.tagName("td")).size()/rowcount;
		System.out.println("Rows "+rowcount);
		
		for(int i=1; i<rowcount;i++) {
			String RptName = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+ i +"]/td[2]")).getText();
			System.out.println("RptName: "+RptName);
			
			if(RptName.equalsIgnoreCase(RptNameExpected)) {
				ChkBox.click();
				RptRun.click();
				reportStatus("PASS", "Selected report is executed successfully");
				Thread.sleep(7000);
			}
		}
	}catch(Exception e) {
		reportStatus("FAIL", "Report Name is NOT searched");
		throw new Exception("Report Name is not searched");
	}
}
	
}
	