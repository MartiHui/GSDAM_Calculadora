package Controlador;

import java.math.BigDecimal;

/**
 * Maneja las operaciones realizadas y el sistema de guardarlas en la base de datos, en caso de haber usuario conectado
 * @author Hui Yang Yang
 *
 */
public class OperacionController {
	public BigDecimal memoria; // El valor actual en memoria
	public BigDecimal resultado; // El resultado actual de la operación
	public String operacion; // La operacion a analizar
	public String operacion_formateada; // La operacion formateada con los símbolos correspondientes a mostrar al usuario
	
	public OperacionController() {
		memoria = BigDecimal.valueOf(0);
		resultado = BigDecimal.valueOf(0);
		operacion = "";
		operacion_formateada = "";
	}
}
