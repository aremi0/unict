//Concrete State

public class InPiedi implements Postura
{
	public void fuoco()
	{
		System.out.println("Sono stati inflitti 50 danni.");
	}
	
	public void impreca()
	{
		System.out.println("<<insulto elfo>>");
	}
	
	public Postura up()
	{
		return new Salto();
	}
	
	public Postura down()
	{
		return new Accovacciato();
	}
}

//Quando è in piedi può imprecare e sparare.
