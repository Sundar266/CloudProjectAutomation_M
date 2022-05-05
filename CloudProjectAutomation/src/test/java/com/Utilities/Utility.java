package com.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Utility {

	private WebDriver driver;
	String nDate;
	private Actions act;

	public Utility(WebDriver driver) {
		this.driver=driver;
		DateFormat dateFromat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		Date date = new Date();
		String cDate = dateFromat.format(date);
		nDate = cDate.replaceFirst("\\s", "-");
		act = new Actions(driver);
	}

	//Captures only the visible webpage
	public void captureScreenShot() {
		File myScrSht = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\2126765\\eclipse-workspace\\CloudProjectAutomation\\target\\Screenshots\\test-"+nDate+".PNG");
		try {FileUtils.copyFile(myScrSht, dest);} 
		catch (IOException e) {e.printStackTrace();}
	}

	//Captures the complete webpage
	public void captureFullScreenShot() {
		Screenshot fShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		try {ImageIO.write(fShot.getImage(), "PNG", new File("C:\\Users\\2126765\\eclipse-workspace\\CloudProjectAutomation\\target\\Screenshots\\test-"+nDate+".PNG"));} 
		catch (IOException e) {e.printStackTrace();}
	}

	//Waiting till the visibility of the element
	public WebElement explicitWaitByVisibility(WebElement ele, int seconds) {
		WebElement waitedForElement = new WebDriverWait(driver,Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOf(ele));
		return waitedForElement;
	}

	//Waiting till the presence of the element
	public WebElement explicitWaitByPresence(WebElement ele, int seconds, String xpath) {
		WebElement waitedForElement = new WebDriverWait(driver,Duration.ofSeconds(seconds)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		return waitedForElement;
	}

	//Drop based on element
	public void dragNDrop(WebElement From, WebElement To) {		
		act.dragAndDrop(From, To).build().perform();
	}

	//Drop based on pixel
	public void dragNDrop(WebElement From, int pix1, int pix2) {		
		act.dragAndDropBy(From, pix1, pix2).build().perform();
	}
}
