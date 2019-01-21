package common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DownloadUtils {
	
	private static String EXCEL_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	private static String HEADER_KEY = "Content-Disposition";
	private static String HEADER_VALUE = "attachment;filename=XSSFWorkbookDownloadExample.xlsx";
	
	/** XSSFWorkbook -> inputStream -> outputStream -> server response */
	public static void downloadExcel(XSSFWorkbook data) throws IOException
	{
		FacesContext ct = FacesContext.getCurrentInstance();
		ExternalContext exct = ct.getExternalContext();
		exct.responseReset();
		exct.setResponseContentType(EXCEL_CONTENT_TYPE);
		exct.setResponseHeader(HEADER_KEY, HEADER_VALUE);
		InputStream input = XSSFWorkbook2InputStream(data);
		OutputStream output = exct.getResponseOutputStream();
		
		byte [] buffer = new byte[1024];
		int length;
		
		while ((length = input.read(buffer)) > 0) {
			output.write(buffer, 0, length);
		}
		input.close();
		ct.responseComplete();
	}
	
	/** convert XSSFWorkbook -> inputStream */
	public static InputStream XSSFWorkbook2InputStream(XSSFWorkbook workbook) throws IOException {
		InputStream in;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();	
		workbook.write(baos);

		in = new ByteArrayInputStream(baos.toByteArray());
		baos.close();
		return in;
	}
}
