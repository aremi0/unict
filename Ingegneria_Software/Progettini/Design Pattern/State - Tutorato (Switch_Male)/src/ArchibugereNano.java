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
		switch(p.getName())
		{
			case "InPiedi":
			{
				p = new Salto();
				break;
			}
			case "Accovacciato":
			{
				p = new InPiedi();
				break;
			}
			case "Sdraiato":
			{
				p = new Accovacciato();
				break;
			}
			case "Salto":
			{
				System.out.println("Il nano ha saltato troppo e si è sfracellato al suolo");
				p = new Sdraiato();
				break;
			}
		}
	}
	
	public void abbassa()
	{
		switch(p.getName())
		{
			case "InPiedi":
			{
				p = new Accovacciato();
				break;
			}
			case "Accovacciato":
			{
				p = new Sdraiato();
				break;
			}
			case "Sdraiato":
			{
				System.out.println("Non puoi abbassarti mentre sei accovacciato.");
				break;
			}
			case "Salto":
			{
				p = new Salto();
				break;
			}
		}
	}
}
