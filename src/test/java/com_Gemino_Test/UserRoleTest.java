package com_Gemino_Test;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com_Gemino_Base.TestBase;
import com_Gemino_POM.LoginPage;
import com_Gemino_POM.HomePage;
import com_Gemino_POM.UserRolePage;

public class UserRoleTest extends TestBase
{
	LoginPage LoginPage;
	HomePage HomePage;
	UserRolePage URP;
	int rows=2;
	public UserRoleTest()
	{
		super();
		
	}
	
	@BeforeMethod
	public void SetUp() throws InterruptedException
	{
		TestBase.initalization();
		LoginPage = new LoginPage();
		HomePage= LoginPage.EnterValidUsernameadmin();
		Thread.sleep(5000);
		HomePage.CickonConfiguration();
		URP=HomePage.ClickonUserRole();
		
		
	}
	
	@Test(priority=1)
	public void InvalidMessageForUserRoleTest()
	{
		URP.AddTheUserRole();
		String Act = URP.InvalidMessageForUserRole();
		String Exp = "Code/Description already exists! Please enter a unique code.";
		Assert.assertEquals(Act, Exp);
		
		
	}
	@Test(priority=2,dataProvider= "getTestData")
	public void AddTheUserRoleTest(String description1,String code2)
	{
		
		 boolean flag1=URP.CheckcodeAvaibility();
		 if(flag1==true)
		 {
			 System.out.println("code already exists");
			 
			 URP.AddTheUserRole();
				String Act = URP.AddUser(description1, code2, rows);
				String Exp = "Code/Description already exists! Please enter a unique description.";
				Assert.assertEquals(Act, Exp);
		 }
		 else
		 {
			 URP.AddTheUserRole();
				String Act = URP.AddUser(description1, code2, rows);
				String Exp = "Record saved successfully!";
				Assert.assertEquals(Act, Exp);
			 
		 }
		
		
	}
	
	@DataProvider
	public  Iterator<Object[]> getTestData(ITestContext c)
	{
		ArrayList<Object[]>	UserRole= UserRolePage.getdatafromExcel();
	return UserRole.iterator();
	}
	
	
	

	@AfterMethod
	public void TearDown()
	{
		try
		{
		driver.quit();
		}
		catch(Exception e)
		{
			System.out.println("Driver is not closed correctly");
		}
	}

}
