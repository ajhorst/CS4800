package pathfinding;

import graph.Edge;
import graph.Node;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

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
	
	protected boolean smallestRemainingIsInfinity(Map<Node, NodeData> nodeData){
		return nodeData.values()
				.stream()
				.allMatch( nd -> nd.visited || nd.distance == Integer.MAX_VALUE);
	}
	
	protected List<Edge> reversePrevList(Node start, Node end, Map<Node, NodeData> nodeData){
		List<Edge> result = Lists.newArrayList();
		Edge prevEdge = nodeData.get(end).prev;
		for(Node n = end; !n.equals(start); prevEdge = nodeData.get(n).prev){
			result.add(prevEdge);
			n = prevEdge.getOtherEnd(n);
		}
		return Lists.reverse(result);
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
