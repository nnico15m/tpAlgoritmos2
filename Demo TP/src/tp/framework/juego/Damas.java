package tp.framework.juego;


import java.awt.Color;

import java.awt.Dimension;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.net.URL;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


//import tp.framework.elementos.GestorFichas;

import tp.framework.elementos.Tablero;


public class Damas extends Juego
{

	/**
	 * 
	 */
	private static final long serialVersionUID=1L;
	
	//private GestorFichas fichas;
	
	public Image imagenFondo;
	public URL rutaFondo;
	
	 boolean negra = false;
     boolean blanca = false;
	
	ImageIcon ICONO1=null;
	
	Damas damas;
	Tablero[][]tablero;
		 
	 ImageIcon ICONO=null;    
	
	public Damas()
	{
		              
		setMinimumSize(new Dimension(600, 600));
		setPreferredSize(new Dimension(600, 600));
		
		setResizable(false);
		getContentPane().setLayout(null);
			
		inicializarTablero();
		
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e)
	{
			
		
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
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
                damas = new Damas();
                damas.setVisible(true);
                
            }
        });
		
	}

	@Override
	public void inicializarTablero()
	{
		 Contador = 0;
	     tablero = Tablero.create();
	    
	     
		
	     for(int i=0;i<8;i++){
	    	 
	    	 for(int j=0;j<8;j++){
	    		 
	    		 tablero[i][j] = new Tablero()
				{
				};
	    		 tablero[i][j].A.setBounds((i*50)+10,(j*50)+10,50,50);
	    		 tablero[i][j].A.addActionListener(this); //Receptor de eventos
	    		 getContentPane().add(tablero[i][j].A);
	    		 getContentPane().add(tablero[i][j].A);
	    		 
	    		 if((i + j) % 2 == 0)tablero[i][j].A.setBackground(Color.lightGray);
	    		 else tablero[i][j].A.setBackground(Color.white);
	    		 
	    		 if(j <= 2)
	    		 {
	    			 if((i + j) % 2 == 0)
	    			 {
	    				 negra = true;
	    				 reiniciarTablero(tablero[i][j]);
	    				 negra = false;
	    			 }
	    			 
	    			 
	    		 }
	    		 
	    		 if(j >= 5)
	    		 {
	    			 if((i + j) % 2 == 0)
	    			 {
	    				 blanca = true;
	    				 reiniciarTablero(tablero[i][j]);
	    				 blanca = false;
	    			 } 
	    			 
	    			 
	    		 }
	    			 
	    		
	    		 
	    		
	    	 }
	    	 
	    	
	     }
	     
	    
	    		
	    		
	    		
	    	
	}

	@Override
	public void reiniciarTablero(Tablero X)
	{
		ImageIcon ICONO=null;
		
		
		if(negra == true){
		ICONO = new ImageIcon(this.getClass().getResource("fichaNs.jpg"));
        X.B=4;
    
		}else
		{
			if(blanca == true){
			ICONO = new ImageIcon(this.getClass().getResource("fichaRs.jpg"));
	        X.B=4;
			}
		}
        ICONO = new ImageIcon(ICONO.getImage().getScaledInstance(90, -1, java.awt.Image.SCALE_DEFAULT));
        X.A.setIcon(ICONO);
        X.A.removeActionListener(this);
		
	}



	@Override
	public void realizarTiro(Tablero X)
	{
		// TODO Auto-generated method stub
		
	}
}
