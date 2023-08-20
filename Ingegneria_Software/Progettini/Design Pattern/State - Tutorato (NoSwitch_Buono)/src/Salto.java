//Concrete State

public class Salto implements Postura
{
	public void fuoco()
	{
		System.out.println("Il nano non può sparare mentre salta.");
	}
	
	public void impreca()
	{
		System.out.println("<<insulto elfo>>");
	}
	
	public Postura up()
	{
		return new Sdraiato();
	}
	
	public Postura down()
	{
		return new InPiedi();
	}
}

//Quando è in salto può imprecare ma non può sparare, poi va in piedi.
