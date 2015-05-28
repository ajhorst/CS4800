package minimumspanningtree;

import graph.Edge;
import graph.Node;

import java.util.Set;
import java.util.SortedSet;

import com.google.common.collect.Sets;

public class Prim implements MinimumSpanningTree{

	@Override
	public Set<Edge> findMinSpanningTree(Set<Node> graph) {
		Set<Edge> minSpanningTree = Sets.newHashSet();
		if(graph.isEmpty()){
			return minSpanningTree;
		}
		Set<Node> nodesSoFar = Sets.newHashSet();
		nodesSoFar.add(graph.iterator().next()); // start node chosen arbitrarily
		
		while(nodesSoFar.size() < graph.size()){
			Edge nextEdge = getLowestGrowableEdge(nodesSoFar);
			nodesSoFar.addAll(nextEdge.getEndsList());
			minSpanningTree.add(nextEdge);
		}

		return minSpanningTree;
		
	}
	
	protected Edge getLowestGrowableEdge(Set<Node> nodes){
		SortedSet<Edge> edges = Sets.newTreeSet();
		for(Node n : nodes){
			for(Edge e : n.getEdges()){
				Node otherEnd = e.getOtherEnd(n);
				if(!nodes.contains(otherEnd)){
					edges.add(e);
				}
			}
		}
		return edges.first(); // algorithmically, there will always be at least one node with at least one valid edge
	}

}
