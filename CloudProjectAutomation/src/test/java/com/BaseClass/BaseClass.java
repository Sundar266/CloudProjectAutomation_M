package com.BaseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.DocReader.ConfigReader;
import com.DocReader.ExcelReader;
import com.LaunchBrowser.LaunchBrowser;
import com.Utilities.Utility;

public class BaseClass {

	ConfigReader cfigRead;
	LaunchBrowser launchBrowser;
	ExcelReader xcelRead;
	Utility utilities;
	public WebDriver driver;
	public Actions act ;

	public BaseClass() {
		cfigRead = new ConfigReader();
	    launchBrowser = new LaunchBrowser();	
	}

	public void loadMyXcel(String Path) {
		try {
			xcelRead = new ExcelReader(Path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver launch() throws Exception {
	    driver = launchBrowser.launchBrowser(cfigRead.getKeyProperty("Browser"), cfigRead.getKeyProperty("Url2Test"));
		utilities = new Utility(driver);
		act = new Actions(driver);
		return driver;	
	}
	
	public void quitBrowser() {
		driver.quit();
	}
}
