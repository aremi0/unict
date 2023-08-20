//Adaptee

public class RoyalKnight
{
	public void attacco(String nome)
	{
		if(nome == "mana") manaBreak();
		else if(nome == "double") doubleBash();
		else tripleBash();
	}
	
	public void manaBreak()
	{
		System.out.println("Mana-Break ha inflitto 360 punti danno.");
	}
	
	public void doubleBash()
	{
		System.out.println("Double-Bash ha inflitto 590 punti danno.");
	}
	
	public void tripleBash()
	{
		System.out.println("Triple-Bash ha inflitto 710 punti danno.");
	}
}
