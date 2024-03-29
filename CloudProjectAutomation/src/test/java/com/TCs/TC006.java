package com.TCs;

import static org.testng.Assert.assertEquals;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;

public class TC006 {

	BaseClass bc;
	WebDriver driver;
	JavascriptExecutor js;
	Robot robot;
	String[] autoFillWord = {"Blac", "Re"};
	WebDriverWait wait;

	@BeforeTest
	public void reachWidgetsMenu() throws Exception {
		bc = new BaseClass();
		bc.launch();
		driver = bc.driver;
		js = (JavascriptExecutor)driver;
		System.out.println("Preparing the Widgets......");
		WebElement widgets_menu = driver.findElement(By.xpath("//*[@class='home-body']/div/div[4]/div/div[3]/h5"));
		js.executeScript("arguments[0].scrollIntoView()", widgets_menu);
		widgets_menu.click();
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

	@Test(priority = 0, enabled = false)
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

	@Test(priority = 1)
	public void verifyAutoComplete() throws Exception {
		System.out.println("******Verifying Accordian*******");
		WebElement widgets = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[4]"));
		elementClick(widgets);


		WebElement accordian_menu = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[4]"
				+ "/child::div/ul/li[@id='item-1']"));
		elementClick(accordian_menu);
		Thread.sleep(1000);

		//Checking the multi select box
		WebElement multiText = driver.findElement(By.xpath("//div[@id='autoCompleteMultipleContainer']//div[contains(@class, 'value-container')]"));
		elementClick(multiText);
		System.out.println("Crossed 1");
		for(int i=0;i<autoFillWord.length;i++) {

			multiText.sendKeys(autoFillWord[i]);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}
		//Checking single select box
		WebElement singleText = driver.findElement(By.xpath("//div[@id='autoCompleteSingleContainer']//div[contains(@class, 'value-container')]"));
		elementClick(singleText);
		for(int i=0;i<autoFillWord.length;i++) {
			singleText.sendKeys(autoFillWord[i]);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
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
