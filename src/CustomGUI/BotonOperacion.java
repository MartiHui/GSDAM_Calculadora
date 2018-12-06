package CustomGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;

import Controlador.c_Calculadora;
import Vista.v_Calculadora;

/**
 * Botón propio derivado de JButton para la creación de los botones de operación.
 * @author Hui Yang Yang
 *
 */
public class BotonOperacion extends JButton {
	public static final int WIDTH = 75;
	public static final int HEIGHT = 50;
	
	/**
	 * Las operaciones que puede realizar la calculadora
	 * @author Hui Yang Yang
	 *
	 */
	public enum Operacion {
		IGUAL,
		CE,
		C,
		RETROCESO,
		SIGNO,
		DECIMAL,
		DIVISION,
		MULTIPLICACION,
		RESTA,
		SUMA,
		PARENTESIS_ABRIR,
		PARENTESIS_CERRAR,
		EXPONENTE,
		RAIZ_CUADRADA,
		SENO,
		COSENO,
		TANGENTE
	}
	
	private Operacion op;
	private c_Calculadora calculadora;
	
	/**
	 * Constructor de BotonOperacion
	 * @param posX la posición del borde izquierdo del botón
	 * @param posY la posición del borde superior del botón
	 * @param op índice de la operación que va a realizar el botón, relativo al enum {@link Operacion}
	 */
	public BotonOperacion(int posX, int posY, int op) {
		calculadora = c_Calculadora.getInstance();
		
		setBounds(posX, posY, WIDTH, HEIGHT);
		setBackground(new Color(0xAFB2B7));
		setMargin(new Insets(0, 0, 0, 0)); // Si no quitamos los margenes hay texto que no se veria en los botones
		setFont(v_Calculadora.openSans.deriveFont(Font.PLAIN, 36));
		this.op = Operacion.values()[op];
		setText(buttonText());
		addListener();
	}
	
	// Devuelve el string que indica que tipo de operacion tiene este boton
	private String buttonText() {
		String temp = "";
		
		switch (op) {
		case IGUAL:
			temp = "=";
			break;
			
		case CE:
			temp = "CE";
			break;
			
		case C:
			temp = "C";
			break;
			
		case RETROCESO:
			temp = "←";
			break;
			
		case SIGNO:
			temp = "±";
			break;
			
		case DECIMAL:
			temp = ".";
			break;
			
		case DIVISION:
			temp = "/";
			break;
			
		case MULTIPLICACION:
			temp = "X";
			break;
			
		case RESTA:
			temp = "-";
			break;
			
		case SUMA:
			temp = "+";
			break;
			
		case PARENTESIS_ABRIR:
			temp = "(";
			break;
			
		case PARENTESIS_CERRAR:
			temp = ")";
			break;
			
		case EXPONENTE:
			temp = "^";
			break;
			
		case RAIZ_CUADRADA:
			temp = "√";
			break;
			
		case SENO:
			temp = "sin";
			break;
			
		case COSENO:
			temp = "cos";
			break;
			
		case TANGENTE:
			temp = "tan";
			break;
		}
		
		return temp;
	}
	
	private void addListener() {
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				calculadora.newOperation(((BotonOperacion)ae.getSource()).operacion());
			}
		});
	}
	
	/**
	 * @return Devuelve la {@link Operacion} que contiene el botón.
	 */
	public Operacion operacion() {
		return op;
	}
}
