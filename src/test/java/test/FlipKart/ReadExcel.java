package test.FlipKart;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class ReadExcel {
	public static final String SAMPLE_XLSX_FILE_PATH = System.getProperty("user.dir")+"\\src\\test\\java\\testData\\Credentials.xlsx";
	  private static Logger Log = Logger.getLogger(AppiumServerJava.class);

	public  String  getColVal(String columnWanted) throws IOException, InvalidFormatException {

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
		// open sheet 0 which is first sheet of your worksheet
		Sheet sheet = workbook.getSheetAt(0);

		// we will search for column index containing string "Your Column Name" in the
		 int columIndex = 0 ;
		switch (columnWanted) {
		case "Password":
			columIndex=1;
			break;
		case "Items":
			columIndex=2;
			break;
		case "Email":
			columIndex=0;
			break;
		}
		Row row;StringBuffer   cellValueMaybeNull = new StringBuffer();
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {	
			row = sheet.getRow(rowIndex);
			if (row != null) {
				Cell cell = row.getCell(columIndex);
				if (cell != null) {
					// Found column and there is value in the cell.
					cellValueMaybeNull = cellValueMaybeNull.append(cell.getStringCellValue()+",");
				}
				else {
					break;
				}
			}
		}
		Log.info(cellValueMaybeNull.toString().substring(0,(cellValueMaybeNull.toString().length())-1));
		return cellValueMaybeNull.toString().substring(0,(cellValueMaybeNull.toString().length())-1);
	}
}