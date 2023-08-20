

public class Libreria
{
	private static Libro[] pool = new Libro[] 
	{
		new Libro("Leviathan", 20.90, Categoria.Fantasy),
		new Libro("Trono di Spade", 40, Categoria.Fantasy),
		new Libro("Signore degli Anelli", 25.20, Categoria.Fantasy),
			
		new Libro("Neuromante", 14.10, Categoria.Cyberpunk),
		new Libro("Monnalisa", 25, Categoria.Cyberpunk),
		new Libro("Mirrorshades", 8.56, Categoria.Cyberpunk),
			
		new Libro("Profondo Blu", 8.40, Categoria.Thriller),
		new Libro("Invasion", 18, Categoria.Thriller),
		new Libro("Il Collezionista D'Ossa", 10, Categoria.Thriller),
			
		new Libro("The call of Cthulhu", 34, Categoria.Horror),
		new Libro("IT", 36, Categoria.Horror),
		new Libro("Design Pattern", 35, Categoria.Informatica)
	};
	
	public static Libro gen()
	{
		int i = (int) (Math.random() * pool.length);
		return pool[i];
	}
}
