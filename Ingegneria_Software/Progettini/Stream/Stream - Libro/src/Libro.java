

public class Libro
{
	private String nome;
	private double prezzo;
	private Categoria tipo;
	
	public Libro(String n, double d, Categoria t)
	{
		this.nome = n;
		this.prezzo = d;
		this.tipo = t;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public double getPrezzo()
	{
		return prezzo;
	}
	
	public Categoria getTipo()
	{
		return tipo;
	}
	
	@Override
	public String toString()
	{
		return "Nome: " + nome + "\nPrezzo: " + prezzo + "\nCategoria: " + tipo + "\n";
	}
}
