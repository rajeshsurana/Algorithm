package week2;

import java.util.Arrays;

public class CountInversions {
	/*
	 * Public interface method to count inversions
	 * 
	 * @param iArr - input array
	 * 
	 * @return - inversion count
	 */
	public static int countInversions(int[] iArr) {
		int length = iArr.length;
		if (length <= 1)
			return 0;
		int[] arr = Arrays.copyOf(iArr, length);
		return CountInversions.countInversions(arr, 0, length - 1);
	}

	/*
	 * Main method which recursively computes inversions using
	 * divide and conquer strategy
	 * 
	 * @param arr - input array
	 * @param i - start index in an array
	 * @param j - end index in an array
	 * 
	 * @return - inversion count
	 */
	private static int countInversions(int[] arr, int i, int j) {
		if (i >= j)
			return 0;
		int m = i + (j - i) / 2;
		// Count inversions in first half of an array
		int left = CountInversions.countInversions(arr, i, m);
		// Count inversions in second half of an array
		int right = CountInversions.countInversions(arr, m + 1, j);
		// Add previous counts plus inversions during merge
		return left + right + CountInversions.countSplitInverse(arr, i, j);
	}

	/*
	 * Helper method to merge two sorted arrays as well as count inversions
	 * 
	 * @param arr - input array
	 * @param i - start index in an array
	 * @param j - end index in an array
	 * 
	 * @return - inverse count while merging two halves of array
	 */
	private static int countSplitInverse(int[] arr, int i, int j) {
		int inversions = 0;
		int start = i;
		int m = i + (j - i) / 2;
		int k = m + 1;
		int[] temp = new int[j - i + 1];
		int iTemp = 0;
		while (i <= m && k <= j) {
			if (arr[i] <= arr[k]) {
				temp[iTemp] = arr[i];
				i++;
			} else {
				// If any element b from second array is lesser than
				// any element a from first array, then we have inversions
				// equals to rest of the elements in first array
				inversions = inversions + m - i + 1;
				temp[iTemp] = arr[k];
				k++;
			}
			iTemp++;
		}
		// Now copy rest of the elements from either of the array
		while (i <= m) {
			temp[iTemp] = arr[i];
			i++;
			iTemp++;
		}
		while (k <= j) {
			temp[iTemp] = arr[k];
			k++;
			iTemp++;
		}
		// Copy back sorted temporary array into original array
		for (iTemp = 0, i = start; iTemp < temp.length; iTemp++, i++) {
			arr[i] = temp[iTemp];
		}
		return inversions;
	}

	public static void main(String[] args) {
		int[] arr1 = { 5, 4, 3, 2, 1 };
		// output = 10
		System.out.println(CountInversions.countInversions(arr1));
		int[] arr2 = { 1, 3, 5, 2, 4, 6 };
		// output = 3
		System.out.println(CountInversions.countInversions(arr2));
		int[] arr3 = { 1, 2, 3, 4, 5, 6 };
		// output = 0
		System.out.println(CountInversions.countInversions(arr3));
		int[] arr4 = { 1 };
		// output = 0
		System.out.println(CountInversions.countInversions(arr4));
	}
}
