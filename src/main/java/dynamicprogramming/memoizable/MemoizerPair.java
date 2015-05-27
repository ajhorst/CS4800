package dynamicprogramming.memoizable;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;


public abstract class MemoizerPair<X, Y, Z>{

	protected final Memoizer<Pair<X, Y>, Z> m;
	
	public MemoizerPair(){
		final MemoizerPair<X, Y, Z> pair = this;
		m = new Memoizer<Pair<X, Y>, Z>(){
			public Z compute(Pair<X, Y> xy) {
				return pair.compute(xy.getKey(), xy.getValue());
		}};
	}
	
	public Z get(X x, Y y) {
		return m.get(new ImmutablePair<X, Y>(x, y));
	}

	public abstract Z compute(X x, Y y);
	
}
