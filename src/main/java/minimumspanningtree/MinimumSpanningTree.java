package minimumspanningtree;

import graph.Edge;
import graph.Node;

import java.util.Set;

public interface MinimumSpanningTree {
	public Set<Edge> findMinSpanningTree(Set<Node> graph);
}
