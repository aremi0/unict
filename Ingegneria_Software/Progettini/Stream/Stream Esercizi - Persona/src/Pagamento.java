

public class Pagamento
{
	private Persona pers;
	private int importo;
	
	public Pagamento(Persona p, int v)
	{
		this.pers = p;
		this.importo = v;
	}
	
	public Persona getPers() {return pers;}
	public int getImporto() {return importo;}
}
