package com.Utilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrokenLinkVerify {

	public void linksverify(WebDriver driver, String Xpath) {

		List<WebElement> ele = driver.findElements(By.xpath(Xpath));
		System.out.println("Total number of elements considered is:\t" +ele.size());
		for(WebElement e : ele) {
			String UrL = e.getAttribute("href");
			clickNcheck(UrL,e);
		}
	}

	public void clickNcheck(String UrL, WebElement el) {
		try {
			URL url = new URL(UrL);
			HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
			httpConnection.setConnectTimeout(4000);
			httpConnection.connect();
			if(httpConnection.getResponseCode()>=400) {
				System.out.println("The link " +UrL+ "is Invalid or Broken,\t" + httpConnection.getResponseMessage());
				System.out.println("And the feature is:\t" +el.getText());
			}else {
				System.out.println("The link " +UrL+ "is Valid,\t" + httpConnection.getResponseMessage());
				System.out.println("And the feature is:\t" +el.getText());
			} 
		}catch(MalformedURLException me) {
			System.out.println("Please enter valid URL\t" +me.getMessage());
			System.out.println("The element engaged above is:\t"+el.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
