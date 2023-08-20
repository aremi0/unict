

public class Berlina implements Auto
{
	private String tipo;
	private float peso;
	private Motore m;
	
	public Berlina(Motore m)
	{
		tipo = "Berlina";
		peso = 1500;
		this.m = m;
	}
	
	public String getTipo()
	{
		return tipo;
	}
	
	public float getPeso()
	{
		return peso;
	}
}
