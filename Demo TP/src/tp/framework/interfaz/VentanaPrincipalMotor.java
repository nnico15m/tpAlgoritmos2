package tp.framework.interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tp.framework.elementos.Jugador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.DefaultListModel;

public class VentanaPrincipalMotor
{
	private JFrame frame;
	private JTextField textJugador1;
	private JTextField textJugador2;
	private Jugador jugador1;
	private Jugador jugador2;
	private String metodoSetearJugadores = "setJugadores";
	private String metodoInicializarJuego = "inicializate";
	static VentanaPrincipalMotor window;
	static JButton btnJugar;
	static boolean juegoTerminado;
	TablaLista TablaLista;
	private JList<String> list;
	private DefaultListModel<String> DLM;	
	
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
	private void crearForm(){
		frame=new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100,100,780,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	private void crearTitulo(int x, int y, int w, int h, String text, int tam){
		JLabel ret = new JLabel(text);
		ret.setForeground(Color.BLACK);
		ret.setBounds(x, y, w, h);
		ret.setFont(new Font("Times New Roman", Font.BOLD, tam));
		frame.getContentPane().add(ret);
	}

	private JTextField textBoxJugador(int x, int y, int w, int h){
		JTextField ret = new JTextField();
		ret.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ret.setText(null);
		ret.setBounds(x, y, w, h);
		ret.setColumns(10);
		frame.getContentPane().add(ret);
		return ret;
	}
	
	private JButton crearBoton(int x, int y, int w, int h, String text){
		JButton ret = new JButton(text);
		ret.setEnabled(true);
		ret.setBackground(Color.BLACK);
		ret.setForeground(Color.WHITE);
		ret.setBounds(x, y, w, h);
		frame.getContentPane().add(ret);
		
		return ret;
	}
	
	private void initialize()
	{
		crearForm();
		crearTitulo(10, 10, 250, 35, "Motor de Juegos", 30);
		
		crearTitulo(10, 50, 250, 35, "Jugador1", 20);
		textJugador1 = textBoxJugador(10, 80, 237, 27);
		
		crearTitulo(10, 120, 250, 35, "Jugador2", 20);
		textJugador2 = textBoxJugador(10, 150, 237, 27);
		
		btnJugar = crearBoton(10, 200, 100, 30, "Jugar");
		btnJugar.addActionListener(new BtnNewButton_2ActionListener());

		crearTitulo(330, 20, 250, 35, "Demos", 25);

		list = crearListado();
		DLM = new DefaultListModel<String>();
		list.setModel(DLM);
		
		DLM.addElement("Tateti");
		DLM.addElement("Damas");
		DLM.addElement("Ajedrez");
		
		setJugador1(new Jugador());
		setJugador2(new Jugador());
	}

	private JList<String> crearListado()
	{
		JList<String> ret = new JList<String>();
		ret.setToolTipText("");
		ret.setForeground(Color.WHITE);
		ret.setBackground(Color.DARK_GRAY);
		ret.setBounds(330, 50, 255, 180);
		
		ret.setEnabled(true);
		frame.getContentPane().add(ret);
		
		return ret;	
	}

	private class BtnNewButton_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnJugar){
				jugador1.setJugadorNombre(textJugador1.getText());
				jugador2.setJugadorNombre(textJugador2.getText());
				
				try
				{
						
					Jugador parametros[] = {jugador1,jugador2};
					Class<?> clases[] = new Class[parametros.length];
					
					for(int i = 0; i<clases.length;i++){
						
						clases[i] = Jugador.class;
						
					}
					
					Class<?> juego = Class.forName("tp.framework.juego." + DLM.get(list.getSelectedIndex()));
					
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

	public void setJuegoSeleccionado(String juegoSeleccionado)
	{
		//this.juegoSeleccionado = juegoSeleccionado;
		TablaLista.setVisible(false);
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
