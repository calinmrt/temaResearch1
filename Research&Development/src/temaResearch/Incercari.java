package temaResearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Incercari {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");
		Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
		data.put("1", new Object[] { "Year", "WinnerCountryName", "LosserCountryName", "By" });
		data.put("2", new Object[] { "1975", "WestInddies", "Australia", "17 Run" });
		data.put("3", new Object[] { "1979", "WestInddies", "England", "92 Run" });
		data.put("4", new Object[] { "1983", "India", "WestIndies", "43 Run" });
		data.put("5", new Object[] { "1987", "Australia", "England", "7 Run" });
		data.put("6", new Object[] { "1992", "Pakistan", "England", "22 Run" });
		data.put("7", new Object[] { "1996", "Sri Lanka", "Australia", "7 Wicket" });
		data.put("8", new Object[] { "1999", "Australia", "Pakistan", "8 Wicket" });
		data.put("9", new Object[] { "2003", "Australia", "India", "125 Run" });
		data.put("10", new Object[] { "2007", "Australia", "Srilanka", "53 Run" });
		data.put("11", new Object[] { "2011", "India", "Srilanka", "6 Wicket" });
		data.put("12", new Object[] { "2015", "Australia", "New Zeland", "7 Wicket" });
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof Date) {
					cell.setCellValue((Date) obj);
				} else if (obj instanceof Boolean) {
					cell.setCellValue((Boolean) obj);
				} else if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else if (obj instanceof Double) {
					cell.setCellValue((Double) obj);
				}
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(
					new File("C:\\Users\\pc\\Desktop\\iccworldcuplistwrite.xls"));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
