package pathfinding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static utils.TestException.assertExceptionThrown;
import graph.Edge;
import graph.Node;

import java.util.List;
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
	public void testSmallestRemainingIsInfinity(){
		assertTrue("smallest remaining distance should be infinity", d.smallestRemainingIsInfinity(nodeData));
		nd1.distance = 0;
		assertFalse("smallest remaining distance should not be infinity", d.smallestRemainingIsInfinity(nodeData));
		nd1.distance = Integer.MAX_VALUE;
		nd1.visited = true;
		assertTrue("unvisited node should be infinity", d.smallestRemainingIsInfinity(nodeData));
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
	
	@Test
	public void testReversePrevList(){
		Node n3 = new Node("N3");
		NodeData nd3 = d.new NodeData();
		nodeData.put(n3, nd3);
		Edge e1 = Edge.connectNodes(n1, n2, 1);
		nd2.prev = e1;
		Edge e2 = Edge.connectNodes(n2, n3, 1);
		nd3.prev = e2;
		List<Edge> edges = d.reversePrevList(n1, n3, nodeData);
		assertEquals("should be two edges in list", 2, edges.size());
		assertEquals("first edge should be e1", e1, edges.get(0));
		assertEquals("second edge should be e2", e2, edges.get(1));
	}
}
