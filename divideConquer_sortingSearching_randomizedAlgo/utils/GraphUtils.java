package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class GraphUtils {
	/*
	 * Helper method to get copy of existing graph
	 * 
	 * @param graph - Input graph to be copied
	 * 
	 * @return - Copy of a input graph
	 */
	public static Map<Integer, Set<Integer>> exportGraph(Map<Integer, Set<Integer>> graph) {
		Map<Integer, Set<Integer>> newGraph = new HashMap<>();
		for (Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
			newGraph.put(entry.getKey(), new HashSet<>(entry.getValue()));
		}
		return newGraph;
	}

	/*
	 * Helper method to get edges from a graph
	 * 
	 * @param graph - Input graph
	 * 
	 * @return - List of edges
	 */
	public static List<Set<Integer>> getEdges(Map<Integer, Set<Integer>> graph) {
		Set<Set<Integer>> edges = new HashSet<>();
		for (Integer node: graph.keySet()) {
			for (Integer neighbor: graph.get(node)) {
				Set<Integer> edge = new HashSet<Integer>(2);
				edge.add(node);
				edge.add(neighbor);
				edges.add(edge);
			}
		}
		return new ArrayList<Set<Integer>>(edges);
	}
}
