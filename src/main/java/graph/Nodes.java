package graph;

import java.util.Set;

import com.google.common.collect.Sets;

public final class Nodes {

	private Nodes(){}

	public static Set<Node> buildGraph(Node... nodes){
		return Sets.newHashSet(nodes);
	}
	
	public static Set<Edge> getAllEdges(Set<Node> nodes){
		Set<Edge> result = Sets.newHashSet();
		for (Node n : nodes){
			result.addAll(n.getEdges());
		}
		return result;
	}
}
