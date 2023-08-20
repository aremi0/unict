//Product

public interface Personaggio
{
	public void attacca(Personaggio enemy);
	public void cura();
	public void damage(int danno);
	public boolean vivo();
	public int getDef();
	public void setVita(boolean set);
	public String getClasse();
	public int getEva();
	public int getHP();
}

//hp-mp = 0-100;
//atk-def-hitRate = 0-1000;
//evasione = 1-10