package tp.framework.juego;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import tp.framework.elementos.Jugador;
import tp.framework.elementos.Tablero;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class Juego extends JFrame implements MouseListener,ActionListener
{
	/**
	 * 
	 */
	public static final long serialVersionUID=1L;
	Tablero [][]tablero;
	private Jugador jugador1;
	private Jugador jugador2;
	private String nombreJugador1 = null;
	private String nombreJugador2 = null;
	int Contador;
		
	public void setJugadores(Jugador j1,Jugador j2)
	{
		setJugador1(j1);
		setJugador2(j2);
		
		System.out.println(getJugador1().getJugadorNombre());
		System.out.println(getJugador2().getJugadorNombre());
		
		setNombreJugador1(getJugador1().getJugadorNombre());
		setNombreJugador2(getJugador2().getJugadorNombre());		
	}

	public abstract boolean estaTerminado();
	
	public abstract void inicializate();
	
	public abstract void inicializarTablero();
	
	public abstract void realizarTiro(Tablero X);

	public Jugador getJugador1()
	{
		return jugador1;
	}

	public void setJugador1(Jugador jugador1)
	{
		this.jugador1 = jugador1;
	}

	public Jugador getJugador2()
	{
		return jugador2;
	}

	public void setJugador2(Jugador jugador2)
	{
		this.jugador2 = jugador2;
	}

	public String getNombreJugador1()
	{
		return nombreJugador1;
	}

	public void setNombreJugador1(String nombreJugador1)
	{
		this.nombreJugador1 = nombreJugador1;
	}

	public String getNombreJugador2()
	{
		return nombreJugador2;
	}

	public void setNombreJugador2(String nombreJugador2)
	{
		this.nombreJugador2 = nombreJugador2;
	}

	public void reiniciarTablero(Tablero X)
	{
		// TODO Auto-generated method stub
		
	}

	public void invocar()
	{
		
		//super ("Cuatro en Raya");
		
	}

}
