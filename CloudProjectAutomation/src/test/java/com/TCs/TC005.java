package com.TCs;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.BaseClass.BaseClass;

public class TC005 {

	BaseClass bc;
	WebDriver driver;
	JavascriptExecutor js;

	@BeforeTest
	public void reachAlertsMenu() throws Exception {
		bc = new BaseClass();
		bc.launch();
		driver = bc.driver;
		js = (JavascriptExecutor)driver;
		System.out.println("Preparing the Alerts, frame & Windows Menu......");
		WebElement alets_menu = driver.findElement(By.xpath("//*[@class='home-body']/div/div[3]/div/div[3]/h5"));
		js.executeScript("arguments[0].scrollIntoView()", alets_menu);
		alets_menu.click();
		Thread.sleep(2000);
		String text = driver.findElement(By.xpath("//div[@class='main-header']")).getText();
		if(text.contains("Alerts")) {
			assertEquals(true, true);
		}	    
	}

	public void elementClick(WebElement ele) throws Exception {
		js.executeScript("arguments[0].scrollIntoView()", ele);
		ele.click();
		Thread.sleep(1000);
	}

	@Test(priority = 0, enabled = false)
	public void verifyBrowserWindows() throws Exception {
		System.out.println("******Verifying browser windows*******");
		WebElement alerts_windows_frames = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[3]"));
		elementClick(alerts_windows_frames);

		String CurrentWindowHandle = driver.getWindowHandle();
		//Clicking the browser windows sub-menu
		WebElement browser_windows = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[3]/child::div/ul/li[@id='item-0']"));
		elementClick(browser_windows);
		//Clicking new tab button
		//WebElement new_tab_btn = driver.findElement(By.xpath("//button[@id='tabButton']"));
		//elementClick(new_tab_btn);
		//String xpath2 = "//h1";
		//handle_windows(CurrentWindowHandle, xpath2);

		//Clicking New Window button
		//WebElement new_window_btn = driver.findElement(By.xpath("//button[@id='windowButton']"));
		//elementClick(new_window_btn);
		//String xpath1 = "//h1[@id='sampleHeading']";
		//handle_windows(CurrentWindowHandle, xpath1);

		//Clicking New window message button
		WebElement msg_window_btn = driver.findElement(By.xpath("//button[@id='messageWindowButton']"));
		elementClick(msg_window_btn);
		String xpath = "/html/body/text()";
		handle_windows(CurrentWindowHandle, xpath);

	}

	private void handle_windows(String CurrentWindowHandle, String xpath) throws Exception {
		Set<String> OtherWindowHandles = driver.getWindowHandles();	
		Iterator<String> it = OtherWindowHandles.iterator();
		while(it.hasNext()) {
			if(it.next()!=CurrentWindowHandle) {
				String handle = it.next();
				System.out.println(handle);
				driver.switchTo().window(handle);
				Thread.sleep(1000);	
				try {
					WebElement element = driver.findElement(By.xpath(xpath));
					System.out.println(element.getText());
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				finally {
					driver.switchTo().window(handle).close();
					Thread.sleep(1000);
					driver.switchTo().window(CurrentWindowHandle);
					System.out.println(driver.getCurrentUrl().toString());
				}			
			}
		}
	}	

	@Test(priority = 1, enabled = false)
	public void verifyAlerts() throws Exception {
		System.out.println("******Verifying browser alerts*******");
		WebElement alerts_windows_frames = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[3]"));
		elementClick(alerts_windows_frames);
		WebElement alerts_menu = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]"
				+ "/div/div/div[3]/child::div/ul/li[@id='item-1']"));
		elementClick(alerts_menu);

		//Clicking the alert button
		WebElement alert_btn = driver.findElement(By.xpath("//button[@id='alertButton']"));
		elementClick(alert_btn);
		Alert b_alert = driver.switchTo().alert();
		System.out.println(b_alert.getText());
		b_alert.accept();

		//Clicking the confirm alert button for OK and Cancel
		WebElement confirm_alert_btn = driver.findElement(By.xpath("//button[@id='confirmButton']"));
		elementClick(confirm_alert_btn);
		Alert c_alert = driver.switchTo().alert();
		c_alert.accept();
		//elementClick(confirm_alert_btn);
		//Alert c1_alert = driver.switchTo().alert();
		//c1_alert.dismiss();

		//Clicking the promt alert button for providing data and clicking OK
		WebElement promt_alert_btn = driver.findElement(By.xpath("//button[@id='promtButton']"));
		elementClick(promt_alert_btn);
		Alert p_alert = driver.switchTo().alert();
		System.out.println(p_alert.getText());
		p_alert.sendKeys("Sundar");
		p_alert.accept();
		
		//Clicking the time alert button and clicking OK
		WebElement time_alert_btn = driver.findElement(By.xpath("//button[@id='timerAlertButton']"));
		elementClick(time_alert_btn);
		WebDriverWait ww = new WebDriverWait(driver, 10);
		ww.until(ExpectedConditions.alertIsPresent());
		Alert t_alert = driver.switchTo().alert();
		System.out.println(t_alert.getText());
		t_alert.accept();
	}
	
	@Test(priority = 2)
	public void verifyFrames() throws Exception {
		System.out.println("******Verifying browser frames*******");
		WebElement alerts_windows_frames = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]/div/div/div[3]"));
		elementClick(alerts_windows_frames);
		WebElement frames_menu = driver.findElement(By.xpath("//*[@id='app']/div/div/div[2]/div[1]"
				+ "/div/div/div[3]/child::div/ul/li[@id='item-2']"));
		elementClick(frames_menu);
		
		System.out.println(driver.findElement(By.xpath("//div[@id='framesWrapper']/div")).getText());
		
		driver.switchTo().frame("frame1");
		System.out.println(driver.findElement(By.xpath("//h1[@id='sampleHeading']")).getText());
		driver.switchTo().defaultContent();
		js.executeScript("window.scrollBy(0,300)", "");
		driver.switchTo().frame("frame2");
		js.executeScript("window.scrollBy(0,100)", "");
		Thread.sleep(4000);
		System.out.println(driver.findElement(By.xpath("//h1[@id='sampleHeading']")).getText());
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
