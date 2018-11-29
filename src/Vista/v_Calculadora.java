package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CustomGUI.BotonMemoria;
import CustomGUI.BotonModo;
import CustomGUI.BotonNumero;
import CustomGUI.BotonOperacion;

import java.io.IOException;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;

public class v_Calculadora extends JFrame {
	private JPanel contentPane;
	
	// Fuentes usadas en la calculadora que pueden no estar instaladas en el sistema
	public static Font openSans; // static para poder acceder desde mis elementos ui propios
	public static Font openSans_Bold;
	
	// Menu
	public JMenuItem historial_mnItem;
	public JMenuItem salir_mnItem;
	public JMenuItem iniciar_mnItem;
	public JMenuItem crear_mnItem;
	public JMenuItem desconectar_mnItem;
	
	// Pantallas que mostraran los inputs y resultados
	public JTextField operaciones_textField;
	public JTextField resultado_textField;
	
	// Botones
	public BotonMemoria memoria_btns[];
	public BotonNumero numero_btns[];
	public BotonOperacion operaciones_btns[]; // Las operaciones que saldrán siempre
	public BotonOperacion cientifica_btns[]; // Las operaciones de la calculadora cientifica
	public BotonModo mode_btn; // El boton que cambiara entre calculadora normal y científica

	private boolean inNormalMode; // Estamos en la calculadora normal?
	
	public v_Calculadora() {
		prepareFrame();
		getFonts();
		
		createMenu();
		createPantalla();
		createBotones();
		
		inNormalMode = true;
		
		pack(); // Cambia el tamaño de la ventana de la aplicacion de forma que se ajusta al tamaño de su conteido
		setVisible(true);
	}
	
	// Crea la ventana principal de la calculadora y ajusta su tamaño
	private void prepareFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(v_Calculadora.class.getResource("/Recursos/Imagenes/logo.png")));
		
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(315, 350));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setBounds(100, 100, 0, 0); // Solo nos interesa posicionar la ventana, el tamaño se adaptara al contenido
		setResizable(false);
	}
	
	// Obtiene las fuentes propias de la carpeta de recursos para poder usarlas
	private void getFonts() {
		try {
			openSans = Font.createFont(Font.TRUETYPE_FONT, v_Calculadora.class.getResourceAsStream("/Recursos/Fuentes/OpenSans-Regular.ttf"));
			openSans_Bold = Font.createFont(Font.TRUETYPE_FONT, v_Calculadora.class.getResourceAsStream("/Recursos/Fuentes/OpenSans-Bold.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Crea la barra de menú con sus submenús y opciones
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Menú Opciones
		JMenu opciones_menu = new JMenu("Opciones");
		menuBar.add(opciones_menu);
		
		historial_mnItem = new JMenuItem("Historial");
		opciones_menu.add(historial_mnItem);
		
		salir_mnItem = new JMenuItem("Salir");
		opciones_menu.add(salir_mnItem);
		
		// Menú Usuarios
		JMenu usuarios_menu = new JMenu("Usuarios");
		menuBar.add(usuarios_menu);
		
		iniciar_mnItem = new JMenuItem("Iniciar sesión");
		usuarios_menu.add(iniciar_mnItem);
		
		crear_mnItem = new JMenuItem("Crear usuario");
		usuarios_menu.add(crear_mnItem);
		
		desconectar_mnItem = new JMenuItem("Desconectar");
		usuarios_menu.add(desconectar_mnItem);
	}
	
	// Crea las pantallas donde se mostrarán los inputs del usuario y el restulado de las operaciones
	private void createPantalla() {
		// Pantalla que mostrará la ecuación actual
		operaciones_textField = new JTextField();
		operaciones_textField.setBounds(0, 0, 300, 25);
		operaciones_textField.setHorizontalAlignment(SwingConstants.RIGHT);
		operaciones_textField.setBackground(new Color(0xF3F3F3));
		operaciones_textField.setEditable(false); // Solo se puede manejar las pantallas mediante los botones
		operaciones_textField.setFont(openSans_Bold.deriveFont(Font.BOLD, 18)); 
		operaciones_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Quitamos los bordes para que las dos pantallsa parezcan una
		contentPane.add(operaciones_textField);
		
		// Pantalla que mostrará el número que se esta introduciendo y los resultados
		resultado_textField = new JTextField();
		resultado_textField.setBounds(0, 25, 300, 50);
		resultado_textField.setHorizontalAlignment(SwingConstants.RIGHT);
		resultado_textField.setBackground(new Color(0xF3F3F3));
		resultado_textField.setEditable(false);
		resultado_textField.setFont(openSans.deriveFont(Font.PLAIN, 36)); 
		resultado_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(resultado_textField);
	}
	
	// Crea todos los botones de la calculadora, tanto normal como científica
	private void createBotones() {
		botonesMemoria();
		botonesNumero();
		botonesOperacion();
		botonesCientifica();
		botonModo();
	}
	
	private void botonesMemoria() {
		memoria_btns = new BotonMemoria[5]; // 5 botones: MC, MS, MR, M+ y M-
		
		for (int i = 0; i < 5; i++) { 
			memoria_btns[i] = new BotonMemoria(i*BotonMemoria.WIDTH, 75, i);
			contentPane.add(memoria_btns[i]);
		}
	}
	
	// Los botones del 1 al 9 los crearemos mediante un loop, de abajo a arriba, y de izquierda a derecha
	// para que tengan la distribución típica de las calculadoras. El 0 lo crearemos aparte
	private void botonesNumero() {
		int initialHeight = 250; // A partir de que altura hay que crearlos.
		int numRows = 3; // Cuantas filas de botones hay
		int numColumn = 3; // Cuantas columnas hay
		
		numero_btns = new BotonNumero[10];
		for (int i = 1; i < 10; i++) {
			numero_btns[i] = new BotonNumero(
					((i-1) % numRows) * BotonNumero.WIDTH,						 // (i-1) en vez de solo i para que los botones empiezen a la 
					initialHeight - ((i-1) / numColumn) * BotonNumero.HEIGHT, 	// izquierda del todo, ya que el loop empieza por el valor 1
					i);
			contentPane.add(numero_btns[i]);
		}
		numero_btns[0] = new BotonNumero(
				BotonNumero.WIDTH, // El boton 0 no esta a la izquierda del todo, sino en la segunda columna 
				initialHeight + BotonNumero.HEIGHT, // El boton esta una fila por debajo del resto del keypad
				0);
		contentPane.add(numero_btns[0]);
	}
	
	// Tenemos que crear los botones de operacion uno a uno ya que no estan todos juntos en una zona de la pantalla, sino
	// que rodean el keypad, y algunos botones incluso estan en columnas del keypad, junto al 0
	private void botonesOperacion() {
		operaciones_btns = new BotonOperacion[10];
		int idx = 0;
		
		int initialHeight = 100;
		
		// Igual
		operaciones_btns[idx] = new BotonOperacion(3*BotonOperacion.WIDTH, initialHeight+4*BotonOperacion.HEIGHT, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// CE
		operaciones_btns[idx] = new BotonOperacion(0, initialHeight, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// C
		operaciones_btns[idx] = new BotonOperacion(BotonOperacion.WIDTH, initialHeight, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// RETROCESO
		operaciones_btns[idx] = new BotonOperacion(2*BotonOperacion.WIDTH, initialHeight, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// SIGNO
		operaciones_btns[idx] = new BotonOperacion(0, initialHeight + 4*BotonOperacion.HEIGHT, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// DECIMAL
		operaciones_btns[idx] = new BotonOperacion(2*BotonOperacion.WIDTH, initialHeight + 4*BotonOperacion.HEIGHT, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		//DIVISION
		operaciones_btns[idx] = new BotonOperacion(3*BotonOperacion.WIDTH, initialHeight, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// MULTIPLICACION
		operaciones_btns[idx] = new BotonOperacion(3*BotonOperacion.WIDTH, initialHeight + BotonOperacion.HEIGHT, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// RESTA
		operaciones_btns[idx] = new BotonOperacion(3*BotonOperacion.WIDTH, initialHeight + 2*BotonOperacion.HEIGHT, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// SUMA
		operaciones_btns[idx] = new BotonOperacion(3*BotonOperacion.WIDTH, initialHeight + 3*BotonOperacion.HEIGHT, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
	}
	
	// Creamos los botones de operaciones avanzadas, pero NO los añadimos a la pantalla, ya que por defecto la calculadora
	// empieza en modo normal. Nos encargaremos de mostrar los botones cuando el usuario elija la calculadora científica
	private void botonesCientifica() {
		cientifica_btns = new BotonOperacion[7];
		int idx = 0;
		int normalOpLen = operaciones_btns.length; // Las operaciones cientificas continuan justo dfepues de las normales
		
		int initialWidth = 300;
				
		// PARENTESIS_ABRIR
		cientifica_btns[idx] = new BotonOperacion(initialWidth, 0, idx + normalOpLen);
		idx++;
		
		// PARENTESIS_CERRAR
		cientifica_btns[idx] = new BotonOperacion(initialWidth, BotonOperacion.HEIGHT, idx + normalOpLen);
		idx++;
		
		// EXPONENTE
		cientifica_btns[idx] = new BotonOperacion(initialWidth, 2 * BotonOperacion.HEIGHT, idx + normalOpLen);
		idx++;
		
		// RAIZ_CUADRADA
		cientifica_btns[idx] = new BotonOperacion(initialWidth, 3 * BotonOperacion.HEIGHT, idx + normalOpLen);
		idx++;
		
		// SENO
		cientifica_btns[idx] = new BotonOperacion(initialWidth, 4 * BotonOperacion.HEIGHT, idx + normalOpLen);
		idx++;
		
		// COSENO
		cientifica_btns[idx] = new BotonOperacion(initialWidth, 5 * BotonOperacion.HEIGHT, idx + normalOpLen);
		idx++;
		
		// TANGENTE
		cientifica_btns[idx] = new BotonOperacion(initialWidth, 6 * BotonOperacion.HEIGHT, idx + normalOpLen);
		idx++;
	}
	
	private void botonModo() {
		mode_btn = new BotonModo();
		mode_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchMode();
			}
		});
		
		contentPane.add(mode_btn);
	}
	
	public void switchMode() {
		inNormalMode = !inNormalMode;
		
		if (inNormalMode) { // Si pasamos a calculadora normal);
			// Movemos el botón de cambiar de modo
			mode_btn.setLocation(300, 0);
			mode_btn.setText(">");
			// Quitamos las operiones de la cientifica
			for (BotonOperacion op : cientifica_btns) {
				contentPane.remove(op);
			}
			// Encogemos la ventana
			contentPane.setPreferredSize(new Dimension(315, 350));
			pack();
		} else { // Si pasamos a calculadora científica
			contentPane.setPreferredSize(new Dimension(390, 350));
			pack();
			
			mode_btn.setLocation(375, 0);
			mode_btn.setText("<");
			
			for (BotonOperacion op : cientifica_btns) {
				contentPane.add(op);
			}
		}
	}
}
