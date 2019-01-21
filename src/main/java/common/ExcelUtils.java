package common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {
	private static String[] headerTest = {"case_name", "test_case_path", "actual_input_data", "expected_output_data"};
	
	public static XSSFWorkbook createXSSFWorkbook() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("sheetTest");
		addHeaderToSheet(sheet, headerTest);
		return workbook;
	}
	
	public static void addSheetToWorbook(XSSFWorkbook wb , String sheetName) {
		XSSFSheet sheet = wb.createSheet(sheetName);
		addHeaderToSheet(sheet, headerTest);
	}
	
	public static void addHeaderToSheet(XSSFSheet sheet , String [] header) {
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < header.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(header[i]);
		}
	}
}
