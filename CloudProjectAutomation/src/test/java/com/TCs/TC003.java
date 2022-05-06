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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;
import com.Utilities.BrokenLinkVerify;
import com.Utilities.DataProviderClass;

public class TC003 {

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
		Iterator<WebElement> it = rbtns.iterator();
		if(it.hasNext()) {
			for(int i=0;i<rbtns.size();i++) {
				js.executeScript("arguments[0].scrollIntoView();", rbtns.get(i));
				if(rbtns.get(i).isEnabled()) {
					js.executeScript("arguments[0].click()", rbtns.get(i));
					Thread.sleep(2000);
					WebElement text = driver.findElement(By.xpath("//p[@class='mt-3']"));
					js.executeScript("arguments[0].scrollIntoView();" , text);
					System.out.println("And the text for your radio button is:\t"+text.getText());
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
					System.out.println(driver.findElement(By.xpath("//p[@id='doubleClickMessage']")).getText());
				}else if (ele.getText().contains("Right")) {
					bc.act.contextClick(ele).perform();
					System.out.println(driver.findElement(By.xpath("//p[@id='rightClickMessage']")).getText());
				}else {
					ele.click();
					System.out.println(driver.findElement(By.xpath("//p[@id='dynamicClickMessage']")).getText());
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
	
	@DataProvider(name="data-provider")
	public Object[][] myDataProvider() throws Exception{
		Object[][] arr = new DataProviderClass().getExcelData(path, "WebTable", 0);
		return arr;
	}
	
	@Test(priority = 4)
	public void verifytable() throws Exception {
		System.out.println("------------Verify WebTable------------");
		driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[1]")).click();
		WebElement WTable = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[1]/div/ul/li[@id='item-3']"));
		js.executeScript("arguments[0].scrollIntoView();", WTable);
		WTable.click();
		Thread.sleep(7000);
		List<WebElement> colNos = driver.findElements(By.xpath("//div[@class='ReactTable -striped -highlight']/descendant::div[@class='rt-tr']/div"));
		assertEquals(colNos.size(), 7);
		List<WebElement> initialRowNos = driver.findElements(By.xpath("//div[@class='ReactTable -striped -highlight']/descendant::div[@class='rt-tbody']/div"));
		System.out.println("Number of Rows before addition:\t" +initialRowNos.size());
	}
	
	@Test(priority = 5, dataProvider = "data-provider")
	public void AddDataInTable(String FirstName ,String LastName, String Age, String Email, String Salary, String Department) throws Exception {
		System.out.println("********Adding values to the Web table*******");
		WebElement addbtn = driver.findElement(By.xpath("//button[@id='addNewRecordButton']"));
		js.executeScript("arguments[0].scrollIntoView();", addbtn);
		addbtn.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@placeholder='name@example.com']")).sendKeys(Email);
		driver.findElement(By.xpath("//input[@placeholder='Age']")).sendKeys(Age);
		driver.findElement(By.xpath("//input[@placeholder='Salary']")).sendKeys(Salary);
		driver.findElement(By.xpath("//input[@placeholder='Department']")).sendKeys(Department);
		driver.findElement(By.xpath("//button[@id='submit']")).click();
		Thread.sleep(1000);
		List<WebElement> NewRowNos = driver.findElements(By.xpath("//div[@class='ReactTable -striped -highlight']/descendant::div[@class='rt-tbody']/div"));
		System.out.println("Number of Rows after addition:\t" +NewRowNos.size());
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
