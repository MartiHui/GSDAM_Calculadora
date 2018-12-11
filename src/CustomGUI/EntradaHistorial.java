package CustomGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Controlador.c_Calculadora;
import Modelo.Operacion;
import Vista.v_Historial;

public class EntradaHistorial extends JPanel {
	private static Color COLOR_PAR = new Color(0xD6D6D6);
	private static Color COLOR_IMPAR = new Color(0xF7F6F6);
	
	public Operacion operacion;
	
	private JTextField fecha;
	private JTextArea operacionFormateada;
	private JTextField resultado;
	private JPopupMenu popupMenu;
	private JMenuItem borrar_menuItem;
	
	private v_Historial historial;
	
	public EntradaHistorial(Operacion op, boolean isPar, v_Historial historial) {
		this.operacion = op;
		this.historial = historial;
		
		preparePanel((isPar) ? COLOR_PAR : COLOR_IMPAR);
		createPopMenu();
	}
	
	private void preparePanel(Color  color) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
		MouseAdapter listener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getButton() == MouseEvent.BUTTON1 && event.getClickCount() == 2) { // Doble click derecho
					c_Calculadora.getInstance().ui.resultado_textField.setText(Double.toString(operacion.resultado));
					historial.dispose();
				} else if (event.getButton() == MouseEvent.BUTTON3) {
					popupMenu.show(EntradaHistorial.this, event.getX(), event.getY());
				}
			}
		};
		
		fecha = new JTextField();
		fecha.setHorizontalAlignment(JTextField.LEFT);
		fecha.setPreferredSize(new Dimension(300, 25));
		fecha.setFont(new Font("Helvetica", Font.ITALIC, 14));
		fecha.setBackground(color);
		fecha.setBorder(null);
		fecha.setText(operacion.fecha);
		fecha.setEditable(false);
		fecha.addMouseListener(listener);
		add(fecha);
		
		operacionFormateada = new JTextArea();
		operacionFormateada.setFont(new Font("Helvetica", Font.PLAIN, 14));
		operacionFormateada.setBackground(color);
		operacionFormateada.setBorder(null);
		operacionFormateada.setLineWrap(true);
		operacionFormateada.setText(operacion.operacionFormateada);
		operacionFormateada.setEditable(false);
		operacionFormateada.addMouseListener(listener);
		add(operacionFormateada);
		
		resultado = new JTextField();
		resultado.setFont(new Font("Helvetica", Font.BOLD, 14));
		resultado.setPreferredSize(new Dimension(300, 25));
		resultado.setBackground(color);
		resultado.setBorder(null);
		resultado.setHorizontalAlignment(JTextField.RIGHT);
		resultado.setText(Double.toString(operacion.resultado));
		resultado.setEditable(false);
		resultado.addMouseListener(listener);
		add(resultado);
		
		repaint(); // Para que el JPanel tome el tamaño adecuado
		revalidate();
	}
	
	private void createPopMenu() {
		popupMenu = new JPopupMenu();
		
		borrar_menuItem = new JMenuItem("Borrar registro");
		borrar_menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operacion.delete();
				historial.showRegistros();
			}
		});
		popupMenu.add(borrar_menuItem);
	}
}
