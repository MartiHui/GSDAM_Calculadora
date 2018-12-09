package CustomGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.spi.NumberFormatProvider;

import javax.swing.JButton;

import Controlador.Operacion;
import Controlador.c_Calculadora;
import CustomGUI.BotonMemoria.MemoriaOperacion;
import Vista.v_Calculadora;

/**
 * Botones que realizar operaciones especiales, como borrar el numero, resetear la operacion,
 * cambiar de signo, dar elresultado, etc
 * @author Hui Yang Yang
 *
 */
public class BotonEspecial extends JButton {
	public static final int WIDTH = 75;
	public static final int HEIGHT = 50;
	
	public enum Especial {
		IGUAL,
		CE,
		C,
		RETROCESO,
		SIGNO,
		DECIMAL,
		PARENTESIS_ABRIR,
		PARENTESIS_CERRAR,
	}
	
	private Especial op;
	private c_Calculadora calculadora;
	
	public BotonEspecial(int posX, int posY, Especial op) {
		calculadora = c_Calculadora.getInstance();
		this.op = op;
		
		setBounds(posX, posY, WIDTH, HEIGHT);
		setBackground(new Color(0xAFB2B7));
		setMargin(new Insets(0, 0, 0, 0)); // Si no quitamos los margenes hay texto que no se veria en los botones
		setFont(v_Calculadora.openSans.deriveFont(Font.PLAIN, 36));
		setText(buttonText());
		addListener();
	}
	
	// Devuelve el string que indica que tipo de operacion tiene este boton
		private String buttonText() {
			String temp = "";
			
			switch (op) {
			case C:
				temp = "C";
				break;
				
			case CE:
				temp = "CE";
				break;
				
			case DECIMAL:
				temp = ".";
				break;
				
			case IGUAL:
				temp = "=";
				break;
				
			case PARENTESIS_ABRIR:
				temp = "(";
				break;
				
			case PARENTESIS_CERRAR:
				temp = ")";
				break;
				
			case RETROCESO:
				temp = "\u2190";
				break;
				
			case SIGNO:
				temp = "\u00B1";
				break;
			}
			
			return temp;
		}
		
		private void addListener() {
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					Especial op = ((BotonEspecial)ae.getSource()).operacion(); 
					switch (op) {
					case C:
						calculadora.reset();
						calculadora.ui.operaciones_textField.setText("");
						
					case CE:
						calculadora.ui.resultado_textField.setText("0");
						break;
						
					case DECIMAL:
						if (!calculadora.ui.resultado_textField.getText().contains(".")) { // Comprobamos que el número no sea ya decimal
							calculadora.addNumPantalla(".");
						}
						break;
						
					case IGUAL:
						calculadora.finalizarOperacion();
						break;
						
					case PARENTESIS_ABRIR:
						calculadora.operacionFormateada += "(";
						calculadora.operacionParser += "(";
						calculadora.numParentesis++;
						calculadora.ui.operaciones_textField.setText(calculadora.operacionFormateada);
						break;
						
					case PARENTESIS_CERRAR:
						if (calculadora.numParentesis > 0) {
							
							calculadora.operacionFormateada += ")";
							calculadora.operacionParser += ")";
							calculadora.numParentesis--;
							calculadora.ui.operaciones_textField.setText(calculadora.operacionFormateada);
						}
						break;
						
					case RETROCESO:
						String temp = calculadora.ui.resultado_textField.getText();
						String resultado = temp.substring(0, temp.length()-1); // Obtenemos todo el string menos el caracter final
						if (resultado.isEmpty()) resultado = "0";
						calculadora.ui.resultado_textField.setText(resultado);
						break;
						
					case SIGNO:
						temp = calculadora.ui.resultado_textField.getText();
						if (!temp.equals("0")) {
							if (temp.charAt(0) == '-') { // Si ya es negativo
								temp = temp.substring(1, temp.length()); // Quitamos el signo
							} else { // Si es positivo
								temp = "-" + temp; // añadimos el signo
							}
						}
						calculadora.ui.resultado_textField.setText(temp);
						break;
					}
					
					if (!(op == Especial.DECIMAL || op == Especial.SIGNO)) {
						calculadora.borrarPantalla = true;
					}
				}
			});
		}
		
		public Especial operacion() {
			return op;
		}
}
