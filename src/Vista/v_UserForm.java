package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;

public class v_UserForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	public JButton aceptar_btn;
	public JButton cancelar_btn;
	
	public JTextField nombre_txtField;
	public JTextField password_txtField;

	/**
	 * Create the dialog.
	 */
	public v_UserForm(String titulo) {
		setTitle(titulo);
		
		prepareDialog();
		crearBotones();
		crearForm();
		
		pack();
		setModal(true);
		setVisible(true);
	}
	
	private void prepareDialog() {		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(v_UserForm.class.getResource("/Recursos/Imagenes/usuario.png")));
		setBackground(new Color(0xF7F6F6));
		
		contentPanel.setPreferredSize(new Dimension(265, 185));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}
	
	private void crearBotones() {
		aceptar_btn = new JButton("Aceptar");
		aceptar_btn.setMargin(new Insets(0, 0, 0, 0));
		aceptar_btn.setFont(new Font("Helvetica", Font.PLAIN, 16));
		aceptar_btn.setBounds(30, 130, 90, 25);
		aceptar_btn.setBackground(new Color(0xD6D6D6));
		contentPanel.add(aceptar_btn);
		
		cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setMargin(new Insets(0, 0, 0, 0));
		cancelar_btn.setFont(new Font("Helvetica", Font.PLAIN, 16));
		cancelar_btn.setBounds(145, 130, 90, 25);
		cancelar_btn.setBackground(new Color(0xD6D6D6));
		contentPanel.add(cancelar_btn);
	}
	
	private void crearForm() {
		JLabel nombre_lbl = new JLabel("Usuario: ");
		nombre_lbl.setBounds(15, 30, 80, 20);
		nombre_lbl.setFont(new Font("Helvetica", Font.PLAIN, 12));
		contentPanel.add(nombre_lbl);
		
		nombre_txtField = new JTextField();
		nombre_txtField.setBounds(100, 30, 150, 20);
		nombre_txtField.setFont(new Font("Helvetica", Font.PLAIN, 12));
		nombre_txtField.setBackground(new Color(0xFFFFFF));
		contentPanel.add(nombre_txtField);
		
		JLabel password_lbl = new JLabel("Contraseña: ");
		password_lbl.setBounds(15, 80, 80, 20);
		password_lbl.setFont(new Font("Helvetica", Font.PLAIN, 12));
		contentPanel.add(password_lbl);
		
		password_txtField = new JTextField();
		password_txtField.setBounds(100, 80, 150, 20);
		password_txtField.setFont(new Font("Helvetica", Font.PLAIN, 12));
		password_txtField.setBackground(new Color(0xFFFFFF));
		contentPanel.add(password_txtField);
	}

}
