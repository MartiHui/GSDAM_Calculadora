package CustomGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import Vista.v_Calculadora;

public class BotonMemoria extends JButton {
	public enum MemoriaOperacion {
		CLEAR, // Elimina el n�mero en memoria
		RECALL, // Recupera el n�mero en memoria
		STORAGE, // Almacena en memoria el n�mero mostrado
		ADD, // Suma al n�mero en memoria el n�mero mostrado
		REMOVE // Resta al n�mero en memoria el n�mero mostrado
	}
	
	private MemoriaOperacion operacion;
	
	public BotonMemoria(int posX, int posY, int operacion) {
		setBounds(posX, posY, 60, 25);
		setBackground(new Color(0x7F8084));
		setFont(v_Calculadora.openSans_Bold.deriveFont(Font.BOLD, 16));
		this.operacion = MemoriaOperacion.values()[operacion];
		setText(buttonText());
	}
	
	// Devuelve el string que indica que tipo de operacion tiene este boton
	private String buttonText() {
		String temp = "";
		
		switch (operacion) {
		case CLEAR:
			temp = "MC";
			break;
			
		case RECALL:
			temp = "MR";
			break;
			
		case STORAGE:
			temp = "MS";
			break;
			
		case ADD:
			temp = "M+";
			break;
			
		case REMOVE:
			temp = "M-";
			break;
			
		}
		
		return temp;
	}
	
	// Solo permitimos saber que operaci�n tiene el bot�n. No podemos cambiarlo una vez creado
	public MemoriaOperacion getOperacion() {
		return operacion;
	}
}
