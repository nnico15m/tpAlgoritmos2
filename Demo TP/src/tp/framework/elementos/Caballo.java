package tp.framework.elementos;
/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may not use this code for any commercial purpose.
*/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.*;


public class Caballo extends PiezaAjedrez
{
	private static final long serialVersionUID = 851797694201220077L;
	
	public Caballo(boolean isWhite, Localizacion loc)
	{
		super(isWhite, loc);
	}
	
	public void draw(Graphics g)
	{
		final int x = 20;
		final int y = 40;
		final int width = 40;
		if(getColor())
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier", g.getFont().getStyle(), width));
			g.fillRect(10, 10, width, width);
			g.setColor(Color.BLACK);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.setFont(new Font("Courier", g.getFont().getStyle(), width));
			g.fillRect(10, 10, width, width);
			g.setColor(Color.WHITE);
		}
		g.drawString("K", x, y);
	}
	
	public ArrayList<Localizacion> getMoves(EstadoTableroAjedrez board)
	{
		ArrayList<Localizacion> possibleMoves = new ArrayList<Localizacion>();
		int y = getLocation().getRow();
		int x = getLocation().getCol();
		
		Localizacion[] locs = new Localizacion[8];
		locs[0] = new Localizacion(y-1,x-2);
		locs[1] = new Localizacion(y-2,x-1);
		locs[2] = new Localizacion(y-2,x+1);
		locs[3] = new Localizacion(y-1,x+2);
		locs[4] = new Localizacion(y+1,x+2);
		locs[5] = new Localizacion(y+2,x+1);
		locs[6] = new Localizacion(y+2,x-1);
		locs[7] = new Localizacion(y+1,x-2);
		
		if(getColor())
		{
			for(Localizacion z: locs)
				if(board.isValid(z) && (board.isEmpty(z) || !board.isPieceWhite(z)))
					possibleMoves.add(z);
		}
		else
		{
			for(Localizacion z: locs)
				if(board.isValid(z) && (board.isEmpty(z) || board.isPieceWhite(z)))
					possibleMoves.add(z);
		}
		return possibleMoves;
	}
	
	public void moveTo(Localizacion moveLoc)
	{
		setLocation(moveLoc);
	}
	
	public String toString()
	{
		return super.toString()+" Knight";
	}
}
