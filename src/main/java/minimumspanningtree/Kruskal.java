package minimumspanningtree;

import graph.Edge;
import graph.Node;
import graph.Nodes;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Kruskal implements MinimumSpanningTree{

	private List<Edge> sortedEdges;
	private Set<Set<Node>> forest;
	
	public Kruskal(){
		this.forest = new HashSet<>();
	}
	
	public Set<Edge> findMinSpanningTree(Set<Node> graph) {
		Set<Edge> result = new HashSet<Edge>();
		sortedEdges = Nodes.getAllEdges(graph).stream()
						.sorted(Edge::compareByWeight)
						.collect(Collectors.toList());
		
		for(Edge e : sortedEdges){
			if( formsCycle(e) ){
				continue;
			}
			
			result.add(e);	
			if(result.size() == graph.size() -1){
				break;
			}
		}
		return result;
	}
	
	private boolean formsCycle(Edge e){
		Node n1 = e.getEnds()[0];
		Node n2 = e.getEnds()[1];
		
		Set<Set<Node>> newForest = new HashSet<>();
		
		List<Set<Node>> containingSubtrees = new LinkedList<>(); 
		for(Set<Node> subtree : forest){
			boolean containsN1 = subtree.contains(n1);
			boolean containsN2 = subtree.contains(n2);
			
			if(containsN1 && containsN2){
				return true;
			} else if(containsN1 || containsN2){
				containingSubtrees.add(subtree);
			} else {
				newForest.add(subtree);
			}
		}
		

		Set<Node> subtree;// = new HashSet<>();
		
		switch(containingSubtrees.size()){
		case 2:	// the edge connects two unconnected subtrees
			subtree = new HashSet<>();
			subtree.addAll(containingSubtrees.get(0)); // join these two by the linking edge
			subtree.addAll(containingSubtrees.get(1));
			break;
		case 1: // the edge connects to a single subtree
			subtree = containingSubtrees.get(0);
			subtree.addAll(e.getEndsList()); // will already contain either N1 or N2, the reduntant add will be a noop due to Node.equals()
			break;
		case 0: // the edge is a new subtree
			subtree = new HashSet<>();
			
			break;
		default: throw new RuntimeException("error in kruskal's algorithm subtree forming");
		}

		newForest.add(subtree);
		forest = newForest;
		
		return false;
	}

}
