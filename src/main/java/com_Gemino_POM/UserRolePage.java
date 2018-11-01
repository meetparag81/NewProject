package com_Gemino_POM;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com_Gemino_Base.TestBase;
import com_Gemino_Excelutility.Exls_Reader;
import com_Gemino_util.Messages;
import com_Gemino_util.TestUtil;




public class UserRolePage extends TestBase
{
	private @FindBy(xpath="//span[@class='glyphicon glyphicon-plus']") WebElement Add;
	private @FindBy(xpath="//label[contains (text(), 'Code')]//following::input[@name='Code']")WebElement Code;
	private @FindBy(xpath="//a[@id='headingSeven'][contains ( text(), ' Queue Management')]/span")WebElement QueueManagement;
	private @FindBy(xpath="(//a[contains (text(), 'ART')])[2]//following::input[@name='IsAll_ART']")WebElement AllART;
	private @FindBy(xpath="(//button[@class='btn btn-primary btn_save m-b-0'])[12]")WebElement Save;
	private @FindBy(xpath="//label[ contains (text(), 'Description')]//following::input[@name='Description']")WebElement Description;
	private @FindBy(xpath="//span[@class='total_count ng-binding']")WebElement count;
Exls_Reader reader = new Exls_Reader("C:\\Parag\\PrjectIVF\\Geminoproject\\src\\main\\java\\com_Gemino_TestData\\UserRole.xlsx");
String msg;
String name1;
String drdescription;
String CodeName;
String Description1;
	
	public UserRolePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public void AddTheUserRole()
	{
		try
		{
			TestUtil.VisibleOn(driver, Add, 30);
		}
		catch(TimeoutException e)
		{
			System.out.println("Element- Add is not seen within 30 sec ");
		}
		TestUtil.ActionForMovetoElement(Add);
		TestUtil.ClickEmementByJavaScriptExecutor(Add);
		
		
	}
	
	
	public String InvalidMessageForUserRole()
	{
		try
		{
			TestUtil.VisibleOn(driver, Code, 30);
		}
		catch(TimeoutException e)
		{
			System.out.println("Element-Code is not seenwithin 30 sec");
		}
		Code.sendKeys("AD001");
		TestUtil.ActionForMovetoElement(QueueManagement);
		QueueManagement.click();
		try
		{
			TestUtil.VisibleOn(driver, AllART, 30);
		}
		catch(TimeoutException e)
		{
			System.out.println("Element-AllART is not seen with in 30 sec");
		}
		TestUtil.ActionForMovetoElement(AllART);
		AllART.click();
		TestUtil.moveScrollbarVerticallydown();
		TestUtil.ActionForMovetoElement(Save);
		Save.click();
		TestUtil.ActionForMovetoElement(Messages.SaveSucessfullyMessage());
		return Messages.SaveSucessfullyMessage().getText();
		
		
	}
	
	public String AddUser(String description1, String code2, int rows)
	{
		int rows1 = rows;
		String description = description1;
		String code3 = reader.getCellData("UserRole", "Code", rows1);
		 description = reader.getCellData("UserRole", "Description", rows1);
		//int row2 = rows1+1;
		/*String code1=Integer.toString(rows1);
		String suffix = "AD00";
		String Prifix = code1;
		String Description1 = description1+Prifix;
		String code3 =suffix+code1;
		reader.setCellData("UserRole", "Code", rows, code3);
		reader.setCellData("UserRole", "Description", rows, Description1);*/
		int size= reader.getRowCount("UserRole");
		for(int i =2;i<=size;i++)
		{
			try
			{
				TestUtil.VisibleOn(driver, Code, 30);
			}
			catch(TimeoutException e)
			{
				System.out.println("Element-Code is not seenwithin 30 sec");
			}
			        Code.clear();
					Description.clear();
					 
					 Code.sendKeys(code3);
					// description1= reader.getCellData("UserRole", "Description", i);
					Description.sendKeys(description);
					TestUtil.ActionForMovetoElement(QueueManagement);
					QueueManagement.click();
					try
					{
						TestUtil.VisibleOn(driver, AllART, 30);
					}
					catch(TimeoutException e)
					{
						System.out.println("Element-AllART is not seen with in 30 sec");
					}
					TestUtil.ActionForMovetoElement(AllART);
					if(AllART.isSelected())
					{
						continue;
					}
					else
					{
						AllART.click();
					}
					TestUtil.moveScrollbarVerticallydown();
					TestUtil.ActionForMovetoElement(Save);
					Save.click();
					driver.navigate().refresh();
					
					 boolean flag1=CheckcodeAvaibility();
					 if(flag1==true)
					 {
						 msg =  MessageForAvaibility();
					 }
					 else
					 {
						 msg =  MessageForAvaibility();
					 }
						
					break;
				}
		return msg;
				
				 
			 }
				
			
			
		
		
		
		
	
	public String MessageForAvaibility()
	{
		
	TestUtil.ActionForMovetoElement(Messages.SaveSucessfullyMessage());
	return Messages.SaveSucessfullyMessage().getText();
	}
	
	public boolean CheckcodeAvaibility()
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
		int rows=2;
		int size1 = 0;
		TestUtil.ActionForMovetoElement(count);
		String size = count.getText();
		size1 = Integer.parseInt(size);
		boolean flag = false;
		List<WebElement>pages = driver.findElements(By.xpath("//table[@class='table table-hover GridTable']//tbody/tr"));
		int k=1;
		
			
		for(int i =1;i<=size1;i++)
		{
			if( k<=10)
			{
				WebElement codevalue = driver.findElement(By.xpath("//table[@class='table table-hover GridTable']//tbody/tr["+k+"]/td[2]"));
				WebElement Descrip = driver.findElement(By.xpath("//table[@class='table table-hover GridTable']//tbody/tr["+k+"]/td[3]"));
				try
				{
				TestUtil.VisibleOn(driver, codevalue, 30);
				}
				catch(TimeoutException e)
				{
					System.out.println("Element-codevalue is not seen with in 30 sec");
				}
				 name1 = codevalue.getText();
				 drdescription= Descrip.getText();
				 CodeName = reader.getCellData("UserRole", "Code", rows);
				 Description1 = reader.getCellData("UserRole", "Description", rows);
				if(name1.contains(CodeName)&&drdescription.contains(Description1) )
				{
					flag=true;
					break;
					
				}
			
				k++;
			}
			else
			{
				k=1;
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				// jse.executeScript("scroll(0, 250)");
				WebElement element = driver.findElement(By.xpath("//a[contains (text(), 'Next')]"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				System.out.println("=============clicked on next==========");
				
			}
			if(k==10&& !name1.contains(CodeName)&&!drdescription.contains(Description1))
					{
				flag= false;
				break;
					}
				
			
			
				/*//List<WebElement>code = driver.findElements(By.xpath("//table[@class='table table-hover GridTable']//tbody/tr["+i+"]/td[2]"));
				
				if (name1.contains(CodeName)&&drdescription.contains(Description1));
				{
					
						
					
					
					
					
					
				}
				if(i==10)
				{
					break;
				}
				else
				{
				j = 1;
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				// jse.executeScript("scroll(0, 250)");
				WebElement element = driver.findElement(By.xpath("//a[contains (text(), 'Next')]"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				System.out.println("=============clicked on next==========");
				}*/
				
				
			
		}
		return flag;
		
			
		
			
		
		}
		
		
		
		
	
	
	
	
	public static ArrayList<Object[]> getdatafromExcel()
	{
		Exls_Reader reader = null;

		ArrayList<Object[]> mydata = new ArrayList<Object[]>();
		try {
			reader = new Exls_Reader("C:\\Parag\\PrjectIVF\\Geminoproject\\src\\main\\java\\com_Gemino_TestData\\UserRole.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}

		int count1 = reader.getRowCount("UserRole");
		for (int rows = 2; rows <= count1; rows++) 
		{

			String code = reader.getCellData("UserRole", "Description", rows);
			String description = reader.getCellData("UserRole", "Description", rows);
			
			Object[] obj = {code,description};
			mydata.add(obj);
		}

		return mydata;
	}

	
	
	
}

		
	

	

	



