package tp.framework.interfaz;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;

public class TablaLista extends javax.swing.JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID=1L;
	private JPanel contentPane;
	private JTextField textFieldListaJuegos;
	private JButton botonAgregarJuego;
	private JList<String> list;
	private DefaultListModel<String> DLM; 
	private VentanaPrincipalMotor ventana;
	TablaLista frame;

	int cont;

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
					TablaLista frame=new TablaLista();
					frame.setVisible(true);
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TablaLista()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,561,398);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setBotonAgregarJuego(new JButton("Agregar Juego"));
		getBotonAgregarJuego().addActionListener(new BtnNewButtonActionListener());
		getBotonAgregarJuego().setBounds(317, 110, 123, 46);
		contentPane.add(getBotonAgregarJuego());
		
		JButton btnEliminarJuego = new JButton("Eliminar Juego");
		btnEliminarJuego.addActionListener(new BtnEliminarJuegoActionListener());
		btnEliminarJuego.setBounds(317, 167, 123, 46);
		contentPane.add(btnEliminarJuego);
		
		JLabel lblNewLabel = new JLabel("LISTA JUEGOS :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(25, 11, 154, 42);
		contentPane.add(lblNewLabel);
		
		textFieldListaJuegos = new JTextField();
		textFieldListaJuegos.setBounds(317, 64, 123, 28);
		contentPane.add(textFieldListaJuegos);
		textFieldListaJuegos.setColumns(10);
		
		list = new JList<String>();
		list.setToolTipText("");
		list.setForeground(Color.PINK);
		list.setBackground(Color.WHITE);
		list.setBounds(53, 69, 185, 205);
		contentPane.add(list);
		
		
		DLM = new DefaultListModel<String>();
		DLM.addElement(textFieldListaJuegos.getText());
			
		list.setModel(DLM);
		
		JButton btnSeleccionarJuego = new JButton("Seleccionar Juego");
		btnSeleccionarJuego.addActionListener(new BtnSeleccionarJuegoActionListener());
		btnSeleccionarJuego.setBounds(290, 237, 171, 83);
		contentPane.add(btnSeleccionarJuego);
		
	}
	private JButton getBotonAgregarJuego()
	{
		return botonAgregarJuego;
	}

	private void setBotonAgregarJuego(JButton botonAgregarJuego)
	{
		this.botonAgregarJuego = botonAgregarJuego;
		
	}
	
	public VentanaPrincipalMotor getVentana()
	{
		return ventana;
	}

	public void setVentana(VentanaPrincipalMotor ventana)
	{
		this.ventana = ventana;
	}
	
	private class BtnNewButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent evento) {
						
			DLM.addElement(textFieldListaJuegos.getText());
			textFieldListaJuegos.setText(null);
			cont++;
			textFieldListaJuegos.setText("");
			
		}
	}
	private class BtnEliminarJuegoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int pos = list.getSelectedIndex();
			DLM.remove(pos);
		}
	}
	private class BtnSeleccionarJuegoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int pos = list.getSelectedIndex();
			String juego = DLM.get(pos);	
			getVentana().setJuegoSeleccionado(juego);
			TablaLista.cerrate();
			
		}
	}
	
	public static void cerrate()
	{
		
		
	}
}
