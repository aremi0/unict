

import java.util.List;

public class Pesante extends Incantesimo
{
	public Pesante(Spada s) 
	{
		super(s);
	}

	@Override
	public String getNome() 
	{
		return super.getNome() + " pesante";
	}

	@Override
	public int getDanno() 
	{
		return super.getDanno() + 5;
	}
}
