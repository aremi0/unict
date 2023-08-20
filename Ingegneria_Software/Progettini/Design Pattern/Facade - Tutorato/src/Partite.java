//Facade

public class Partite
{	
	CheatBuster cb = new CheatBuster();
	Server s = new Server(100, 56, "DeathMatch");
	ListaUtenti lu = new ListaUtenti();
	
	public void registra(Giocatore g)
	{
		lu.addID(g);
	}
	
	public void partecipa(Giocatore g, String mode)
	{
		if(!cb.check(g) && s.getMode() == "DeathMatch" && lu.checkID(g))
		{
			System.out.println("Il giocatore è entrato in partita.");
		}
		else System.out.println("Il giocatore non è entrato in partita.");
	}
	
	public void stampaServer()
	{
		System.out.println(s);
	}
}
