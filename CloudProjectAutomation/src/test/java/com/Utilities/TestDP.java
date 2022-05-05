package com.Utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDP {
	
	String path="C:\\Users\\2126765\\eclipse-workspace\\CloudProjectAutomation\\src\\test\\java\\com\\DataDocs\\Test1.xlsx";
	
	@DataProvider(name="data-provider")
	public Object[][] myDataProvider() throws Exception{
		Object[][] arr = new DataProviderClass().getExcelData(path, "Sheet1", 1);
		return arr;
	}
	
	@Test(dataProvider="data-provider")
	public void myDAta(String name, String Race,String Nationality) throws Exception {	
		System.out.println(name +", "+ Race +", "+Nationality);
	}

}
