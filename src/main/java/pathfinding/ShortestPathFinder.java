package pathfinding;

import graph.Edge;
import graph.Node;

import java.util.List;
import java.util.Set;

public interface ShortestPathFinder {
	public List<Edge> findShortestPath(Set<Node> graph, Node start, Node end);
}
