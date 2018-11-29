package CustomGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JButton;

import Vista.v_Calculadora;

public class BotonModo extends JButton {
	private Rectangle normalCalculator = new Rectangle(300, 0, 15, 350);
	private Rectangle cientificCalculator = new Rectangle(450, 0, 15, 350);
	
	private boolean inNormalMode;
	
	public BotonModo() {
		setBounds(normalCalculator);
		setBackground(new Color(0xE46A68));
		setMargin(new Insets(0, 0, 0, 0)); // Si no quitamos los margenes, el texto no se mostraria, al ser el boton tan estrecho
		setFont(v_Calculadora.openSans.deriveFont(Font.PLAIN, 16));
		inNormalMode = false; // Para que se cambia a true en la siguiente linea
		swapMode();
	}
	
	public void swapMode() {
		inNormalMode = !inNormalMode;
		setBounds((inNormalMode) ? normalCalculator : cientificCalculator);
		setText((inNormalMode) ? ">" : "<");
	}

}
