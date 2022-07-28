package com.netbankingGuru.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.netbankingGuru.utilities.ReadConfig;

import freemarker.log.Logger;



public class BaseTestClass {
	
	ReadConfig read = new ReadConfig();
	
	public String baseURL =read.get_app_url();
	public String username=read.get_userName();
	public String password=read.get_password();
	
	public static WebDriver driver;
	public Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser_name) {
		
		//String project_home_path = System.getProperty("user.dir");
		
		//System.setProperty("webdriver.chrome.driver",project_home_path+"/Drivers/chromedriver.exe");
		
		if(browser_name.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver",read.get_chrome_driver_path());
		
		/*
		 *  ChromeOptions is To handle below exception
		 *  org.openqa.selenium.WebDriverException: unknown error: cannot determine loading status
		 */
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-site-isolation-trials");
		driver = new ChromeDriver(options);
		}
		
		else if(browser_name.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", read.get_firefox_driver_path());
			driver = new FirefoxDriver();
		}
		
		else if(browser_name.equals("ie")) {
			System.setProperty("webdriver.ie.driver", read.get_ie_driver_path());
			driver = new InternetExplorerDriver();
		}
		
		else {
			System.out.println("Please pass correct browser name");
			
		}
		
		driver.get(baseURL);
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		// for logging
		logger = Logger.getLogger("NetBanking");
		PropertyConfigurator.configure("log4j.properties");
		
	}
	
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		try {
			FileUtils.copyFile(source, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot taken");
		
	}
}
