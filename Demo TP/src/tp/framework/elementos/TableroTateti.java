package tp.framework.elementos;

public class TableroTateti extends Tablero
{
	int Contador = 0;
	private static Tablero[][] tablero = new Tablero[8][8];
	
	
    
	static public Tablero[][] create()
	{
		return tablero;
		
	}
    
    
}
