package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vista.v_Calculadora;

public class c_Calculadora {
	v_Calculadora ui;
	
	public c_Calculadora() {
		ui = new v_Calculadora();
		
		
	}
	
	private void funcionalidadMenu() {
		
	}
	
	private void funcionalidadBotones() {
		botonesMemoria();
		botonesNumero();
		botonesOperacion();
		botonModo();
	}
	
	private void botonesMemoria() {
		
	}
	
	private void botonesNumero() {
		
	}
	
	// Se añade funcionalidad a los botones d eoperaciones, tanto de la calculadora normal como la científica
	private void botonesOperacion() {
		
	}
	
	private void botonModo() {
		ui.mode_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ui.switchMode();
			}
		});
	}
}
