package utils;

public interface Graph {
	/* Creates a vertex with the given number. */
	void addVertex(int num);

	/**
	 * Creates an edge from the first vertex to the second. Weight will be ignored
	 * as its one for each edge
	 */
	void addEdge(int from, int to, int weight);

	/*
	 * Returns the number of vertices in a graph.
	 */
	int getNumVertices();

	/*
	 * Returns the number of edges in a graph. For undirected graph one edge between
	 * two vertices will be counted 2.
	 */
	int getNumEdges();
	
	/**
	 * Check whether given vertex is there or not in a graph
	 */
	boolean contains(int vertex);
}
