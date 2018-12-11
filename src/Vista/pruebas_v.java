package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

public class pruebas_v extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pruebas_v frame = new pruebas_v();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public pruebas_v() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		String text = "aaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbcccccccccccccccccccccc";
		lblNewLabel.setText(String.format("<html><div WIDTH=%d>%s</div><html>", 20, text));
		lblNewLabel.setBounds(52, 0, 344, 71);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(52, 71, 301, 179);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(panel);
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel1.setBackground(Color.RED);
		panel1.setBounds(0, 0, 50, 20);
		panel1.setPreferredSize(new Dimension(50, 20));
		panel.add(panel1);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.PINK);
		panel2.setBounds(0, 20, 50, 20);
		panel2.setPreferredSize(new Dimension(50, 20));
		panel.add(panel2);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(panel2, popupMenu);
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.BLUE);
		panel3.setBounds(0, 40,50, 20);
		panel3.setPreferredSize(new Dimension(50, 20));
		panel.add(panel3);
		
		JTextArea area = new JTextArea("JTextArea");
		area.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		area.setRows(1);
		area.setBounds(0, 0, 0, 20);
		area.setLineWrap(true);
		area.getDocument().addDocumentListener(new DocumentListener() {

	        @Override
	        public void removeUpdate(DocumentEvent e) {

	    		lblNewLabel.setText("remove " + area.getPreferredSize());
	        }

	        @Override
	        public void insertUpdate(DocumentEvent e) {

	    		lblNewLabel.setText("insert" + area.getPreferredSize());
	        }

	        @Override
	        public void changedUpdate(DocumentEvent arg0) {

	    		lblNewLabel.setText("update" + area.getPreferredSize());
	        }
	    });
		panel.add(area);
		
		JPanel panel4 = new JPanel();
		panel4.setBackground(Color.GREEN);
		panel4.setPreferredSize(new Dimension(50, 20));
		panel.add(panel4);
		
		JPanel panel5 = new JPanel();
		panel5.setBackground(Color.RED);
		panel5.setPreferredSize(new Dimension(50, 20));
		panel.add(panel5);
		
		panel.revalidate();
		panel.repaint();
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
