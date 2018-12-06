package Controlador;

import java.math.BigDecimal;

import CustomGUI.BotonOperacion.Operacion;

/**
 * Almacena información de la calculadora: memoria, operaciones, resultados, etc.
 * @author Hui Yang Yang
 *
 */
public class CalculadoraInfo {
	public BigDecimal memoria; // El valor actual en memoria
	private String operacion; // La operacion a analizar
	private String operacion_formateada; // La operacion formateada con los símbolos correspondientes a mostrar al usuario
	private Operacion ultimaOperacion;
	private BigDecimal ultimoOperando;
	
	public CalculadoraInfo() {
		memoria = null;
		operacion = "";
		operacion_formateada = "";
		ultimaOperacion = null;
	}
}
