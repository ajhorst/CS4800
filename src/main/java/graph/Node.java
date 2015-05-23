package graph;

public class Node {
	public final String name;
	
	public Node(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
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
