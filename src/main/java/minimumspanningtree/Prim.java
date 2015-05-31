package minimumspanningtree;

import graph.Edge;
import graph.Node;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
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
		List<Edge> edges = Lists.newArrayList();//Sets.newTreeSet();
		for(Node n : nodes){
			for(Edge e : n.getEdges()){
				Node otherEnd = e.getOtherEnd(n);
				if(!nodes.contains(otherEnd)){
					edges.add(e);
				}
			}
		}
		edges.sort(Edge::compareByWeight);
		return edges.get(0); // algorithmically, there will always be at least one node with at least one valid edge
	}

}
