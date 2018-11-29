package CustomGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import Vista.v_Calculadora;

public class BotonNumero extends JButton {
	public static final int WIDTH = 75;
	public static final int HEIGHT = 50;
	
	private int buttonValue;
	
	public BotonNumero(int posX, int posY, int num) {
		setBounds(posX, posY, WIDTH, HEIGHT);
		setBackground(new Color(0xD7D8DA));
		setFont(v_Calculadora.openSans.deriveFont(Font.PLAIN, 36));
		buttonValue = num;
		setText(String.valueOf(num));
	}
	
	public int value() {
		return buttonValue;
	}
}
