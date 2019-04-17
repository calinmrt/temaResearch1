package temaResearch;

import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Incercari2 {

	public static void main(String[] args) throws IOException, RowsExceededException, WriteException {
		int a[] = { 1, 5, 20, 50, 120 };

		File f = new File("C:\\Users\\pc\\Desktop\\exel.xls");
		WritableWorkbook myExel = Workbook.createWorkbook(f);
		WritableSheet mySheet = myExel.createSheet("mySheet", 0);
		Label text1 = new Label(0, 1, "N");
		mySheet.addCell(text1);
		Label text2 = new Label(2, 0, "CountSort");
		mySheet.addCell(text2);
		Label text3 = new Label(5, 0, "BubbleSort");
		mySheet.addCell(text3);
		Label text4 = new Label(8, 0, "HeapSort");
		mySheet.addCell(text4);
		text1 = new Label(1, 1, "best");
		mySheet.addCell(text1);
		text1 = new Label(2, 1, "avg");
		mySheet.addCell(text1);
		text1 = new Label(3, 1, "worst");
		mySheet.addCell(text1);
		text1 = new Label(4, 1, "best");
		mySheet.addCell(text1);
		text1 = new Label(5, 1, "avg");
		mySheet.addCell(text1);
		text1 = new Label(6, 1, "worst");
		mySheet.addCell(text1);
		text1 = new Label(7, 1, "best");
		mySheet.addCell(text1);
		text1 = new Label(8, 1, "avg");
		mySheet.addCell(text1);
		text1 = new Label(9, 1, "worst");
		mySheet.addCell(text1);

		for (int x = 1; x < 10; x++) {
			for (int i = 0; i < a.length; i++) {
				Number n = new Number(x, i + 4, a[i]);

				mySheet.addCell(n);
			}
		}

		myExel.write();
		myExel.close();

	}

}
