package minimumspanningtree;

import static org.junit.Assert.assertEquals;
import static utils.TestException.assertExceptionThrown;

import graph.Edge;
import graph.Node;

import org.junit.Test;

import com.google.common.collect.Sets;

public class TestPrim {

	@Test
	public void testGetLowestGrowableEdge(){
		final Node a = new Node("A");
		final Node b = new Node("B");
		final Node c = new Node("C");
		
		Edge ab = Edge.connectNodes(a, b, 2);
		Edge.connectNodes(a, c, 4);
		Edge bc = Edge.connectNodes(b, c, 1);
		
		final Prim p = new Prim();
		
		Edge l1 = p.getLowestGrowableEdge(Sets.newHashSet(a));
		assertEquals("lowest growable edge should be AB", ab, l1);
		Edge l2 = p.getLowestGrowableEdge(Sets.newHashSet(b));
		assertEquals("lowest growable edge should be BC", bc, l2);
		
		assertExceptionThrown(new Runnable(){
			@Override
			public void run() {
				p.getLowestGrowableEdge(Sets.newHashSet(a, b, c));
		}});
	}
}
