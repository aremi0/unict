//Concrete Observer

import java.util.ArrayList;
import java.util.List;

public class SpiaDellaGilda implements Spia
{
	private List<Eroe> targets;
	private TipoEroe tipo;
	private Taverna t;
	
	public SpiaDellaGilda(TipoEroe tp, Taverna riferimento)
	{
		targets = new ArrayList<>();
		tipo = tp;
		t = riferimento;
	}
	
	public void nuovoEroe()
	{
		targets = t.getEroi();
		
		for(Eroe h : targets)
			if(h.tipo() == tipo) System.out.println("La spia dei *" + tipo + "* sta spiando *" + h.nome() + "*.");
	}
}
