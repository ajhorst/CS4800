package dynamicprogramming.memoizable;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestMemoizable {

	@Test
	public void testMemoizable(){
		Memoizable<Integer, Integer> m = new Memoizable<Integer, Integer>(){
			@Override
			public Integer compute(Integer x) {
				return x;
		}};
		
		assertEquals("Memoizable map should be empty", 0, m.memo.size());
		m.get(0);
		assertEquals("Memoizable map should be size 1", 1, m.memo.size());
		m.get(0);
		assertEquals("Memoizable map should still be size 1", 1, m.memo.size());
		m.get(1);
		assertEquals("Memoizable map should now be size 2", 2, m.memo.size());
	}
	
	@Test
	public void testMemoizablePair(){
		MemoizablePair<Integer, Integer, Integer> m = new MemoizablePair<Integer, Integer, Integer>(){
			@Override
			public Integer compute(Integer x, Integer y) {
				return x + y;
		}};

		
		assertEquals("Memoizable map should be empty", 0, m.memo.size());
		m.get(0, 0);
		assertEquals("Memoizable map should be size 1", 1, m.memo.size());
		m.get(0, 0);
		assertEquals("Memoizable map should still be size 1", 1, m.memo.size());
		m.get(1, 0);
		assertEquals("Memoizable map should now be size 2", 2, m.memo.size());
	}
}
