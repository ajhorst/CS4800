package graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestNodes {

	@Test
	public void testGetAllEdges(){
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		
		Edge.connectNodes(a, b, 1);
		Edge.connectNodes(a, c, 1);
		
		assertEquals("Should be two edges total", 2, Nodes.getAllEdges(Nodes.buildGraph(a, b, c)).size());
	}
}
