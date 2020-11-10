package week2;

/*
 * You are given a unimodal array of n distinct elements, meaning
 * that its entries are in increasing order up until its maximum element,
 * after which its elements are in decreasing order.
 * Give an algorithm to compute the maximum element that runs in O(log n) time.
 */
public class MaxFinderInUnimodalArray {

	/*
	 * Public interface method to find maximum element in a unimodal array.
	 * Running time complexity - O(log(n))
	 * 
	 * @param arr - Input array
	 *
	 * @return - Maximum element; otherwise minimum integer value possible
	 */
	public static int findMaxInUnimodalArray(int[] arr) {
		return MaxFinderInUnimodalArray.findMaxInUnimodalArrayMain(arr, 0, arr.length - 1);
	}

	/*
	 * Main method to find maximum element in a unimodal array.
	 * 
	 * @param arr - Input array
	 * @param i - First index of an array
	 * @param j - Last index of an array
	 * 
	 * @return - Maximum element; otherwise minimum integer value possible
	 */
	private static int findMaxInUnimodalArrayMain(int[] arr, int i, int j) {
		if (arr.length == 1)
			return arr[0];

		while (i <= j) {
			int m = i + (j - i) / 2;
			if ((m == 0 && arr[m] > arr[m + 1]) || (m == arr.length - 1 && arr[m - 1] < arr[m])
					|| (arr[m - 1] < arr[m] && arr[m] > arr[m + 1]))
				return arr[m];
			else if ((m == 0 && arr[m] < arr[m + 1]) || (arr[m - 1] < arr[m] && arr[m] < arr[m + 1]))
				i = m + 1;
			else
				j = m - 1;
		}
		return Integer.MIN_VALUE;
	}

	public static void main(String[] args) {
		int[] arr1 = { 1, 2, 3, 2, 1 };
		// output = 3
		System.out.println(MaxFinderInUnimodalArray.findMaxInUnimodalArray(arr1));

		int[] arr2 = { 1, 2, 3, 4, 5 };
		// output = 5
		System.out.println(MaxFinderInUnimodalArray.findMaxInUnimodalArray(arr2));

		int[] arr3 = { 5, 4, 3, 2, 1 };
		// output = 5
		System.out.println(MaxFinderInUnimodalArray.findMaxInUnimodalArray(arr3));

		int[] arr4 = { 1 };
		// output = 1
		System.out.println(MaxFinderInUnimodalArray.findMaxInUnimodalArray(arr4));
	}
}
