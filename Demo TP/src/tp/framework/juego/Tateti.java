package tp.framework.juego;

import java.awt.event.ActionEvent;

import tp.framework.elementos.Tablero;
import tp.framework.elementos.TableroTateti;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Dimension;

public class Tateti extends Juego
{
	
	Tateti tateti;
	Tablero[][]tablero;
	
	public Tateti() {
		setTitle("TA-TE-TI");
		
		setMinimumSize(new Dimension(320, 340));
		setPreferredSize(new Dimension(320, 340));
		
		setResizable(false);
		getContentPane().setLayout(null);
			
		inicializarTablero();
				
	}

	/**
	 * 
	 */
	private static final long serialVersionUID=1L;

	@Override
	public boolean estaTerminado()
	{
		   boolean Gano= false;
	        int Suma=0;
	        for(int i=0;i<3;i++)
	        {
	            Suma=tablero[i][0].B+tablero[i][1].B+tablero[i][2].B;
	            if(Suma==3 || Suma==12)
	            {
	                Gano=true;
	                break;
	            }
	        }
	        for(int i=0;i<3;i++)
	        {
	            Suma=tablero[0][i].B+tablero[1][i].B+tablero[2][i].B;
	            if(Suma==3 ||Suma==12)
	            {
	                Gano=true;
	                break;
	            } 
	        }
	        Suma=tablero[0][2].B+tablero[1][1].B+tablero[2][0].B;
	        if(Suma==3 ||Suma==12)
	                Gano=true;
	        Suma=0;
	        for(int i=0;i<3;i++)
	            Suma+=tablero[i][i].B;
	         
	        if(Suma==3 ||Suma==12)
	                Gano=true;            
	         
	        return Gano;
	    
				
	}

	@Override
	public void inicializate()
	{
		    
		java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	
	                tateti = new Tateti();
	                tateti.setVisible(true);
	                
	            }
	        });
		
	}

	@Override
	public void inicializarTablero()
	{
		 Contador = 0; 
		 tablero = TableroTateti.create();
	     
	     for(int i=0;i<3;i++){
	    	 
	    	 for(int j=0;j<3;j++){
	    		 
	    		 tablero[i][j] = new TableroTateti();
	    		 tablero[i][j].A.setBounds((i*100)+10,(j*100)+10,100,100);
	    		 tablero[i][j].A.addActionListener(this); //Receptor de eventos
	    		 getContentPane().add(tablero[i][j].A);
	    	 }	    	 
	     }	     	     
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
				
		for(int i=0;i<3;i++){
			
			for(int j=0;j<3;j++){
				
				if (e.getSource()==tablero[i][j].A){
					
					if(Contador%2 ==0){
						
						//JOptionPane.showMessageDialog(null,this.getNombreJugador1());
						
					}else{
						
						//JOptionPane.showMessageDialog(null,"El jugador2" + this.getJugador2().getJugadorNombre());
						
					}
					
					realizarTiro(tablero[i][j]);
					
					if(estaTerminado()){
						
						JOptionPane.showMessageDialog(null,"Juego Terminado: Has ganado,Felicitaciones!!!"); //Ver como hacer para que muestre los jugadores
						
					}else{
						
						if(Contador == 8)
						JOptionPane.showMessageDialog(null,"Juego Termindo: Es un empate!!!");
						
					}
					Contador++;
				} 
                			
			}			
		}
		
	}

	@Override
	public void realizarTiro(Tablero X)
	{
		ImageIcon ICONO=null;
        if(Contador%2==0)
        {
            ICONO = new ImageIcon(this.getClass().getResource("homero.png"));
            X.B=1;
        }
        else
        {
            ICONO = new ImageIcon(this.getClass().getResource("flanders.jpeg"));
            X.B=4;
        }
        
        ICONO = new ImageIcon(ICONO.getImage().getScaledInstance(90, -1, java.awt.Image.SCALE_DEFAULT));
        X.A.setIcon(ICONO);
        X.A.removeActionListener(this);
		
	}

	

	
	
}
