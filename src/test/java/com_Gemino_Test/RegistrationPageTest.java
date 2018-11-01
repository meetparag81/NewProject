package com_Gemino_Test;

import org.testng.annotations.Test;

import com_Gemino_Base.TestBase;
import com_Gemino_POM.LoginPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class RegistrationPageTest extends TestBase  
{
	RegistrationPageTest RegP;
	LoginPage LoginPage;
	LoginPageTest LoginPageTest;
	@BeforeMethod	
	public void setupRegistration() throws InterruptedException
	{  
		TestBase.initalization();
		Thread.sleep(3000);
		LoginPage.EnterValidUsername();
		LoginPage.EnterValidPassword();
		LoginPage.SelectClinic();
		LoginPage.ClickOnLoginButton();
	    Thread.sleep(3000);
	  
	}
	@Test
	public void f() throws InterruptedException 
	{
		//  LoginPTest.ValidUsernameNValidPasswordTest();
	}

	@AfterMethod
	public void afterMethod() 
	{
		
	}

}
