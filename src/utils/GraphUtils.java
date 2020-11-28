package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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

	/**
     * Loads graph with data from a file.
     * The file should consist of lines with 2 integers each, corresponding
     * to a "from" vertex and a "to" vertex.
     */ 
    public static void loadGraph(SimpleGraph g, String filename) {
        Set<Integer> seen = new HashSet<Integer>();
        Scanner sc;
        try {
            sc = new Scanner(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // Iterate over the lines in the file, adding new
        // vertices as they are found and connecting them with edges.
        while (sc.hasNextInt()) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            if (!seen.contains(v1)) {
                g.addVertex(v1);
                seen.add(v1);
            }
            if (!seen.contains(v2)) {
                g.addVertex(v2);
                seen.add(v2);
            }
            g.addEdge(v1, v2, 1);
        }
        
        sc.close();
    }
    
	/**
     * Loads graph with data from a file.
     * The file should consist of lines with 2 integers each, corresponding
     * to a "from" vertex and a "to" vertex.
     */ 
    public static void loadGraph(FancyGraph g, String filename) {
        Scanner sc;
        try {
            sc = new Scanner(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // Iterate over the lines in the file, adding new
        // vertices as they are found and connecting them with edges.
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line == null || line.trim().equals("")) {
				continue;
			}
			String[] items = line.split("\\s+");
			if (items.length == 0) {
				continue;
			}
			int source = Integer.parseInt(items[0]);
			g.addVertex(source);
			for (int i = 1; i < items.length; i++) {
				String[] vertexWeight = items[i].split(",");
				int target = Integer.parseInt(vertexWeight[0]);
				int weight = Integer.parseInt(vertexWeight[1]);
				g.addEdge(source, target, weight);
			}
		}
        sc.close();
    }
}
