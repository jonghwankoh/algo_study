package example;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {
	
	public static int[] mergeSort(int[] arr) {
		int mid = arr.length / 2;
		int[] a = Arrays.copyOfRange(arr, 0, mid);
		int[] b = Arrays.copyOfRange(arr, mid, arr.length);

		return merge(mergeSort(a), mergeSort(b));
	}

	private static int[] merge(int[] a, int[] b) {

		return null;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine(); // 한 줄 입력
		String[] tokens = line.split(" ");

		int[] sorted = Arrays.stream(tokens)
			.mapToInt(Integer::parseInt)
			.toArray();

		sorted = mergeSort(sorted);

		System.out.println(Arrays.toString(sorted));
		sc.close();
	}
}
