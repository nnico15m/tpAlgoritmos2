package tp.framework.interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tp.framework.elementos.Jugador;
import tp.framework.juego.Juego;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;;

public class VentanaPrincipalMotor
{

	private JFrame frame;
	private JTextField textJugador1;
	private JTextField txtJugador2;
	private JButton buttonAceptarJugador2;
	private JButton botonListaJuegos;
	private String juegoSeleccionado;
	private String metodoParaJuegoSeleccionado;
	private Jugador jugador1;
	private Jugador jugador2;
	private String metodoSetearJugadores = "setJugadores";
	private String metodoInicializarJuego = "inicializate";
	static VentanaPrincipalMotor window;
	static boolean flagJugador1 = false;
	static boolean flagJugador2 = false;
	static boolean  flagJuegoSelccionado = false;
	static JButton btnJugar;
	static boolean juegoTerminado;
	TablaLista TablaLista;
	private JButton btnAceptarJugador1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					window=new VentanaPrincipalMotor();
					window.frame.setVisible(true); 
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipalMotor()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize()
	{
		frame=new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100,100,724,499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MOTOR DE JUEGOS ");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(247, 24, 158, 47);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frame.getContentPane().add(lblNewLabel);
		
		txtJugador2 = new JTextField();
		txtJugador2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtJugador2.setText(null);
		txtJugador2.setBounds(62, 197, 253, 31);
		txtJugador2.setColumns(10);
		frame.getContentPane().add(txtJugador2);
		
		btnJugar = new JButton("JUGAR");
		btnJugar.setEnabled(false);
		btnJugar.addActionListener(new BtnNewButton_2ActionListener());
		btnJugar.setBackground(Color.WHITE);
		btnJugar.setForeground(Color.BLACK);
		btnJugar.setBounds(418, 320, 148, 57);
		frame.getContentPane().add(btnJugar);
		
		textJugador1 = new JTextField();
		textJugador1.setText(null);
		textJugador1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		textJugador1.setToolTipText("");
		textJugador1.setBackground(Color.WHITE);
		textJugador1.setForeground(Color.DARK_GRAY);
		textJugador1.setBounds(62, 112, 253, 31);
		frame.getContentPane().add(textJugador1);
		textJugador1.setColumns(10);
		
		buttonAceptarJugador2 = new JButton("Aceptar Jugador 2");
		buttonAceptarJugador2.addActionListener(new ButtonActionListener());
		buttonAceptarJugador2.setBounds(418, 199, 166, 27);
		frame.getContentPane().add(buttonAceptarJugador2);
		
		botonListaJuegos = new JButton("Ver Lista Juegos");
		botonListaJuegos.addActionListener(new BotonListaJuegosActionListener());
		botonListaJuegos.setBounds(110, 320, 177, 57);
		frame.getContentPane().add(botonListaJuegos);
		
		btnAceptarJugador1 = new JButton("Aceptar Jugador 1");
		btnAceptarJugador1.addActionListener(new BtnAceptarJugador1ActionListener());
		btnAceptarJugador1.setBounds(418, 114, 166, 27);
		frame.getContentPane().add(btnAceptarJugador1);
		
		setJugador1(new Jugador());
		setJugador2(new Jugador());
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
			
			if(evento.getSource() == buttonAceptarJugador2)
			{
				jugador2.setJugadorNombre(txtJugador2.getText());
				flagJugador2 = true;
				VentanaPrincipalMotor.verificarJugabilidad();
				JOptionPane.showMessageDialog(null,txtJugador2.getText());
				
			}
			
		}
	}
	private class BotonListaJuegosActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			TablaLista = new TablaLista();
			TablaLista.setVentana(window);
			TablaLista.setVisible(true);
			
		}
	}
	private class BtnNewButton_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnJugar){
				
				JOptionPane.showMessageDialog(null,"EL juego " + getJuegoSeleccionado() + " esta por comenzar");
				try
				{
						
					Jugador parametros[] = {jugador1,jugador2};
					Class<?> clases[] = new Class[parametros.length];
					
					for(int i = 0; i<clases.length;i++){
						
						clases[i] = Jugador.class;
						
					}
					
					Class<?> juego = Class.forName("tp.framework.juego."+ getJuegoSeleccionado());
					
					Object ob = juego.getConstructor(null).newInstance(null); //Creo un objeto generico --> Lo instancio
					ob.getClass().getMethod(metodoSetearJugadores,clases).invoke(ob,parametros);
					
					ob.getClass().getMethod(metodoInicializarJuego,null).invoke(ob,null);
										
				}
				catch(ClassNotFoundException ex)
				{
					JOptionPane.showMessageDialog(null,"Ocurrio un error");
					ex.printStackTrace();
				}
				catch(InstantiationException ex)
				{
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				catch(IllegalAccessException ex)
				{
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				catch(IllegalArgumentException ex)
				{
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				catch(InvocationTargetException ex)
				{
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				catch(NoSuchMethodException ex)
				{
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				catch(SecurityException ex)
				{
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
							
			}
			
		}
	}
	private class BtnAceptarJugador1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnAceptarJugador1)
			{
				jugador1.setJugadorNombre(textJugador1.getText());
				flagJugador1 = true;
				VentanaPrincipalMotor.verificarJugabilidad();
				JOptionPane.showMessageDialog(null,textJugador1.getText());
				
												
			}
			
			
		}
	}
	

	public String getJuegoSeleccionado()
	{
		return juegoSeleccionado;
	}

	public static void verificarJugabilidad()
	{
		if(flagJuegoSelccionado == true && flagJugador1 == true && flagJugador2 == true){
			
			btnJugar.setEnabled(true);
		}
		
	}

	public void setJuegoSeleccionado(String juegoSeleccionado)
	{
		this.juegoSeleccionado = juegoSeleccionado;
		TablaLista.setVisible(false);
		flagJuegoSelccionado = true;
		verificarJugabilidad();
		JOptionPane.showMessageDialog(null,"El juego seleccionado es : " + this.getJuegoSeleccionado());
	}

	

	public String getMetodoParaJuegoSeleccionado()
	{
		return metodoParaJuegoSeleccionado;
	}

	public void setMetodoParaJuegoSeleccionado(String metodoParaJuegoSeleccionado)
	{
		this.metodoParaJuegoSeleccionado = metodoParaJuegoSeleccionado;
	}

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
}
