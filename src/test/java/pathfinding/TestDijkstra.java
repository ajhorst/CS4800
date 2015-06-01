package pathfinding;

import static org.junit.Assert.assertEquals;
import static utils.TestException.assertExceptionThrown;
import graph.Node;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import pathfinding.Dijkstra.NodeData;

import com.google.common.collect.Maps;

public class TestDijkstra {
	
	private Node n1, n2;
	private NodeData nd1, nd2;
	private Dijkstra d;
	private Map<Node, NodeData> nodeData;
	
	@Before
	public void setup(){
		d = new Dijkstra();
		n1 = new Node("N1");
		n2 = new Node("N2");
		nd1 = d.new NodeData();
		nd2 = d.new NodeData();
		nodeData = Maps.newHashMap();
		nodeData.put(n1,  nd1);
		nodeData.put(n2,  nd2);
	}
	
	@Test
	public void testGetClosestUnvisitedNode(){
		nd1.distance = 5;
		nd2.distance = 7;
		assertEquals("closest remaining node should be n1", n1, d.getClosestUnvisitedNode(nodeData));
		nd1.visited = true;
		assertEquals("closest remaining node should be n2", n2, d.getClosestUnvisitedNode(nodeData));
		nd2.visited = true;
		assertExceptionThrown( () -> d.getClosestUnvisitedNode(nodeData) );
	}
}
