package dynamicprogramming.memoizable;

import java.util.HashMap;
import java.util.Map;

import com.rits.cloning.Cloner;

public abstract class Memoizer<X, Y>{
	
	protected Map<X, Y> memo = new HashMap<X, Y>();
	private final Cloner cloner = new Cloner();
	
	public Y get(X x){
		if(!memo.containsKey(x)){
			memo.put(x, compute(x));
		}
		return cloner.deepClone(memo.get(x));
		
	}
	
	public abstract Y compute(X x);
}
