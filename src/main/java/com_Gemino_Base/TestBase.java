package com_Gemino_Base;
//
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.Listeners;

import com_Gemino_util.TestUtil;
@Listeners(com_Gemino_util.TestNGListners.class)
public class TestBase 
{
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverEventListener e_driver;

	public TestBase()
	{
		prop = new Properties();
		FileInputStream ip;
		try
		{
			prop = new Properties();
			 ip = new FileInputStream("C:\\Parag\\PrjectIVF\\Geminoproject\\src\\main\\java\\com_Gemino_config\\config.properties");
			try 
			{
				prop.load(ip);
			} 
			catch (IOException e) 
			{
				
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e) 
		{
			
			System.out.println("file is openor not available");
		}
		}

	public static void initalization()
	{
		String browsername = prop.getProperty("browser");
		if(browsername.equals("firefox"))
		{
			//System.setProperty("webdriver.gecko.driver","C:\\Parag\\Paragdata30032018\\Parag\\Selenium\\Selenium Setup\\geckodriver-v0.18.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();		
		}	
		else if(browsername.equals("chrome"))
		 {
		  // System.setProperty("webdriver.chrome.driver" ,"C:\\Parag\\Selenium\\Selenium Setup\\chrome exe for 65\\chromedriver.exe");
			driver = new ChromeDriver();
		 }
		else if(browsername.equals("IE"))
		{
			driver = new InternetExplorerDriver();
		}
		/*EventFiringWebDriver e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
			WebDriverEventListener eventListener =  new TestNGListners();
			e_driver.register(eventListener);
			driver = e_driver;			
		e_driver.register(eventListener);
		driver = e_driver;*/
		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		try
		{
		driver.get(prop.getProperty("url"));
		}
		catch(TimeoutException e)
		{
			System.out.println("url is not entered");
			
		
	}
	}
}
