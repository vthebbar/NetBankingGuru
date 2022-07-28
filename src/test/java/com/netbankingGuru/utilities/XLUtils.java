package com.netbankingGuru.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	
	public static int get_row_count(String xlfile, String xlsheet) {
		
		int rowCount=0;
		
		try {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		rowCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return rowCount;
	}
	
	
	public static int get_cell_count(String xlfile, String xlsheet, int rownum) {
		int cellCount=0;
		try {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cellCount;
		
	}
	
	
	public static String get_cell_data(String xlfile, String xlsheet, int rownum, int colnum)  {
		
		try {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		String data;
		
		try {
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
			
		}
		
		catch(Exception e) {
			data ="";
		}
		
		try {
			wb.close();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return data;
	}
	
	
	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data) {
		
		try {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		
		wb.close();
		fi.close();
		fo.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
