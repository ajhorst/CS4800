package pathfinding;

import graph.Edge;
import graph.Node;

import java.util.List;
import java.util.Set;

public class Dijkstra implements ShortestPathFinder{
	
	protected Dijkstra(){}
	
	@Override
	public List<Edge> findShortestPath(Set<Node> graph, Node start, Node end) {
		return null;
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
