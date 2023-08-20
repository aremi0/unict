

public class Colleague
{
	private Mediator m;
	
	public Colleague(Mediator m)
	{
		this.m = m;
	}
	
	public void completed(String task)
	{
		m.taskCompleted(task);
	}
}
