

import java.util.List;

public class Spettrale extends Incantesimo
{
	public Spettrale(Spada s) 
	{
		super(s);
	}
	
	@Override
	public String getNome() 
	{
		return super.getNome() + " delle tenebre";
	}
	
	@Override
	public List<String> getEffetti() 
	{
		List<String> effetti = super.getEffetti();
		effetti.add("paura");
		return effetti;
	}
}
