package pathfinding;

import graph.Edge;
import graph.Node;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Dijkstra implements ShortestPathFinder{
	
	protected Dijkstra(){}
	
	@Override
	public List<Edge> findShortestPath(Set<Node> graph, Node start, Node end) {
		return null;
	}
	
	/*
	 * assumed that there's at least one unvisited node
	 */
	protected Node getClosestUnvisitedNode(Map<Node, NodeData> nodeData){
		
		List<Node> remainingNodes = nodeData.keySet().stream()
										.filter(n -> !nodeData.get(n).visited)
										.sorted((n1, n2) -> nodeData.get(n1).distance - nodeData.get(n2).distance)
										.collect(Collectors.toList() );
		
		return remainingNodes.get(0);	
	}
	
	// the extra data about each node required for Dijkstra's
	protected class NodeData{
		boolean visited;
		int distance;
		Edge prev;
		
		public NodeData(){
			this.visited = false;
			this.distance = Integer.MAX_VALUE; // used to represent INFINITY
			this.prev = null;
		}
	}
}
