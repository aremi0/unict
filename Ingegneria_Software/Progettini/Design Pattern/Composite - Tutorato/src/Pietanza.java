

public abstract class Pietanza
{
	protected String nome; // posso definire equals
	
	public abstract void mostra(String indenta);
	
	public abstract int calcolaCalorie();
	
	// add, remove not to be called on Leaf
	public Pietanza add(Pietanza p) {return this;}

	public Pietanza remove(Pietanza p) {return this;}
	
	public String getNome() {return nome;}
}
