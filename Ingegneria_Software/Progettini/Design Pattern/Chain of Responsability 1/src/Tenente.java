

public class Tenente extends Ufficiale
{
	public Tenente(Ufficiale succ)
	{
		this.successore = succ;
	}
	
	public void check(int paga)
	{
		if(paga > 3000)
			System.out.println("Paga troppo alta.");
		else
			System.out.println("Il Tenente viene pagato: " + paga);
	}
}
