package dynamicprogramming.knapsack;

import java.util.List;

public class Knapsack extends MemoizerPair<Integer, Integer, List<Item>>{
	
	protected static int sumValue(List<Item> items, Item... extraItems){
		int result = 0;
		for(Item item : items){
			result += item.value;
		}
		return result;
	}

}

class Item{
	public final Integer value, weight;
	
	public Item(Integer value, Integer weight){
		this.value = value;
		this.weight = weight;
	}
}
