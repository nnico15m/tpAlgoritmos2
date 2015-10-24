package tp.framework.elementos;
/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may not use this code for any commercial purpose.
*/
import java.awt.event.*;
import javax.swing.*;

public class ActualizacionDinamicaBarraDeEstado extends MouseAdapter
{
	private GUI gui;
	
	public ActualizacionDinamicaBarraDeEstado(GUI gui)
	{
		this.gui = gui;
	}
	
	public void mouseEntered(MouseEvent e)
	{
		JButton temp = (JButton)e.getComponent();
		gui.dynamicUpdateStatusBar(temp);
	}
	public void mouseExited(MouseEvent e)
	{
		gui.updateStatusBar(" ", false);
	}
}
