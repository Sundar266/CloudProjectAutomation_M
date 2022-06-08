package com.TCs;

import static org.testng.Assert.assertEquals;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;

public class SixTest {

	BaseClass bc;
	WebDriver driver;
	JavascriptExecutor js;
	Robot robot;
	String[] autoFillWord = {"Blac", "Re"};
	WebDriverWait wait;
	Actions act;

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
		act = new Actions(driver);
		robot = new Robot();
		Thread.sleep(2000);
		String text = driver.findElement(By.xpath("//div[@class='main-header']")).getText();
		if(text.contains("Widgets")) {
			assertEquals(true, true);
		}
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public void elementClick(WebElement ele) throws Exception {
		js.executeScript("arguments[0].scrollIntoView()", ele);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.click();
		Thread.sleep(1000);
	}

	@Test(priority = 1)
	public void verifyAutoComplete() throws Exception {
		System.out.println("******Verifying Accordian*******");
		WebElement widgets = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[4]"));
		elementClick(widgets);

		WebElement accordian_menu = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[4]"
				+ "/child::div/ul/li[@id='item-1']"));
		elementClick(accordian_menu);
		Thread.sleep(1000);
		js.executeScript("window.scrollBy(0,200)", "");

		//Checking the multi select box
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='autoCompleteMultipleContainer']//div[contains(@class,'value-container')]"))).click();
		//driver.findElement(By.xpath("//div[@id='autoCompleteMultipleContainer']//div[contains(@class,'value-container')]")).sendKeys("Blac");
		//Thread.sleep(1000);
		//robot.keyPress(KeyEvent.VK_ENTER);
		//robot.keyRelease(KeyEvent.VK_ENTER);
		WebElement element = driver.findElement(By.xpath("//div[@id='autoCompleteMultipleContainer']//div[contains(@class,'value-container')]"));
		for(int i=0;i<autoFillWord.length;i++) {
			act.click(element).sendKeys(autoFillWord[i]).build().perform();
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}	
	}


	@AfterTest
	public void tearDown() {
		bc.quitBrowser();
	}
}
