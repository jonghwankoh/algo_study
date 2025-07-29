package example;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {
	
	
	private static <T extends Comparable<T>> T[] mergeSort(T[] arr) {
		if (arr.length <= 1) {
			return arr;
		}
		
		int mid = arr.length / 2;
		T[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
		T[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
		
		return merge(left, right);
	}
	
	private static <T extends Comparable<T>> T[] merge(T[] left, T[] right) {
		ArrayList<T> result = new ArrayList<>();
		
		int i = 0, j = 0;
		
		while (i < left.length && j < right.length) {
			if (left[i].compareTo(right[j]) != -1) {
				result.add(left[i]);
				i++;
			} else {
				result.add(right[j]);
				j++;
			}
		}
		
		while (i < left.length) {
			result.add(left[i]);
			i++;
		}
		
		while (i < left.length) {
			result.add(left[i]);
			i++;
		}
		
		return null; // TODO
	}
}
