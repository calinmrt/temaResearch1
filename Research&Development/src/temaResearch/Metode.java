package temaResearch;

import java.util.Arrays;

public class Metode {
	static long ha;
	static long hc;

	static void countSort(int[] arr) {//
		long assignements = 0L;
		long comparisons = 0L;

		int max = Arrays.stream(arr).max().getAsInt();
		assignements++;
		int min = Arrays.stream(arr).min().getAsInt();
		assignements++;
		int range = max - min + 1;
		assignements++;
		int count[] = new int[range];
		assignements++;
		int output[] = new int[arr.length];
		assignements++;
		for (int i = 0; i < arr.length; i++) {
			assignements++;
			comparisons++;
			count[arr[i] - min]++;
			assignements++;
		}

		for (int i = 1; i < count.length; i++) {
			assignements++;
			comparisons++;
			count[i] += count[i - 1];
			assignements++;

		}

		for (int i = arr.length - 1; i >= 0; i--) {
			assignements++;
			comparisons++;
			output[count[arr[i] - min] - 1] = arr[i];
			count[arr[i] - min]--;
			assignements += 2;
		}
		EvaluareSortari.operatii = assignements + comparisons;

	}

	static void bubbleSort(int arr[]) {
		int output[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++)
			output[i] = arr[i];
		int assignements = 0;
		int comparisons = 0;

		int x = output.length;
		assignements += 2;
		for (int i = 0; i < x - 1; i++) {
			assignements++;
			comparisons++;
			for (int j = 0; j < x - i - 1; j++) {
				assignements++;
				comparisons++;
				if (output[j] > output[j + 1]) {
					comparisons++;
					int temp = output[j];
					output[j] = output[j + 1];
					output[j + 1] = temp;
					assignements += 3;
				}
			}
		}
		
		EvaluareSortari.operatii = assignements + comparisons;

	}

	static void heapSort(int arr[]) {
		int output[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++)
			output[i] = arr[i];
		int assignements = 0;
		int comparisons = 0;

		int n = arr.length;
		assignements++;

		for (int i = n / 2 - 1; i >= 0; i--) {
			assignements++;
			comparisons++;
			heapify(output, n, i);
		}

		for (int i = n - 1; i >= 0; i--) {

			assignements++;
			comparisons++;
			int temp = output[0];
			output[0] = output[i];
			output[i] = temp;
			assignements += 3;

			heapify(output, i, 0);
		}

		assignements += ha;
		comparisons += hc;
		// System.out.println(Arrays.toString(output));
		// System.out.println(assignements);
		// System.out.println(comparisons);
		EvaluareSortari.operatii = assignements + comparisons;
		ha = 0L;
		hc = 0L;

	}

	static void heapify(int output[], int n, int i) {

		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		ha += 3;

		if (l < n && output[l] > output[largest]) {
			hc += 3;
			largest = l;
			ha++;
		}

		if (r < n && output[r] > output[largest]) {
			hc += 3;
			largest = r;
			ha++;
		}

		if (largest != i) {
			hc++;
			int swap = output[i];
			output[i] = output[largest];
			output[largest] = swap;
			ha += 3;

			heapify(output, n, largest);
		}

	}

}
