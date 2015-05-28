package minimumspanningtree;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Sets;

import graph.Edge;
import graph.Node;
import static graph.TestEdge.assertGraphContainsEdges;

public class TestMinimumSpanningTreeAlgos {
	public static Node a, b, c, d, e, f, g;
	public static Edge ab, ad, bc, bd, be, ce, de, df, ef, eg, fg;
	
	@BeforeClass
	public static void setup(){
		a = new Node("A");
		b = new Node("B");
		c = new Node("C");
		d = new Node("D");
		e = new Node("E");
		f = new Node("F");
		g = new Node("G");
		
		ab = Edge.connectNodes(a, b, 7);
		ad = Edge.connectNodes(a, d, 5);
		bc = Edge.connectNodes(b, c, 8);
		bd = Edge.connectNodes(b, d, 9);
		be = Edge.connectNodes(b, e, 7);
		ce = Edge.connectNodes(c, e, 5);
		de = Edge.connectNodes(d, e, 15);
		df = Edge.connectNodes(d, f, 6);
		ef = Edge.connectNodes(e, f, 8);
		eg = Edge.connectNodes(e, g, 7);
		fg = Edge.connectNodes(f, g, 11);
	}
	
	@Test
	public void testKruskal(){
		Set<Node> nodes = Sets.newHashSet(a, b, c, d, e, f, g);
		verifyMinSpanningTree(new Kruskal().findMinSpanningTree(nodes));
	}
	
	@Test
	public void testPrim(){
		Set<Node> nodes = Sets.newHashSet(a, b, c, d, e, f, g);
		verifyMinSpanningTree(new Prim().findMinSpanningTree(nodes));
	}
	
	public void verifyMinSpanningTree(Set<Edge> edges){
		assertGraphContainsEdges(edges, ab, ad, be, ce, df, eg);
	}
}
