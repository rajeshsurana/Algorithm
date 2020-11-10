package week2;

/*
 * You are given as input an unsorted array of n distinct numbers,
 * where n is a power of 2. Give an algorithm that identifies
 * the second-largest number in the array, and that uses at most
 * n + log_2 n - 2 comparisons.
 */
public class SecondLargestFinderInUnsorted {

	/*
	 * Method to find second largest element in an array.
	 * Running time complexity - O(n)
	 * 
	 * @param arr - Input array
	 * 
	 * @return - Second largest element in an array
	 */
	public static int findSecondLargestInUnsortedArray(int[] arr) {
		if (arr.length <= 1)
			return Integer.MIN_VALUE;

		int first = Math.max(arr[0], arr[1]);
		int second = Math.min(arr[0], arr[1]);

		for (int i = 2; i < arr.length - 1; i++) {
			if (arr[i] > first) {
				second = first;
				first = arr[i];
			} else if (arr[i] > second) {
				second = arr[i];
			}
		}
		return second;
	}

	public static void main(String[] args) {
		int[] arr1 = { 33, 4, 17, 7, 25, 9 };
		// output = 25
		System.out.println(SecondLargestFinderInUnsorted.findSecondLargestInUnsortedArray(arr1));

		int[] arr2 = { 22, 23 };
		// output = 23
		System.out.println(SecondLargestFinderInUnsorted.findSecondLargestInUnsortedArray(arr2));

		int[] arr3 = { 33 };
		// output = Integer.MIN_VALUE
		System.out.println(SecondLargestFinderInUnsorted.findSecondLargestInUnsortedArray(arr3));

		int[] arr4 = { 33, 32, 31, 30 };
		// output = 32
		System.out.println(SecondLargestFinderInUnsorted.findSecondLargestInUnsortedArray(arr4));
	}
}
