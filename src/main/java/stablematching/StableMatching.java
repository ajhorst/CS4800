package stablematching;

import java.util.Collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Table;

public class StableMatching{

	private StableMatching(){};
	
	public static <X extends Matchable<Y>, Y extends Matchable<X>> BiMap<X, Y> GaleShapley(Collection<X> listX, Collection<Y> listY){
		GaleShapley<X, Y> gs = new GaleShapley<>(listX, listY);
		return gs.match();
	}
	
	private static class GaleShapley<X extends Matchable<Y>, Y extends Matchable<X>>{
		private Collection<X> listX;
		private Collection<Y> listY;
		private Table<X, Y, Boolean> attemptedPairs;
		
		protected GaleShapley(Collection<X> listX, Collection<Y> listY){
			this.listX = listX;
			this.listY = listY;
			validateArgs();
		}
		
		protected BiMap<X, Y> match(){
			BiMap<X, Y> result = HashBiMap.create();
			
			return result;
		}
		
		private void validateArgs(){
			int len1 = listX.size(), len2 = listY.size();
			if (len1 != len2){
				throw new RuntimeException( String.format(
								"Collection arguments to Gale-Shapley matching must be of equal length: were size %d and %d", len1, len2));
			}
			
			String sizeMsg = "Element \"%s\" in Gale-Shapley matching must have a preference list of length %d; was %d";
			
			for( X elem : listX){
				if (elem.getPreferences() == null){
					throw new RuntimeException(String.format("Preferences list for %s was null", elem));
				}
				int elemSize = elem.getPreferences().size();
				if(elemSize != len1){
					throw new RuntimeException(String.format(sizeMsg, elem.toString(), elemSize, len1));
				}
			}
			
			for( Y elem : listY){
				if (elem.getPreferences() == null){
					throw new RuntimeException(String.format("Preferences list for %s was null", elem));
				}
				int elemSize = elem.getPreferences().size();
				if(elemSize != len1){
					throw new RuntimeException(String.format(sizeMsg, elem.toString(), elemSize, len1));
				}
			}
		}
		
		private Y getHighestRemainingChoice(X x){
			for(Y y : x.getPreferences()){
				if(!attemptedPairs.contains(x, y)){
					attemptedPairs.put(x, y, true);
					return y;
				}
			}
			throw new RuntimeException(String.format("Chooser %s ran out of available matches", x.toString()));
		}
	}
}
