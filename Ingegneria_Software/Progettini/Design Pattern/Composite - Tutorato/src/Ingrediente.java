


public class Ingrediente extends Pietanza
{
	private int quantita; // in grammi
	private int calorie; // per 100g

	public Ingrediente(String nome, int quantita, int calorie) 
	{
		this.nome = nome;
		this.quantita = quantita;
		this.calorie = calorie;
	}

	@Override
	public void mostra(String indenta) 
	{
		System.out.format(indenta + "- %dg %s (%d kcal per 100g)\n", quantita, nome, calorie);
	}

	@Override
	public int calcolaCalorie() 
	{
		return calorie * quantita / 100;
	}
}
