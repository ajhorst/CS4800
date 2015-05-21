package stablematching;

import java.util.Collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class StableMatching{

	private StableMatching(){};
	
	public static <X extends Matchable<Y>, Y extends Matchable<X>> BiMap<X, Y> GaleShapley(Collection<X> listX, Collection<Y> listY){
		GaleShapley<X, Y> gs = new GaleShapley<>(listX, listY);
		return gs.match();
	}
	
	private static class GaleShapley<X extends Matchable<Y>, Y extends Matchable<X>>{
		private Collection<X> listX;
		private Collection<Y> listY;
		
		protected GaleShapley(Collection<X> listX, Collection<Y> listY){
			this.listX = listX;
			this.listY = listY;
		}
		
		protected BiMap<X, Y> match(){
			BiMap<X, Y> result = HashBiMap.create();
			return result;
		}
	}
}
