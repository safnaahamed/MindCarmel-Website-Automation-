package com.utils;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MindCarmelExcelUtils {
	FileInputStream ofile;
	XSSFWorkbook oworkbook;
	XSSFSheet osheet;
	XSSFRow orow;
	XSSFCell ocell;
	String value;
	int colNo;
	int j;
	public MindCarmelExcelUtils(String FileLocation, String SheetName) throws Exception {
		ofile = new FileInputStream(FileLocation);
		oworkbook = new XSSFWorkbook(ofile);
		osheet = oworkbook.getSheet(SheetName);
	
}
public String getCellData(int rowNo, String colName) {
	try {
		orow = osheet.getRow(0);
		for(int i=0;i<=36;i++) {
			ocell = orow.getCell(i);
			String cellcontent = ocell.getStringCellValue();
			
			if(cellcontent.equalsIgnoreCase(colName)) {
				colNo = i;
				break;
			}
		}
		orow = osheet.getRow(rowNo);
		ocell = orow.getCell(colNo);
		value = ocell.getStringCellValue();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	return value;
}



	}
	
	

