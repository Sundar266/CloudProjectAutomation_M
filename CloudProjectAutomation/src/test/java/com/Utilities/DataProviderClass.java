package com.Utilities;

import com.DocReader.ExcelReader;

public class DataProviderClass {

	public Object[][] getExcelData(String filePath, String sheetName, int Row) throws Exception {		
				
		ExcelReader exlReader = new ExcelReader(filePath);	
	    int rowTotal = exlReader.wb.getSheet(sheetName).getPhysicalNumberOfRows();
		int columnTotal = exlReader.wb.getSheet(sheetName).getRow(Row).getLastCellNum();
		
		String[][] data = new String[rowTotal-1][columnTotal-1];
		
		for(int i=1;i<rowTotal;i++) {
			for(int j=1;j<columnTotal;j++) {
				 data[i-1][j-1] = exlReader.wb.getSheet(sheetName).getRow(i).getCell(j).getStringCellValue();}}
		exlReader.closeDoc();
		return data;
	}
}

