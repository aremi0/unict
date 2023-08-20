

public class Garage
{	
	public static Auto getBerlina()
	{
		return new Berlina(new Rail());
	}
	
	public static Auto getMini()
	{
		return new Mini(new Fire());
	}
}
