package com.netbankingGuru.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties prop;
	
	//constructor
	public ReadConfig()
	{
		File src = new File("./Configurations/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
		}
		
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception is:"+ e.getMessage());
		}
	}
	
	public String get_app_url()
	{
		String url = prop.getProperty("baseURL");
		return url;
	}
	
	
	public String get_userName() {
		
		String user = prop.getProperty("username");
		return user;
	}
	
	public String get_password()
	{
		String pwd = prop.getProperty("password");
		return pwd;
	}
	
	public String get_chrome_driver_path() {
		
		String chrome_path = prop.getProperty("chromepath");
		return chrome_path;
	}
	
public String get_firefox_driver_path() {
		
		String firefox_path = prop.getProperty("firefoxpath");
		return firefox_path;
	}

public String get_ie_driver_path() {
	
	String ie_path = prop.getProperty("iepath");
	return ie_path;
}
}
