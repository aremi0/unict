
public class Mini implements Auto
	{
		private String tipo;
		private float peso;
		private Motore m;
		
		public Mini(Motore m)
		{
			tipo = "Mini";
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
