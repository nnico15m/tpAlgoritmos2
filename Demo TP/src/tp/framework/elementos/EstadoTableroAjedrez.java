package tp.framework.elementos;
/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may not use this code for any commercial purpose.
*/
import java.util.ArrayList;
import java.io.*;


@SuppressWarnings("serial")
public class EstadoTableroAjedrez implements Serializable
{
	private PiezaAjedrez[][] boardState;
	private PiezaAjedrez takenPiece;
	private boolean isWhiteTurn;
	private ArrayList<PiezaAjedrez[][]> savedStates;
	private int savedStateQueue;
	
	public EstadoTableroAjedrez()
	{
		boardState = new PiezaAjedrez[8][8];
		savedStates = new ArrayList<PiezaAjedrez[][]>();
		savedStateQueue = 0;
		resetBoardState();
	}
	
	public PiezaAjedrez[][] getState()
	{
		return boardState;
	}
	
	public boolean isPieceWhite(Localizacion loc)
	{
		return boardState[loc.getRow()][loc.getCol()].getColor();
	}
	
	public void setTurn(boolean isWhite)
	{
		isWhiteTurn = isWhite;
	}
	
	public boolean getTurn()
	{
		return isWhiteTurn;
	}
	
	public void resetOtherPawns(Localizacion currentPiece)
	{
		for(int y=0; y<boardState.length; y++)
			for(int x=0; x<boardState.length; x++)
			{
				Localizacion loc = new Localizacion(y,x);
				if(isValid(loc) && !isEmpty(loc) && boardState[y][x] instanceof Pawn)
					if(!currentPiece.equals(loc))
						((Pawn)boardState[y][x]).setDoubleMove(false);
			}
	}
	
	public int saveState()
	{
		savedStates.add(new PiezaAjedrez[8][8]);
		for(int y=0; y<boardState.length; y++)
			for(int x=0; x<boardState.length; x++)
				savedStates.get(savedStateQueue)[y][x]=PiezaAjedrez.clone(boardState[y][x]);
		savedStateQueue++;
		return savedStateQueue-1;
	}
	
	public void undoMove(int queueNumber)
	{
		for(int y=0; y<boardState.length; y++)
			for(int x=0; x<boardState.length; x++)
					boardState[y][x]=savedStates.get(queueNumber)[y][x];
	}
	
	public void moveFrom_To(Localizacion start, Localizacion end)
	{
		boardState[start.getRow()][start.getCol()].moveTo(end);
		PiezaAjedrez temp = boardState[start.getRow()][start.getCol()];
		takenPiece = boardState[end.getRow()][end.getCol()];
		boardState[start.getRow()][start.getCol()] = null;
		boardState[end.getRow()][end.getCol()] = temp;
		
		if(takenPiece == null && start.getCol()!=end.getCol() && temp instanceof Pawn)
			if(temp.getColor())
			{
				Localizacion EP = new Localizacion(end.getRow()+1, end.getCol());
				if(!isEmpty(EP) && !boardState[EP.getRow()][EP.getCol()].getColor() && boardState[EP.getRow()][EP.getCol()] instanceof Pawn)
					boardState[EP.getRow()][EP.getCol()] = null;
			}
			else
			{
				Localizacion EP = new Localizacion(end.getRow()-1, end.getCol());
				if(!isEmpty(EP) && boardState[EP.getRow()][EP.getCol()].getColor() && boardState[EP.getRow()][EP.getCol()] instanceof Pawn)
					boardState[EP.getRow()][EP.getCol()] = null;
			}
		if(takenPiece == null && temp instanceof Rey)
			if(start.getCol()-end.getCol() == 2)
			{
				boardState[end.getRow()][end.getCol()-2].moveTo(new Localizacion(end.getRow(), end.getCol()+1));
				moveFrom_To(new Localizacion(end.getRow(), end.getCol()-2), new Localizacion(end.getRow(), end.getCol()+1));
			}
			else if(start.getCol()-end.getCol() == -2)
			{
				boardState[end.getRow()][end.getCol()+1].moveTo(new Localizacion(end.getRow(), end.getCol()-1));
				moveFrom_To(new Localizacion(end.getRow(), end.getCol()+1), new Localizacion(end.getRow(), end.getCol()-1));	
			}
	}
		
	public boolean isValid(Localizacion loc)
	{
		if( (loc.getRow() < boardState.length && loc.getRow() >= 0) && (loc.getCol() < boardState.length && loc.getCol() >=0) )
			return true;
		return false;
	}
	
	public boolean isEmpty(Localizacion loc)
	{
		if(!isValid(loc))
			return false;
		if(boardState[loc.getRow()][loc.getCol()]==null)
			return true;
		return false;
	}
	
	public void addQueen(Localizacion loc)
	{
		boardState[loc.getRow()][loc.getCol()] = new Reina(boardState[loc.getRow()][loc.getCol()].getColor(), new Localizacion(loc.getRow(),loc.getCol()));
	}
	
	public void addRook(Localizacion loc)
	{
		boardState[loc.getRow()][loc.getCol()] = new Torre(boardState[loc.getRow()][loc.getCol()].getColor(), new Localizacion(loc.getRow(),loc.getCol()));
	}
	
	public void addBishop(Localizacion loc)
	{
		boardState[loc.getRow()][loc.getCol()] = new Alfil(boardState[loc.getRow()][loc.getCol()].getColor(), new Localizacion(loc.getRow(),loc.getCol()));
	}
	
	public void addKnight(Localizacion loc)
	{
		boardState[loc.getRow()][loc.getCol()] = new Caballo(boardState[loc.getRow()][loc.getCol()].getColor(), new Localizacion(loc.getRow(),loc.getCol()));
	}
	
	public void resetBoardState()
	{
		takenPiece=null;
		savedStates = new ArrayList<PiezaAjedrez[][]>();
		savedStateQueue = 0;
		for(int y=0; y<boardState.length; y++)
			for(int x=0; x<boardState.length; x++)
			{
				if(y==0)
					if(x==0 || x==7)
						boardState[y][x] = new Torre(false, new Localizacion(y,x));
					else if(x==1 || x==6)
						boardState[y][x] = new Caballo(false, new Localizacion(y,x));
					else if(x==2 || x==5)
						boardState[y][x] = new Alfil(false, new Localizacion(y,x));
					else if(x==3)
						boardState[y][x] = new Reina(false, new Localizacion(y,x));
					else
						boardState[y][x] = new Rey(false, new Localizacion(y,x));
				else if(y==1)
					boardState[y][x] = new Pawn(false, new Localizacion(y,x));
				else if(y==6)
					boardState[y][x] = new Pawn(true, new Localizacion(y,x));
				else if(y==7)
					if(x==0 || x==7)
						boardState[y][x] = new Torre(true, new Localizacion(y,x));
					else if(x==1 || x==6)
						boardState[y][x] = new Caballo(true, new Localizacion(y,x));
					else if(x==2 || x==5)
						boardState[y][x] = new Alfil(true, new Localizacion(y,x));
					else if(x==3)
						boardState[y][x] = new Reina(true, new Localizacion(y,x));
					else
						boardState[y][x] = new Rey(true, new Localizacion(y,x));
				else
					boardState[y][x]=null;
			}
	}
}
