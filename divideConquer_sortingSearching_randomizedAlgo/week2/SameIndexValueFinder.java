package week2;

/*
 * You are given a sorted (from smallest to largest) array A of n distinct integers
 * which can be positive, negative, or zero.
 * You want to decide whether or not there is an index i such that A[i] = i.
 * Design the fastest algorithm that you can for solving this problem.
 */
public class SameIndexValueFinder {

	/*
	 * Public interface method to find index i in an array A
	 * such a that i == A[i].
	 * 
	 * Assumptions - Array is sorted from smallest to largest.
	 * 
	 * Keep checking difference between index and value.
	 * If index is less than value, discard right half of an array
	 * and vice versa. If same, then return that index.
	 * At each iterative loop, our array will be half of original size.
	 * If not found, we will return array length.
	 * 
	 * @param arr - Sorted array
	 * 
	 * @return - Index where it equals to value; otherwise array length
	 */
	public static int findSameIndexValuePosition(int[] arr) {
		return SameIndexValueFinder.findSameIndexValuePositionMain(arr, 0, arr.length - 1);
	}

	/*
	 * Helper method to SameIndexValueFinder#findSameIndexValuePosition
	 * 
	 * @param arr - Sorted array
	 * @param i - First index of an array
	 * @param j = Last index of an array
	 * 
	 * @return - index where it equals to value; otherwise array length
	 */
	private static int findSameIndexValuePositionMain(int[] arr, int i, int j) {
		while (i <= j) {
			int m = i + (j - i) / 2;
			if (m == arr[m])
				return m;
			else if (m < arr[m])
				j = m - 1;
			else
				i = m + 1;
		}
		return arr.length;
	}

	public static void main(String[] args) {
		int[] arr1 = { -4, -1, 0, 1, 3, 5, 10, 14 };
		// output = 5
		System.out.println(SameIndexValueFinder.findSameIndexValuePosition(arr1));

		int[] arr2 = { -4, -1, 0, 1, 3, 8, 10, 14 };
		// output = 8 (array length)
		System.out.println(SameIndexValueFinder.findSameIndexValuePosition(arr2));

		int[] arr3 = { 0 };
		// output = 0
		System.out.println(SameIndexValueFinder.findSameIndexValuePosition(arr3));

		int[] arr4 = { 14 };
		// output = 1 (array length)
		System.out.println(SameIndexValueFinder.findSameIndexValuePosition(arr4));
	}
}
