package tp.framework.juego;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import tp.framework.elementos.Tablero;
 
public class CuatroRaya extends Juego 
	{
		/**
	 * 
	 */
	private static final long serialVersionUID=1L;
		JButton Comienza =new JButton("Comenzar");
		JButton Salir =new JButton("Salir");
		int estados[][] =new int[7][7];
		int Turno;
		int victorias;
		int derrotas;
		boolean jugando;
		boolean unjugador;
		Panel PanelPrincipal = new Panel();
 
		public CuatroRaya() 
			{
				
				getFrames();
				int x,y;
				getContentPane().setLayout(new GridLayout(20,5));
				addMouseListener(this);
				Comienza.addMouseListener(this);
				Salir.addMouseListener(this);
			
		
				PanelPrincipal.add (Comienza);
				getContentPane().add (PanelPrincipal);
				victorias=0;
				derrotas=0;
				jugando=false;
				unjugador=true;
				addWindowListener(new WindowAdapter()
					{
						public void windowClosing(WindowEvent we)
						{
							System.exit(0);
						}
					});
			}
 
		public void PintaCasilla (int x)
			{
				Graphics g= getGraphics();
				int Resultados[][]=new int[3][3];
				int result;
				int z=0;
				int y;
				for (z=0;z<7;z++)
					{
						if (estados[x][z]!=0)
						{
							break;
						}
					}
				if (z==0)
					y=7;
				else
					y=z-1;
 
				if ((y==6 && estados[x][y]==0) || ((y<6) && (estados[x][y+1] !=0) && (estados[x][y]==0)))
					{
						if (Turno%2==0)
							{
								g.setColor(Color.red);
								estados [x][y]=-1;
								
						
									
							}
						else
							{
								g.setColor(Color.blue);
								estados [x][y]=1;
								
							}
						g.fillOval((x*90)+20,(y*80)+100,70,70);
						Turno++;
					
 
						if (CompruebaCasilla(x,y,Resultados))
							Ganaste(estados[x][y]);
						else
						{
						if (Turno%2!=0)
							{
					/*			if (unjugador)
								{
//									result=JuegaIA(0);
									if (result>=10)
									{
										System.out.println("la IA decidi�: " + Integer.toString((result/10)-1));
										PintaCasilla((result/10)-1);
									}
									else
									{
										System.out.println("la IA decidi�: " + Integer.toString(result));
										PintaCasilla(result);
									}
								}*/
							}
						}
					}
			}
		public boolean CompruebaCasilla(int posx, int posy, int cuantas[][])
 			{
				int jugador=estados[posx][posy];
				int x,y;
			//System.out.println("entr� en Compruebacasilla el Jugador: " + jugador + " y la casilla x: "+Integer.toString(posx) + "  y: "+Integer.toString(posy));
				for (x=(-1);x<2;x++)
					for (y=(-1);y<2;y++)
						if (x!=0 || y!=0)
							{
								cuantas[x+1][y+1]=CompruebaLinea(posx,posy,x,y);
								if (cuantas[x+1][y+1]>=4)
									return true;
							}
				return false;
			}
		public int CompruebaLinea(int posx, int posy, int x, int y)
			{
				int tempx, tempy;
				int val;
				int cuantas=1;
				int jugador=estados[posx][posy];
				tempx=posx+x;
				tempy=posy+y;
				while ((tempy>=0 && tempx>=0) && (tempx<7 && tempy<7))
					{
						if (estados[tempx][tempy]==jugador)
							{
								cuantas++;
								tempx+=x;
								tempy+=y;
							}
						else
							tempx=(-1);
					}
				tempx=posx-x;
				tempy=posy-y;
				while ((tempy>=0 && tempx>=0) && (tempx<7 && tempy<7))
					{
						if (estados[tempx][tempy]==jugador)
							{
								cuantas++;
								tempx-=x;
								tempy-=y;
							}
						else
							tempx=(-1);
					}
				return cuantas;
			}
 
/*****************************************************************************************
								CONCIENCIA IA
******************************************************************************************/
/*		public int JuegaIA(int Nveces)
			{
				int temp;
				int ValoresEl[][]=new int[3][3];
				int ValoresYo[][]=new int[3][3];
				int PuntosCasilla[]=new int[7];
				int x,y,casilla;
				int z;
				boolean mayor;
//				System.out.println("entr� en JuegaIA, Turno: " + Integer.toString(Turno));
				//Recorrer� cada casilla en busca de la mejor opci�n
				for (casilla=0;casilla<7;casilla++)
					{
						//calculo la y actual para cada casilla
						z=0;
						while (estados[casilla][z]==0)
							{
								z++;
								if (z==7)
									break;
							}
						if (z==0)
								y=6;
						else
						{
							y=z-1;
//							System.out.println("IA analizando x: " + Integer.toString(casilla) + "  y: "+Integer.toString(y));
							//Una casilla recibe puntos por altura, un factor semialeatorio, para evitar
							//bucles infinitos
							PuntosCasilla[casilla]+=y;
							//otro factor aleatorio	para evitar atascos
							PuntosCasilla[casilla]+=Nveces%(casilla+1);
 
							//Obtengo mis valores para esa jugada simulando la colocaci�n de una ficha
							estados[casilla][y]=1;
							CompruebaCasilla(casilla,y,ValoresYo);
 
							estados[casilla][y]=-1;
							//Obtengo los resultados que el tendr�oa con esa jugada simulando
							CompruebaCasilla(casilla,y,ValoresEl);
							estados[casilla][y]=0;
 
							//valoro mucho si yo gano(1000) o si el gana(100)
							for (x=0;x<3;x++)
								for (z=0;z<3;z++)
									{
										if (ValoresYo[x][z]>=4)
											PuntosCasilla[casilla]+=1000;
										if (ValoresEl[x][z]>=4)
											PuntosCasilla[casilla]+=100;
										if (ValoresYo[x][z]==3 && Nveces<3)
											{
												estados[casilla][y]=1;
												System.out.println("Llamando a IA Recursiva en la vez: " + Nveces);
												if (JuegaIA(Nveces+1)>10)
													PuntosCasilla[casilla]+=90;
												else
													PuntosCasilla[casilla]+=5;
 
												estados[casilla][y]=0;
											}
										if (ValoresEl[x][z]==3 && Nveces<3)
											{
												estados[casilla][y]=(-1);
												System.out.println("Llamando a IA Recursiva en la vez: " + Nveces);
												if (JuegaIA(Nveces+1)>10)
													PuntosCasilla[casilla]+=100;
												else
													PuntosCasilla[casilla]+=10;
												estados[casilla][y]=0;
											}
										if (ValoresYo[x][z]==2)
											{
												PuntosCasilla[casilla]+=5;
											}
									}
							//Compruebo la casilla de encima Tanto de �l como yo
							if (y>0)
								{
									estados[casilla][y-1]=(1);
									CompruebaCasilla(casilla,y-1,ValoresYo);
 
									estados[casilla][y-1]=(-1);
									CompruebaCasilla(casilla,y-1,ValoresEl);
									estados[casilla][y-1]=0;
								}
							//valoro si el gana poniendo encima de mi o si me jodo
							for (x=0;x<3;x++)
								for (z=0;z<3;z++)
									{
										//Me joder�a una de victoria y eso no mola
										if (ValoresYo[x][z]>=4)
											PuntosCasilla[casilla]-=10;
 
										//Le dar�a la victoria y eso solo lo puedo permitir
										//si gano con esa jugada(o sea con 1000 de antes)
										if (ValoresEl[x][z]>=4)
											PuntosCasilla[casilla]-=100;
									}
							}
						}
				//Tenemos los tanteos de cada casilla
				//as� que vamos a ver cual tiene mayor puntuaci�n y colocar en ella
				z=0;
				for (y=0;y<7;y++)
					{
//						System.out.println("La opci�n: "+Integer.toString(y) + " Recibe "+Integer.toString(PuntosCasilla[y])+ " Puntos");
						mayor=true;
						for (x=0;x<7;x++)
							{
								if (PuntosCasilla[y]<PuntosCasilla[x])
									mayor=false;
							}
						if (mayor==true)
							{
								if (z!=0)
									z=y*10;
								else
									z=y;
							}
					}
 
				System.out.println("Final de JuegaIA");
				return(z);
			}*/
 
/***************************HASTA AQU� LA IA***************************/
 
		public void Ganaste(int jugador)
			{
				String s;
				Thread tiempo = new Thread();
				if (jugador==(-1))
					{
						victorias++;
						if (unjugador)
						{
				
							
						}
						else
						{
							s="GANASTE JUGADOR 1";
							
						}
					}
				else
					{
						derrotas++;
						if (unjugador)
						{
						}
						else
						{
							s="GANASTE JUGADOR 2";
						
						}
					}
 
			
//				g.setColor(Color.white);
//				g.setFont (new Font ("Impact",10,60));
		//		g.drawString(100,333,s);
 
				jugando=false;
			
				try
				{
					tiempo.sleep(1000);
				}catch(InterruptedException e){	}
			}
		public void paint (Graphics g)
			{
					int x;
					int y;
					g.setColor (Color.lightGray);
					g.fillRect(1,1,650,690);
 
					g.setColor (Color.white);
					g.fillRect(1,1,650,55);
 
					for (x=0;x<7;x++)
						for(y=0;y<7;y++)
							{
								g.fillOval((x*90)+20,(y*80)+100,70,70);
								estados[x][y]=0;
							}
			}
 
			public void ReStart()
				{
					Graphics g = getGraphics();
					int x;
					int y;
					Turno=0;
					jugando=true;
					System.out.println("reestart");
					g.setColor (Color.lightGray);
					g.fillRect(1,1,650,690);
 
					g.setColor (Color.white);
					g.fillRect(1,1,650,50);
 
					for (x=0;x<7;x++)
						for(y=0;y<7;y++)
							{
								g.fillOval((x*90)+20,(y*80)+100,70,70);
								estados[x][y]=0;
							}
				}
 
			public static void main (String []Args)
				{
					CuatroRaya a=new CuatroRaya();
					a.pack();
					a.setSize(650,690);
					a.setResizable(false);
					a.show();
				}
 
			public void actionPerformed(ActionEvent ae)
				{
				}
			public void mousePressed(MouseEvent me)
				{
					int z;
					z=me.getX();
					System.out.println("evento click");
					if (jugando)
						{
						System.out.println("evento click jugando");
							z=(z-10)/90;
							PintaCasilla(z);
						}
					if (me.getSource() == Salir)
						{
							System.exit(0);
						}
 
					if (me.getSource() == Comienza)
						{
							ReStart();
						}
				}
			public void mouseClicked(MouseEvent me)
				{
					//Vacio
				}
			public void mouseEntered(MouseEvent me)
				{
					//Vacio
				}
			public void mouseExited(MouseEvent me)
				{
					//Vacio
				}
			public void mouseReleased(MouseEvent me)
				{
					//Vacio
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
			            	
			                CuatroRaya CuatroRaya=new CuatroRaya();
			                CuatroRaya.setVisible(true);
			                
			            }
			        });
				
			}

			@Override
			public void inicializarTablero()
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void realizarTiro(Tablero X)
			{
				// TODO Auto-generated method stub
				
			}
 
	}
 
	class CuadroDialogo extends Dialog
	{
		Button aceptar = new Button ("OK");
		Label texto = new Label ();
		String cad;
		CuadroDialogo(Frame f, String s)
			{
				super (f,"informacion");
				setSize (400,100);
				setResizable(false);
				setBackground(Color.white);
				texto.setText(s);
				add("North",texto);
				add("South",aceptar);
			}
		public void paint (Graphics g)
			{
				g.setColor (Color.white);
				g.drawString (cad,50,50);
			}
		public boolean action (Event evt, Object obj)
			{
				if (evt.target == aceptar)
					{
						dispose();
						return true;
					}
				return false;
			}
	}