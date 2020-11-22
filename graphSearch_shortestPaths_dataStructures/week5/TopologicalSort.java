package week5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import utils.DataLoader;

/*
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices
 * such that for every directed edge u v, vertex u comes before v in the ordering.
 * Topological Sorting for a graph is not possible if the graph is not a DAG.
 */
public class TopologicalSort {

	/*
	 * Public interface method to get topological ordering of vertices
	 * in a graph.
	 * 
	 * @param graph - Input graph as an adjacent list
	 *
	 * @return - Topologically sorted vertices list
	 */
	public static List<Integer> sort(Map<Integer, Set<Integer>> graph) {
		Set<Integer> visited = new HashSet<>();
		Stack<Integer> stack = new Stack<>();
		// Go over all the vertices one by one
		for (Integer vertex : graph.keySet()) {
			// Call recursive routine if not visited
			if (!visited.contains(vertex)) {
				TopologicalSort.sortMain(graph, vertex, visited, stack);
			}
		}
		List<Integer> list = new ArrayList<>(stack.size());
		while (!stack.empty()) {
			list.add(stack.pop());
		}
		return list;
	}

	/*
	 * Main recursive routine which is a modification of DFS.
	 * Current vertex will be added last to the stack and sink vertex
	 * will be added first.
	 * 
	 * @param graph - Input graph
	 * @param visited - Set to keep track of visited vertices
	 * @param stack - To hold the vertices in the order they are visited
	 */
	private static void sortMain(Map<Integer, Set<Integer>> graph, Integer vertex, Set<Integer> visited,
			Stack<Integer> stack) {
		// Mark vertex as visited
		visited.add(vertex);
		// Call recursive routine for non-visited neighbors
		for (Integer neighbor : graph.get(vertex)) {
			if (!visited.contains(neighbor)) {
				TopologicalSort.sortMain(graph, neighbor, visited, stack);
			}
		}
		// Push to stack once all its neighbors are visited and nowhere to go.
		stack.push(vertex);
	}

	public static void main(String[] args) {
		Map<Integer, Set<Integer>> graph = DataLoader.loadDirectedGraph("data/TopologicalSort.txt");
		System.out.println("Topological sorted sequence -\n" + TopologicalSort.sort(graph));
	}
}
