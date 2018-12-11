package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Modelo.Usuario;
import Vista.v_UserForm;

public class c_Login {
	private v_UserForm ui;
	private c_Calculadora calculadora;
	
	public c_Login() {
		ui = new v_UserForm("Login");
		calculadora = c_Calculadora.getInstance();
		
		funcionalidadBotones();
		ui.setModal(true);
		ui.setVisible(true);
	}
	
	private void funcionalidadBotones() {
		ui.aceptar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkUserInfo();
			}
		});
		
		ui.cancelar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ui.dispose();
			}
		});
	}
	
	private void checkUserInfo() {
		String nombre = ui.nombre_txtField.getText();
		String password = ui.password_txtField.getText();
		
		Usuario usuario = Usuario.login(nombre, password);
		if (usuario != null) {
			calculadora.usuario = usuario;
			JOptionPane.showMessageDialog(null, "Bienvenido " + nombre);
			ui.dispose();
		}
	}
}
