

public class Eroe
{
	private String nome;
	private TipoEroe tipo;
	
	public Eroe(String n, TipoEroe t)
	{
		nome = n;
		tipo = t;
	}
	
	public String nome()
	{
		return nome;
	}
	
	public TipoEroe tipo()
	{
		return tipo;
	}
}
