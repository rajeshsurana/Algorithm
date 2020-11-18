package week4;

import java.util.Arrays;

/*
 * A QuickSort variant to get nth biggest number in an array
 * Array elements are unsorted and order could be of max array length
 * Running time complexity - O(n) (Its contrary to what code looks like O(n^2))
 * 
 * @param iArr - Input array
 * @param order - order position biggest element in an array
 * 
 * @return - nth largest element from an input array
 */
public class NthLargestFinder {
	/*
	 * Public interface method to return nth biggest element from input array.
	 * 
	 * @param arr - Input array to be sorted
	 * @param pivotLogic - Logic to select pivot from array
	 * 
	 * @return - Number of comparisons
	 */
	public static int findNthLargestNumber(int[] iArr, int order) {
		if (order < 1 || iArr.length < order) {
			throw new IllegalArgumentException("Order can not be outside of 1 and " + (iArr.length));
		}
		int[] arr = Arrays.copyOf(iArr, iArr.length);
		// Pass order as zero based for easy calculation
		return NthLargestFinder.findNthLargestNumberMain(arr, 0, arr.length - 1, order - 1);
	}

	/*
	 * Main method to find biggest element of order - order
	 * 
	 * @param arr - Input array
	 * @param l - First element to consider from array
	 * @param r - Last element to consider from array
	 * @param order - Order of which biggest element should be returned 
	 * 
	 * @return - nth biggest element of order - order
	 */
	private static int findNthLargestNumberMain(int[] arr, int l, int r, int order) {
		while (l <= r) {
			int initialPivotIndex = l + (int) (Math.random() * (r - l));
			int pivot = arr[initialPivotIndex];
			// Swap with first index
			NthLargestFinder.swap(arr, l, initialPivotIndex);
			// i to track separation
			int i = l + 1;
			// j to track current index of processing
			int j = i;
			for (; j <= r; j++) {
				if (arr[j] < pivot) {
					NthLargestFinder.swap(arr, i, j);
					i++;
				}
			}
			// Put pivot to its correct place
			NthLargestFinder.swap(arr, l, i - 1);
			if (order == i - 1) {
				return arr[i - 1];
			}
			if (i - 1 > order) {
				r = i - 2;
			} else {
				l = i;
			}
		}
		return arr[order];
	}

	/*
	 * Helper method to swap elements at position i and j in an array
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

	public static void main(String[] args) {
		int[] arr1 = { 0, 1, 2, 3 };
		int order1 = 2;
		System.out.println("Array: " + Arrays.toString(arr1));
		System.out.println("Order: " + order1);
		// output = 1
		System.out.println(NthLargestFinder.findNthLargestNumber(arr1, order1));

		int[] arr2 = { 5 };
		int order2 = 1;
		System.out.println("Array: " + Arrays.toString(arr2));
		System.out.println("Order: " + order2);
		// output = 5
		System.out.println(NthLargestFinder.findNthLargestNumber(arr2, order2));

		int[] arr3 = { 1, 3, 5, 2, 4, 6 };
		int order3 = 6;
		System.out.println("Array: " + Arrays.toString(arr3));
		System.out.println("Order: " + order3);
		// output = 6
		System.out.println(NthLargestFinder.findNthLargestNumber(arr3, order3));
	}
}
