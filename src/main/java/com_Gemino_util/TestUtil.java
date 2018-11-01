package com_Gemino_util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com_Gemino_Base.TestBase;
//
public class TestUtil extends TestBase 
{
	
	public static long IMPLICIT_WAIT = 30;
	public static long PAGE_LOAD_TIMEOUT = 20;
	
	public static void ClickOn(WebDriver driver,WebElement locator,int timeout)
	{
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeSelected(locator));
		locator.click();
		
	}
	public static WebElement  VisibleOn(WebDriver driver,WebElement element,int timeout)
	{
		return new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
	}
	
	public static List<WebElement>  VisibleElementsOn(WebDriver driver,List<WebElement> element,int timeout)
	{
		return new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public static WebElement  ClickableElement(WebDriver driver,WebElement element,int timeout)
	{
		return new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	
	public static void ScrollthePage(JavascriptExecutor js,WebDriver driver)
	{
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("scroll(0, 250);");
		
	}
	
	
	
	
	public static void takeScreenshotAtEndOfTest1()
	{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		try 
		{
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots1/" + System.currentTimeMillis() + ".png"));
		}
		catch (IOException e) 
		{
			System.out.println("Exception are" + e.getMessage());
			e.printStackTrace();
		}
	}
	private static void copyRow(HSSFWorkbook workbook, HSSFSheet worksheet, int sourceRowNum, int destinationRowNum) 
	{
        // Get the source / new row
        HSSFRow newRow = worksheet.getRow(destinationRowNum);
        HSSFRow sourceRow = worksheet.getRow(sourceRowNum);

        // If the row exist in destination, push down all rows by 1 else create a new row
        if (newRow != null) 
        {
            worksheet.shiftRows(destinationRowNum, worksheet.getLastRowNum(), 1);
        } else {
            newRow = worksheet.createRow(destinationRowNum);
        }

        // Loop through source columns to add to new row
        for (int i = 0; i < sourceRow.getLastCellNum(); i++) 
        {
            // Grab a copy of the old/new cell
            HSSFCell oldCell = sourceRow.getCell(i);
            HSSFCell newCell = newRow.createCell(i);
        }
	}
	
	public static void takescreenshot(WebDriver driver, String screenshotname)  
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		try 
		{
			FileUtils.copyFile(source, new File(currentDir + "/screenshots"+ screenshotname +".png"));
		} 
		catch (IOException e) 
		{
			System.out.println("Exception are" + e.getMessage());
			e.printStackTrace();
		}	

	}
	public static  Actions ActionForMovetoElement(WebElement element )
	{
		
		Actions act1 = new Actions(driver);
		return act1.moveToElement(element);
	}
	
	
	public static  Select SelectOption(WebElement element )
	{
		
		Select sel = new Select(element);
		
			return sel;	
		
		
	}
	public static void ClickEmementByJavaScriptExecutor(WebElement element)
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	public static void SendvaluesInputboxByJavaScriptExecutor(WebElement element,String value)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.element.value='value'");
	}
	
	public static void MoveScrollBarHorizontally(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public static void moveScrollbarVerticallydown()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,1000)");
	}
	public static void moveScrollbarVerticallyup()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,-1000)");
	}
	
	
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception 
		{
	        //below line is just to append the date format with the screenshot name to avoid duplicate names 
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    formatter = new SimpleDateFormat("dd,MM,yyyy"); 
	    String dateName = new SimpleDateFormat("dd,MM,yyyy").format(new Date());
	   //     String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
	String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
	File finalDestination = new File(destination);
	FileUtils.copyFile(source, finalDestination);
	        //Returns the captured file path
	return destination;
		}
	public static String getMonthForInt(int num) 
	{
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) 
        {
            month = months[num];
        }
        return month;	
		
	}
	public static String Date()
	{
		Date date1 = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    formatter = new SimpleDateFormat("dd,MMMM,yyyy,zzzz"); 
	    String strDate = formatter.format(date1);  
		strDate = formatter.format(date1); 
		
		
		return strDate;
		
	}
	public static String DateForCompare()
	{
		Date date1 = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    formatter = new SimpleDateFormat("dd-MMM-yyyy"); 
	    String strDate = formatter.format(date1);  
		strDate = formatter.format(date1); 
		
		
		return strDate;
		
	}
	
	public static void IntDate()
	{
		
	
	
	}

	
	

}
