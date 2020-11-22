package utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Generic graph represented using adjacency list with vertex as a number.
 */
public class Graph {
	private Map<Integer, Set<Integer>> graph = new HashMap<>();

	/* Creates a vertex with the given number. */
	public void addVertex(int num) {
		graph.put(num, new HashSet<Integer>());
	}

	/* Creates an edge from the first vertex to the second. */
	public void addEdge(int from, int to) {
		if (!graph.containsKey(from)) {
			throw new IllegalArgumentException("Edge source element " + from + " does not exist in the graph.");
		} else if (!graph.containsKey(to)) {
			throw new IllegalArgumentException("Edge destination element " + to + " does not exist in the graph.");
		}

		graph.get(from).add(to);
	}

	/*
	 * Return the graph's connections in a readable format. The keys in this HashMap
	 * are the vertices in the graph. The values are the nodes that are reachable
	 * via a directed edge from the corresponding key. The returned representation
	 * ignores edge weights and multi-edges.
	 */
	public Map<Integer, Set<Integer>> exportGraph() {
		Map<Integer, Set<Integer>> exportedGraph = new HashMap<>();
		for (int vertex: graph.keySet()) {
			exportedGraph.put(vertex, new HashSet<Integer>(graph.get(vertex)));
		}
		return exportedGraph;
	}

	/*
	 * Returns the number of vertices in a graph.
	 */
	public int getNumVertices() {
		return graph.size();
	}
}
