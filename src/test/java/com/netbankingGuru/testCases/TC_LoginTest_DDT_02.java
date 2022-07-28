package com.netbankingGuru.testCases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.netbankingGuru.pageObjects.LoginPage;
import com.netbankingGuru.utilities.XLUtils;



public class TC_LoginTest_DDT_02 extends BaseTestClass {
	
	@Test(dataProvider="loginData")
	public void login_ddt(String userid, String pwd) {
		
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userid);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		if(isAlertPresent()==true) {
			logger.info("Login Failed");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}
		
		else {
			logger.info("Login success");
			Assert.assertTrue(true);
			lp.logout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}
	
	// user defined method to check if alert is present
	
	public boolean isAlertPresent() {
		
		try {
		driver.switchTo().alert();
		return true;
		}
		
		catch(Exception e) {
			return false;
		}
	}
	
	@DataProvider(name="loginData")
	public String[][] getdata(){
		String test_data_path=System.getProperty("user.dir")+"/src/test/java/com/netbankingGuru/testData/TestData.xlsx";
		
		int row_count = XLUtils.get_row_count(test_data_path, "sheet1");
		int col_count = XLUtils.get_cell_count(test_data_path, "sheet1", 1);
		String loginData[][]=new String[row_count][col_count];
		
		// loop starts from 1 , because first row in excel is header
		for(int i=1; i<= row_count ; i++) {
			
			// column start from 0
			for(int j=0; j< col_count; j++) {
				loginData[i-1][j]= XLUtils.get_cell_data(test_data_path, "sheet1", i, j);
			}
		}
		
		return loginData;
	} 
}
