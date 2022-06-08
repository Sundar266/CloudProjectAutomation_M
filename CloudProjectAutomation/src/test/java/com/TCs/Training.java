package com.TCs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Training {
	
	@Test
	public void testT() throws Exception {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[contains(@class,'form-control') and @name='name']")).sendKeys("Sundar Pichai");
		driver.findElement(By.xpath("//input[contains(@class,'form-control') and @name='email']")).sendKeys("sp@abc.com");
		driver.findElement(By.xpath("//input[@id='exampleInputPassword1']")).sendKeys("123@$Aabc");
		WebElement checkBox = driver.findElement(By.xpath("//input[@id='exampleCheck1']"));
		checkBox.click();
		Assert.assertTrue(checkBox.isSelected());
		WebElement gender = driver.findElement(By.xpath("//select[@id='exampleFormControlSelect1']"));
		Select select = new Select(gender);
		select.selectByVisibleText("Male");
		List<WebElement> EmpStatus = driver.findElements(By.xpath("//div[@class='form-group']//child::input[@class='form-check-input']"));
		for(WebElement EmpSts : EmpStatus) {
			if(EmpSts.isEnabled()) {
				if(driver.findElement(By.xpath("//div[@class='form-group']//child::label[@class='form-check-label']")).getText().equalsIgnoreCase("Student")) {
					driver.findElement(By.xpath("//div[@class='form-group']//child::label[@class='form-check-label']")).click();
				}
			}
		}
		driver.findElement(By.xpath("//label[@for='dateofBirth']//following-sibling::input[@class='form-control']")).sendKeys("2000-05-30");
		driver.findElement(By.xpath("//input[@type='submit']")).submit();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'success')]")).getText().contains("success"));
	}

}
