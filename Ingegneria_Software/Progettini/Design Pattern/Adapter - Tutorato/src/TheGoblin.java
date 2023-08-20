//Adapter

public class TheGoblin extends PelleVerde implements Goblin
{
	public TheGoblin()
	{
		super();
	}
	
	public void tiraFreccia(int damage)
	{
		this.scagliaPezzoDiLegno(damage);
	}
	
	@Override
	public void scagliaPezzoDiLegno(int damage)
	{
		int adaptedDamage = damage*10;
		super.scagliaPezzoDiLegno(adaptedDamage);
	}
}

//L'adapter controlla l'input fornito dal Client e lo adatta (in questo caso moltiplicando x10) fornendolo poi all'implementazione vera e propria del metodo nella superclasse (Adaptee).