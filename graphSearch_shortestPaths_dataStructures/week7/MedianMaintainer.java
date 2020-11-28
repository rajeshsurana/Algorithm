package week7;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

import utils.DataLoader;

/**
 * 
 * @author rajesh.surana
 *
 *         The goal of this problem is to implement the "Median Maintenance"
 *         algorithm. The text file contains a list of the integers from 1 to
 *         10000 in unsorted order; you should treat this as a stream of
 *         numbers, arriving one by one.
 */
public class MedianMaintainer {
	private Queue<Integer> lowMax = new PriorityQueue<>(Collections.reverseOrder());
	private Queue<Integer> highMin = new PriorityQueue<>();

	/**
	 * Inserts the number into median maintainer.
	 * 
	 * @param x - Number to be inserted
	 */
	public void insert(int x) {
		if (lowMax.peek() == null || lowMax.peek() > x) {
			lowMax.add(x);
		} else {
			highMin.add(x);
		}
		this.balanceQueues();
	}

	/**
	 * Balances the low and high priority queues once number is inserted.
	 */
	private void balanceQueues() {
		int lowSize = this.lowMax.size();
		int highSize = this.highMin.size();
		if (lowSize > 0 && lowSize > highSize - 1) {
			Integer removed = this.lowMax.remove();
			this.highMin.add(removed);
		} else if (highSize > 0 && highSize > lowSize - 1) {
			Integer removed = this.highMin.remove();
			this.lowMax.add(removed);
		}
	}

	/**
	 * Calculates and returns the median of the numbers
	 * 
	 * @return The median of numbers inserted so far
	 */
	public Integer getMedian() {
		int lowSize = this.lowMax.size();
		int highSize = this.highMin.size();
		if (lowSize < highSize) {
			return this.highMin.peek();
		} else {
			return this.lowMax.peek();
		}
	}

	/**
	 * Calculates sum of medians after each insertion to DS
	 * maintaining last 4 digits only.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = DataLoader.loadArray("data/Median.txt");
		MedianMaintainer mm = new MedianMaintainer();
		int sum = 0;
		for (int x : arr) {
			mm.insert(x);
			sum = (sum + mm.getMedian()) % 10000;
		}
		System.out.println("Sum of medians - " + sum);
	}
}
