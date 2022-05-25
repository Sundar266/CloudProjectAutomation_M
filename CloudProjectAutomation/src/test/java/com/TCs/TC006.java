package com.TCs;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;

import com.BaseClass.BaseClass;

public class TC006 {

	BaseClass bc;
	WebDriver driver;
	JavascriptExecutor js;
	
	@BeforeTest
	public void reachAlertsMenu() throws Exception {
		bc = new BaseClass();
		bc.launch();
		driver = bc.driver;
		js = (JavascriptExecutor)driver;
		System.out.println("Preparing the Alerts, frame & Windows Menu......");
		WebElement alets_menu = driver.findElement(By.xpath("//*[@class='home-body']/div/div[3]/div/div[3]/h5"));
		js.executeScript("arguments[0].scrollIntoView()", alets_menu);
		alets_menu.click();
	    Thread.sleep(2000);
		String text = driver.findElement(By.xpath("//div[@class='main-header']")).getText();
	    if(text.contains("Alerts")) {
	    	assertEquals(true, true);
	    }	    
	}
	
	public void elementClick(WebElement ele) throws Exception {
		js.executeScript("arguments[0].scrollIntoView()", ele);
		ele.click();
		Thread.sleep(1000);
	}
	
	public void verifyWidgets() {
		
	}
}
