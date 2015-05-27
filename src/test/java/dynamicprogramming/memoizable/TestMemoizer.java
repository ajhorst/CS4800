package dynamicprogramming.memoizable;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class TestMemoizer {

	@Test
	public void testMemoizable(){
		Memoizer<Integer, Integer> m = new Memoizer<Integer, Integer>(){
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
	public void testCloning(){
		Memoizer<Integer, List<Integer>> m = new Memoizer<Integer, List<Integer>>(){
			public List<Integer> compute(Integer x) {
				if(x == 0){
					return Lists.newLinkedList();
				}
				List<Integer> result = this.get(x-1);
				result.add(0);
				return this.get(x-1);
			}
		};
		
		List<Integer> emptyResult = m.get(1);
		assertEquals("result should always be empty", 0, emptyResult.size());
	}
	
	@Test
	public void testMemoizablePair(){
		MemoizerPair<Integer, Integer, Integer> mPair = new MemoizerPair<Integer, Integer, Integer>(){
			@Override
			public Integer compute(Integer x, Integer y) {
				return x + y;
		}};

		
		assertEquals("Memoizable map should be empty", 0, mPair.m.memo.size());
		mPair.get(0, 0);
		assertEquals("Memoizable map should be size 1", 1, mPair.m.memo.size());
		mPair.get(0, 0);
		assertEquals("Memoizable map should still be size 1", 1, mPair.m.memo.size());
		mPair.get(1, 0);
		assertEquals("Memoizable map should now be size 2", 2, mPair.m.memo.size());
	}
}
