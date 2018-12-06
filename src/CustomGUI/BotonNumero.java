package CustomGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Controlador.c_Calculadora;
import Vista.v_Calculadora;

/**
 * Botón propio derivado de JButton para la creación de los botones de números.
 * @author Hui Yang Yang
 *
 */
public class BotonNumero extends JButton {
	public static final int WIDTH = 75;
	public static final int HEIGHT = 50;
	
	private int buttonValue;
	private c_Calculadora calculadora;
	
	public BotonNumero(int posX, int posY, int num) {
		calculadora = c_Calculadora.getInstance();
		
		setBounds(posX, posY, WIDTH, HEIGHT);
		setBackground(new Color(0xD7D8DA));
		setFont(v_Calculadora.openSans.deriveFont(Font.PLAIN, 36));
		buttonValue = num;
		setText(String.valueOf(num));
		addListener();
	}
	
	/**
	 * @return Un int con el valor que representa el botón
	 */
	public int value() {
		return buttonValue;
	}
	
	private void addListener() {
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// Si en la pantalla unicamente hay un 0 y añadimos un numero, quitamos ese 0, ya que los 0s a la izquierda no sirven de nada
				if (calculadora.ui.resultado_textField.getText().equals("0") || calculadora.borrarPantalla) { 
					calculadora.ui.resultado_textField.setText("");
				}
				
				calculadora.ui.addNumPantalla(((BotonNumero)ae.getSource()).getText());
				
				calculadora.borrarPantalla = false;
			}
		});
	}
}
