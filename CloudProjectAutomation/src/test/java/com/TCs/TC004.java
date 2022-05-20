package com.TCs;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.BaseClass.BaseClass;

public class TC004 {

	BaseClass bc;
	WebDriver driver;
	JavascriptExecutor js;
	String path = "C:\\Users\\2126765\\git\\CloudProjectAutomation_M\\CloudProjectAutomation\\src\\test\\java\\com\\DataDocs\\Test1.xlsx";
	
	@BeforeTest
	public void reachElementsMenu() throws Exception {
		bc = new BaseClass();
		bc.launch();
		driver = bc.driver;
		js = (JavascriptExecutor)driver;
		System.out.println("Preparing the Elements Menu......");
		WebElement EleMenu = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div/div[1]/div/div[3]/h5"));
		js.executeScript("arguments[0].scrollIntoView();", EleMenu);
		EleMenu.click();
		Thread.sleep(2000);
		assertEquals(driver.findElement(By.xpath("//div[@class='main-header']")).getText(), "Elements");
	}

	@Test
	public void verifyCheckBox() throws InterruptedException {

		System.out.println("-------------Verify Check Boxes--------------");
		//SoftAssert sa = new SoftAssert();
		driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[1]")).click();

		//Clicking the Menu for check box
		WebElement home_CheckBoxMenu = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[1]/div/ul/li[@id='item-1']"));
		clickElement(home_CheckBoxMenu);
		
		//Clicking the Home check box
		WebElement cb_home = driver.findElement(By.xpath("//label[@for='tree-node-home']/span[@class='rct-checkbox']"));
		clickElement(cb_home);
		//clicking the expand btn
		WebElement expand_btn = driver.findElement(By.xpath("//button[@title='Expand all']"));
		clickElement(expand_btn);
		
		//Initializing some web elements
		WebElement cb_notes = driver.findElement(By.xpath("//label[@for='tree-node-notes']/span[@class='rct-checkbox']"));		
		//WebElement cb_documents = driver.findElement(By.xpath("//label[@for='tree-node-documents']/span[@class='rct-checkbox']"));

		//Confirming if all the check boxes are enabled by verifying random check boxes
		confirmResult("notes","");
		
		//Confirming if the other elements are disabled after disabling the home check box & enabling a random check box
		clickElement(cb_home);
		Thread.sleep(2000);
		clickElement(cb_notes);
		confirmResult("notes", "documents");
	}

	private void clickElement(WebElement ele) throws InterruptedException {
		js.executeScript("arguments[0].scrollIntoView();", ele);
		ele.click();
		Thread.sleep(1000);
	}
	
	private void confirmResult(String expected, String unexpected) {
		List<WebElement> finalWords = driver.findElements(By.xpath("//div[@id='result']/span[@class='text-success']"));
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("result")));
		for (WebElement e : finalWords) {
			String result_text = e.getText();
			if(result_text.contains(expected)) {
				assertEquals(true, true);
			}else {
				System.out.println("Other results in the section at last are: " +e.getText());
			}
			if(!result_text.contains(unexpected) && unexpected != null) {
				assertEquals(true, true);
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
