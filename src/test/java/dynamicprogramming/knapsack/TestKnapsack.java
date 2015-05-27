package dynamicprogramming.knapsack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class TestKnapsack {

	private static Item i1, i2, i3, i4, i5;
	
	@BeforeClass
	public static void setup(){
		i1 = new Item(1, 1);
		i2 = new Item(6, 2);
		i3 = new Item(18, 5);
		i4 = new Item(22, 6);
		i5 = new Item(28, 7);
	}
	
	@Test
	public void testSumList(){
		List<Item> l = ImmutableList.of(i1, i2);
		assertEquals("values should sum to 7", 7, Knapsack.sumValue(l));
	}
	
	@Test
	public void testCompute(){
		List<Item> emptyResult = new Knapsack(0, i1, i2, i3, i4, i5).solve();
		assertEquals("Solving on an empty list should result in an empty list", 0, emptyResult.size());
		
		List<Item> smallCapacityResult = new Knapsack(1, i1, i2).solve();
		assertEquals("Result should only have one element", 1, smallCapacityResult.size());
		assertEquals("Result should contain item 1", i1, smallCapacityResult.get(0));
		
		List<Item> oneItemResult = new Knapsack(2, i1, i2).solve();
		assertEquals("Result should only have one element", 1, oneItemResult.size());
		assertEquals("Result should contain item 2", i2, oneItemResult.get(0));
		
		List<Item> generalResult = new Knapsack(8, i1, i2, i3, i4, i5).solve();
		assertEquals("Result should have two elements but was" + generalResult, 2, generalResult.size());
		assertTrue("Result should contain item 5", generalResult.contains(i5));
		assertTrue("Result should contain item 1", generalResult.contains(i1));
		
		
	}
}
