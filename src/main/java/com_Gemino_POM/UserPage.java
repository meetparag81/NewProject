package com_Gemino_POM;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com_Gemino_Base.TestBase;

import com_Gemino_util.TestUtil;
import com_Gemino_Excelutility.Exls_Reader;

public class UserPage extends TestBase 
{
	private @FindBy(xpath="//span[@class='glyphicon glyphicon-plus']")WebElement Add;
	private @FindBy(xpath="(//div[@class='dropdown-menu'])[7]//li/a[@class='ng-binding'][1]")WebElement User;
	private @FindBy(xpath="//label[contains (text(),' User Type')]//following::select[1]")WebElement UserType;
	private @FindBy(xpath="//label[contains (text(),' User Name')]//following::input[1]")WebElement UN;
	private @FindBy(xpath="//label[contains (text(),'Login Name')]//following::input[1]")WebElement LN;
	private @FindBy(xpath="(//label[contains (text(),'Password')]//following::input)[1]")WebElement Password;
	private @FindBy(xpath="(//label[contains (text(),'Confirm Password')]//following::input)[1]")WebElement ConfirmPassword;
	private @FindBy(xpath="//a[contains (text(), 'User Role')]//following::select[3]")WebElement UserRole;
	private @FindBy(xpath="//a[contains (text(), 'User Role')]//following::select[2]")WebElement Clinic;
	private @FindBy(xpath="//h5[text()='Password Configuration']//following::input[1]")WebElement MinimumPasswordLength;
	private @FindBy(xpath="//h5[text()='Password Configuration']//following::input[2]")WebElement MaximumPasswordLength;
	private @FindBy(xpath="//h5[text()='Password Configuration']//following::input[3]")WebElement MinimumimumPasswordAge;
	private @FindBy(xpath="//h5[text()='Password Configuration']//following::input[4]")WebElement MaximumPasswordAge;
	private @FindBy(xpath="//h5[text()='Password Configuration']//following::input[6]")WebElement AccountLockDuration;
	private @FindBy(xpath="//h5[text()='Password Configuration']//following::input[7]")WebElement NoofPasswordToRemember;
	private @FindBy(xpath="//h5[text()='Password Configuration']//following::input[5]")WebElement AccountLockThreshold;
	private @FindBy(xpath="//span[@class='glyphicon glyphicon-plus']")WebElement Addrole;
	private @FindBy(xpath="//button[text()='Save']")WebElement Save;
	private @FindBy(xpath="//span[@class='toast-msg ng-binding ng-scope']")WebElement SucessfulMessage;
	private @FindBy(xpath="//span[@class='toast-msg ng-binding ng-scope']")WebElement ErrorMessage;
	Exls_Reader reader = new Exls_Reader("C:\\Parag\\Git\\IVFmilan\\src\\main\\java\\com_Milan_TestData\\Milandata.xlsx");
	HomePage HomePage;
	
	public UserPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public void ClickonAddicon()
	{
		try
		{
			TestUtil.VisibleOn(driver, Add, 30);
		}
		catch(TimeoutException e)
		{
			System.out.println("Element-Add is not seen without 30 sec");
		}
		TestUtil.ActionForMovetoElement(Add);
		Add.click();
	
	}
	
	public UserPage ClickonUser()
	{
		try
		{
			TestUtil.VisibleOn(driver, User, 30);
		}
		catch(TimeoutException e)
		{
			System.out.println("Element-User is not seen within30 sec");
		}
		TestUtil.ActionForMovetoElement(User);
		User.click();
		return new UserPage();
	}
	
		
		
	
	public void AddNewDoctor()
	{
		TestUtil.ActionForMovetoElement(Add);
		Add.click();
		
	}
	public void AddRole()
	{
		
		TestUtil.ActionForMovetoElement(Addrole);
		Addrole.click();
	}
	public void AddUserDetails(String DocNames)
	{
		String UserName =DocNames;
		String arr[]= UserName.split(" ");
		String password= arr[0];
		
		int rows=2;
		
		SelectUserType();
		String Name= reader.getCellData("UserName", "Names", rows);
		TestUtil.ActionForMovetoElement(UN);
		UN.sendKeys(UserName);
		List<WebElement>Drdropdown = driver.findElements(By.xpath("//li[@role='option']/a"));
		int size = Drdropdown.size();
		for( int i=0;i<size;i++)
		{
			if(Drdropdown.get(i).getText().equals(UserName));
			{
				Drdropdown.get(i).click();
				break;
			}
		}
		TestUtil.ActionForMovetoElement(Password);
		Password.clear();
		Password.sendKeys(password);
		TestUtil.ActionForMovetoElement(ConfirmPassword);
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys(password);
		
		}		
		
		public void AddStaffDetails(String staffName)
		{
			String UserName =staffName;
			String arr[]= UserName.split(" ");
			String password= arr[0];
			
			int rows=2;
			
			SelectUserTypeStaff();
			String Name= reader.getCellData("StaffPage", "Names", rows);
			TestUtil.ActionForMovetoElement(UN);
			UN.sendKeys(UserName);
			List<WebElement>Drdropdown = driver.findElements(By.xpath("//li[@role='option']/a"));
			int size = Drdropdown.size();
			for( int i=0;i<size;i++)
			{
				if(Drdropdown.get(i).getText().equals(UserName));
				{
					Drdropdown.get(i).click();
					break;
				}
			
			}		
		/*TestUtil.ActionForMovetoElement(LN);
		LN.clear();
		LN.sendKeys(Name1);*/
		TestUtil.ActionForMovetoElement(Password);
		Password.clear();
		Password.sendKeys(password);
		TestUtil.ActionForMovetoElement(ConfirmPassword);
		ConfirmPassword.clear();
		ConfirmPassword.sendKeys(password);
	}
	
	public void SelectUserType()
	{
		TestUtil.ActionForMovetoElement(UserType);
		Select userT = new Select(UserType);
		userT.selectByVisibleText("Doctor");
		TestUtil.ActionForMovetoElement(UserRole);
		Select userR = new Select(UserRole);
		userR.selectByVisibleText("Doctor");
		TestUtil.ActionForMovetoElement(Clinic);
		Select clinicdrop = new Select(Clinic);
		clinicdrop.selectByVisibleText("IVF Fertility Centre");
		
	}
	public void SelectUserTypeStaff()
	{
		TestUtil.ActionForMovetoElement(UserType);
		Select userT = new Select(UserType);
		userT.selectByVisibleText("Employee");
		TestUtil.ActionForMovetoElement(UserRole);
		Select userR = new Select(UserRole);
		userR.selectByVisibleText("Staff");
		TestUtil.ActionForMovetoElement(Clinic);
		Select clinicdrop = new Select(Clinic);
		clinicdrop.selectByVisibleText("IVF Fertility Centre");
	}
	
	
	
	
	
	
	public void Passwordconfiguration()
	{
		TestUtil.ActionForMovetoElement(MinimumPasswordLength);
		String No=reader.getCellData("UserName", "MinimumPasswordLength", 2);
		MinimumPasswordLength.sendKeys("6");
		String No1= reader.getCellData("UserName", 5, 2);
		TestUtil.ActionForMovetoElement(MaximumPasswordLength);
		MaximumPasswordLength.sendKeys("8");
		String No2= reader.getCellData("UserName", 6, 2);
		TestUtil.ActionForMovetoElement(MaximumPasswordAge);
		MaximumPasswordAge.sendKeys("5");
		String No3= reader.getCellData("UserName", 7, 2);
		TestUtil.ActionForMovetoElement(AccountLockDuration);
		AccountLockDuration.sendKeys("2");
		MinimumimumPasswordAge.sendKeys("2");
		TestUtil.ActionForMovetoElement(AccountLockThreshold);
		AccountLockThreshold.sendKeys("2");
	}
	public String MessageForNonvalidCondition()
	{
		TestUtil.ActionForMovetoElement(ErrorMessage);
		return ErrorMessage.getText();
		
	}
	public String SucessfulMessage()
	{
		ClickonSave();	
		TestUtil.ActionForMovetoElement(SucessfulMessage);
		
		return SucessfulMessage.getText();

	}
	public void ClickonSave()
	{
		try
		{
		TestUtil.VisibleOn(driver, Save, 30);
		}
		catch(TimeoutException e)
		{
			System.out.println("Element- Save is not seen within30 sec");
		}
		TestUtil.ActionForMovetoElement(Save);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Save);
	}
}
	
	
	


