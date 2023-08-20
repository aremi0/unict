//Class A

public class CheatBuster
{
	public boolean check(Giocatore g)
	{
		if(g.getEquip("Spara-coccodrilli")) return true;
		else if(g.getEquip("Spara-galline")) return true;
		return false;
	}
}
