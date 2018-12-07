package Controlador;

/**
 * Clase para manejar la información de las operaciones de la calculadora
 * @author Hui Yang Yang
 *
 */
public class Operacion {
	public enum Tipo {
		IGUAL,
		CE,
		C,
		RETROCESO,
		SIGNO,
		DECIMAL,
		PARENTESIS_ABRIR,
		PARENTESIS_CERRAR,
		OPERACION
	}
	
	private Tipo tipo;
	private String textoFormateado;
	private String textoParser;
	
	private boolean isUnary; // Si la operacion es de un solo operando
	
	public Operacion(Tipo tipo, String format, String parser, boolean isUnary) {
		this.tipo = tipo;
		this.textoFormateado = format;
		this.textoParser = parser;
		this.isUnary = isUnary;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public String getTextoFormateado() {
		return textoFormateado;
	}
	
	public String getTextoParser() {
		return textoParser;
	}
	
	public boolean isUnary() {
		return isUnary;
	}
}
