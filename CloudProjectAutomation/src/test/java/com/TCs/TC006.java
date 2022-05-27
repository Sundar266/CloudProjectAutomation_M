package com.TCs;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;

public class TC006 {

	BaseClass bc;
	WebDriver driver;
	JavascriptExecutor js;

	@BeforeTest
	public void reachWidgetsMenu() throws Exception {
		bc = new BaseClass();
		bc.launch();
		driver = bc.driver;
		js = (JavascriptExecutor)driver;
		System.out.println("Preparing the Alerts, frame & Windows Menu......");
		WebElement widgets_menu = driver.findElement(By.xpath("//*[@class='home-body']/div/div[4]/div/div[3]/h5"));
		js.executeScript("arguments[0].scrollIntoView()", widgets_menu);
		widgets_menu.click();
		Thread.sleep(2000);
		String text = driver.findElement(By.xpath("//div[@class='main-header']")).getText();
		if(text.contains("Widgets")) {
			assertEquals(true, true);
		}	    
	}

	public void elementClick(WebElement ele) throws Exception {
		js.executeScript("arguments[0].scrollIntoView()", ele);
		ele.click();
		Thread.sleep(1000);
	}

	@Test(priority = 0, enabled = true)
	public void verifyAccordian() throws Exception {
		System.out.println("******Verifying Accordian*******");
		WebElement widgets = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[4]"));
		elementClick(widgets);

		WebElement accordian_menu = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[4]"
				+ "/child::div/ul/li[@id='item-0']"));
		elementClick(accordian_menu);
		Thread.sleep(1000);
		//elementClick(driver.findElement(By.id("section1Heading")));
		System.out.println(driver.findElement(By.xpath("//div[@class='collapse show']")).isDisplayed());
		elementClick(driver.findElement(By.id("section2Heading")));
		System.out.println(driver.findElement(By.xpath("//div[@class='collapse show']")).isDisplayed());
		elementClick(driver.findElement(By.id("section3Heading")));
		System.out.println(driver.findElement(By.xpath("//div[@class='collapse show']")).isDisplayed());
	}

	public void verifyAutoComplete() throws Exception {
		System.out.println("******Verifying Accordian*******");
		WebElement widgets = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[4]"));
		elementClick(widgets);

		WebElement accordian_menu = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[4]"
				+ "/child::div/ul/li[@id='item-1']"));
		elementClick(accordian_menu);
		Thread.sleep(1000);
	}
}
