package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CustomGUI.BotonMemoria;
import CustomGUI.BotonModo;
import CustomGUI.BotonNumero;
import CustomGUI.BotonOperacion;

import java.awt.Rectangle;
import java.io.IOException;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Insets;

public class v_Calculadora extends JFrame {
	private JPanel contentPane;
	
	// Fuentes usadas en la calculadora que pueden no estar instaladas en el sistema
	public static Font openSans; // static para poder acceder desde mis elementos ui propios
	public static Font openSans_Bold;
	
	// Pantallas que mostraran los inputs y resultados
	public JTextField operaciones_textField;
	public JTextField resultado_textField;
	
	// Botones
	public BotonMemoria memoria_btns[];
	public BotonNumero numero_btns[];
	public BotonOperacion operaciones_btns[]; // Las operaciones que saldrán siempre
	public BotonOperacion cientifica_btns[]; // Las operaciones de la calculadora cientifica
	public BotonModo mode_btn; // El boton que cambiara entre calculadora normal y científica

	public v_Calculadora() {
		prepareFrame();
		getFonts();
		
		createPantalla();
		createBotones();
	}
	
	// Crea la ventana principal de la calculadora y ajusta su tamaño
	private void prepareFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(315, 350));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setBounds(100, 100, 0, 0); // Solo nos interesa posicionar la ventana, el tamaño lo designamos en pack()
		setResizable(false); // Esta linea debe estar antes de pack(), de lo contraria, la ventana tendrá un tamaño incorrecto
		pack(); // Para que el tamaño de la ventana se ajuste al panel que contiene la calculadora
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
	
	// Crea las pantallas donde se mostrarán los inputs del usuario y el restulado de las operaciones
	private void createPantalla() {
		// Pantalla que mostrará la ecuación actual
		operaciones_textField = new JTextField();
		operaciones_textField.setBounds(0, 0, 300, 25);
		operaciones_textField.setHorizontalAlignment(SwingConstants.RIGHT);
		operaciones_textField.setBackground(new Color(0xF3F3F3));
		operaciones_textField.setEditable(false); // Solo se puede manejar las pantallas mediante los botones
		operaciones_textField.setFont(openSans_Bold.deriveFont(Font.BOLD, 18)); operaciones_textField.setText("1+1+1+1+1+1+1+1");
		operaciones_textField.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Quitamos los bordes para que las dos pantallsa parezcan una
		contentPane.add(operaciones_textField);
		
		// Pantalla que mostrará el número que se esta introduciendo y los resultados
		resultado_textField = new JTextField();
		resultado_textField.setBounds(0, 25, 300, 50);
		resultado_textField.setHorizontalAlignment(SwingConstants.RIGHT);
		resultado_textField.setBackground(new Color(0xF3F3F3));
		resultado_textField.setEditable(false);
		resultado_textField.setFont(openSans.deriveFont(Font.PLAIN, 36)); resultado_textField.setText("5436.3123");
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
		int numOperaciones = 5; // Cuantos botones hay que crear
		int width = 60; // Cuanto medira de ancho cada boton, para poder situarlos uno al lado del otro
		int height = 75; // A que altura de la ventana hay que situar los botones

		memoria_btns = new BotonMemoria[numOperaciones];
		
		for (int i = 0; i < numOperaciones; i++) { 
			memoria_btns[i] = new BotonMemoria(i*width, height, i);
			contentPane.add(memoria_btns[i]);
		}
	}
	
	// Los botones del 1 al 9 los crearemos mediante un loop, de abajo a arriba, y de izquierda a derecha
	// para que tengan la distribución típica de las calculadoras. El 0 lo crearemos aparte
	private void botonesNumero() {
		int width = 75; // Cuanto mide de ancho, para poder ponerlos uno al lado del otro
		int initialHeight = 250; // A partir de que altura hay que crearlos.
		int height = 50; // Cuanto mid de alto, para pode ponerlos uno encima del otro
		int numRows = 3; // Cuantas filas de botones hay
		int numColumn = 3; // Cuantas columnas hay
		
		numero_btns = new BotonNumero[10];
		for (int i = 1; i < 10; i++) {
			numero_btns[i] = new BotonNumero(
					((i-1) % numRows) * width,						 // (i-1) en vez de solo i para que los botones empiezen a la 
					initialHeight - ((i-1) / numColumn) * height, 	// izquierda del todo, ya que el loop empieza por el valor 1
					i);
			contentPane.add(numero_btns[i]);
		}
		numero_btns[0] = new BotonNumero(
				width, // El boton 0 no esta a la izquierda del todo, sino en la segunda columna 
				initialHeight + height, // El boton esta una fila por debajo del resto del keypad
				0);
		contentPane.add(numero_btns[0]);
	}
	
	// Tenemos que crear los botones de operacion uno a uno ya que no estan todos juntos en una zona de la pantalla, sino
	// que rodean el keypad, y algunos botones incluso estan en columnas del keypad, junto al 0
	private void botonesOperacion() {
		operaciones_btns = new BotonOperacion[10];
		int idx = 0;
		
		// VAriables para facilitarme la vida a la hora de colocar los botones
		int initialHeight = 100;
		int height = 50;
		int width = 75;
		
		// Igual
		operaciones_btns[idx] = new BotonOperacion(3*width, initialHeight+4*height, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// CE
		operaciones_btns[idx] = new BotonOperacion(0, initialHeight, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// C
		operaciones_btns[idx] = new BotonOperacion(width, initialHeight, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// RETROCESO
		operaciones_btns[idx] = new BotonOperacion(2*width, initialHeight, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
		
		// SIGNO
		operaciones_btns[idx] = new BotonOperacion(0, initialHeight+4*height, idx);
		contentPane.add(operaciones_btns[idx]);
		idx++;
	}
	
	// Creamos los botones de operaciones avanzadas, pero NO los añadimos a la pantalla, ya que por defecto la calculadora
	// empieza en modo normal. Nos encargaremos de mostrar los botones cuando el usuario elija la calculadora científica
	private void botonesCientifica() {
		
		int idx = 10; // Empieza donde acaban las operaciones normales
	}
	
	private void botonModo() {
		mode_btn = new BotonModo();
		contentPane.add(mode_btn);
	}
}
