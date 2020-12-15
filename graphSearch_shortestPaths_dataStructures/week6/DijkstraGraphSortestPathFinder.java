package week6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Comparator;

import utils.Edge;
import utils.FancyGraph;
import utils.GraphUtils;
import utils.Node;

/**
 * Class implementation to demonstrate Dijkstra shortest path algorithm 
 * @author rajeshsurana
 *
 */
public class DijkstraGraphSortestPathFinder {
	
	public static final long NO_PATH_WEIGHT = 1000000L;

	/**
	 * Public interface method which returns minimum distance from source to each vertex.
	 * Time Complexity - O (|V|*|E|)
	 * 
	 * @param g
	 * @param source
	 * @return
	 */
	public static Map<Integer, Long> dijkstra(FancyGraph g, int source) {
		if (!g.contains(source)) {
			throw new IllegalArgumentException("Source vertex " + source + " does not exist in the graph.");
		}
		Map<Integer, Integer> parentMap = new HashMap<>();
		Map<Integer, Long> weightSoFar = new HashMap<>();

		// Populate parent map which will help construct shortest path
		DijkstraGraphSortestPathFinder.dijkstraSearch(g, source, parentMap, weightSoFar);

		return weightSoFar;
	}

	/**
	 * Main method which generates parent map and minimum weight map for each vertex.
	 * Parent map can be used to generate shortest distance path from source to any vertex.
	 * Refer {@link DijkstraGraphSortestPathFinder#constructPath(Integer, Integer, Map)}
	 * 
	 * @param g
	 * @param source
	 * @param parentMap
	 * @param weightSoFar
	 */
	private static void dijkstraSearch(FancyGraph g, int source, Map<Integer, Integer> parentMap,
			Map<Integer, Long> weightSoFar) {
		Set<Integer> visited = new HashSet<>();
		Map<Integer, Long> priorityMap = new HashMap<>();
		Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer vertex1, Integer vertex2) {
				return priorityMap.get(vertex1).compareTo(priorityMap.get(vertex2));
			}
		});

		weightSoFar.put(source, 0L);
		priorityMap.put(source, 0L);
		queue.add(source);

		while (!queue.isEmpty()) {
			Integer curr = queue.remove();
			if (visited.contains(curr)) {
				continue;
			}
			visited.add(curr);
			Node currNode = g.getNode(curr);
			List<Edge> neighborPaths = currNode.getEdges();
			for (Edge neighborPath : neighborPaths) {
				Integer neighbor = neighborPath.getTarget();
				Long newWeight = weightSoFar.get(curr) + neighborPath.getWeight();
				if (weightSoFar.getOrDefault(neighbor, NO_PATH_WEIGHT).compareTo(newWeight) > 0) {
					weightSoFar.put(neighbor, newWeight);
					priorityMap.put(neighbor, newWeight);
					queue.add(neighbor);
					parentMap.put(neighbor, curr);
				}
			}
		}
	}

	/**
	 * Given parent map builds path from source to target.
	 * @param source
	 * @param target
	 * @param parentMap
	 * @return
	 */
	@SuppressWarnings("unused")
	private static List<Integer> constructPath(Integer source, Integer target, Map<Integer, Integer> parentMap) {
		List<Integer> path = new LinkedList<>();
		Integer currNode = target;
		int iteration = 200;
		while (iteration > 0 && currNode != null && !source.equals(currNode)) {
			path.add(0, currNode);
			if (!parentMap.containsKey(currNode)) {
				return new LinkedList<>();
			}
			currNode = parentMap.get(currNode);
			iteration--;
		}
		path.add(0, source);
		return path;
	}

	public static void main(String[] args) {
		FancyGraph g = new FancyGraph();
		GraphUtils.loadGraph(g, "data/dijkstraData.txt");
		int source = 1;
		Map<Integer, Long> weightSoFar = DijkstraGraphSortestPathFinder.dijkstra(g, source);
		int[] arr = { 7, 37, 59, 82, 99, 115, 133, 165, 188, 197 };
		for (int target : arr) {
			System.out.print(weightSoFar.get(target) + ",");
		}

	}
}
