package tp.framework.elementos;
/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may not use this code for any commercial purpose.
*/
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public abstract class PiezaAjedrez implements Serializable
{
	private boolean isWhite;
	
	private Localizacion loc;
	
	public PiezaAjedrez(boolean isWhite, Localizacion loc)
	{
		this.isWhite = isWhite;
		this.loc = loc;
	}
	
	public void setLocation(Localizacion loc)
	{
		this.loc = loc;
	}
	
	public Localizacion getLocation()
	{
		return loc;
	}
	
	public boolean getColor()
	{
		return isWhite;
	}
	
	public abstract void draw(Graphics g);
	
	public abstract ArrayList<Localizacion> getMoves(EstadoTableroAjedrez board);
	
	public abstract void moveTo(Localizacion moveLoc);
	
	public static PiezaAjedrez clone(PiezaAjedrez dolly)
	{
		if(dolly instanceof Rey)
		{
			Rey piece = new Rey(dolly.getColor(), dolly.getLocation());
			piece.setHasMoved(((Rey)dolly).getHasMoved());
			piece.setIsChecked(((Rey)dolly).isChecked());
			return piece;
		}
		else if(dolly instanceof Reina)
			return new Reina(dolly.getColor(), dolly.getLocation());
		else if(dolly instanceof Pawn)
		{
			Pawn piece = new Pawn(dolly.getColor(), dolly.getLocation());
			piece.setDoubleMove(((Pawn)dolly).getDoubleMove());
			return piece;
		}
		else if(dolly instanceof Torre)
		{
			Torre piece = new Torre(dolly.getColor(), dolly.getLocation());
			piece.setHasMoved(((Torre)dolly).getHasMoved());
			return piece;
		}
		else if(dolly instanceof Alfil)
			return new Alfil(dolly.getColor(), dolly.getLocation());
		else if(dolly instanceof Caballo)
			return new Caballo(dolly.getColor(), dolly.getLocation());
		else
			return null;
	}
	public String toString()
	{
		if(isWhite)
			return "White";
		else
			return "Black";
	}
}
