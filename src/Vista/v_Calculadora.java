package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Color;

public class v_Calculadora extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	public v_Calculadora() {
		prepareFrame();
		createPantalla();
	}
	
	// Crea la ventana principal de la calculadora y ajusta su tamaño
	private void prepareFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(315, 350));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pack(); // Para que el tamaño de la ventana se ajuste al panel que contiene la calculadora
	}
	
	// Crea las pantallas donde se mostrarán los inputs del usuario y el restulado de las operaciones
	private void createPantalla() {
		textField = new JTextField();
		textField.setBounds(0, 0, 300, 25);
		textField.setAlignmentY(RIGHT_ALIGNMENT); 
		textField.setBackground(new Color(0xF3F3F3));
		textField.setEditable(false); // Solo se puede manejar las pantallas mediante los botones
		contentPane.add(textField);
	}
}
