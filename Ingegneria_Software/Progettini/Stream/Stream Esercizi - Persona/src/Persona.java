

public class Persona
{
	private String nome, ruolo;
	private int eta, costo;
	
	public Persona(String n, int e, String r, int c)
	{
		this.nome = n;
		this.eta = e;
		this.ruolo = r;
		this.costo  =c;
	}
	
	public int getCosto() {return costo;}
	public int getEta() {return eta;}
	public String getNome() {return nome;}
	public String getRuolo() {return ruolo;}
}
