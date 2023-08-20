//Concrete Product

import java.util.Random;

public class Necromante implements Personaggio
{
	private int hp, mp, atk, def, hitRate, evasione;
	private boolean inVita;
	
	public Necromante()
	{
		hp = mp = 100;
		atk = 810;
		def = 300;
		hitRate = 680;
		evasione = 5;
		inVita = true;
	}
	
	public void attacca(Personaggio enemy)
	{
		System.out.println(getClasse() + " -> attacca: " + enemy.getClasse());
		
		if(!enemy.vivo())
		{
			System.out.println("Il personaggio che vuoi attaccare è già morto.\n");
			return;
		}
		
		Random rand = new Random();
		int randomNum = rand.nextInt((10 - 1) + 1) + 1;
		if(randomNum < enemy.getEva())
		{
			System.out.println("Il nemico ha evitato il colpo.\n");
			return;
		}
		
		int danno = (hitRate * atk) / (enemy.getDef() * 20);
		enemy.damage(danno);
		System.out.println(getClasse() + " ha inflitto " + danno + " danni a " + enemy.getClasse() + ".\n");
	}
	
	public void setVita(boolean set)
	{
		inVita = set;
	}
	
	public void damage(int danno)
	{
		hp -= danno;
		if(hp <= 0) setVita(false);
	}
	
	public String getClasse()
	{
		return "Necromante";
	}
	
	public int getEva()
	{
		return evasione;
	}
	
	public boolean vivo()
	{
		return inVita;
	}
	
	public void cura()
	{
		if(!vivo())
		{
			System.out.println(getClass() + " è morto quindi non può curarsi.\n");
			return;
		}
		
		if(mp >= 40)
		{
			System.out.println(getClass() + " ha ottenuto 40 punti vita.\n");
			mp -=40;
			hp += 40;
		}
		else if(mp > 0)
		{
			hp += mp;
			System.out.println(getClass() + " ha ottenuto " + mp + " punti vita.\n");
			mp = 0;
		}
	}
	
	public int getHP()
	{
		return hp;
	}
	
	public int getDef()
	{
		return def;
	}
	
	@Override
	public String toString()
	{
		return "Necromante: \nHP: "+hp+"\nMP: "+mp+'\n';
	}
}
