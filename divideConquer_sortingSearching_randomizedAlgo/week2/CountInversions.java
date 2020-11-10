package week2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * Given an array A, compute the number of inversions = number of pairs
 * {i, j} such a that A[i] > A[j] and i < j.
 */
public class CountInversions {
	/*
	 * Public interface method to count inversions
	 * 
	 * @param iArr - input array
	 * 
	 * @return - inversion count
	 */
	public static long countInversions(int[] iArr) {
		int length = iArr.length;
		if (length <= 1)
			return 0;
		int[] arr = Arrays.copyOf(iArr, length);
		return CountInversions.countInversions(arr, 0, length - 1);
	}

	/*
	 * Main method which recursively computes inversions using
	 * divide and conquer strategy
	 * Running time complexity - O(n log(n))
	 * 
	 * @param arr - input array
	 * @param i - start index in an array
	 * @param j - end index in an array
	 * 
	 * @return - inversion count
	 */
	private static long countInversions(int[] arr, int i, int j) {
		if (i >= j)
			return 0;
		int m = i + (j - i) / 2;
		// Count inversions in first half of an array
		long left = CountInversions.countInversions(arr, i, m);
		// Count inversions in second half of an array
		long right = CountInversions.countInversions(arr, m + 1, j);
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
	private static long countSplitInverse(int[] arr, int i, int j) {
		long inversions = 0;
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

	/*
	 * Helper method to load array from file
	 * 
	 * @param filename - File name which contains integers
	 */
	private static int[] loadArray(String filename) {
		List<Integer> list = new ArrayList<Integer>();
		Scanner sc;
		try {
			sc = new Scanner(new File(filename));
		} catch (Exception e) {
			e.printStackTrace();
			return new int[0];
		}
		while (sc.hasNextInt()) {
			list.add(sc.nextInt());
		}
		return list.stream().mapToInt(i -> i).toArray();
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
		// Load array data from file
		int[] arr5 = CountInversions.loadArray("data/IntegerArray.txt");
		System.out.println(CountInversions.countInversions(arr5));
	}
}
