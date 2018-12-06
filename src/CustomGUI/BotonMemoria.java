package CustomGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;

import Controlador.c_Calculadora;
import Vista.v_Calculadora;

/**
 * Botón propio derivado de JButton para la creación de los botones de memoria.
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
		CLEAR, // Elimina el número en memoria
		RECALL, // Recupera el número en memoria
		STORAGE, // Almacena en memoria el número mostrado
		ADD, // Suma al número en memoria el número mostrado
		REMOVE // Resta al número en memoria el número mostrado
	}
	
	private MemoriaOperacion operacion;
	private c_Calculadora calculadora;
	
	/**
	 * Constructor de BotonMemoria
	 * @param posX la posición del borde izquierdo del botón
	 * @param posY la posición del borde superior del botón
	 * @param operacion índice de la operación que va a realizar el botón, relativo al enum {@link MemoriaOperacion}
	 */
	public BotonMemoria(int posX, int posY, int operacion) {
		calculadora = c_Calculadora.getInstance();
		
		setBounds(posX, posY, WIDTH, HEIGHT);
		setBackground(new Color(0x7F8084));
		setFont(v_Calculadora.openSans_Bold.deriveFont(Font.BOLD, 16));
		this.operacion = MemoriaOperacion.values()[operacion];
		setText(buttonText());
		addListener();
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
	
	private void addListener() {
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				switch (((BotonMemoria)ae.getSource()).operacion()) {
				case CLEAR:
					calculadora.info.memoria = null;
					calculadora.ui.disableMemoriaBtns();
					break;
					
				case RECALL:
					calculadora.ui.resultado_textField.setText(calculadora.info.memoria.toString());
					break;
					
				case STORAGE:
					calculadora.info.memoria = new BigDecimal(calculadora.ui.resultado_textField.getText());
					calculadora.ui.enableMemoriaBtns();
					break;
					
				case ADD:
					calculadora.info.memoria = calculadora.info.memoria.add(new BigDecimal(calculadora.ui.resultado_textField.getText()));
					break;
					
				case REMOVE:
					calculadora.info.memoria = calculadora.info.memoria.subtract(new BigDecimal(calculadora.ui.resultado_textField.getText()));
					break;
				}

				calculadora.borrarPantalla = true;
			}
		});
	}
	
	/**
	 * @return La {@link MemoriaOperacion} que representa el botón.
	 */
	public MemoriaOperacion operacion() {
		return operacion;
	}
}
