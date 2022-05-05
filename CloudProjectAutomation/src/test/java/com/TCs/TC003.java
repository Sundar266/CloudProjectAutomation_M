package com.TCs;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
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
import com.Utilities.BrokenLinkVerify;

public class TC003 {

	BaseClass bc;
	WebDriver driver;
	JavascriptExecutor js;

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

	public void updateForm() {
		driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[1]")).click();
		driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[1]/div/ul/li[@id='item-0']")).click();
	}

	@Test(priority = 1)
	public void verifyRadioBtns() throws Exception {
		System.out.println("------------Verify Radio Buttons-------------");
		driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[1]")).click();
		WebElement radioBtn = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[1]/div/ul/li[@id='item-2']"));
		js.executeScript("arguments[0].scrollIntoView();", radioBtn);
		radioBtn.click();
		Thread.sleep(1000);
		List<WebElement> rbtns = driver.findElements(By.xpath("//div/child::input[@type='radio' and @name='like']"));
		System.out.println("Checkpoint 1");
		Iterator<WebElement> it = rbtns.iterator();
		if(it.hasNext()) {
			for(int i=0;i<rbtns.size();i++) {
				js.executeScript("arguments[0].scrollIntoView();", rbtns.get(i));
				if(rbtns.get(i).isEnabled()) {
					js.executeScript("arguments[0].click()", rbtns.get(i));
					Thread.sleep(10000);
					String Text = driver.findElement(By.xpath("//p[@class='mt-3']/child::span")).getText();
					System.out.println(rbtns.get(i).getText());
					System.out.println(Text);
				}else{
					System.out.println("Element has been disabled");
				}
			}
		}
	}

	public void verifyCheckBox() {

	}

	@Test(priority = 3)
	public void verifyButtons() throws InterruptedException {
		System.out.println("-------------Verify Buttons--------------");
		driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[1]")).click();
		WebElement buttons = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[1]/div/ul/li[@id='item-4']"));
		js.executeScript("arguments[0].scrollIntoView();", buttons);
		buttons.click();
		Thread.sleep(1000);
		try {
			List<WebElement>butnElem = driver.findElements(By.xpath("//button[@class='btn btn-primary']"));
			for (WebElement ele : butnElem) {
				js.executeScript("arguments[0].scrollIntoView();", ele);
				System.out.println(ele.getText());
				if(ele.getText().contains("Double")) {
					bc.act.doubleClick(ele).perform();
					System.out.println(driver.findElement(By.xpath("//p[@id='doubleClickMessage']")));
				}else if (ele.getText().contains("Right")) {
					bc.act.contextClick(ele).perform();
					System.out.println(driver.findElement(By.xpath("//p[@id='doubleClickMessage']")));
				}else {
					ele.click();
					System.out.println(driver.findElement(By.xpath("//p[@id='doubleClickMessage']")));
				}
			}
		}catch(Exception e) {
			System.out.println("Web Element couldn't be located");
		}

	}

	@Test(priority = 2)
	public void verifyLinks() throws Exception {
		System.out.println("------------Verify Links------------");
		driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[1]")).click();
		WebElement links = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[1]/div/ul/li[@id='item-5']"));
		js.executeScript("arguments[0].scrollIntoView();", links);
		links.click();
		Thread.sleep(1000);
		String XPath = "//div[@id='linkWrapper']/p/a";
		BrokenLinkVerify linkVerify = new BrokenLinkVerify();
		linkVerify.linksverify(driver, XPath);		
	}
	
	@Test(priority = 4)
	public void verifytable() throws InterruptedException {
		System.out.println("------------Verify WebTable------------");
		driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[1]")).click();
		WebElement WTable = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[1]/div/ul/li[@id='item-5']"));
		js.executeScript("arguments[0].scrollIntoView();", WTable);
		WTable.click();
		Thread.sleep(1000);
		List<WebElement> colNos = driver.findElements(By.xpath("//div[@class='ReactTable -striped -highlight']/descendant::div[@class='rt-tr']/div"));
		assertEquals(colNos.size(), 7);
		List<WebElement> initialRowNos = driver.findElements(By.xpath("//div[@class='ReactTable -striped -highlight']/descendant::div[@class='rt-tbody']/div"));
		System.out.println("Number of Rows before addition:\t" +initialRowNos);
		WebElement addbtn = driver.findElement(By.xpath("//button[@id='addNewRecordButton']"));
		js.executeScript("arguments[0].scrollIntoView();", addbtn);
		addbtn.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Sundar");
		//use page factory
	}

	@AfterMethod
	public void reload() {
		driver.navigate().refresh();
	}

	@AfterTest
	public void tearDown() {
		bc.quitBrowser();
	}
}
