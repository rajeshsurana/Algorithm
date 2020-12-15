package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author rajesh.surana
 *
 *         Graph with directed edges and edge weights
 */
public class FancyGraph implements Graph {
	private Map<Integer, Node> valueToNodeMap;
	private int numEdges;

	public FancyGraph() {
		this.valueToNodeMap = new HashMap<>();
		this.numEdges = 0;
	}
	
	@Override
	public void addVertex(int num) {
		Node node = new Node(num);
		this.valueToNodeMap.put(node.getValue(), node);
	}

	@Override
	public void addEdge(int from, int to, int weight) {
		if (!valueToNodeMap.containsKey(from)) {
			throw new IllegalArgumentException("Edge source element " + from + " does not exist in the graph.");
		}
		valueToNodeMap.get(from).addEdge(to, weight);
		this.numEdges++;
	}

	@Override
	public int getNumVertices() {
		return this.valueToNodeMap.size();
	}

	@Override
	public int getNumEdges() {
		return this.numEdges;
	}

	@Override
	public boolean contains(int vertex) {
		return this.valueToNodeMap.containsKey(vertex);
	}

	public Node getNode(int vertex) {
		return this.valueToNodeMap.getOrDefault(vertex, null);
	}
}
