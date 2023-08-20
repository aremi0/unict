//Concrete Product

import java.util.Random;

public class Cavaliere implements Personaggio
{
	private int hp, mp, atk, def, hitRate, evasione;
	private boolean inVita;
	
	public Cavaliere()
	{
		hp = mp = 100;
		atk = 680;
		def = 790;
		hitRate = 310;
		evasione = 4;
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
		return "Cavaliere";
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
		return "Cavaliere: \nHP: "+hp+"\nMP: "+mp+'\n';
	}
}
