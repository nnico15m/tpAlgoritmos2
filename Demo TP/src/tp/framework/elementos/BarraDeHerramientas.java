package tp.framework.elementos;
/*
G Chess version 1.0
Copyright (c) 2010 Gary Menezes

Copyright Notice
  You may not use this code for any commercial purpose.
*/
import java.awt.*;

import javax.swing.*;


public class BarraDeHerramientas extends JPanel
{
	private static final long serialVersionUID = 956862821780246300L;

	public BarraDeHerramientas(GUI listener, Dimension size)
	{
		setPreferredSize(size);
		setOpaque(true);
		setBackground(new Color(51,102,255));
		
		JButton flip = new JButton("FLIP");
		flip.setPreferredSize(new Dimension(size.width-5, 60));
		flip.setBorder(BorderFactory.createRaisedBevelBorder());
		flip.setBackground(Color.WHITE);
		flip.setToolTipText("Turn Board 180 Degrees");
		
		JButton undo = new JButton("UNDO");
		undo.setPreferredSize(new Dimension(size.width-5, 60));
		undo.setBorder(BorderFactory.createRaisedBevelBorder());
		undo.setBackground(Color.LIGHT_GRAY);
		undo.setToolTipText("Undo Move");
		
		JButton highlight = new JButton("HIGHLIGHT");
		highlight.setPreferredSize(new Dimension(size.width-5, 60));
		highlight.setBorder(BorderFactory.createRaisedBevelBorder());
		highlight.setBackground(Color.WHITE);
		highlight.setToolTipText("Toggle Move Highlighting");
		
		JButton newGameButton = new JButton("Nuevo Juego");
		newGameButton.setPreferredSize(new Dimension(size.width-5, 60));
		newGameButton.setBorder(BorderFactory.createRaisedBevelBorder());
		newGameButton.setBackground(Color.LIGHT_GRAY);
		newGameButton.setToolTipText("New Game");
		
		JButton exitButton = new JButton("EXIT");
		exitButton.setPreferredSize(new Dimension(size.width-5, 60));
		exitButton.setBorder(BorderFactory.createRaisedBevelBorder());
		exitButton.setBackground(Color.WHITE);
		exitButton.setToolTipText("Exit G Chess");
		
		flip.setActionCommand("-20");
		undo.setActionCommand("-21");
		highlight.setActionCommand("-22");
		newGameButton.setActionCommand("-10");
		exitButton.setActionCommand("-11");
		
		flip.addActionListener(listener);
		undo.addActionListener(listener);
		highlight.addActionListener(listener);
		newGameButton.addActionListener(listener);
		exitButton.addActionListener(listener);
		
		//add(title);
						
		add(flip);
		add(undo);
		add(highlight);
		add(newGameButton);
		add(exitButton);
	}
}
