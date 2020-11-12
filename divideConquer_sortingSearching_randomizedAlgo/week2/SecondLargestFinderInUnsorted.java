package week2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
	 * Max comparisons - 2n
	 * 
	 * @param arr - Input array
	 * 
	 * @return - Second largest element in an array
	 */
	public static int findSecondLargestInUnsortedArraySlow(int[] arr) {
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

	/*
	 * Method to find second largest element in an array.
	 * Running time complexity - O(n)
	 * Max comparisons - n + log_2(n) - 2
	 * Assumption - Input array length is power of 2
	 * Inspired from - 'https://stackoverflow.com/questions/9889679/
	 * find-second-largest-number-in-array-at-most-nlog%E2%82%82n%E2%88%922-comparisons'
	 * 
	 * @param arr - Input array
	 * 
	 * @return - Second largest element in an array
	 */
	public static int findSecondLargestInUnsortedArrayFast(int[] arr) {
		if (arr.length <= 1)
			return Integer.MIN_VALUE;
		List<List<Integer>> listOfList = new ArrayList<>();
		List<Integer> minIndexList = null;
		// List from 0 to array length - 1
		List<Integer> maxIndexList = IntStream.rangeClosed(0, arr.length - 1).boxed().collect(Collectors.toList());

		// n/2 + n/4 + n/8 + ... 1 = n - 1 comparisons
		int maxIndexListSize = maxIndexList.size();
		while (maxIndexListSize > 1) {
			minIndexList = new ArrayList<>(maxIndexListSize / 2);
			List<Integer> currMaxIndexList = new ArrayList<>(maxIndexListSize / 2);
			for (int i = 0; i < maxIndexList.size(); i = i + 2) {
				if (arr[maxIndexList.get(i)] < arr[maxIndexList.get(i + 1)]) {
					minIndexList.add(maxIndexList.get(i));
					currMaxIndexList.add(maxIndexList.get(i + 1));
				} else {
					minIndexList.add(maxIndexList.get(i + 1));
					currMaxIndexList.add(maxIndexList.get(i));
				}
			}
			listOfList.add(minIndexList);
			maxIndexList = currMaxIndexList;
			maxIndexListSize = maxIndexList.size();
		}

		int maxElementIndex = maxIndexList.get(0);
		// First list in listOflist is half the size of original array
		// So dividing maxElementIndex by 2
		int indexToLook = maxElementIndex / 2;
		// Initialize second maximum element from last minIndexList
		// No need to consider last list from listOfList now :-)
		int secondMaxElement = arr[minIndexList.get(0)];

		// Log_2(n) - 1 comparisons
		for (int i = 0; i < listOfList.size() - 1; i++) {
			int element = arr[listOfList.get(i).get(indexToLook)];
			if (secondMaxElement < element) {
				secondMaxElement = element;
			}
			// Every list in listOfList is half the size of previous list
			// So we will keep dividing indexToLook by 2
			indexToLook = indexToLook / 2;
		}
		return secondMaxElement;
	}

	public static void main(String[] args) {
		int[] arr1 = { 33, 4, 17, 7, 25, 9 };
		// output = 25
		System.out.println(SecondLargestFinderInUnsorted.findSecondLargestInUnsortedArraySlow(arr1));

		int[] arr2 = { 22, 23 };
		// output = 22
		System.out.println(SecondLargestFinderInUnsorted.findSecondLargestInUnsortedArraySlow(arr2));

		int[] arr3 = { 33 };
		// output = Integer.MIN_VALUE
		System.out.println(SecondLargestFinderInUnsorted.findSecondLargestInUnsortedArraySlow(arr3));

		int[] arr4 = { 33, 32, 31, 30 };
		// output = 32
		System.out.println(SecondLargestFinderInUnsorted.findSecondLargestInUnsortedArraySlow(arr4));

		int[] arr5 = { 22, 33 };
		// output = 22
		System.out.println(SecondLargestFinderInUnsorted.findSecondLargestInUnsortedArrayFast(arr5));

		int[] arr6 = { 111, 9, 5, 4, 11, 100, 120, 110 };
		// output = 111
		System.out.println(SecondLargestFinderInUnsorted.findSecondLargestInUnsortedArrayFast(arr6));
	}
}
