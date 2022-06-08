package com.ExtentReport;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class XtentReport {
	
	static ExtentReports exReport;
	
	public static ExtentReports createExtentObject() {
		
		String file = System.getProperty("user.dir") + "\\Reports\\index.html";
		ExtentSparkReporter extentRep = new ExtentSparkReporter(new File(file));
		extentRep.config().setTheme(null);
		extentRep.config().setDocumentTitle("Test Results");
		extentRep.config().setReportName("Overall Test Result Summary");
		
	    exReport = new ExtentReports();
		exReport.attachReporter(extentRep);
		exReport.setSystemInfo("Tester", "Sundar");
	    return exReport;
	}
}

