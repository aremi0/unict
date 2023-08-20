//Adapter

public class Adapter implements Cavaliere
{
	private RoyalKnight rk;
	
	public Adapter()
	{
		rk = new RoyalKnight();
	}
	
	public void attacca(int skill)
	{
		if(skill == 0) rk.attacco("mana");
		else if(skill == 1) rk.attacco("doble");
		else rk.attacco("triple");
	}
}
