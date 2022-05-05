package com.DocReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public XSSFWorkbook wb;
	
	public ExcelReader(String Path) throws Exception {
		FileInputStream FIS = new FileInputStream(Path);
	    wb = new XSSFWorkbook(FIS);
	}

	public double getNumericCellValue(String sheet, int row, int cell) {
		double value = wb.getSheet(sheet).getRow(row).getCell(cell).getNumericCellValue();
		return value;
	}
	
	public String getStringCellValue(String sheet, int row, int cell) {
		String value = wb.getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
		return value;
	}
	
	public Date getDateValue(String sheet, int row, int cell) {
		Date value = wb.getSheet(sheet).getRow(row).getCell(cell).getDateCellValue();
		return value;
	}
	
	public int rowCount(String sheet) {
		int rows = wb.getSheet(sheet).getPhysicalNumberOfRows();
		return rows;
	}
	
	public int cellCount(String sheet, int row) {
		int columns = wb.getSheet(sheet).getRow(row).getLastCellNum();
		return columns;
	}
	
	public void closeDoc() throws IOException {
		wb.close();
	}
	
}
