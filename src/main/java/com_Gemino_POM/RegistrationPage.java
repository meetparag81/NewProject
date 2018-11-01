package com_Gemino_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage 
{
	WebDriver driver;
	private @FindBy(css="input[name='txtFirstName']")
	WebElement FirstName_Textbox;

	private @FindBy(css="input[name='txtMiddleName']")
	WebElement MiddleName_Textbox;

	private @FindBy(css="input[name='txtLastName']")
	WebElement LastName_Textbox;

	private @FindBy(css="input[name='rdbGender']")
	WebElement Gender_RadioButton;

	private @FindBy(xpath="//select[@class='ng-pristine ng-valid ng-empty ng-not-modified ng-touched']")
	WebElement DateOfBirthYear_DropDown;

	private @FindBy(xpath="//select[@class='ng-pristine ng-valid ng-empty ng-not-modified ng-touched']")
	WebElement DateOfBirthMonth_DropDown;

	private @FindBy(xpath="//select[@class='ng-pristine ng-untouched ng-valid ng-empty ng-not-modified']")
	WebElement DateOfBirthDay_DropDown;

	/*private @FindBy(css="input[name='txtAge']")
	WebElement Age_Textbox;*/

	private @FindBy(css="input[name='txtMobCountryCode']")
	WebElement MobileCountryCode_Testbox;

	private @FindBy(css="input[name='txtMobNo']")
	WebElement MobileNo_Textbox;
	
	private @FindBy(xpath="//select[@class='form-control ng-pristine ng-valid ng-not-empty ng-modified ng-touched']")
	WebElement SelectIdentityProof;

	private @FindBy(css="input[name='txtIdProofNo']")
	WebElement IDProofNo_Textbox;

	public RegistrationPage()
	{
		PageFactory.initElements(driver, this);
	}
    public void EnterFirstName()
	{
		FirstName_Textbox.sendKeys("sayali");
	}
	public void EnterMiddleName()
	{
		MiddleName_Textbox.sendKeys("Sagar");
	}
	public void EnterLastName()
	{
		LastName_Textbox.sendKeys("Sharma");
	}
	public void SelectGender()
	{
       Gender_RadioButton.click();
	}
	public void SelectDateOfBirthYear()
	{
		Select drop=new Select(DateOfBirthYear_DropDown);
		drop.selectByVisibleText("1994");
	}
	public void SelectDateOfBirthMonth()
	{
		Select drop=new Select(DateOfBirthMonth_DropDown);
		drop.selectByVisibleText("10");
	}
	public void SelectDateOfBirthDay()
	{
		Select drop=new Select(DateOfBirthDay_DropDown);
		drop.selectByVisibleText("18");
	}
	/*public void EnterAge()
	{
		Age_Textbox.sendKeys("24");
	}*/
	public void EnterCountryCode()
	{
       MobileCountryCode_Testbox.sendKeys("91");
    }
	public void EnterMobileNo()
	{
		MobileNo_Textbox.sendKeys("9876543210");
	}
	public void SelectIdentityProof()
	{
		Select drop=new Select(SelectIdentityProof);
		drop.selectByVisibleText("Aadhar Card");
	}
	public void EnterIdProofNo()
	{
		IDProofNo_Textbox.sendKeys("123456789123");
	}
	
	
	
	
	
	
}
