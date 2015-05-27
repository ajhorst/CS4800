package dynamicprogramming.memoizable;

import java.util.HashMap;
import java.util.Map;

public abstract class Memoizer<X, Y> {
	
	protected Map<X, Y> memo = new HashMap<X, Y>();
	
	public Y get(X x){
		if(!memo.containsKey(x)){
			memo.put(x, compute(x));
		}
		return memo.get(x);
		
	}
	
	public abstract Y compute(X x);
}
