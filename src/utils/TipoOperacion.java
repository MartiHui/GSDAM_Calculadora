package utils;

/**
 * Clase para manejar la información de las operaciones de la calculadora
 * @author Hui Yang Yang
 *
 */
public class TipoOperacion {
	private String textoFormateado;
	private String textoParser;
	
	private boolean isUnary; // Si la operacion es de un solo operando
	
	public TipoOperacion(String format, String parser, boolean isUnary) {
		this.textoFormateado = format;
		this.textoParser = parser;
		this.isUnary = isUnary;
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
