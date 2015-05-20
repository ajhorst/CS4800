package stablematching;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

import org.junit.BeforeClass;
import org.junit.Test;

import stablematching.Matchable;

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