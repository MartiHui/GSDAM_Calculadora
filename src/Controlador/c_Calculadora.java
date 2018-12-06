package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import CustomGUI.BotonMemoria;
import CustomGUI.BotonNumero;
import Vista.v_Calculadora;

/**
 * Almacena información sobre la calculador y añade funcionalidad a los menús.
 * @author Hui Yang Yang
 *
 */
public class c_Calculadora {
	private static c_Calculadora INSTANCE = null;
	
	public v_Calculadora ui;
	public boolean borrarPantalla; // Para saber si, al introducir un numero, antes tenemos que borrar la pantalla
	public CalculadoraInfo info;								
	
	private c_Calculadora() {
		borrarPantalla = true;
		info = new CalculadoraInfo();
		
		funcionalidadMenu();
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
	
	private void funcionalidadMenu() {
		
	}
}
