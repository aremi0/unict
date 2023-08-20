

public class BustaPaga
{
	private Persona pers;
	private int totale;
	
	public BustaPaga(Persona p)
	{
		this.pers = p;
		totale = 0;
	}
	
	public void calcolaCostoBase() {totale = pers.getCosto() * 30;}
	public void aggiungiBonus() {totale = (int)Math.round(totale * 1.1);}
	public Persona getPersona() {return pers;}
	public void stampa() {System.out.println(pers.getNome() + "\t\t" + totale + " euro.");}
	public int getImporto() {return totale;}
}
