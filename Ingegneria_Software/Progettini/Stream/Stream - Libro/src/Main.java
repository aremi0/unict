import java.util.stream.Stream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.Map;
import java.util.function.Function;

		//query0(); // Stampare 10 libri (usando generate)
		//query1(); // Contare tutti i libri CYBERPUNK
		//query2(); // Lista dei titoli dei libri CYBERPUNK o FANTASY 
		//query3(); // Somma dei costi di tutti i libri
		//query4(); // Somma in dollari dei costi di tutti i libri (1 EUR = 1.12 USD)
		//query5(); // Stampa tutti i libri con prezzo compreso tra 10â‚¬ e 15â‚¬
		//query6(); // Titolo del Libro meno caro (ma a partire da 12â‚¬)
		//query7(); // Stampa la lista dei libri ordinati per prezzo
		//query8(); // Stampa il conteggio vendite per ogni libro (senza groupingBy)
		//query9(); // Stampa i libri raggruppati per categoria (senza groupingBy)
		//query10(); // Creare una lista di libri fantasy da "Harry Potter 1" a "Harry Potter 7", tutti da 15 euro 
		//query11(); // Sfruttando il metodo precedente, ottenere la lista di libri e mescolarla in ordine casuale
		//query12(); // Data una lista di libri, stampare il primo che ha un prezzo maggiore del precedente (Funzione Pura)
		//query13(); // Data una lista di libri, stampare il primo che ha un prezzo maggiore del precedente (Funzione Non Pura)
		//query14(); // Data una lista di libri, applicare ad ogni libro uno sconto che dipende dalla categoria
				   // -	FANTASY -5â‚¬
				   // -	CYBERPUNK -10%
				   // -	THRILLER -50%
		           // -	STORICO -0â‚¬
		           // -	INFORMATICA x2+10â‚¬ (niente sconti per noi programmatori...)

public class Main
{
	public static void main(String[] args)
	{
		//query0();
		//query1();
		//query2();
		//query3();
		//query4();
		//query5();
		//query6();
		//query7();
		//query8();
		//query9();
		//query10();
		//query11();
		query12();
	}
	
	public static void query12()
	{
		List<Libro> l = Stream.generate(Libreria::gen)
				.limit(10)
				.collect(Collectors.toList());
		
		boolean c = IntStream.rangeClosed(0, l.size()-1)
			.filter(i -> l.get(i+1).getPrezzo() > l.get(i).getPrezzo())
			.mapToObj(i -> l.get(i))
			.findAny()
			.isEmpty();
		
		if(c) System.out.println("Ordinata");
		else System.out.println("Non ordinata");
	}
	
	public static void query11()
	{
		List<Libro> hp = IntStream.rangeClosed(1, 7)
				.mapToObj(i -> new Libro("HP " + i, 15, Categoria.Fantasy))
				.collect(Collectors.toList());
		
		Stream.generate(() -> (int)(Math.random() * hp.size()))
			.distinct()
			.limit(hp.size())
			.forEach(i -> System.out.println(hp.get(i)));
	}
	
	public static void query10()
	{
		List<Libro> hp = IntStream.rangeClosed(1, 7)
				.mapToObj(i -> new Libro("HP " + i, 15, Categoria.Fantasy))
				.collect(Collectors.toList());
		
		hp.forEach(System.out::println);
	}
	
	public static void query9()
	{
		List<Libro> l = Stream.generate(Libreria::gen)
			.limit(10)
			.collect(Collectors.toList());
		
		l.stream()
			.map(Libro::getTipo)
			.distinct()
			.peek(x -> System.out.print("Categoria " + x + " : "))
			.forEach(x -> l.stream()
					.filter(s -> s.getTipo() == x)
					.map(Libro::getNome)
					.forEach(System.out::println));
	}
	
	public static void query8()
	{
		List<Libro> l = Stream.generate(Libreria::gen)
				.limit(10)
				.collect(Collectors.toList());
		
		l.stream()
			.map(Libro::getNome)
			.distinct()
			.peek(x -> System.out.print("Libro -> " + x + " : "))
			.forEach(x -> System.out.println(
					l.stream()
						.filter(s -> s.getNome().equals(x))
						.count()));
	}
	
	public static void query7()
	{
		Stream.generate(Libreria::gen)
			.limit(10)
			.sorted(Comparator.comparing(Libro::getPrezzo))
			.forEach(System.out::println);
	}
	
	public static void query6()
	{
		Optional<Libro> l = Stream.generate(Libreria::gen)
				.limit(10)
				.filter(x -> x.getPrezzo() >= 12)
				.min(Comparator.comparing(Libro::getPrezzo));
		
		System.out.println(l.get().getNome());
	}
	
	public static void query5()
	{
		Stream.generate(Libreria::gen)
			.limit(10)
			.filter(x -> x.getPrezzo() >= 10)
			.filter(x -> x.getPrezzo() <= 15)
			.forEach(System.out::println);
	}
	
	public static void query4()
	{
		double sum = Stream.generate(Libreria::gen)
				.limit(10)
				.map(Libro::getPrezzo)
				.reduce(0.0, (v, accum) -> v*1.2 + accum);
		
		System.out.println(sum);
	}
	
	public static void query3()
	{
		int sum = Stream.generate(Libreria::gen)
				.limit(10)
				.mapToInt(x -> (int)x.getPrezzo())
				.sum();
		
		System.out.println(sum);
	}
	
	public static void query2()
	{
		List<String> titoli = Stream.generate(Libreria::gen)
				.limit(10)
				.filter(x -> x.getTipo() == Categoria.Cyberpunk || x.getTipo() == Categoria.Fantasy)
				.map(Libro::getNome)
				.collect(Collectors.toList());
		
		titoli.forEach(System.out::println);
	}
	
	public static void query1()
	{
		Stream.generate(Libreria::gen)
			.limit(10)
			.filter(x -> x.getTipo() == Categoria.Cyberpunk)
			.forEach(System.out::println);
	}
	
	public static void query0()
	{
		Stream.generate(Libreria::gen)
			.limit(10)
			.forEach(System.out::println);
	}
}
