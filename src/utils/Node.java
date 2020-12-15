package utils;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author rajesh.surana
 *
 * Node value and its edges builds a graph
 */
public class Node {
	private int value;
	private List<Edge> edges;

	/**
	 * @param value
	 */
	public Node(int value) {
		this.value = value;
		this.edges = new LinkedList<>();
	}

	/**
	 * @param value
	 * @param edges
	 */
	public Node(int value, List<Edge> edges) {
		this.value = value;
		this.edges = edges;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the edges
	 */
	public List<Edge> getEdges() {
		return edges;
	}

	/**
	 * @param edges the edges to set
	 */
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	/**
	 * 
	 * @param edge
	 */
	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}

	/**
	 * 
	 * @param edge
	 */
	public void addEdge(int to, int weight) {
		this.edges.add(new Edge(this.value, to, weight));
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edges == null) ? 0 : edges.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (edges == null) {
			if (other.edges != null)
				return false;
		} else if (!edges.equals(other.edges))
			return false;
		if (value != other.value)
			return false;
		return true;
	}
	
	
}
