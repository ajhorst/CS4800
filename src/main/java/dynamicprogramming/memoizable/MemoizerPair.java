package dynamicprogramming.memoizable;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;


public abstract class MemoizerPair<X, Y, Z> extends Memoizer<Pair<X, Y>, Z> {
	
	public abstract Z compute(X x, Y y);
	
	public Z get(X x, Y y){
		Pair<X, Y> p = new ImmutablePair<>(x, y);
		return super.get(p);
	}
	
	@Override
	public Z compute(Pair<X, Y> p){
		return compute(p.getKey(), p.getValue());
	}
}
