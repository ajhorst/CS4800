package graph;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class TestNode {

	Node a,b;
	
	@Before
	public void setup(){
		a = new Node("A");
		b = new Node("B");
	}
	
	@Test
	public void testNodeName(){
		assertEquals(format("Node should be 'A', was %s", "asdfasdfasdf"), a.getName(), "A");
	}
	
	@Test
	public void testNodeEquals(){
		assertEquals("Node A should equal itself", a, a);
		assertFalse("Node A should not equal Node B", a.equals(b));
	}
}
