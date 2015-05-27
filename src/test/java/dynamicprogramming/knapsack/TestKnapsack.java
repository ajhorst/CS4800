package dynamicprogramming.knapsack;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class TestKnapsack {

	private static Item i1, i2;
	
	@BeforeClass
	public static void setup(){
		i1 = new Item(1, 1);
		i2 = new Item(6, 2);
	}
	
	@Test
	public void testSumList(){
		List<Item> l = ImmutableList.of(i1, i2);
		assertEquals("values should sum to 7", 7, Knapsack.sumValue(l));
	}
}
