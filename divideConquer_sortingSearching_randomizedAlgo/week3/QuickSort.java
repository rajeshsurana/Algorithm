package week3;

import java.util.Arrays;

import utils.DataLoader;

/*
 * Count the number of comparisons during quick sort. If m is the length of an array
 * then m-1 comparisons will happen as pivot is compared with rest of the elements.
 * Average case running time complexity - O(n log(n))
 * Worst case running time complexity - O(n^2)
 * Running time complexity depends on how good the pivot is selected.
 * I found 'median of three' pivot selection produced the near merge sort results.
 */
public class QuickSort {
	/*
	 * Public interface method to sort array using quick sort and corresponding
	 * pivot selection logic. Array is sorted in place. It returns the count
	 * of comparisons.
	 * 
	 * @param arr - Input array to be sorted
	 * @param pivotLogic - Logic to select pivot from array
	 * 
	 * @return - Number of comparisons
	 */
	public static long sort(int[] arr, String pivotLogic) {
		return QuickSort.sortMain(arr, 0, arr.length - 1, pivotLogic);
	}

	/*
	 * Main method doing quick sort and counting comparisons.
	 * 
	 * @param arr - Input array
	 * @param l - First element to consider from array
	 * @param r - Last element to consider from array
	 * @param pivotLogic - Logic to select pivot from array
	 * 
	 * @return - Number of comparisons
	 */
	private static long sortMain(int[] arr, int l, int r, String pivotLogic) {
		if (l >= r)
			return 0L;
		long currComparisons = r - l;
		// Choose pivot
		int pivot = QuickSort.getPivot(arr, l, r, pivotLogic);

		// Partition
		int pivotIndex = QuickSort.partition(arr, pivot, l, r);

		// 2 recursive calls for left and right partitions
		long leftComparisons = QuickSort.sortMain(arr, l, pivotIndex - 1, pivotLogic);
		long rightComparisons = QuickSort.sortMain(arr, pivotIndex + 1, r, pivotLogic);

		// Final comparison count
		return currComparisons + leftComparisons + rightComparisons;
	}

	/*
	 * Helper method to partition given array once pivot is chosen.
	 * 
	 * @param arr - Input array
	 * @param pivot - Selected pivot value
	 * @param l - First element to consider from array
	 * @param r - Last element to consider from array
	 * 
	 * @return - Pivot index where pivot is placed at the end
	 */
	private static int partition(int[] arr, int pivot, int l, int r) {
		// i to keep track of partition
		int i = l + 1;
		// j to traverse through an array
		int j = i;
		for (; j <= r; j++) {
			if (pivot > arr[j]) {
				swap(arr, i, j);
				i++;
			}
		}
		// Replace pivot with first element to place it at appropriate
		// location in an array
		QuickSort.swap(arr, l, i - 1);
		return i - 1;
	}

	/*
	 * Helper method to get pivot based on fist, last and median of three logic
	 * 
	 * @param arr - Input array
	 * @param l - First element to consider from array
	 * @param r - Last element to consider from array
	 * @param pivotLogic - Logic to select pivot from array
	 * 
	 * @return - pivot based on logic
	 */
	private static int getPivot(int[] arr, int l, int r, String pivotLogic) {
		int pivot;
		switch (pivotLogic) {
		case "first":
			pivot = QuickSort.getFirstPivot(arr, l, r);
			break;
		case "last":
			pivot = QuickSort.getLastPivot(arr, l, r);
			break;
		case "median":
		default:
			pivot = QuickSort.getMedianOfThreePivot(arr, l, r);
		}
		return pivot;
	}

	/*
	 * Helper method to swap elements at position i and j
	 * in an array
	 * 
	 * @param arr - Input array
	 * @param i - Fist element index to swap
	 * @param j - Second element index to swap
	 */
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/*
	 * Helper method to get first element as pivot from an array
	 */
	private static int getFirstPivot(int[] arr, int l, int r) {
		return arr[l];
	}

	/*
	 * Helper method to get last element as pivot from an array
	 */
	private static int getLastPivot(int[] arr, int l, int r) {
		QuickSort.swap(arr, l, r);
		return arr[l];
	}

	/*
	 * Helper method to get median of 3 elements as pivot from an array.
	 * Median will be taken out of first, middle and last element
	 */
	private static int getMedianOfThreePivot(int[] arr, int l, int r) {
		int selectedPivotIndex;
		int middleIndex = l + (r - l) / 2;
		if (arr[l] > arr[middleIndex]) {
			if (arr[middleIndex] > arr[r]) {
				selectedPivotIndex = middleIndex;
			} else if (arr[l] > arr[r]) {
				selectedPivotIndex = r;
			} else {
				selectedPivotIndex = l;
			}
		} else {
			if (arr[l] > arr[r]) {
				selectedPivotIndex = l;
			} else if (arr[middleIndex] > arr[r]) {
				selectedPivotIndex = r;
			} else {
				selectedPivotIndex = middleIndex;
			}
		}
		QuickSort.swap(arr, l, selectedPivotIndex);
		return arr[l];
	}

	public static void main(String[] args) {
		int[] arr1 = { 3, 7, 1, 5, 4, 2, 6, 8 };
		// output = 14
		System.out.println(QuickSort.sort(arr1, "first"));
		System.out.println(Arrays.toString(arr1));

		int[] arr2 = { 3, 7, 1, 5, 4, 2, 6, 8 };
		// output = 20
		System.out.println(QuickSort.sort(arr2, "last"));
		System.out.println(Arrays.toString(arr2));

		int[] arr3 = { 3, 7, 1, 5, 4, 2, 6, 8 };
		// output = 13
		System.out.println(QuickSort.sort(arr3, "median"));
		System.out.println(Arrays.toString(arr3));

		// Load array data from file
		int[] arr4 = DataLoader.loadArray("data/QuickSort.txt");
		System.out.println(QuickSort.sort(arr4, "first"));
		int[] arr5 = DataLoader.loadArray("data/QuickSort.txt");
		System.out.println(QuickSort.sort(arr5, "last"));
		int[] arr6 = DataLoader.loadArray("data/QuickSort.txt");
		System.out.println(QuickSort.sort(arr6, "median"));
	}
}
