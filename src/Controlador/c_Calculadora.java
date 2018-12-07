package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import org.mariuszgromada.math.mxparser.Expression;

import CustomGUI.BotonMemoria;
import CustomGUI.BotonNumero;
import Vista.v_Calculadora;

/**
 * Almacena información sobre la calculadora.
 * @author Hui Yang Yang
 *
 */
public class c_Calculadora {
	final private String  FORMAT_START = "<html><body>";
	final private String FORMAT_END = "</html></body>";
	
	private static c_Calculadora INSTANCE = null;
	
	public v_Calculadora ui;
	
	public boolean borrarPantalla; // Para saber si, al introducir un numero, antes tenemos que borrar la pantalla
	public BigDecimal memoria; // El valor actual en memoria
	private String operacionParser; // La operacion a analizar
	private String unaryParserBuffer; // Almacena las operaciones de un solo operando que aun no este confirmada
	private String operacionFormateada; // La operacion formateada con los símbolos correspondientes a mostrar al usuario
	private String unaryFormateadaBuffer; // La operacion unaria formateada que aun no este confirmada
	private Operacion ultimaOperacion;	
	
	private int numParentesis; // Para controlar que cada parentesis que use el usuario este abierto y cerrado
	
	private c_Calculadora() {
		borrarPantalla = true;
		memoria = null;
		reset();
	}
	
	public static c_Calculadora getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new c_Calculadora();
			// No se inicializa la ui en el constructor ya que crearia un loop infinito
			// de llamada a constructores
			INSTANCE.ui = new v_Calculadora();
		}
		
		return INSTANCE;
	}
	
	/**
	 * Resetea todas las variables menos la memoria
	 */
	private void reset() {
		operacionParser = ""; // La operacion a analizar
		unaryParserBuffer = ""; // Almacena la operacion que aun no esta confirmada
		operacionFormateada = ""; // La operacion formateada con los símbolos correspondientes a mostrar al usuario
		unaryFormateadaBuffer = ""; // La operacion formateada que aun no esta confirmada
		ultimaOperacion = null;
	}
	
	private BigDecimal getNum() {
		return new BigDecimal(ui.resultado_textField.getText());
	}
	
	public void newOperation(Operacion op) {
		switch (op.getTipo()) {
		case IGUAL:
			// Si el usuario ha introducido un numero desde la ultima operacion, se
			// realiza esa operacion con el nuevo numero antes de mostrar el resultado
			if (!borrarPantalla) {
				realizarOperacion();
			}
			Expression e = new Expression(operacionParser);
			ui.resultado_textField.setText(String.valueOf(e.calculate()));
			break;
			
		case C:
			reset();
			ui.operaciones_textField.setText("");
			
		case CE:
			ui.resultado_textField.setText("0");
			break;
			
		case RETROCESO:
			String temp = ui.resultado_textField.getText();
			String resultado = temp.substring(0, temp.length()-1); // Obtenemos todo el string menos el caracter final
			if (resultado.isEmpty()) resultado = "0";
			ui.resultado_textField.setText(resultado);
			break;
			
		case DECIMAL:
			if (!ui.resultado_textField.getText().contains(".")) { // Comprobamos que el número no sea ya decimal
				ui.addNumPantalla(".");
			}
			break;

		case SIGNO:
			temp = ui.resultado_textField.getText();
			if (!temp.equals("0")) {
				if (temp.charAt(0) == '-') { // Si ya es negativo
					temp = temp.substring(1, temp.length()); // Quitamos el signo
				} else { // Si es positivo
					temp = "-" + temp; // añadimos el signo
				}
			}
			ui.resultado_textField.setText(temp);
			break;
		}
		
		if (op.getTipo() != Operacion.Tipo.DECIMAL) {
			borrarPantalla = true;
		}
	}
	
	/**
	 * Realiza la operación que haya en la variable ultimaOperacion
	 */
	private void realizarOperacion() {
		
	}
}
