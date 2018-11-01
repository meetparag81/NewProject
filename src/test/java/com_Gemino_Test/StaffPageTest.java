package com_Gemino_Test;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com_Gemino_Base.TestBase;
import com_Gemino_Excelutility.Exls_Reader;
import com_Gemino_util.Messages;
import com_Gemino_util.TestUtil;
import com_Gemino_POM.LoginPage;
import com_Gemino_POM.DoctorPage;
import com_Gemino_POM.HomePage;
import com_Gemino_POM.StaffPage;
import com_Gemino_POM.UserPage;

public class StaffPageTest extends TestBase 
{
	LoginPage LoginPage;
	HomePage HomePage;
	StaffPage SP;
	UserPage UserPage;
	DoctorPage DoctorPage;
	Exls_Reader reader = new Exls_Reader("C:\\Parag\\PrjectIVF\\Geminoproject\\src\\main\\java\\com_Gemino_TestData\\Doctor.xlsx");
	int k=2;
	boolean flag1=false;
	public StaffPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void Setup() throws InterruptedException
	{
		TestBase.initalization();
		LoginPage = new LoginPage();
		HomePage= LoginPage.EnterValidUsernameadmin();
		HomePage.CickonConfiguration();
		SP=HomePage.ClickonStaff();
		
	}
	
	@Test(priority=1,groups = {"functional" },dataProvider= "getTestData",enabled= true)
	public void AddStaffDetailsTest(String Firstname1, String LastName1, String Gender1,String Age1, String MobileNo1,String country) throws InterruptedException
	{
		int count=0;
		int rows= reader.getRowCount("DoctorPage");
		for (int i = 2; i <=4 ; i++) 
		{
			
			boolean flag = SP.CheckNameAvaibility(k);
			SP.AddNewStaff();
			Thread.sleep(3000);
			SP.AddStaffDetails(Firstname1, LastName1, Gender1, Age1, MobileNo1, country, k);
			SP.Uploadphoto();
			Thread.sleep(2000);
			SP.UploadPhotoStaff();
			Thread.sleep(2000);
			SP.ClickOnSave();
			TestUtil.moveScrollbarVerticallydown();
			if(flag==true)
			{
				SP.SavePage();
				String Act= Messages.ErrorMessage().getText();
				String Exp = "Record saved successfully!";
				k++;
				count++;
				Assert.assertEquals(Act, Exp);
				System.out.println("SaveDoctorDetailsTest is completed");
				flag1= SP.ForLogin(flag, Act);
			}
			else
			{
				SP.SavePage();
				String Act= Messages.SaveSucessfullyMessage().getText();
				String Exp = "Record saved successfully!";
				k++;
				count++;
				Assert.assertEquals(Act, Exp);
				
				System.out.println("SaveDoctorDetailsTest is completed-by adding new doctor");
					
		
		}
			if(count<=4)
			{
				break;
			}	
	
	
		
	}
	
	}
	@Test(priority=2,groups = {"functional" },enabled= true)
	public void LoginwithStaff() throws InterruptedException
	{
		HomePage.CickonConfiguration();
		HomePage.ClickOnUser();
		UserPage.AddRole();
		
		
		
	}
	
	
	@DataProvider
	public  Iterator<Object[]> getTestData(ITestContext c)
	{
		ArrayList<Object[]>	Staff= StaffPage.getdatafromExcel();	
	return Staff.iterator();
	}
	
	@AfterMethod
	public void TearDown()
	{
		driver.quit();
	}
	

}
