package com_Gemino_Test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com_Gemino_POM.HomePage;


import com_Gemino_Base.TestBase;
import com_Gemino_POM.LoginPage;
import com_Gemino_POM.StaffPage;
import com_Gemino_POM.UserPage;
import com_Gemino_util.TestUtil;


public class UserPageTest extends TestBase
{
	LoginPage LoginPage;
	HomePage HomePage;
	UserPage UserPage;
	StaffPage StaffPage;
	com_Gemino_POM.DoctorPage DoctorPage;
	String DocNames;
	String StaffNames;
	
	

public UserPageTest()
{
	super();
}

@BeforeMethod
public void Setup() throws InterruptedException
{
	TestBase.initalization();
	LoginPage = new LoginPage();
	HomePage= LoginPage.EnterValidUsernameadmin();
	Thread.sleep(5000);
	HomePage.CickonConfiguration();
	
}
@Test(priority=1)
public void AddNewDoctorTest() throws InterruptedException 
{
	DoctorPage= HomePage.ClickonDoctor();
	DocNames= DoctorPage.SetDoctorNameFromClinic();
	DoctorPage.CickonConfiguration();
	UserPage=DoctorPage.ClickOnUser();
	Thread.sleep(3000);
	UserPage.AddNewDoctor();
	Thread.sleep(3000);
	UserPage.AddUserDetails(DocNames);
	UserPage.AddRole();
	UserPage.Passwordconfiguration();
	String Act= UserPage.SucessfulMessage();
	String Exp= "Record saved successfully!";
	Assert.assertEquals(Act, Exp);
	System.out.println("AddNewDoctorTest is completed");

}
@Test(priority=3)
public void AddNewStaffTest() throws InterruptedException 
{
	StaffPage= HomePage.ClickonStaff();
	StaffNames= StaffPage.SetStaffNameFromClinic();
	StaffPage.CickonConfiguration();
	UserPage=StaffPage.ClickOnUser();
	Thread.sleep(3000);
	UserPage.AddNewDoctor();
	Thread.sleep(3000);
	UserPage.AddStaffDetails(StaffNames);
	UserPage.AddRole();
	UserPage.Passwordconfiguration();
	String Act= UserPage.SucessfulMessage();
	String Exp= "Record saved successfully!";
	Assert.assertEquals(Act, Exp);
	System.out.println("AddNewDoctorTest is completed");

}

@Test(priority=2)
public void MessageForEmbtyUserForm() throws InterruptedException
{
	
	UserPage=HomePage.ClickOnUser();
	Thread.sleep(3000);
	UserPage.AddNewDoctor();
	Thread.sleep(3000);
	UserPage.ClickonSave();
	String Act = UserPage.MessageForNonvalidCondition();
	String Exp = "Please fill mandatory fields";
	Assert.assertEquals(Act, Exp);
	System.out.println("MessageForEmbtyUserFormTest is completed");
	
	
}


@AfterMethod
public void TearDown()
{
	driver.quit();
}

	





}
