package CustomGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import Vista.v_Calculadora;

/**
 * Bot�n propio derivado de JButton para la creaci�n de los botones de memoria.
 * @author Hui Yang Yang
 *
 */
public class BotonMemoria extends JButton {
	public static final int WIDTH = 60;
	public static final int HEIGHT = 25;
	
	/**
	 * Las operaciones que se pueden realizar respecto a la memoria de la calculadora
	 * @author Hui Yang Yang
	 *
	 */
	public enum MemoriaOperacion {
		CLEAR, // Elimina el n�mero en memoria
		RECALL, // Recupera el n�mero en memoria
		STORAGE, // Almacena en memoria el n�mero mostrado
		ADD, // Suma al n�mero en memoria el n�mero mostrado
		REMOVE // Resta al n�mero en memoria el n�mero mostrado
	}
	
	private MemoriaOperacion operacion;
	
	/**
	 * Constructor de BotonMemoria
	 * @param posX la posici�n del borde izquierdo del bot�n
	 * @param posY la posici�n del borde superior del bot�n
	 * @param operacion �ndice de la operaci�n que va a realizar el bot�n, relativo al enum {@link MemoriaOperacion}
	 */
	public BotonMemoria(int posX, int posY, int operacion) {
		setBounds(posX, posY, WIDTH, HEIGHT);
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
	
	/**
	 * @return La {@link MemoriaOperacion} que representa el bot�n.
	 */
	public MemoriaOperacion operacion() {
		return operacion;
	}
}
