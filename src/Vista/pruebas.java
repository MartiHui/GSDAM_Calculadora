package Vista;

import org.mariuszgromada.math.mxparser.Expression;

import Controlador.c_Calculadora;

public class pruebas {

	public static void main(String[] args) {
		//c_Calculadora calc = c_Calculadora.getInstance();
		Expression e = new Expression(" (5 + 3) * 3");
		System.out.println(e.calculate());
	}

}
