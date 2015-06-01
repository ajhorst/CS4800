package pathfinding;

import graph.Edge;
import graph.Node;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Dijkstra implements ShortestPathFinder{
	
	protected Dijkstra(){}
	
	@Override
	public List<Edge> findShortestPath(Set<Node> graph, Node start, Node end) {
		Map<Node, NodeData> nodeData = Maps.newHashMap();
		
		for(Node n : graph){
			nodeData.put(n, new NodeData()); 
		}
		nodeData.get(start).distance = 0;
		nodeData.get(start).visited = true;
		
		Node currentNode = start;
		
		while(true){ // terminate if break conditions are reached at end of loop
			Set<Edge> nextEdges = currentNode.getEdges();
			for(Edge e : nextEdges){
				Node nextNode = e.getOtherEnd(currentNode);
				NodeData nd = nodeData.get(nextNode);
				int newDistance = nodeData.get(currentNode).distance + e.getWeight();
				if(nd.distance > newDistance){
					nd.distance = newDistance;
					nd.prev = e;
				}
			}
			currentNode = getClosestUnvisitedNode(nodeData);
			nodeData.get(currentNode).visited = true;
			
			boolean endReached = nodeData.get(end).visited;
			if(smallestRemainingIsInfinity(nodeData) || endReached){
				break;
			}
		}
		
		return reversePrevList(start, end, nodeData);
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
