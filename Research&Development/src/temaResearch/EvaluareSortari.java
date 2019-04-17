package temaResearch;

import java.io.File;
import java.io.IOException;
//import java.util.Arrays;
import java.util.Random;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class EvaluareSortari {
	static Random random = new Random();
	// int n;
	static int max = 10000;
	static int m = 10;
	static int[] best;
	static int[] worst;
	static int[][] avg;
	static long operatii;
	static long sumaOpAvg = 0;
	static long[] countBest = new long[max / 100];
	static long[] countAvg = new long[max / 100];
	static long[] countWorst = new long[max / 100];
	static long[] bubbleBest = new long[max / 100];
	static long[] bubbleAvg = new long[max / 100];
	static long[] bubbleWorst = new long[max / 100];
	static long[] heapBest = new long[max / 100];
	static long[] heapAvg = new long[max / 100];
	static long[] heapWorst = new long[max / 100];

	public static void generare(int n) { // metoda cu care generez 3 array, cate 1 pt fiecare scenariu
		best = new int[n];
		for (int i = 0; i < n; i++) {
			best[i] = i + 1;
		}
		worst = new int[n];
		for (int i = 0; i < n; i++) {
			worst[i] = n - i;
		}
		avg = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				avg[i][j] = random.nextInt(n);
			}
		}

	}

	public static void main(String[] args) throws IOException, RowsExceededException, WriteException {
		/*
		 * generare(50); // System.out.println(Arrays.toString(best));
		 * System.out.println(Arrays.toString(worst)); // for (int i = 0; i < m; i++) {
		 * // System.out.println(Arrays.toString(avg[i])); // }
		 * 
		 * Metode.bubbleSort(worst); System.out.println(Arrays.toString(worst));
		 * System.out.println(a); System.out.println(c);
		 * System.out.println("s-au efectuat " + (a + c) + " operatii");
		 * 
		 * // Metode.countSort(worst); // System.out.println(Arrays.toString(worst)); //
		 * Metode.heapSort(worst); // System.out.println(Arrays.toString(worst));
		 */
		for (int n = 100; n <= max; n += 100) {
			// la fiecare iteratie generez array-le de dimensiune n cu incrementare de 100
			// si pt fiecare scenariu le sortez cu fiecare dintre cele 3 metode
			// fiecare sortare returneaza numarul de pasi efectuati pt sortare
			// acest nr il memorez intr-un array specific metodei si scenariului de sortare
			// exp. countBest[] pt sortarea cu CountSort in scenariu Best
			generare(n);

			Metode.countSort(best);
			countBest[n / 100 - 1] = operatii;
			for (int i = 0; i < m; i++) {
				Metode.countSort(avg[i]);
				sumaOpAvg += operatii;
			}
			countAvg[n / 100 - 1] = sumaOpAvg / m;
			sumaOpAvg = 0L;
			Metode.countSort(worst);
			countWorst[n / 100 - 1] = operatii;

			Metode.bubbleSort(best);
			bubbleBest[n / 100 - 1] = operatii;
			for (int i = 0; i < m; i++) {
				Metode.bubbleSort(avg[i]);
				sumaOpAvg += operatii;
			}
			bubbleAvg[n / 100 - 1] = sumaOpAvg / m;
			sumaOpAvg = 0L;
			Metode.bubbleSort(worst);
			bubbleWorst[n / 100 - 1] = operatii;

			Metode.heapSort(best);
			heapBest[n / 100 - 1] = operatii;
			for (int i = 0; i < m; i++) {
				Metode.heapSort(avg[i]);
				sumaOpAvg += operatii;
			}
			heapAvg[n / 100 - 1] = sumaOpAvg / m;
			sumaOpAvg = 0L;
			Metode.heapSort(worst);
			heapWorst[n / 100 - 1] = operatii;

			System.out.println(n);

		}
		/*
		 * System.out.println(Arrays.toString(countBest));
		 * System.out.println(Arrays.toString(countAvg));
		 * System.out.println(Arrays.toString(countWorst));
		 * System.out.println(Arrays.toString(bubbleBest));
		 * System.out.println(Arrays.toString(bubbleAvg));
		 * System.out.println(Arrays.toString(bubbleWorst));
		 * System.out.println(Arrays.toString(heapBest));
		 * System.out.println(Arrays.toString(heapAvg));
		 * System.out.println(Arrays.toString(heapWorst));
		 */

		/*
		 * System.out.println(Arrays.toString(best)); for (int i = 0; i < m; i++)
		 * System.out.println(Arrays.toString(avg[i]));
		 * System.out.println(Arrays.toString(worst));
		 */

		File f = new File("C:\\Users\\pc\\Desktop\\exel.xls");
		WritableWorkbook myExel = Workbook.createWorkbook(f);
		WritableSheet mySheet = myExel.createSheet("mySheet", 0);
		Label txt = new Label(0, 0, "N");
		mySheet.addCell(txt);
		txt = new Label(2, 0, "countBest");
		mySheet.addCell(txt);
		txt = new Label(3, 0, "countAvg");
		mySheet.addCell(txt);
		txt = new Label(4, 0, "countWorst");
		mySheet.addCell(txt);
		txt = new Label(6, 0, "bubbleBest");
		mySheet.addCell(txt);
		txt = new Label(7, 0, "bubbleAvg");
		mySheet.addCell(txt);
		txt = new Label(8, 0, "bubbleWorst");
		mySheet.addCell(txt);
		txt = new Label(10, 0, "heapBest");
		mySheet.addCell(txt);
		txt = new Label(11, 0, "heapAvg");
		mySheet.addCell(txt);
		txt = new Label(12, 0, "heapWorst");
		mySheet.addCell(txt);

		for (int i = 0; i < max / 100; i++) {
			Number n = new Number(2, i + 2, countBest[i]);
			mySheet.addCell(n);
		}
		for (int i = 0; i < max / 100; i++) {
			Number n = new Number(3, i + 2, countAvg[i]);
			mySheet.addCell(n);
		}
		for (int i = 0; i < max / 100; i++) {
			Number n = new Number(4, i + 2, countWorst[i]);
			mySheet.addCell(n);
		}
		for (int i = 0; i < max / 100; i++) {
			Number n = new Number(6, i + 2, bubbleBest[i]);
			mySheet.addCell(n);
		}
		for (int i = 0; i < max / 100; i++) {
			Number n = new Number(7, i + 2, bubbleAvg[i]);
			mySheet.addCell(n);
		}
		for (int i = 0; i < max / 100; i++) {
			Number n = new Number(8, i + 2, bubbleWorst[i]);
			mySheet.addCell(n);
		}
		for (int i = 0; i < max / 100; i++) {
			Number n = new Number(10, i + 2, heapBest[i]);
			mySheet.addCell(n);
		}
		for (int i = 0; i < max / 100; i++) {
			Number n = new Number(11, i + 2, heapAvg[i]);
			mySheet.addCell(n);
		}
		for (int i = 0; i < max / 100; i++) {
			Number n = new Number(12, i + 2, heapWorst[i]);
			mySheet.addCell(n);
		}
		myExel.write();
		myExel.close();

		System.out.println("exel generat cu succes ...\\descktop\\exel.xls");

	}

}
