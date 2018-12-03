package CustomGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;

import Vista.v_Calculadora;

public class BotonModo extends JButton {
	public static final int WIDTH = 15;
	public static final int HEIGHT = 350;
	
	public BotonModo() {
		setBounds(300, 0, WIDTH, HEIGHT);
		setBackground(new Color(0xE46A68));
		setMargin(new Insets(0, 0, 0, 0)); // Si no quitamos los margenes, el texto no se mostraria, al ser el boton tan estrecho
		setFont(v_Calculadora.openSans.deriveFont(Font.PLAIN, 16));
		setText(">");
	}
}
