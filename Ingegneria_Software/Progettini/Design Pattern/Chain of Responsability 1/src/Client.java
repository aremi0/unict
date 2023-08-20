

public class Client
{
	public static void main(String[] args)
	{
		Ufficiale c = new Capitano(new Maggiore(new Tenente(null)));
		c.check(900);
	}
}
