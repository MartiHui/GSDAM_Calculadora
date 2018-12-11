package CustomGUI;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Modelo.Operacion;

public class EntradaHistorial extends JPanel {
	public Operacion operacion;
	
	private JLabel fecha;
	private JLabel operacionFormateada;
	private JLabel resultado;
	
	private int lines;
	
	public EntradaHistorial(Operacion op) {
		this.operacion = op;
		
		
	}
	
	private void preparePanel() {
		
	}

}
