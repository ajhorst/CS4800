package graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Node {
	public final String name;
	public Set<Edge> edges;
	
	public Node(String name){
		this.name = name;
		this.edges = new HashSet<>();
	}
	
	public Node(String name, Set<Edge> edges){
		this.name = name;
		this.edges = edges;
	}
	
	public Node(String name, Edge[] edges){
		this.name = name;
		HashSet<Edge> e = new HashSet<>();
		e.addAll( Arrays.asList(edges) );
		this.edges = e;
	}
	
	public void setEdges(Set<Edge> edges){
		this.edges = edges;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Set<Edge> getEdges(){
		return this.edges;
	}
	
	public void addEdge(Edge edge){
		if (this.edges == null){
			this.edges = new HashSet<>();
		}
		edges.add(edge);
	}

	public boolean isConnectedToNode(Node node){
		for(Edge e : this.edges){
			if (e.getOtherEnd(this).equals(node)){
				return true;
			}
		}
		return false;
	}
	
	public static Set<Node> buildGraph(Node... nodes){
		Set<Node> result = new HashSet<>();
		for(Node n : nodes){
			result.add(n);
		}
		return result;
	}
	
	public String toString(){
		return this.getName();
	}
	
	/* Nodes are identified by their name within a set. Comparing named nodes across 
	 * different sets doesn't make logical sense and therefore isn't considered here
	 * */
	public boolean equals(Object otherNode){
		if (!(otherNode instanceof Node)){
			return false;
		}
		Node other = (Node)otherNode;
		return this.getName().equals(other.getName());
	}
}
