package Vista;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import CustomGUI.EntradaHistorial;
import Modelo.Operacion;
import Modelo.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;

public class v_Historial extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	
	private JLabel nombre_lbl;
	
	Usuario usuario;

	private JPanel scrollpaneContents;
	
	public v_Historial(Usuario usuario) {
		this.usuario = usuario;
		
		prepareDialog();
		showRegistros();
		
		pack();
		setVisible(true);
	}
	
	private void prepareDialog() {
		setTitle("Historial");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(v_Historial.class.getResource("/Recursos/Imagenes/historial.png")));
		setBackground(new Color(0xFFFFFF));
		
		contentPanel.setPreferredSize(new Dimension(320, 400));
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		nameLabel();
		
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setBounds(0, 60, 320, 340);
		contentPanel.add(scrollpane);
		
		scrollpaneContents = new JPanel();
		scrollpaneContents.setLayout(new BoxLayout(scrollpaneContents, BoxLayout.Y_AXIS));
		scrollpane.setViewportView(scrollpaneContents);
		showRegistros();
	}
	
	private void nameLabel() {
		nombre_lbl = new JLabel();
		nombre_lbl.setBounds(0, 0, 320, 60);
		nombre_lbl.setFont(new Font("Helvetica", Font.PLAIN, 24));
		nombre_lbl.setText("Usuario: " + usuario.nombre);
		contentPanel.add(nombre_lbl);
	}
	
	public void showRegistros() {
		scrollpaneContents.removeAll();
		
		LinkedList<Operacion> registros = 
				Operacion.buscarPorUsuario(usuario);
		boolean isPar = true;
		
		for (Operacion op : registros) {
			EntradaHistorial temp = new EntradaHistorial(op, isPar, this);
			scrollpaneContents.add(temp);
			isPar = !isPar;
		}
		scrollpaneContents.add(Box.createVerticalGlue()); // https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
		
		scrollpaneContents.revalidate();
		scrollpaneContents.repaint();
	}

}
