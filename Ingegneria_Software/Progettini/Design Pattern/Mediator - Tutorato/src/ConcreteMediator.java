

public class ConcreteMediator implements Mediator
{
	Visualiser v = new Visualiser();
	ResultCounter rc = new ResultCounter(this);
	ResultAdder ra = new ResultAdder(this);
	Loader l = new Loader(this);
	
	@Override
	public void taskCompleted(String task)
	{
		if (task.equals("load"))
		{
			rc.count(l.getValues());
			ra.add(l.getValues());
		} 
		else if (task.equals("count")) 
		{
			v.setCount(rc.getCnt());
		} 
		else if (task.equals("sum")) 
		{
			v.setSum(ra.getSum());
		}
	}

	@Override
	public void loadValues()
	{
		l.loadValues();
	}

	@Override
	public void show()
	{
		v.show();
	}
}
