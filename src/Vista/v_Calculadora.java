package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class v_Calculadora extends JFrame {

	private JPanel contentPane;

	public v_Calculadora() {
		prepareFrame();
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

}
