package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * Data loader class to load data from various files
 * into appropriate data structures
 */
public class DataLoader {
	/*
	 * Helper method to load array from file
	 * 
	 * @param filename - File name which contains integers
	 * 
	 * @return - integer array
	 */
	public static int[] loadArray(String filename) {
		Scanner sc;
		try {
			sc = new Scanner(new File(filename));
		} catch (Exception e) {
			e.printStackTrace();
			return new int[0];
		}

		List<Integer> list = new ArrayList<Integer>();
		while (sc.hasNextInt()) {
			list.add(sc.nextInt());
		}
		return list.stream().mapToInt(i -> i).toArray();
	}

	/*
	 * Helper method to load set from file
	 * 
	 * @param filename - File name which contains integers
	 * 
	 * @return - integer set
	 */
	public static Set<Long> loadSet(String filename) {
		Scanner sc;
		Set<Long> set = new HashSet<>();
		try {
			sc = new Scanner(new File(filename));
		} catch (Exception e) {
			e.printStackTrace();
			return set;
		}

		while (sc.hasNextLong()) {
			set.add(sc.nextLong());
		}
		return set;
	}

	/*
	 * Helper method to load undirected graph from file as a adjacency list
	 * 
	 * @param filename - File name which contains integers
	 * 
	 * @return - Map of adjacency list
	 */
	public static Map<Integer, Set<Integer>> loadUndirectedGraph(String filename) {
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		Scanner sc;
		try {
			sc = new Scanner(new File(filename));
		} catch (Exception e) {
			e.printStackTrace();
			return graph;
		}

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] arrStr = line.split(" ");
			int node = Integer.parseInt(arrStr[0]);
			Set<Integer> neighbors = new HashSet<>();
			for (int i = 1; i < arrStr.length; i++) {
				neighbors.add(Integer.parseInt(arrStr[i]));
			}
			graph.put(node, neighbors);
		}
		return graph;
	}

	/*
	 * Helper method to load directed graph from file as a adjacency list
	 * 
	 * @param filename - File name which contains integers
	 * 
	 * @return - Map of adjacency list
	 */
	public static Map<Integer, Set<Integer>> loadDirectedGraph(String filename) {
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		Scanner sc;
		try {
			sc = new Scanner(new File(filename));
		} catch (Exception e) {
			e.printStackTrace();
			return graph;
		}

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] arrStr = line.split(" ");
			int node = Integer.parseInt(arrStr[0]);
			Set<Integer> neighbors;
			if (graph.containsKey(node)) {
				neighbors = graph.get(node);
			} else {
				neighbors = new HashSet<>();
			}
			for (int i = 1; i < arrStr.length; i++) {
				Integer neighbor = Integer.parseInt(arrStr[i]);
				neighbors.add(neighbor);
				if (!graph.containsKey(neighbor)) {
					graph.put(neighbor, new HashSet<>());
				}
			}
			graph.put(node, neighbors);
		}
		return graph;
	}
}
