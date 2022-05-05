package com.DocReader;

public class TestTheConfigfile {
	
	public static void main (String[] args) throws Exception {
		String path = "C:\\Users\\2126765\\eclipse-workspace\\CloudProjectAutomation\\src\\test\\java\\com\\DataDocs\\Test1.xlsx";
		ExcelReader xcelRead = new ExcelReader(path);
		System.out.println(xcelRead.getStringCellValue("sheet1", 1, 1));
		
		int totalrows = xcelRead.rowCount("sheet1");
		int totalcolumns = xcelRead.cellCount("sheet1", 0);
		
		String[][] table = new String[totalrows][totalcolumns];
		
		for(int i=1;i<totalrows;i++) {
		  for(int j=1;j<totalcolumns;j++) { 
			   table[i-1][j-1] = xcelRead.getStringCellValue("sheet1", i, j);
			   System.out.println(table[i-1][j-1]);
		  }
		}
		xcelRead.closeDoc();
	}
}
