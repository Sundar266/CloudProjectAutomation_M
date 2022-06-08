package com.ListenerPack;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ExtentReport.XtentReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener{

	ExtentReports exReport = XtentReport.createExtentObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> ThreadedTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		test = exReport.createTest(result.getMethod().getMethodName());
		ThreadedTest.set(test); // Ensuring the test object is thread safe especially while parallel run
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ThreadedTest.get().log(Status.PASS, "Test has been PASSED");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		DateFormat dateFromat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		Date date = new Date();
		String cDate = dateFromat.format(date);
		String nDate = cDate.replaceFirst("\\s", "-");
		String TCname = result.getMethod().getMethodName();
		try {
			WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String dest = System.getProperty("user.dir") + "\\Screenshots\\"+ TCname + "-" + nDate + ".png";
			FileUtils.copyFile(src, new File(dest));
			ThreadedTest.get().fail(result.getThrowable());
			ThreadedTest.get().addScreenCaptureFromPath(dest);
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		exReport.flush();
	}
	
	

}
