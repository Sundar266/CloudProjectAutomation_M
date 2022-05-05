package com.TCs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;

//To test the Home Page
public class TC001 {
	
	BaseClass bc;
	
	@Test
	public void verifyTitle() throws Exception {
		bc = new BaseClass();
		bc.launch();
		WebDriver driver = bc.driver;
		Assert.assertEquals(driver.getTitle(),"ToolsQA","Title not valid");
		String name = driver.findElement(By.xpath("//div[@class='card-body']")).getText();
		System.out.println(name);
	}
	
	@AfterTest
	public void tearDown() {
		bc.quitBrowser();
	}

}
