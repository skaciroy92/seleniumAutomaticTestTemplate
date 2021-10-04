package com.wi.main.util;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelUtil {
	public static final String testDataExcelFileName = "testingData.xlsx";
	public static final String currentDir = System.getProperty("user.dir");
	public static String testDataExcelPath = null;
	private static XSSFWorkbook excelWBook;
	private static XSSFSheet excelWSheet;
	private static XSSFCell cell;
	private static XSSFRow row;
	public static int rowNumber;
	public static int columnNumber;

	public static void setExcelFileSheet(String sheetName) throws Exception {
		testDataExcelPath = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "testingData";
		FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + File.separator + testDataExcelFileName);
		excelWBook = new XSSFWorkbook(ExcelFile);
		excelWSheet = excelWBook.getSheet(sheetName);
	}

	public static String getCellData(int RowNum, int ColNum) {
		cell = excelWSheet.getRow(RowNum).getCell(ColNum);
		DataFormatter formatter = new DataFormatter();
		return formatter.formatCellValue(cell);
	}

	public static XSSFRow getRowData(int RowNum) {
		row = excelWSheet.getRow(RowNum);
		return row;
	}

	public static void setCellData(String value, int RowNum, int ColNum) throws Exception {
		row = excelWSheet.getRow(RowNum);
		cell = row.getCell(ColNum);
		if (cell == null) {
			cell = row.createCell(ColNum);
			cell.setCellValue(value);
		} else {
			cell.setCellValue(value);
		}

		FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
		excelWBook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
}