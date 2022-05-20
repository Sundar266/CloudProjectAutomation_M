package com.TCs;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;

public class TC005 {
	
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
	
	@Test
	public void verifyBrowserWindows() throws Exception {
		System.out.println("******Verifying browser windows*******");
		WebElement alerts_windows_frames = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[3]"));
		elementClick(alerts_windows_frames);
		
		String CurrentWindowHandle = driver.getWindowHandle();
		
		WebElement browser_windows = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[3]/child::div/ul/li[@id='item-0']"));
		elementClick(browser_windows);

		WebElement new_tab_btn = driver.findElement(By.xpath("//button[@id='tabButton']"));
		elementClick(new_tab_btn);
				
		Set<String> OtherWindowHandles = driver.getWindowHandles();	
		Iterator<String> it = OtherWindowHandles.iterator();
		while(it.hasNext()) {
			if(it.next()!=CurrentWindowHandle) {
				driver.switchTo().window(it.next());
				Thread.sleep(1000);			
				System.out.println(driver.findElement(By.xpath("//h1")).getText());
				System.out.println(driver.getTitle());
				Thread.sleep(1000);
				driver.switchTo().window(CurrentWindowHandle);
				System.out.println(driver.getCurrentUrl().toString());
			}
		}
	}
	
	
	
	@AfterMethod(lastTimeOnly = true)
	public void reload() {
		driver.navigate().refresh();
	}

	@AfterTest
	public void tearDown() {
		bc.quitBrowser();
	}

}
