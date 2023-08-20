//Subject

import java.util.ArrayList;
import java.util.List;

public class Edificio
{
	private List<Spia> spie = new ArrayList<>();
	
	public void inviaSpia(Spia s) //Attach
	{
		if(!spie.contains(s)) spie.add(s);
	}
	
	public void ritiraSpia(Spia s) //Detach
	{
		if(spie.contains(s)) spie.remove(s);
	}
	
	public void avvertiSpia() //Notify
	{
		for(Spia h : spie)
			h.nuovoEroe();
	}
}
