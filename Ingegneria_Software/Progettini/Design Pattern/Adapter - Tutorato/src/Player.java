//Client
//I danni del client vanno da 0 a 10.
//I danni dell'adaptee vanno da 0 a 100, quindi devo adattare nell'adapter i danni inflitti dall'adaptee.

public class Player
{
	public static void main(String[] args)
	{
		Goblin g = new TheGoblin();
		g.tiraFreccia(5);
	}
}
