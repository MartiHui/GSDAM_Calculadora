package CustomGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import Vista.v_Calculadora;

public class BotonNumero extends JButton {
	private int buttonValue;
	
	public BotonNumero(int posX, int posY, int num) {
		setBounds(posX, posY, 75, 50);
		setBackground(new Color(0xD7D8DA));
		setFont(v_Calculadora.openSans.deriveFont(Font.PLAIN, 36));
		buttonValue = num;
		setText(String.valueOf(num));
	}
	
	public int value() {
		return buttonValue;
	}
}
