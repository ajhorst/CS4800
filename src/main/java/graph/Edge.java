package graph;

public class Edge implements Comparable<Edge>{
	private final Node end1, end2;
	private final int weight;
	
	private Edge(Node end1, Node end2, int weight){
		/* An edge created from Nodes (n1, n2) is the same as a Node created from (n2, n1)
		 * This is enforced internally by making end1 the Node that comes first 
		 * alphabetically by name, since Nodes are considered unique by their name 
		 */
		if(end1.getName().compareTo(end2.getName()) < 0){
			this.end1 = end1;
			this.end2 = end2; 
		} else {
			this.end1 = end2;
			this.end2 = end1;
		}
		this.weight = weight;
	}
	
	public Node[] getEnds(){
		return new Node[]{end1, end2};
	}
	
	public int getWeight(){
		return this.weight;
	}
	
	public Node getOtherEnd(Node end){
		if(end.equals(this.end1)){
			return this.end2;
		} else if (end.equals(this.end2)){
			return this.end1;
		} else{
			throw new RuntimeException(String.format("Edge %s does not contain Node %s", this, end));
		}
	}
	
	public static Edge connectNodes(Node end1, Node end2, int weight){
		Edge e = new Edge(end1, end2, weight);
		e.end1.addEdge(e);
		e.end2.addEdge(e);
		return e;
	}
	
	@Override
	public String toString(){
		return String.format("%s-%d->%s", this.end1.getName(), this.weight, this.end2.getName());
	}
	
	@Override
	public boolean equals(Object obj){
		/*
		 * equality is determined by whether nodes have the same ends. This does
		 * raise the possibility of two edges with the same ends but different weights
		 * being equal, but in our definition of graphs, two nodes can only be connected by 
		 * a single edge, at max
		 */
		if(!(obj instanceof Edge)){
			return false;
		}
		
		Edge otherEdge = (Edge)obj;
		return this.end1 == otherEdge.end1 && this.end2 == otherEdge.end2;
	}

	@Override
	public int compareTo(Edge o) {
		/* 
		 * orders edges by weight. If weight is the same, order by toString, which is somewhat arbitrary
		 * but makes sure that different edges of the same weight aren't considered identical. 
		 */
		int weightDiff = this.getWeight() - o.getWeight();
		if(weightDiff != 0){
			return weightDiff;
		}
		return this.toString().compareTo(o.toString());
	}
}
