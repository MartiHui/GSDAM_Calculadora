package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CustomGUI.BotonNumero;
import Vista.v_Calculadora;

public class c_Calculadora {
	v_Calculadora ui;
	
	public c_Calculadora() {
		ui = new v_Calculadora();
		
		funcionalidadMenu();
		funcionalidadBotones();
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
		for (int i = 0; i < 10; i++) {
			ui.numero_btns[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					// Si en la pantalla unicamente hay un 0 y añadimos un numero, quitamos ese 0, ya que los 0s a la izquierda no sirven de nada
					if (ui.resultado_textField.getText().equals("0")) { 
						ui.resultado_textField.setText("");
					}
					
					ui.resultado_textField.setText( // Añadimos el número a la derecha del todo
							ui.resultado_textField.getText() 
							+ ((BotonNumero)ae.getSource()).getText());
				}
			});
		}
	}
	
	// Se añade funcionalidad a los botones d eoperaciones, tanto de la calculadora normal como la científica
	private void botonesOperacion() {
		
	}
	
	private void botonModo() {
		ui.mode_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ui.switchMode();
			}
		});
	}
}
