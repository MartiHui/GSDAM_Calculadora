package CustomGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;

import Vista.v_Calculadora;

public class BotonOperacion extends JButton {
	public enum Operacion {
		IGUAL,
		CE,
		C,
		RETROCESO,
		SIGNO,
		DECIMAL,
		DIVISION,
		MULTIPLICACION,
		RESTA,
		SUMA
	}
	
	Operacion op;
	
	public BotonOperacion(int posX, int posY, int op) {
		setBounds(posX, posY, 75, 50);
		setBackground(new Color(0xAFB2B7));
		setMargin(new Insets(0, 0, 0, 0)); // Si no quitamos los margenes hay texto que no se veria en los botones
		setFont(v_Calculadora.openSans.deriveFont(Font.PLAIN, 36));
		this.op = Operacion.values()[op];
		setText(buttonText());
	}
	
	// Devuelve el string que indica que tipo de operacion tiene este boton
	private String buttonText() {
		String temp = "";
		
		switch (op) {
		case IGUAL:
			temp = "=";
			break;
			
		case CE:
			temp = "CE";
			break;
			
		case C:
			temp = "C";
			break;
			
		case RETROCESO:
			temp = "←";
			break;
			
		case SIGNO:
			temp = "±";
			break;
			
		case DECIMAL:
			temp = ".";
			break;
			
		case DIVISION:
			temp = "/";
			break;
			
		case MULTIPLICACION:
			temp = "X";
			break;
			
		case RESTA:
			temp = "-";
			break;
			
		case SUMA:
			temp = "+";
			break;
			
		}
		
		return temp;
	}
}
