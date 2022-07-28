package com.netbankingGuru.testCases;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.netbankingGuru.pageObjects.LoginPage;

public class TC_LoginTest_01 extends BaseTestClass {

	@Test
	public void loginTest() {
		
		
		logger.info("URL opened");
		
		
		LoginPage lp = new LoginPage(driver);
		
		logger.info("Login Test started");
		
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickLogin();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.titleContains("Bank Manager"));
				
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			logger.info("Login test successful");
			Assert.assertTrue(true);
		}
		else
		{	captureScreen(driver,"TC_LoginTest_01");
			logger.info("Login test failed");
			Assert.assertTrue(false);
		}
		
		logger.info("Login test completed");
	}
	

}
