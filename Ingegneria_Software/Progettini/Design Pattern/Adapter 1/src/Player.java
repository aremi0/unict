//Client

public class Player
{
	public static void main(String[] args)
	{
		Cavaliere cav = new Adapter();
		cav.attacca(1);
	}
}

//Il client si riferisce alle skill con numeri interi (le attiva con il tastierino numerico).
//La classe RoyalKnight conosce solamente il nome delle skill, perchè ogni player può settarle in qualsiasi tasto numerico.
//Bisogna quindi creare un intermediario tra i due, per farli capire.