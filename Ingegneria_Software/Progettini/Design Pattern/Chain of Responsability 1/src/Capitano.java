//ConcreteHandler

public class Capitano extends Ufficiale
{
	public Capitano(Ufficiale succ)
	{
		this.successore = succ;
	}
	
	public void check(int paga)
	{
		if(paga > 1000)
			successore.check(paga);
		else
			System.out.println("Il Capitano viene pagato: " + paga);
	}
}
