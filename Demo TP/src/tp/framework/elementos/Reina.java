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


public class Reina extends PiezaAjedrez
{
	private static final long serialVersionUID = 716804568379608862L;
	
	public Reina(boolean isWhite, Localizacion loc)
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
			g.fillOval(10, 10, width, width);
			g.setColor(Color.BLACK);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.setFont(new Font("Courier", g.getFont().getStyle(), width));
			g.fillOval(10, 10, width, width);
			g.setColor(Color.WHITE);
		}
		g.drawString("Q", x, y);
	}
	
	public ArrayList<Localizacion> getMoves(EstadoTableroAjedrez board)
	{
		ArrayList<Localizacion> possibleMoves = new ArrayList<Localizacion>();
		int y = getLocation().getRow();
		int x = getLocation().getCol();
		
		boolean[] isRowBlocked = new boolean[8];
		
		Localizacion[] locs = new Localizacion[64];
		
		for(int z=1; z<=64; z++)
		{
			if(z<=8)
				locs[z-1] = new Localizacion(y-z%8, x-z%8);
			else if(z<=16)
				locs[z-1] = new Localizacion(y-z%8, x);
			else if(z<=24)
				locs[z-1] = new Localizacion(y-z%8, x+z%8);
			else if(z<=32)
				locs[z-1] = new Localizacion(y, x+z%8);
			else if(z<=40)
				locs[z-1] = new Localizacion(y+z%8, x+z%8);
			else if(z<=48)
				locs[z-1] = new Localizacion(y+z%8, x);
			else if(z<=56)
				locs[z-1] = new Localizacion(y+z%8, x-z%8);
			else
				locs[z-1] = new Localizacion(y, x-z%8);
		}

		if(getColor())
		{
			for(int i=0; i<locs.length; i++)
				if(board.isValid(locs[i]) && !isRowBlocked[i/8] && (board.isEmpty(locs[i]) || !board.isPieceWhite(locs[i])))
				{
					possibleMoves.add(locs[i]);
					if(!board.isEmpty(locs[i]) && !board.isPieceWhite(locs[i]))
						isRowBlocked[i/8]=true;
				}
				else
					isRowBlocked[i/8]=true;
		}
		else
		{
			for(int i=0; i<locs.length; i++)
				if(board.isValid(locs[i]) && !isRowBlocked[i/8] && (board.isEmpty(locs[i]) || board.isPieceWhite(locs[i])))
				{
					possibleMoves.add(locs[i]);
					if(!board.isEmpty(locs[i]) && board.isPieceWhite(locs[i]))
						isRowBlocked[i/8]=true;
				}
				else
					isRowBlocked[i/8]=true;
		}
		return possibleMoves;
	}

	public void moveTo(Localizacion moveLoc)
	{
		setLocation(moveLoc);
	}
	
	public String toString()
	{
		return super.toString()+" Queen";
	}
}
