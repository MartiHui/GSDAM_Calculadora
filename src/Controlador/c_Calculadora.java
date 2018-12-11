package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.security.auth.callback.TextOutputCallback;

import org.mariuszgromada.math.mxparser.Expression;

import CustomGUI.BotonMemoria;
import CustomGUI.BotonNumero;
import Modelo.Usuario;
import Vista.v_Calculadora;
import utils.TipoOperacion;

/**
 * Almacena información sobre la calculadora.
 * @author Hui Yang Yang
 *
 */
public class c_Calculadora {
//	final private String  FORMAT_START = "<html><body>";
//	final private String FORMAT_END = "</html></body>";
	
	private static c_Calculadora INSTANCE = null;
	
	public v_Calculadora ui;
	
	public Usuario usuario;
	
	public boolean borrarPantalla; // Para saber si, al introducir un numero, antes tenemos que borrar la pantalla
	private boolean operacionAcabada;
	
	public double memoria; // El valor actual en memoria
	
	public String operacionParser; // La operacion a analizar
	private String unaryParser; // Almacena la operacion unaria a realizar
	public String operacionFormateada; // La operacion formateada con los símbolos correspondientes a mostrar al usuario
	private String unaryFormateada; // La operacion unaria formateada
	
	public TipoOperacion ultimaOperacion;	
	
	//public int numParentesis; // Para controlar que cada parentesis que use el usuario este abierto y cerrado
	
	private c_Calculadora() {
		borrarPantalla = true;
		memoria = 0;
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
	 * Añade los ActionListeners de los elementos del menu
	 */
	private void funcionalidadMenu() {
		ui.historial_mnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		ui.salir_mnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		ui.iniciar_mnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new c_Login();
			}
		});
		
		ui.crear_mnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new c_CrearUsuario();
			}
		});
		
		ui.desconectar_mnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario = null;
			}
		});
	}
	
	/**
	 * Resetea todas las variables menos la memoria
	 */
	public void reset() {
		operacionParser = ""; 
		operacionFormateada = ""; 
		unaryParser = "";
		unaryFormateada = "";
		ultimaOperacion = null;
	}
	
	private String getNum() {
		return ui.resultado_textField.getText();
	}
	
	/**
	 * Realiza la operación que haya en la variable ultimaOperacion
	 */
	public void realizarOperacion(TipoOperacion op) {
		if (ultimaOperacion != null) {
			if (op.isUnary()) {
				if (ultimaOperacion.isUnary()) {
					unaryParser = String.format(op.getTextoParser(), unaryParser);
					unaryFormateada = String.format(op.getTextoFormateado(), unaryFormateada);
				} else {
					unaryParser = String.format(op.getTextoParser(), getNum());
					unaryFormateada = String.format(op.getTextoFormateado(), getNum());
					
					operacionParser += ultimaOperacion.getTextoParser();
					operacionFormateada += ultimaOperacion.getTextoFormateado();
				}
			} else {
				if (ultimaOperacion.isUnary()) {
					operacionParser += unaryParser; 
					unaryParser = "";
					operacionFormateada += unaryFormateada;
					unaryFormateada = "";
				} else {
					operacionParser += String.format("%s %s", ultimaOperacion.getTextoParser() , getNum());
					operacionFormateada += String.format("%s %s", ultimaOperacion.getTextoFormateado() , getNum());
				}
			}
		} else {
			if (op.isUnary()) {
				unaryParser = String.format(op.getTextoParser(), getNum());
				unaryFormateada = String.format(op.getTextoFormateado(), getNum());
			} else {
				operacionParser += getNum();
				operacionFormateada += getNum();
			}
		}
		
		if (operacionAcabada) {
			operacionAcabada = false;
		}
		
		ultimaOperacion = op;
		borrarPantalla = true;
		updatePantalla();
	}
	
	public void finalizarOperacion() {
		realizarOperacion(new TipoOperacion(" = ", " = ", false));
		operacionAcabada = true;
		reset();
	}
	
	public void addNumPantalla(String num) {
		// Si en la pantalla unicamente hay un 0 y añadimos un numero, quitamos ese 0, ya que los 0s a la izquierda no sirven de nada
		if (ui.resultado_textField.getText().equals("0") || borrarPantalla) { 
			ui.resultado_textField.setText("");
		}
		
		if ((ultimaOperacion != null && ultimaOperacion.isUnary())) {
			ui.operaciones_textField.setText(operacionFormateada);
			ultimaOperacion = null;
			unaryParser = "";
			unaryFormateada = "";
		}
		
		if (operacionAcabada) {
			ui.operaciones_textField.setText("");
			operacionAcabada = false;
		}
		
		ui.resultado_textField.setText(ui.resultado_textField.getText() + num);
		
		borrarPantalla = false;
	}
	
	public void updatePantalla() {
		ui.operaciones_textField.setText(operacionFormateada +
				((ultimaOperacion.getTextoFormateado() == null || ultimaOperacion.isUnary()) ? "" : ultimaOperacion.getTextoFormateado())
				+ unaryFormateada);
		
		Expression e = new Expression((ultimaOperacion.isUnary()) ? unaryParser : operacionParser); 
		double sol = e.calculate();
		System.out.println(e.getExpressionString());
		ui.resultado_textField.setText((sol - (int)sol != 0) ? String.valueOf(sol) : String.valueOf((int)sol));
	}
}
