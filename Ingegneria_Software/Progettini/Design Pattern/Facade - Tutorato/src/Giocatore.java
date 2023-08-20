//Client

import java.util.ArrayList;
import java.util.List;

public class Giocatore
{
	private int id;
	private List<String> equip;
	
	public Giocatore(int i)
	{
		id = i;
		equip = new ArrayList<>();
	}
	
	public void addEquip(String e)
	{
		if(!equip.contains(e))
			equip.add(e);
	}
	
	public int getID()
	{
		return id;
	}
	
	public boolean getEquip(String e)
	{
		if(equip.contains(e)) return true;
		return false;
	}
}
