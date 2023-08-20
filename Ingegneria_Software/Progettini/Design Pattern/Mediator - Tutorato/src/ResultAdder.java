

import java.util.List;

public class ResultAdder extends Colleague
{
	int sum;
	
	public ResultAdder(Mediator m)
	{
		super(m);
	}

	public void add(List<Integer> values)
	{
		this.sum = values.stream().reduce(0, Integer::sum);
		completed("sum");
	}
	
	public int getSum()
	{
		return sum;
	}
}
