package graph;

import java.util.Set;

import com.google.common.collect.Sets;

public final class Nodes {

	private Nodes(){}

	public static Set<Node> buildGraph(Node... nodes){
		return Sets.newHashSet(nodes);
	}
}
