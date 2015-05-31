package pathfinding;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import graph.Edge;
import graph.Node;
import graph.Nodes;

import org.junit.Before;
import org.junit.Test;

public class TestNonNegativePathfinding {

	Node a, b, c, d, e, f;
	Edge ab, ac, ae, bc, bd, be, cd, cf, de, df, ef;
	@Before
	public void setup(){
		a = new Node("A");
		b = new Node("B");
		c = new Node("C");
		d = new Node("D");
		e = new Node("E");
		f = new Node("F");
		
		ab = Edge.connectNodes(a, b, 1);
		ac = Edge.connectNodes(a, c, 3);
		ae = Edge.connectNodes(a, e, 4);
		bc = Edge.connectNodes(b, c, 1);
		bd = Edge.connectNodes(b, d, 3);
		be = Edge.connectNodes(b, e, 4);
		cd = Edge.connectNodes(c, d, 1);
		cf = Edge.connectNodes(c, f, 3);
		de = Edge.connectNodes(d, e, 1);
		df = Edge.connectNodes(d, f, 1);
		ef = Edge.connectNodes(e, f, 1);
	}
	
	@Test
	public void testDijkstra(){
		Set<Node> graph = Nodes.buildGraph(a, b, c, d, e, f);
		List<Edge> shortestPath = new Dijkstra().findShortestPath(graph, a, f);
		assertEquals("should be 4 edges in shortest path", 4, shortestPath.size());
		
		assertEquals("first edge in shortest path should be AB", ab, shortestPath.get(0));
		assertEquals("first edge in shortest path should be BC", bc, shortestPath.get(1));
		assertEquals("first edge in shortest path should be CD", cd, shortestPath.get(2));
		assertEquals("first edge in shortest path should be DF", df, shortestPath.get(3));
	}
}
