//Creator/Concrete Creator

public class ClasseCreator
{
	public static Personaggio getClasse(int tipo)
	{
		if(tipo == 0) return new Ranger();
		else if(tipo == 1) return new Cavaliere();
		return new Necromante();
	}
}
