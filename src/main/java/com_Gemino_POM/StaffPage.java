package com_Gemino_POM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com_Gemino_Base.TestBase;
import com_Gemino_Excelutility.Exls_Reader;
import com_Gemino_util.Messages;
import com_Gemino_util.TestUtil;

public class StaffPage extends TestBase
{
	private @FindBy(xpath="//input[@name='FirstName']")WebElement FirstName;
	private @FindBy(xpath="//input[@name='txtLN']")WebElement LastName;
	private @FindBy(xpath="//label[contains (text(),'Gender')]//following::select[1]")WebElement Gender;
	private @FindBy(xpath=" //label[contains (text(),'Designation')]//following::select[1]")WebElement Designation;
	private @FindBy(xpath="//label[contains (text(),'Age')]//following::input[1]")WebElement Age;
	private @FindBy(xpath="(//button[@class='glyphicon glyphicon-pencil imgEdit'])[1]")WebElement UploadPhoto1;
	private @FindBy(xpath = "//h4[text()='Please Choose Image Upload Type']//following::button[text()='Choose From Gallery']") WebElement ChooseFromGallery;
	private @FindBy(xpath = "//h4[text()='Please Choose Image Upload Type']//following::div[@class='fileUpload btn btn-primary']") WebElement UploadImage;
	private @FindBy(xpath="//button[text()='Save']")WebElement SavePage;
	private @FindBy(xpath = "(//h4[text()='Please Choose Image Upload Type']//following::button[text()='Save'])[1]") WebElement Save;
	private @FindBy(xpath= "(//label[contains (text(),'Mobile No.')]//following::input)[1]")WebElement countrycode;
	private @FindBy(xpath = "//label[contains (text() ,'Mobile No.')]//following::input[@name='txtMobNo']") WebElement MobileNo;
	private @FindBy(xpath="//label[contains (text(),'Department')]//following::select[1]")WebElement Department;
	private @FindBy(xpath = "//button[@class='btn-link p-r-0']") WebElement Add;
	private @FindBy(xpath="//span[@class='total_count ng-binding']")WebElement count;
	private @FindBy(linkText = "Configuration") WebElement Configuration;
	Exls_Reader reader = new Exls_Reader("C:\\Parag\\PrjectIVF\\Geminoproject\\src\\main\\java\\com_Gemino_TestData\\Staff.xlsx");
	boolean flag1;
	WebElement name;
	String Name;
	
	public StaffPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public void AddNewStaff() 
	{
		try {
			TestUtil.VisibleOn(driver, Add, 40);
			
		} 
		catch (Exception e) 
		{
			System.out.println("Element-Add is not seen with in 30 sec");
		}
		TestUtil.ActionForMovetoElement(Add);
		TestUtil.ClickEmementByJavaScriptExecutor(Add);
	
		
	}
	
	
	public void AddStaffDetails(String Firstname1, String LastName1, String Gender1, String Age1,String MobileNo1, String country,int k2)
	{
		for (int i =k2; i <= 5; i++) 
		{
			flag1=true;
			try
			{
				TestUtil.VisibleOn(driver, FirstName, 30);	
			}
			catch(TimeoutException e)
			{
				System.out.println("Element- FirstName is  not seen with in 30 sec");
			}
			String Name = reader.getCellData("StaffPage", 0, i);
			FirstName.clear();
			FirstName.sendKeys(Name);
			LastName1 = reader.getCellData("StaffPage", "LastName", i);
			LastName.clear();
			LastName.sendKeys(LastName1);
			TestUtil.SelectOption(Gender).selectByVisibleText("Male");
			LastName1 = reader.getCellData("StaffPage", "Age", i);
			Age.clear();
			Age.sendKeys("36");
			
			TestUtil.ActionForMovetoElement(countrycode);
			countrycode.click();
			countrycode.clear();
			String Country = reader.getCellData("StaffPage", "Country", i);
			countrycode.sendKeys(Country);
			List<WebElement> country1 = driver.findElements(By.xpath("//ul[@class='dropdown-menu ng-isolate-scope']/li/a"));
			if (country1.size() == 0) 
			{
				countrycode.sendKeys(Keys.BACK_SPACE);
				List<WebElement> country11 = driver.findElements(By.xpath("//ul[@class='dropdown-menu ng-isolate-scope']/li/a"));
				for (int j = 0; j < country11.size(); j++) 
				{
					String s =country11.get(j).getText();
					String[] s2= s.split("\\)");
					String cno=s2[0];
					String cna=s2[1];
					if (cna.equals("India"));
					{
						country11.get(1).click();
						break;
					}
				}
				MobileNo.clear();
				MobileNo1 = reader.getCellData("StaffPage", "MobileNo.", i);
				MobileNo.sendKeys(MobileNo1);
				TestUtil.SelectOption(Designation).selectByIndex(i);
				TestUtil.SelectOption(Department).selectByIndex(i);
				break;
			
			
			
			}
			
		}
		
		
		
		
	}
	
	public void ClickOnSave()
	{
		TestUtil.ActionForMovetoElement(Save).click().perform();
	}
	
	public void Uploadphoto() 
	{
		
		try
		{
			TestUtil.VisibleOn(driver, UploadPhoto1, 30);
		}
		catch(TimeoutException e)
		{
			System.out.println("Element- UploadPhoto1 is not seen with in 30 sec");
		}
		TestUtil.ActionForMovetoElement(UploadPhoto1).click().perform();
		
		
		

	}
	
	
	public void UploadPhotoStaff() 
	{
		try 
		{
			TestUtil.VisibleOn(driver, ChooseFromGallery, 30);
		} catch (TimeoutException e) {
			System.out.println("Element-ChooseFromGallery is not seen within 30 sec");
		}
		TestUtil.ActionForMovetoElement(ChooseFromGallery).click().perform();
		TestUtil.ActionForMovetoElement(UploadImage).click().perform();
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e1) 
		{
			System.out.println("InterruptedException is seen");
		}
		try {
			Runtime.getRuntime().exec("C:\\Parag\\PrjectIVF\\Geminoproject\\src\\main\\java\\AutoIT\\Doctorphoto.exe");
		} 
		catch (IOException e)
		{
			System.out.println("IOException is seen");
		}
		
		
	}
	
	
	public String Message()
	{
	String msg=	Messages.SaveSucessfullyMessage().getText();
	return msg;
		
	}
	public  static ArrayList<Object[]> getdatafromExcel() 
	{
		Exls_Reader reader = null;

		ArrayList<Object[]> mydata = new ArrayList<Object[]>();
		try {
			reader = new Exls_Reader("C:\\Parag\\PrjectIVF\\Geminoproject\\src\\main\\java\\com_Gemino_TestData\\Staff.xlsx");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		int count1 = reader.getRowCount("StaffPage");
		for (int rows = 2; rows <= 4; rows++) 
		{

			String Firstname = reader.getCellData("StaffPage", 0, rows);
			String LastName = reader.getCellData("StaffPage", 1, rows);
			String Gender = reader.getCellData("StaffPage", 2, rows);
			String Country = reader.getCellData("StaffPage", 4, rows);
			String MobileNo = reader.getCellData("StaffPage", 5, rows);
			String Age = reader.getCellData("StaffPage", 5, rows);
			
			 
			Object[] obj = { Firstname, LastName, Gender, Country, MobileNo,Age };
			mydata.add(obj);
		}

		return mydata;
	}

	public boolean CheckNameAvaibility(int n) 
	{
		try
		{
			TestUtil.VisibleOn(driver, count, 30);
		}
		catch(TimeoutException e)
		{
			System.out.println("Element- count is not seen with in 30 sec");
		}
		
		
		int j = 1;
		int rows=n;
		int size1 = 0;
		TestUtil.ActionForMovetoElement(count);
		String size = count.getText();
		try
		{
		size1 = Integer.parseInt(size);
		}
		catch(NumberFormatException e)
		{
			System.out.println("NumberFormatException is seen");
		}
		boolean flag = false;
		List<WebElement>pages = driver.findElements(By.xpath("//table[@class='table table-hover GridTable']//tbody/tr"));
		int k=1;
		
		int m = 1;
		for(int i =1;i<=25;i++)
		{
			
			
			if( m<=7)
			{
				List<WebElement>Noofrows = driver.findElements(By.xpath("//table[@class='table table-hover table-striped']//tbody/tr"));
				int size11 = Noofrows.size();
				//WebElement codevalue = driver.findElement(By.xpath("//table[@class='table table-hover GridTable']//tbody/tr["+k+"]/td[2]"));
				if(size11>m)
				{
				 name = driver.findElement(By.xpath("//table[@class='table table-hover table-striped']//tbody/tr["+m+"]/td[2]"));
				}
				else
				{
					break;
				}
				try
				{
				TestUtil.VisibleOn(driver, name, 30);
				}
				catch(TimeoutException e)
				{
					System.out.println("Element-name is not seen with in 30 sec");
				}
				String name1= name.getText();
				String arr[] = name1.split(" ");
				String FirstName = arr[0];
				String LastName = arr[1];
				
				 String StaffFirstName = reader.getCellData("StaffPage", "FirstName", rows);
				 String StaffLastName = reader.getCellData("StaffPage", "LastName", rows);
				
				if(FirstName.equals(StaffFirstName)&& LastName.equals(StaffLastName))
				{
					 flag1=true;
					break;
					
				}
			
				m++;
				
			}
			else 
			{
				m=1;
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				// jse.executeScript("scroll(0, 250)");
				WebElement element = driver.findElement(By.xpath("//a[contains (text(), 'Next')]"));
				if(element.isEnabled()==true&&element.isDisplayed()==true)
				{
					TestUtil.ClickEmementByJavaScriptExecutor(element);
					System.out.println("=============clicked on next==========");
					
				}
				else
				{
					break;
				}
				
			
			}
			
		
		}
		if(m==10)
		{
	flag1= false;
	
		}
		return flag1;
		
	}

	public void SavePage()
	{
		try
		{
			TestUtil.VisibleOn(driver, SavePage, 30);
		}
		catch(TimeoutException e)
		{
			System.out.println("SavePage is not seen with in 30 sec");
		}
		
		TestUtil.ActionForMovetoElement(SavePage);
		TestUtil.ClickEmementByJavaScriptExecutor(SavePage);	
	}
	
	public boolean ForLogin(boolean flag,String Act)
	{
		String exp = "Record saved successfully!";
		if(Act.equals(exp))
		{
			flag=true;
		}
		else
		{
			flag=false;
		}
		return flag;
		
	}
	public String SetStaffNameFromClinic() 
	{
		

		List<WebElement> NoofStaff = driver.findElements(By.xpath("//table[@class='table table-hover table-striped']//tbody//tr/td[2]"));
		int count = 0;
		for (WebElement we : NoofStaff) 
		{
			count++;
			try {
				Thread.sleep(3000);
			} 
			catch (InterruptedException e) 
			{
				System.out.println("InterruptedException is seen");
			}
			Name = we.getText();

			if (count == 1) 
			{
				reader.setCellData("StaffPage", "Names", 2, Name);
				break;

			}

		}
		return Name;
	}
	
	
	public void CickonConfiguration() throws InterruptedException 
	{
		try 
		{
			TestUtil.VisibleOn(driver, Configuration, 30);
		} 
		catch (TimeoutException e) 
		{
			System.out.println("TimeoutException is seen");
		}

		TestUtil.ActionForMovetoElement(Configuration);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", Configuration);
	}
	
	
	public  UserPage ClickOnUser() 
	{
		try 
		{
			Thread.sleep(3000);
		} 
		catch (InterruptedException e) 
		{
			System.out.println("InterruptedException is seen");
		}
		List<WebElement> FormNames = driver.findElements(By.xpath("//a[@class='ng-binding']"));
		WebElement User = driver.findElement(By.xpath("//a[contains (text(),'User Role')]//preceding::a[contains (text(),'User')]"));
		User.click();
		return new UserPage();



}
}

	
	
	


	
	

