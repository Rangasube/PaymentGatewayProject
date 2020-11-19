package com.pgw.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pgw.qa.base.TestBase;

public class TestUtil extends TestBase{
	public static long PAGELOAD_TIMEOUT = 60;
	public static long IMPLICIT_WAIT = 60;
	public static FileInputStream fis;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static String TESTDATA_FILE = "C:\\Users\\Sai Sharan\\eclipse-workspace\\PaymentGateway\\src\\main\\java\\com\\pgw\\qa\\testdata\\PGW_TestData.xlsx";

	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}


	public static Object[][] getTestData(String sheetName){
		try {
			fis = new FileInputStream(TESTDATA_FILE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb =  new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet = wb.getSheet(sheetName);
		
		int lastrow = sheet.getLastRowNum();
		int lastcell = sheet.getRow(1).getLastCellNum();
		
		Object[][] data = new Object[lastrow][lastcell];
		System.out.println(lastrow+"---"+lastcell);
		for(int i=0;i<lastrow;i++)
		{
			for(int j=0;j<lastcell;j++)
			{
				/*if(sheet.getRow(i+1).getCell(j).getCellType() == CellType.NUMERIC) {
					sheet.getRow(i+1).getCell(j).setCellType(CellType.STRING);
				}*/
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				System.out.println(data[i][j]);
			}
		}
		return data;
		/*HashMap<String,String> hm = new HashMap<String,String>();
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			if(sheet.getRow(i+1).getCell(0).getCellType() == CellType.NUMERIC) {
				sheet.getRow(i+1).getCell(0).setCellType(CellType.STRING);
			}
			if(sheet.getRow(i+1).getCell(1).getCellType() == CellType.NUMERIC) {
				sheet.getRow(i+1).getCell(1).setCellType(CellType.STRING);
			}
			String key = sheet.getRow(i+1).getCell(0).getStringCellValue().trim();
			String value = sheet.getRow(i+1).getCell(1).getStringCellValue().trim();
			hm.put(key, value);
		}
		System.out.println("     PaymentPage TestData     ");
		System.out.println("------------------------------");
		for(Entry<String, String> entry : hm.entrySet())
		{
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		System.out.println("------------------------------");
		return hm;	*/
	}
	//public static void main(String[] args) throws Exception {
		//getTestData("PaymentPage");
	//}
	
}
