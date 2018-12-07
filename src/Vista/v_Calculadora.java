package Vista;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Operacion;
import CustomGUI.BotonMemoria;
import CustomGUI.BotonModo;
import CustomGUI.BotonNumero;
import CustomGUI.BotonOperacion;

import java.io.IOException;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import javax.swing.JLabel;

/**
 * Crea todos los elementos visuales de la calculadora.
 * @author Hui Yang Yang
 *
 */
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
	public JLabel operaciones_textField;
	public JLabel resultado_textField;
	
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
	
	/**
	 *  Crea la ventana principal de la calculadora y ajusta su tamaño.
	 */
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
	
	/**
	 *  Obtiene las fuentes propias de la carpeta de recursos para poder usarlas.
	 */
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
	
	/**
	 *  Crea la barra de menú con sus submenús y opciones.
	 */
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
	
	/**
	 *  Crea las pantallas donde se mostrarán los inputs del usuario y el restulado de las operaciones.
	 */
	private void createPantalla() {
		// Pantalla que mostrará la ecuación actual
		operaciones_textField = new JLabel();
		operaciones_textField.setBounds(0, 0, 300, 25);
		operaciones_textField.setHorizontalAlignment(SwingConstants.RIGHT);
		operaciones_textField.setBackground(new Color(0xF3F3F3));
		operaciones_textField.setFont(openSans_Bold.deriveFont(Font.BOLD, 18)); 
		operaciones_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Quitamos los bordes para que las dos pantallsa parezcan una
		contentPane.add(operaciones_textField);
		
		// Pantalla que mostrará el número que se esta introduciendo y los resultados
		resultado_textField = new JLabel();
		resultado_textField.setBounds(0, 25, 300, 50);
		resultado_textField.setHorizontalAlignment(SwingConstants.RIGHT);
		resultado_textField.setBackground(new Color(0xF3F3F3));
		resultado_textField.setFont(openSans.deriveFont(Font.PLAIN, 36)); 
		resultado_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		resultado_textField.setText("0");
		contentPane.add(resultado_textField);
	}
	
	/**
	 *  Crea todos los botones de la calculadora, tanto normal como científica.
	 */
	private void createBotones() {
		botonesMemoria();
		botonesNumero();
		botonesOperacion();
		botonesCientifica();
		botonModo();
	}
	
	// Ya que al iniciarse la calculadora, no habra ningun valor en la memoria,
	// se deshabilitan todos los botones de memoria excepto el de guardar en memoria
	private void botonesMemoria() {
		memoria_btns = new BotonMemoria[5]; // 5 botones: MC, MS, MR, M+ y M-
		
		for (int i = 0; i < 5; i++) { 
			memoria_btns[i] = new BotonMemoria(i*BotonMemoria.WIDTH, 75, i);
			contentPane.add(memoria_btns[i]);
		}
		
		disableMemoriaBtns();
	}
	
	// Desactiva todos los botones de memoria excepto el de guardar
	public void disableMemoriaBtns() {
		for (BotonMemoria bm : memoria_btns) {
			if (bm.operacion() != BotonMemoria.MemoriaOperacion.STORAGE) {
				bm.setEnabled(false);
			}
		}
	}
	
	// Activa todos los botones de memoria
	public void enableMemoriaBtns() {
		for (BotonMemoria bm : memoria_btns) {
			bm.setEnabled(true);
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
		Operacion op;
		
		int initialHeight = 100;
		
		// Igual
		op = new Operacion(Operacion.Tipo.IGUAL, null, null, false);
		operaciones_btns[idx] = new BotonOperacion(3*BotonOperacion.WIDTH, initialHeight+4*BotonOperacion.HEIGHT, op);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// CE
		op = new Operacion(Operacion.Tipo.CE, null, null, false);
		operaciones_btns[idx] = new BotonOperacion(0, initialHeight, op);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// C
		op = new Operacion(Operacion.Tipo.C, null, null, false);
		operaciones_btns[idx] = new BotonOperacion(BotonOperacion.WIDTH, initialHeight, op);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// RETROCESO
		op = new Operacion(Operacion.Tipo.RETROCESO, null, null, false);
		operaciones_btns[idx] = new BotonOperacion(2*BotonOperacion.WIDTH, initialHeight, op);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// SIGNO
		op = new Operacion(Operacion.Tipo.SIGNO, null, null, false);
		operaciones_btns[idx] = new BotonOperacion(0, initialHeight + 4*BotonOperacion.HEIGHT, op);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// DECIMAL
		op = new Operacion(Operacion.Tipo.DECIMAL, null, null, false);
		operaciones_btns[idx] = new BotonOperacion(2*BotonOperacion.WIDTH, initialHeight + 4*BotonOperacion.HEIGHT, op);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		//DIVISION
		op = new Operacion(Operacion.Tipo.OPERACION, "%s / ", "%s / ", false);
		operaciones_btns[idx] = new BotonOperacion(3*BotonOperacion.WIDTH, initialHeight, op);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// MULTIPLICACION
		op = new Operacion(Operacion.Tipo.OPERACION, "%s * ", "%s * ", false);
		operaciones_btns[idx] = new BotonOperacion(3*BotonOperacion.WIDTH, initialHeight + BotonOperacion.HEIGHT, op);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// RESTA
		op = new Operacion(Operacion.Tipo.OPERACION, "%s - ", "%s - ", false);
		operaciones_btns[idx] = new BotonOperacion(3*BotonOperacion.WIDTH, initialHeight + 2*BotonOperacion.HEIGHT, op);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// SUMA
		op = new Operacion(Operacion.Tipo.OPERACION, "%s + ", "%s + ", false);
		operaciones_btns[idx] = new BotonOperacion(3*BotonOperacion.WIDTH, initialHeight + 3*BotonOperacion.HEIGHT, op);
		contentPane.add(operaciones_btns[idx]);
		idx++;
	}
	
	// Creamos los botones de operaciones avanzadas, pero NO los añadimos a la pantalla, ya que por defecto la calculadora
	// empieza en modo normal. Nos encargaremos de mostrar los botones cuando el usuario elija la calculadora científica
	private void botonesCientifica() {
		cientifica_btns = new BotonOperacion[7];
		int idx = 0;
		Operacion op;
		
		int initialWidth = 300;
				
		// PARENTESIS_ABRIR
		op = new Operacion(Operacion.Tipo.PARENTESIS_ABRIR, "(", "(", false);
		cientifica_btns[idx] = new BotonOperacion(initialWidth, 0, op);
		idx++;
		
		// PARENTESIS_CERRAR
		op = new Operacion(Operacion.Tipo.PARENTESIS_CERRAR, ")", ")", false);
		cientifica_btns[idx] = new BotonOperacion(initialWidth, BotonOperacion.HEIGHT, op);
		cientifica_btns[idx].setEnabled(false); // Solo se puede usar el cerrar parentesis cuando hay un aprentesis abierto
		idx++;
		
		// EXPONENTE
		op = new Operacion(Operacion.Tipo.OPERACION, "%s ^ ", "%s ^ ", false);
		cientifica_btns[idx] = new BotonOperacion(initialWidth, 2 * BotonOperacion.HEIGHT, op);
		idx++;
		
		// RAIZ_CUADRADA
		op = new Operacion(Operacion.Tipo.OPERACION, "\u221A(%s)", "sqrt(%s) ", true);
		cientifica_btns[idx] = new BotonOperacion(initialWidth, 3 * BotonOperacion.HEIGHT, op);
		idx++;
		
		// SENO
		op = new Operacion(Operacion.Tipo.OPERACION, "sin(%s)", "sin(%s)", true);
		cientifica_btns[idx] = new BotonOperacion(initialWidth, 4 * BotonOperacion.HEIGHT, op);
		idx++;
		
		// COSENO
		op = new Operacion(Operacion.Tipo.OPERACION, "cos(%s)", "cos(%s)", true);
		cientifica_btns[idx] = new BotonOperacion(initialWidth, 5 * BotonOperacion.HEIGHT, op);
		idx++;
		
		// TANGENTE
		op = new Operacion(Operacion.Tipo.OPERACION, "tan(%s)", "tan(%s)", true);
		cientifica_btns[idx] = new BotonOperacion(initialWidth, 6 * BotonOperacion.HEIGHT, op);
		idx++;
	}
	
	private void botonModo() {
		mode_btn = new BotonModo();
		contentPane.add(mode_btn);
	}
	
	/**
	 * Cambia el aspecto de la calculadora entre normal y científica.
	 */
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
	
	// Añade un caracter a la pantalla de resultado
	public void addNumPantalla(String str) {
		resultado_textField.setText(resultado_textField.getText() + str);
	}
}
