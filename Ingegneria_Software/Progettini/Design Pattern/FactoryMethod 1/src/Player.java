//Client

public class Player
{
	public static void main(String[] args)
	{
		Personaggio p1 = ClasseCreator.getClasse(2);
		Personaggio p2 = ClasseCreator.getClasse(1);
		
		while(p1.vivo() && p2.vivo())
		{
			p1.attacca(p2);
			p2.attacca(p1);
			
			if(p1.getHP() <= 35) p1.cura();
			if(p2.getHP() <= 35) p2.cura();
		}
		
		System.out.println(p1);
		System.out.println(p2);
	}
}
