package utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Generic undirected graph represented using adjacency list with vertex as a number.
 * Edge weight is one.
 */
public class SimpleGraph implements Graph {
	private Map<Integer, Set<Integer>> graph = new HashMap<>();
	private int numEdges;

	public SimpleGraph() {
		this.graph = new HashMap<>();
		this.numEdges = 0;
	}
	
	@Override
	public void addVertex(int num) {
		graph.put(num, new HashSet<Integer>());
	}


	@Override
	public void addEdge(int from, int to, int weight) {
		if (!graph.containsKey(from)) {
			throw new IllegalArgumentException("Edge source element " + from + " does not exist in the graph.");
		} else if (!graph.containsKey(to)) {
			throw new IllegalArgumentException("Edge destination element " + to + " does not exist in the graph.");
		}

		graph.get(from).add(to);
		this.numEdges++;
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


	@Override
	public int getNumVertices() {
		return graph.size();
	}

	@Override
	public int getNumEdges() {
		return this.numEdges;
	}

	@Override
	public boolean contains(int vertex) {
		return this.graph.containsKey(vertex);
	}
}
