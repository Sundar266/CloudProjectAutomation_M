package com.TCs;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;

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
		WebElement EleMenu = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[1]/div/div[3]/h5"));
		js.executeScript("arguments[0].scrollIntoView();", EleMenu);
		EleMenu.click();
		Thread.sleep(2000);
		assertEquals(driver.findElement(By.xpath("//div[@class='main-header']")).getText(), "Elements");
	}
	
	public void verifyCheckBox() {

		//button[@title="Toggle"] -- Drop down btn
		//label[@for="tree-node-home"]/descendant::span[@class="rct-checkbox"] -- Home check box
		//button[@title="Expand all"] -- Expand button
		//label[@for="tree-node-desktop"]/span[@class="rct-checkbox"] -- Desktop drop down
		//label[@for="tree-node-notes"]/span[@class="rct-checkbox"] -- Notes check box
		//label[@for="tree-node-documents"]/span[@class="rct-checkbox"] -- Documents check box
		
	}

}
