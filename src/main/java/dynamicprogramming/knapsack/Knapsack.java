package dynamicprogramming.knapsack;

import java.util.ArrayList;
import java.util.List;

import dynamicprogramming.memoizable.MemoizerPair;

public class Knapsack extends MemoizerPair<Integer, Integer, List<Item>>{

	private final Item[] items;
	private final int maxCapacity;
	
	public Knapsack(int maxCapacity, Item... items){
		this.maxCapacity = maxCapacity;
		this.items = items;
	}
	
	public List<Item> solve(){
		return this.get(items.length -1, this.maxCapacity);
	}
	
	@Override
	public List<Item> compute(Integer index, Integer capacity) {
		if(index < 0){
			return new ArrayList<Item>(); // empty 
		}
		Item currentItem = items[index];
		if(capacity < currentItem.weight){ // not enough room, skip this item
			return this.get(index-1, capacity);
		}
		List<Item> branch1 = this.get(index -1, capacity); // outcome of skipping this item
		List<Item> branch2 = this.get(index -1, capacity - currentItem.weight);
		branch2.add(currentItem); // outcome of including this item
		return sumValue(branch1) > sumValue(branch2) ? branch1 : branch2;
	}
	
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
	
	public boolean equals(Object obj){
		if (!(obj instanceof Item)){
			return false;
		}
		Item other = (Item)obj;
		return this.value == other.value && this.weight == other.weight;
	}
}
