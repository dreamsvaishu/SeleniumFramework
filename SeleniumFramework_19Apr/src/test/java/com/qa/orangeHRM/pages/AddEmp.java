package com.qa.orangeHRM.pages;

import java.io.IOException;

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
import com.qa.orangeHRM.util.util;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TakesScreenshot;
public class AddEmp extends Report {
	
	public static Logger log=LogManager.getLogger(JobTitles.class);
	public WebDriver driver;
	public ExtentTest test;
	String eid;
  
	@FindBy(xpath="//b[text()='PIM']")
	WebElement PIM;
	
	@FindBy(xpath="//a[text()='Add Employee']")
	WebElement addEmp;
	
	@FindBy(id="firstName")
	WebElement firstName;
	
	@FindBy(id="middleName")
	WebElement midName;
	
	@FindBy(id="lastName")
	WebElement lastName;
	
	@FindBy(xpath="//input[@id='employeeId']")
	WebElement employeeId;
	
	@FindBy(id="btnSave")
	WebElement btnSave;
	
	@FindBy(xpath="//a[text()='Personal Details']")
	WebElement PersonalDetails;
	
	@FindBy(id="empsearch_id")
	WebElement searchEmpId;
	
	@FindBy(id="searchBtn")
	WebElement searchBtn;
	
	@FindBy(id="menu_pim_viewEmployeeList")
	WebElement EmpList;
	
	@FindBy(id="resultTable")
	WebElement resultTable;
	
	//Constructor to initialize webElements
	public AddEmp(WebDriver driver,ExtentTest test) throws IOException {
		setDriver(driver);
		setTest(test);
		setLog(log);
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	//Navigate to AddEmployee page
	public void Navigate_To_AddEmp() {
		
		PIM.click();
		Actions a =new Actions(driver);
		a.moveToElement(addEmp).pause(2000).click().build().perform();
		
	}
	
  //public void Add_EmpDetails(String firstName, String midName, String lastName, String empId  ) {
	  
	//Enter first name
	  public void Enter_FirstName(String firstName){
		  
	  this.firstName.sendKeys(firstName);
	  }
	  
	  ////Enter middle name
	  public void Enter_MidName(String midName){
		  
		  this.midName.sendKeys(midName);
		  }
	  
	//Enter last name
	  public void Enter_LastName(String lastName){
		  
		  this.lastName.sendKeys(lastName);
		  }
	  
	//Get employee id
	  public String Get_EmpId(){
		  
		  //	employeeId.sendKeys("");
		  	eid=employeeId.getAttribute("value");
			return eid;		  
		}
	  
	//Click on Save button
	 public void ClickOnSaveBtn() throws Exception {
		try{
		 	btnSave.click();
			
			if(PersonalDetails.isDisplayed()){
	
			reportStatus("PASS","Employee added successfully");
			
			}
		}catch(Exception e) {
			reportStatus("FAIL","Employee already exist");
			throw new Exception("Failed: Employee already exist");
		}
	 }	
	 
	 	
	//Navigate to Employee list page	
	 public void EmpSearchPage() {
		Actions a=new Actions(driver);
		a.moveToElement(EmpList).click().build().perform();
	 }
	 
	 //Search by Employee id
	 public void Enter_EmpId(String EmpId ) throws InterruptedException
	 {
		 System.out.println("Entered Emp Id: "+EmpId);
		 searchEmpId.sendKeys(EmpId);
		 Thread.sleep(3000);
	 }
	 
	 public void ClickOn_SearchBtn() throws InterruptedException
	 {
		 searchBtn.click();
		 Thread.sleep(2000);
	 }
	 
	//Verify employee
	 public void Verify_Emp(String empId) throws Exception {
		 
		 try {
				//rows
				int rowcount = resultTable.findElements(By.tagName("tr")).size();
				int colcount = resultTable.findElements(By.tagName("td")).size()/rowcount;

				for(int i=1; i<rowcount;i++) {
					String EmployeeId = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+ i +"]/td//a")).getText();

					if((EmployeeId).matches(empId)) {
						reportStatus("PASSED", "Employee(ID: "+empId+") is verified successfully");
												
					}
				}
			}catch(Exception e) {
				reportStatus("FAIL", "Employee is not found");
				throw new Exception("Emp is not found");
				
			}

	 }
		
  }

