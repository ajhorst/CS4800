package stablematching;

import java.util.List;

public interface Matchable<X> {
	public List<X> getPreferences();
}
