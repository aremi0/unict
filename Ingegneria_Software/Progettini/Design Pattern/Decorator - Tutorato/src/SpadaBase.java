

import java.util.ArrayList;
import java.util.List;

public class SpadaBase implements Spada
{
	@Override
	public String getNome()
	{
		return "spada";
	}

	@Override
	public int getDanno()
	{
		return 10;
	}

	@Override
	public List<String> getEffetti() 
	{
		return new ArrayList<String>();
	}
}
