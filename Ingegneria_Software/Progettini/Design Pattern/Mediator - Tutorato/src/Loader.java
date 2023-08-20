

import java.util.List;

public class Loader extends Colleague
{
	List<Integer> values;
	
	public Loader(Mediator m) {
		super(m);
	}
	
	public void loadValues() {
		values = List.of(10, 20, 15, 13);
		completed("load");
	}
	
	public List<Integer> getValues() {
		return values;
	}
}
