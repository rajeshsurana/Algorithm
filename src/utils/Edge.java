package utils;

/*
 * @author rajesh.surana
 * 
 * Holds edge information of a graph.
 */
public class Edge {
	private int source;
	private int target;
	private int weight;

	/**
	 * @param source
	 * @param target
	 * @param weight
	 */
	public Edge(int source, int target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}

	/**
	 * @return the source
	 */
	public int getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(int source) {
		this.source = source;
	}

	/**
	 * @return the target
	 */
	public int getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(int target) {
		this.target = target;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + source;
		result = prime * result + target;
		result = prime * result + weight;
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
		Edge other = (Edge) obj;
		if (source != other.source)
			return false;
		if (target != other.target)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
	
	
}
