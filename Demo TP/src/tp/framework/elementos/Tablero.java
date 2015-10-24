package tp.framework.elementos;

import javax.swing.JButton;

public abstract class Tablero
{

	public JButton A;
	public int B;
	
	private static Tablero[][] tablero = new Tablero[8][8];


	public Tablero()
	{
		A = new JButton();
		B=0;		
	}
	
	

	static public Tablero[][] create()
	{
		return tablero;
		
	}	
}
