

public class TestPietanza 
{
	/* 					!!! DISCLAIMER !!!
	 * Le dosi proposte di seguito sono state inserite solo 
	 * a titolo esemplificativo, e non sono da intendersi come 
	 * dosi veritiere. Il sottoscritto declina ogni responsabilitÃ  
	 * relativa ad indigestioni o allucinazioni per overdose da burro.
	 * 				!!! Don't try this at home !!!
	 */
	public static void main(String[] args) 
	{
		// Ispirati dal nuovo canale su YouTube di MastroCuoco
		// vogliamo cimentarci nella preparazione di autentiche
		// tagliatelle alla bolognese, fatte in casa!
		// Stando pero' sempre attenti alla lista ingredienti
		// e alle calorie totali! (Summer is coming)

		// Prendiamo nota degli ingredienti per le tagliatelle
		Pietanza tagliatelle = Ricettario.getPreparato("tagliatelle")
				.add(Ricettario.getIngrediente("farina", 80))
				.add(Ricettario.getIngrediente("uova", 20))
				.add(Ricettario.getIngrediente("sale", 5))
				.add(Ricettario.getIngrediente("acqua", 50));

		// Per la salsa
		Pietanza salsa = Ricettario.getPreparato("salsa")
				.add(Ricettario.getIngrediente("pomodoro", 60))
				.add(Ricettario.getIngrediente("olio", 10))
				.add(Ricettario.getIngrediente("aglio", 3))
				.add(Ricettario.getIngrediente("basilico", 3));

		// E per il macinato alla cipolla
		Pietanza macinato = Ricettario.getPreparato("macinato alla cipolla")
				.add(Ricettario.getIngrediente("macinato", 80))
				.add(Ricettario.getIngrediente("cipolla", 50))
				.add(Ricettario.getIngrediente("olio", 10))
				.add(Ricettario.getIngrediente("pepe", 2))
				.add(Ricettario.getIngrediente("sale", 3));

		// Mettiamo assieme la salsa e il macinato,
		// e giusto un po' di burro, per preparare il ragu
		Pietanza ragu = Ricettario.getPreparato("ragu")
				.add(macinato)
				.add(salsa)
				.add(Ricettario.getIngrediente("burro", 50));

		// Tutto e' pronto per le nostre tagliatelle alla bolognese!
		Pietanza bolognese = Ricettario.getPreparato("tagliatelle alla bolognese")
				.add(tagliatelle)
				.add(ragu);

		// Euforici, mostriamo la lista ingredienti e le calorie totali
		bolognese.mostra("");
		System.out.println("Calorie totali: " + bolognese.calcolaCalorie());

		// All'improvviso, il dramma! 
		// Abbiamo dimenticato di comprare il macinato. Tutto sembra perduto.
		// Togliamo il ragu e mostriamo nuovamente ingredienti e calorie
		bolognese.remove(ragu);
		System.out.println();
		bolognese.mostra("");
		System.out.println("Calorie totali: " + bolognese.calcolaCalorie());

		// Ma proprio quando abbiamo perso ogni speranza, 
		// dietro la scorta annuale di lenticchie troviamo 
		// una lattina di funghi in scatola e della panna da cucina del '48!
		// Si passa al piano B: tagliatelle ai funghi!
		// Prepariamo la lista per la salsa "panna e funghi"
		Pietanza salsaFunghi = Ricettario.getPreparato("salsa panna e funghi")
				.add(Ricettario.getIngrediente("panna", 40))
				.add(Ricettario.getIngrediente("funghi", 40))
				.add(Ricettario.getIngrediente("olio", 10))
				.add(Ricettario.getIngrediente("prezzemolo", 5))
				.add(Ricettario.getIngrediente("parmigiano", 20));

		// E aggiungiamo la salsa al piatto di prima...
		bolognese.add(salsaFunghi);
		System.out.println();
		bolognese.mostra("");
		System.out.println("Calorie totali: " + bolognese.calcolaCalorie());

		// Tutto e pronto! Si cucina!!! Buon appetito!
	}
}