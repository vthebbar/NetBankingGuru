package com.netbankingGuru.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	// WebDriver variable 
	WebDriver driver;
	
	// constructor
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="uid")
	WebElement txtUserName;
	
	@FindBy(name="password")
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	WebElement loginbtn;
	
	@FindBy(xpath="//a[text()='Log out']")
	WebElement logoutLink;
	
	public void setUserName(String username)
	{
		txtUserName.sendKeys(username);
	}
	
	
	public void setPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		loginbtn.click();
	}
	
	public void logout() {
		logoutLink.click();
		
	}
}

