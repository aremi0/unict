//Class C

import java.util.ArrayList;
import java.util.List;

public class ListaUtenti
{
	List<Integer> idList = new ArrayList<>();
	
	public boolean checkID(Giocatore g)
	{
		if(idList.contains(g.getID())) return true;
		return false;
	}
	
	public void addID(Giocatore g)
	{
		if(!idList.contains(g.getID())) idList.add(g.getID());
	}
}
