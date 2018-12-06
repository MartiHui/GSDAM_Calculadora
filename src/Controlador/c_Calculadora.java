package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import CustomGUI.BotonMemoria;
import CustomGUI.BotonNumero;
import CustomGUI.BotonOperacion.Operacion;
import Vista.v_Calculadora;

/**
 * Almacena información sobre la calculadora.
 * @author Hui Yang Yang
 *
 */
public class c_Calculadora {
	private static c_Calculadora INSTANCE = null;
	
	public v_Calculadora ui;
	
	public boolean borrarPantalla; // Para saber si, al introducir un numero, antes tenemos que borrar la pantalla
	public BigDecimal memoria; // El valor actual en memoria
	private String operacion; // La operacion a analizar
	private String operacion_formateada; // La operacion formateada con los símbolos correspondientes a mostrar al usuario
	private Operacion ultimaOperacion;
	private BigDecimal ultimoOperando;								
	
	private c_Calculadora() {
		borrarPantalla = true;
		memoria = null;
		operacion = "";
		operacion_formateada = "";
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
}
