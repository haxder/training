package common;

import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.DateFormatConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import thinhtv.training.entity.Movie;


public class ExcelUtils {
	private static String[] MOVIE_HEADER = {"Genre", "Title", "Release Date", "Price", "Rate"};
	private static String SHEET_NAME = "movies";
	
	public static XSSFWorkbook createXSSFWorkbook(List<Movie> movies) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		CellStyle cellStyle =  configCellStyle(workbook);
		XSSFSheet sheet = workbook.createSheet(SHEET_NAME);
		addHeaderToSheet(sheet, MOVIE_HEADER);
		addDataToSheet(sheet, cellStyle, movies);
		return workbook;
	}
	
	public static void addHeaderToSheet(XSSFSheet sheet , String [] header) {
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < header.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(header[i]);
		}
	}
	
	public static void addDataToSheet(XSSFSheet sheet ,CellStyle cellStyle, List<Movie> movies) {

		int rowCount = 1;   //  header la  0
		for (Movie mv : movies) {
			Row row = sheet.createRow(rowCount++);
			if(mv.getGenre() != null) {
				row.createCell(0).setCellValue(mv.getGenre());
			}
			if(mv.getTitle() != null){
				row.createCell(1).setCellValue(mv.getTitle());
			}
			if(mv.getReleaseDate() !=null) {
				Cell cell =  row.createCell(2);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(mv.getReleaseDate());
			}
			if (mv.getPrice() != null) {
				row.createCell(3).setCellValue(mv.getPrice());
			}
			row.createCell(4).setCellValue(mv.getRate());
		}
	}
	
	public static CellStyle configCellStyle (XSSFWorkbook workbook) {
		 String excelFormatPattern = DateFormatConverter.convert(Locale.ENGLISH, "yyyy-MM-dd");
		 CellStyle cellStyle = workbook.createCellStyle();
		 DataFormat poiFormat = workbook.createDataFormat();
	     cellStyle.setDataFormat(poiFormat.getFormat(excelFormatPattern));
	     return cellStyle;
	}
}
