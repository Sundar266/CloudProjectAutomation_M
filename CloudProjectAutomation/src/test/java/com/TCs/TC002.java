package com.TCs;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;

//To check all the Menu features
public class TC002 {

	BaseClass bc;
	@Test
	public void verifyAllMenu() throws Exception {
		bc = new BaseClass();
		bc.launch();
		WebDriver driver = bc.driver;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		List<WebElement> menu = driver.findElements(By.xpath("//div[@class='card-body']"));
		Iterator<WebElement> it = menu.iterator();
		try {
			if(it.hasNext()) {
				for(int i=1;i<=menu.size();i++) {
					WebElement ele = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div["+i+"]/div/div[3]/h5"));
					js.executeScript("arguments[0].scrollIntoView();", ele);
					System.out.println("Menu number " +i+ ": " +ele.getText());
					ele.click();
					Thread.sleep(4000);
					if(!ele.getText().equalsIgnoreCase("Book Store")) {
						assertEquals(ele.getText(), driver.findElement(By.xpath("//div[@class='main-header']")).getText());}
					driver.navigate().back();
					driver.navigate().refresh();
					Thread.sleep(2000);
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@AfterTest
	public void tearDown() {
		bc.quitBrowser();
	}

}
