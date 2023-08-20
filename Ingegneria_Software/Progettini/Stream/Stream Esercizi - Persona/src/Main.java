import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

public class Main
{
	private final static List<Persona> p = List.of(
			new Persona("Jerry", 26, "Architetto", 44),
			new Persona("Francesca", 22, "Programmatore", 51), 
			new Persona("Ed", 34, "Programmatore", 68), 
			new Persona("Claire", 29, "Tester", 56),
			new Persona("Francies", 25, "Programmatore", 46));
	
	public static void main(String[] args)
	{
		//query1();
		//query2();
		//query3();
		//query4();
		query5();
	}
	
	//Data una liste di buste paga stampare i nomi delle persone e sommare gli importi.
	public static void query5()
	{
		List<BustaPaga> b = p.stream()
				.map(s -> new BustaPaga(s))
				.peek(s -> s.calcolaCostoBase())
				.peek(s -> s.aggiungiBonus())
				.collect(Collectors.toList());
		
		int sm = b.stream()
			.peek(s -> s.stampa())
			.mapToInt(BustaPaga::getImporto)
			.sum();
		
		System.out.println("Totale: \t" + sm);
	}
	
	//Data una lista di persone creare una lista di buste paga e ordinarla per nome di persona.
	public static void query4()
	{
		List<BustaPaga> b = p.stream()
			.map(s -> new BustaPaga(s))
			.peek(s -> s.calcolaCostoBase())
			.peek(s -> s.aggiungiBonus())
			.sorted(Comparator.comparing(s -> s.getPersona().getNome()))
			.collect(Collectors.toList());
			
		b.forEach(s -> s.stampa());
	}
	
	//Creare una lista di nomi di persona e poi creare una lista di pagamenti calcolato in base a ciascuna
	//persona, poi stampare i pagamenti.
	public static void query3()
	{		
		List<Pagamento> pg = p.stream()
				.map(s -> new Pagamento(s, s.getCosto()*30))
				.collect(Collectors.toList());
		
		pg.forEach(s -> System.out.println(s.getPers().getNome() + " : " + s.getImporto()));
	}
	
	//Stampare i ruoli presenti e, per ciascun ruolo, la lista di persone.
	public static void query2()
	{
		p.stream()
			.map(s -> s.getRuolo())
			.distinct()
			.peek(s -> System.out.println("Ruolo: " + s))
			.forEach(s -> p.stream()
					.filter(x -> x.getRuolo().equals(s))
					.forEach(x -> System.out.println(x.getNome())));
	}
	
	//Stampare e contare i nomi dei Programmatori.
	public static void query1()
	{
		long c = p.stream()
			.filter(s -> s.getRuolo().equals("Programmatore"))
			.peek(s -> System.out.println(s.getNome()))
			.count();
		
		System.out.println("Ci sono " + c + " Programmatori.");
	}
}
