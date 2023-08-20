//Context

public class ArchibugereNano
{
	private Postura p;
	
	public ArchibugereNano()
	{
		p = new InPiedi();
	}
	
	public void spara()
	{
		p.fuoco();
	}
	
	public void insultaElfo()
	{
		p.impreca();
	}
	
	public void alza()
	{
		p = p.up();
	}
	
	public void abbassa()
	{
		p.down();
	}
}
