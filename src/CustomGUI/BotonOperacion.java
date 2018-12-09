package CustomGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;

import Controlador.Operacion;
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
	
	private Operacion op;
	private c_Calculadora calculadora;
	
	/**
	 * Constructor de BotonOperacion
	 * @param posX la posición del borde izquierdo del botón
	 * @param posY la posición del borde superior del botón
	 * @param op índice de la operación que va a realizar el botón, relativo al enum {@link Operacion}
	 */
	public BotonOperacion(int posX, int posY, Operacion op) {
		calculadora = c_Calculadora.getInstance();
		this.op = op;
		
		setBounds(posX, posY, WIDTH, HEIGHT);
		setBackground(new Color(0xAFB2B7));
		setMargin(new Insets(0, 0, 0, 0)); // Si no quitamos los margenes hay texto que no se veria en los botones
		setFont(v_Calculadora.openSans.deriveFont(Font.PLAIN, 32));
		setText(String.format(op.getTextoFormateado(), ""));
		addListener();
	}
	
	private void addListener() {
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				calculadora.realizarOperacion(((BotonOperacion)ae.getSource()).operacion());
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
