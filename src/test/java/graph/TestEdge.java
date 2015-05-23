package graph;

import org.junit.Before;
import org.junit.Test;

import static java.lang.String.format;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestEdge {

	Node a, b, c, d;
	
	@Before
	public void setup(){
		a = new Node("A");
		b = new Node("B");
		c = new Node("C");
		d = new Node("D");
	}
	
	public void testEquals(){
		Edge ab = Edge.connectNodes(a, b, 1);
		Edge ba = Edge.connectNodes(b, a, 1);
		assertTrue("Edges AB and BA should be equal", ab.equals(ba));
		assertEquals("Node A should have one edge", 1, a.getEdges().size());
		
		Edge ab2 = Edge.connectNodes(a, b, 2);
		assertTrue("Edges AB (weight 1) and AB (weight 2) should be treated as equal", ab.equals(ab2));
		assertEquals("Node A should still only have one edge", 1, a.getEdges().size());
	}

	
	public void testEdgeEndOrdering(){
		
		Edge ab = Edge.connectNodes(a, b, 1);
		assertEquals("Edge AB should have Node A as its first end", a, ab.getEnds()[0]);
		assertEquals("Edge AB should have Node B as its second end", b, ab.getEnds()[1]);
		
		setup();
		Edge ba = Edge.connectNodes(b, a, 1);
		assertEquals("Edge BA should have Node A as its first end", a, ba.getEnds()[0]);
		assertEquals("Edge BA should have Node B as its second end", a, ba.getEnds()[1]);
	}
	
	@Test
	public void testConnectedNodes(){
		Edge e = Edge.connectNodes(a, b, 2);
		assertEquals(format("Edge weight should be 2, was %s", e.getWeight()), e.getWeight(), 2);
		
		assertEquals(format("Edge should have 2 end Nodes, had %d", e.getEnds().length), e.getEnds().length, 2);
		assertEquals(format("Given A, edge's other node should be B, was %s", e.getOtherEnd(a)), e.getOtherEnd(a), b);
		assertEquals(format("Given B, edge's other node should be A, was %s", e.getOtherEnd(b)), e.getOtherEnd(b), a);
		
		
		assertTrue("Node A should connect to Node B", a.isConnectedToNode(b));
		assertTrue("Node B should connect to Node A", b.isConnectedToNode(a));
		
		assertFalse("Node A should not connect to Node C", a.isConnectedToNode(c));
		assertFalse("Node A should not connect to a null node", a.isConnectedToNode(null));
	}
	
	@Test
	public void testEdgeConnections(){
		
		assertEquals(format("Node A should have 0 edges, had %d", a.getEdges().size() ), a.getEdges().size(), 0);
		assertEquals(format("Node B should have 0 edges, had %d", b.getEdges().size() ), b.getEdges().size(), 0);
		assertEquals(format("Node C should have 0 edges, had %d", c.getEdges().size() ), c.getEdges().size(), 0); 

		Edge.connectNodes(a, b, 1);
		Edge.connectNodes(a, c, 4);
		
		assertEquals(format("Node A should have 2 edges, had %d", a.getEdges().size() ), a.getEdges().size(), 2);
		assertEquals(format("Node B should have 1 edges, had %d", b.getEdges().size() ), b.getEdges().size(), 1);
		assertEquals(format("Node C should have 1 edges, had %d", c.getEdges().size() ), c.getEdges().size(), 1); 

		Edge.connectNodes(b, c, 2);
		
		assertEquals(format("Node A should have 2 edges, had %d", a.getEdges().size() ), a.getEdges().size(), 2);
		assertEquals(format("Node B should have 2 edges, had %d", b.getEdges().size() ), b.getEdges().size(), 2);
		assertEquals(format("Node C should have 2 edges, had %d", c.getEdges().size() ), c.getEdges().size(), 2); 
		
	}
	
	public void testEdgeOrdering(){
		
		Edge ab = Edge.connectNodes(a, b, 1);
		Edge ba = Edge.connectNodes(b, a, 1);
		
		assertEquals("Edges AB and BA should be equal", a, b);
		assertEquals("Edges AB and BA should have the same toString", ab.toString(), ba.toString());
		
		assertEquals("Node A should only have one edge", 1, a.getEdges().size());
		assertEquals("Node B should only have one edge", 1, b.getEdges().size());
		
	}
}
