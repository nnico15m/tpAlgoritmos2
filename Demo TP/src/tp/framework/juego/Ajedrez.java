package tp.framework.juego;
import java.awt.event.ActionEvent;

/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may not use this code for any commercial purpose.
*/
import javax.swing.SwingUtilities;

import tp.framework.elementos.GUIRunner;
import tp.framework.elementos.Tablero;


public class Ajedrez extends Juego {
	/**
	 * 
	 */
	private static final long serialVersionUID=1L;

	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean estaTerminado()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void inicializate()
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIRunner run = new GUIRunner();
				run.setVisible();
			}
		});
		
	}

	@Override
	public void inicializarTablero()
	{
		
		
	}

	@Override
	public void realizarTiro(Tablero X)
	{
		// TODO Auto-generated method stub
		
	}
}
