package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Modelo.Usuario;
import Vista.v_UserForm;

public class c_CrearUsuario {
	private v_UserForm ui;
	private c_Calculadora calculadora;
	
	public c_CrearUsuario() {
		ui = new v_UserForm("Nuevo usuario");
		calculadora = c_Calculadora.getInstance();
		
		funcionalidadBotones();
		ui.setModal(true);
		ui.setVisible(true);
	}
	
	private void funcionalidadBotones() {
		ui.aceptar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createUser();
			}
		});
		
		ui.cancelar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ui.dispose();
			}
		});
	}
	
	private void createUser() {
		String nombre = ui.nombre_txtField.getText();
		String password = ui.password_txtField.getText();
		
		if (nombre.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debes rellenar ambos campos");
		} else {
			Usuario usuario = Usuario.insert(nombre, password);
			if (usuario != null) {
				calculadora.usuario = usuario;
				JOptionPane.showMessageDialog(null, "Usuario creado");
				ui.dispose();
			}
		}
	}
}
