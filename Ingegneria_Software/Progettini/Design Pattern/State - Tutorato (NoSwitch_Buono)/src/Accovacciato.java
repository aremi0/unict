//Concrete State

public class Accovacciato implements Postura
{
	public void fuoco()
	{
		System.out.println("Sono stati inflitti 100 punti danno.");
	}
	
	public void impreca()
	{
		System.out.println("<<insulto elfi>>");
	}
	
	public Postura up()
	{
		return new InPiedi();
	}
	
	public Postura down()
	{
		return new Sdraiato();
	}
}

//Quando è accovacciato può imprecare e può sparare infliggendo 100 punti danno.
