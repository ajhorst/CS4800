package stablematching;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static utils.Error.newMessage;
import static utils.TestError.assertNoError;
import static utils.TestError.assertError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import stablematching.Matchable;
import utils.Error;

public class StableMatchingTest {

	private static List<Proposer> ps;
	private static List<Acceptor> as;

	private static Proposer v, w, x, y, z;
	private static Acceptor a, b, c, d, e;
	
	@BeforeClass
	public static void setup(){

		v = new Proposer("V");
		w = new Proposer("W");
		x = new Proposer("X");
		y = new Proposer("Y");
		z = new Proposer("Z");
		
		a = new Acceptor("A");
		b = new Acceptor("B");
		c = new Acceptor("C");
		d = new Acceptor("D");
		e = new Acceptor("E");
		
		v.setPreferences(a, b, c, d, e);
		w.setPreferences(b, c, d, a, e);
		x.setPreferences(c, d, a, b, e);
		y.setPreferences(d, a, b, c, e);
		z.setPreferences(a, b, c, d, e);
		
		a.setPreferences(w, x, y, z, v);
		b.setPreferences(x, y, z, v, w);
		c.setPreferences(y, z, v, w, x);
		d.setPreferences(z, v, w, x, y);
		e.setPreferences(v, w, x, y, z);
		
		ps = new ArrayList<>(Arrays.asList( new Proposer[]{v, w, x, y, z}));
		as = new ArrayList<>(Arrays.asList( new Acceptor[]{a, b, c, d, e}));
	}
	
	@Test
	public void testMatchables(){
		int size = 5;
		testMatchable(a, size);
		testMatchable(b, size);
		testMatchable(c, size);
		testMatchable(d, size);
		testMatchable(e, size);

		testMatchable(v, size);
		testMatchable(w, size);
		testMatchable(x, size);
		testMatchable(y, size);
		testMatchable(z, size);
	}
	
	@Test
	public void testGaleShapley(){
		
		BiMap<Proposer, Acceptor> matches = StableMatching.GaleShapley(ps, as);
		Error<Boolean> err = isStableMatching(matches);
		assertNoError(err);
	}
	
	@Test
	public void testIsStableMatching(){
		BiMap<Proposer, Acceptor> unstableMatches = HashBiMap.create();
		unstableMatches.put(v, a);
		unstableMatches.put(w, b);
		unstableMatches.put(x, c);
		unstableMatches.put(y, d);
		unstableMatches.put(z, e);
		Error<Boolean> err = isStableMatching(unstableMatches);
		assertError(err);
		
		// known stable matching from above set
		BiMap<Proposer, Acceptor> stableMatches = HashBiMap.create();
		stableMatches.put(v, e);
		stableMatches.put(w, a);
		stableMatches.put(x, b);
		stableMatches.put(y, c);
		stableMatches.put(z, d);
		err = isStableMatching(stableMatches);
		assertNoError(err);
	}
	
	public Error<Boolean> isStableMatching(BiMap<Proposer, Acceptor> matches){
		for(Proposer originalProposer : matches.keySet()){
			List<Acceptor> preferredAcceptors = originalProposer.getPreferences();
			Acceptor originalAcceptor = matches.get(originalProposer);
			// look at all preferred acceptors for this proposer
			for(int i = 0; i < preferredAcceptors.indexOf(originalAcceptor); i++){
				Acceptor preferredAcceptor = preferredAcceptors.get(i);
				// if an acceptor prefer this proposer to their match, not stable
				List<Proposer> acceptorPrefs = preferredAcceptor.getPreferences();
				Proposer currentMatch = matches.inverse().get(preferredAcceptor);
				int currentMatchPriority = acceptorPrefs.indexOf(currentMatch);
				int newSuitorPriority = acceptorPrefs.indexOf(originalProposer);
				if(newSuitorPriority < currentMatchPriority){
					return newMessage(false, "%s and %s prefer each other to their current pairing: (%s,%s) and (%s,%s)",
							originalProposer, preferredAcceptor, originalProposer, originalAcceptor, currentMatch, preferredAcceptor);
				}
			}
		}
		
		return newMessage(true, null);
	}
	
	public <X> void testMatchable(Matchable<X> m, int size){
		assertNotNull(format("%s preferences should not be null", m), m.getPreferences());
		assertEquals(format("%s should have %d matchabes, had %d", m, size, m.getPreferences().size()), m.getPreferences().size(), size );
		for(int i = 0; i < size; i++){
			assertNotNull(format("%s preference #d should not be null", m, i), m.getPreferences().get(i));
		}
	}
}

class MatchableTest<X> implements Matchable<X>{

	private final String name;
	private List<X> preferences;
	
	public MatchableTest(String name){
		this.name = name;
	}
	
	@SuppressWarnings("unchecked")
	public void setPreferences(X... preferences){
		this.preferences = new ArrayList<>(Arrays.asList(preferences));
	}
	
	@Override
	public List<X> getPreferences() {
		return this.preferences;
	}

	@Override
	public String toString(){
		return this.name;
	}	
}


class Proposer extends MatchableTest<Acceptor>{
	public Proposer(String name) {	super(name);	}
}

class Acceptor extends MatchableTest<Proposer>{
	public Acceptor(String name) {	super(name);	}
}