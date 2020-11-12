package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Data loader class to load data from various files
 * into appropriate data structures
 */
public class DataLoader {
	/*
	 * Helper method to load array from file
	 * 
	 * @param filename - File name which contains integers
	 */
	public static int[] loadArray(String filename) {
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
}
