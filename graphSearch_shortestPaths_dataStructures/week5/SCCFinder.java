/**
 * 
 */
package week5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import utils.Graph;
import utils.GraphUtils;

/**
 * @author rajesh.surana
 * 
 *         A directed graph is strongly connected if there is a path between all
 *         pairs of vertices. A strongly connected component (SCC) of a directed
 *         graph is a maximal strongly connected subgraph.
 *
 */
public class SCCFinder {

	public static List<Graph> getSCCs(Graph iGraph) {
		Map<Integer, Set<Integer>> graph = iGraph.exportGraph();
		// STEP 1: DFS
		Stack<Integer> finished = SCCFinder.dfs(graph);

		// STEP 2: Transpose
		Map<Integer, Set<Integer>> transposeGraph = SCCFinder.transpose(graph);

		// STEP 3: DFS in reverse order
		List<Stack<Integer>> sccStacks = SCCFinder.dfsFinal(transposeGraph, finished);

		// Result: Build SCC graphs
		List<Graph> sccs = new ArrayList<>();
		for (Stack<Integer> stack : sccStacks) {
			Set<Integer> vertices = new HashSet<>(stack.subList(0, stack.size()));
			Graph newGraph = new Graph();
			for (Integer vertex : vertices) {
				newGraph.addVertex(vertex);
			}
			for (Integer vertex : vertices) {
				for (Integer neighbor : graph.get(vertex)) {
					if (vertices.contains(neighbor)) {
						newGraph.addEdge(vertex, neighbor);
					}
				}
			}
			sccs.add(newGraph);
		}
		return sccs;
	}

	private static Stack<Integer> dfs(Map<Integer, Set<Integer>> graph) {
		Set<Integer> visited = new HashSet<>();
		Stack<Integer> finished = new Stack<>();
		for (Integer vertex : graph.keySet()) {
			if (!visited.contains(vertex)) {
				SCCFinder.dfsVisit(graph, vertex, visited, finished);
			}
		}
		return finished;
	}

	private static List<Stack<Integer>> dfsFinal(Map<Integer, Set<Integer>> graph, Stack<Integer> vertices) {
		Set<Integer> visited = new HashSet<>();
		List<Stack<Integer>> sccStacks = new ArrayList<Stack<Integer>>();
		while (!vertices.empty()) {
			Integer vertex = vertices.pop();
			if (!visited.contains(vertex)) {
				Stack<Integer> finished = new Stack<>();
				SCCFinder.dfsVisit(graph, vertex, visited, finished);
				sccStacks.add(finished);
			}
		}
		return sccStacks;
	}

	private static void dfsVisit(Map<Integer, Set<Integer>> graph, Integer vertex, Set<Integer> visited,
			Stack<Integer> finished) {
		visited.add(vertex);
		for (Integer neighbor : graph.get(vertex)) {
			if (!visited.contains(neighbor)) {
				SCCFinder.dfsVisit(graph, neighbor, visited, finished);
			}
		}
		finished.push(vertex);
	}

	private static Map<Integer, Set<Integer>> transpose(Map<Integer, Set<Integer>> graph) {
		Map<Integer, Set<Integer>> transposeGraph = new HashMap<>();
		for (Integer vertex : graph.keySet()) {
			transposeGraph.put(vertex, new HashSet<>());
		}
		for (Integer vertex : graph.keySet()) {
			for (Integer neighbor : graph.get(vertex)) {
				transposeGraph.get(neighbor).add(vertex);
			}
		}
		return transposeGraph;
	}

	/*
	 * Important: Before you run this program, add VM arguments -> -Xss1000m -Xss1000m
	 * to increase recursive call stack size, otherwise you will get StackOverflow exception.
	 */
	public static void main(String[] args) {
		Graph graph = new Graph();
		GraphUtils.loadGraph(graph, "data/SCC.txt");
		List<Graph> sccs = SCCFinder.getSCCs(graph);
		// Print size of SCC graphs in decreasing order.
		// output = [434821, 968, 313, 138, 459, 68, 177, 69, 101, 78, 211, 30, 64,...
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(sccs.size(), new Comparator<Integer>() {

			@Override
			public int compare(Integer v1, Integer v2) {
				if (v1 > v2) {
					return -1;
				} else if (v1 < v2) {
					return 1;
				} else {
					return 0;
				}
			}

		});
		for (Graph g : sccs) {
			queue.add(g.getNumVertices());
		}
		System.out.println(queue);
	}
}
