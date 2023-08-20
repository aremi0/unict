//Class B

public class Server
{
	private int capienzaMax;
	private int numGiocatori;
	private String modalità;
	
	public Server(int cm, int ng, String m)
	{
		capienzaMax = cm;
		numGiocatori = ng;
		modalità = m;
	}
	
	public String getMode()
	{
		return modalità;
	}
	
	@Override
	public String toString()
	{
		return "Cap. Max: " + capienzaMax + "\nNum. Giocatore: " + numGiocatori + "\nModalità: " + modalità + '\n';
	}
}
