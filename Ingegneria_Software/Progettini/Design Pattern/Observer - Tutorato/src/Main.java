

public class Main 
{
	public static void main(String[] args)
	{
		Taverna t = new Taverna();
		t.inviaSpia(new SpiaDellaGilda(TipoEroe.Guerriero, t));
		t.inviaSpia(new SpiaDellaGilda(TipoEroe.Mago, t));
		
		Eroe p1 = new Eroe("Paul", TipoEroe.Guerriero);
		Eroe p2 = new Eroe("Frank", TipoEroe.Mago);
		Eroe p3 = new Eroe("Perk", TipoEroe.Guerriero);
		
		
		t.entra(p1);
		System.out.println();
		t.entra(p2);
		System.out.println();
		t.entra(p3);
	}
}
