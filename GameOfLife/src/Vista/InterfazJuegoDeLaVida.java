package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//import Modelo.EspacioCelular;

/**
 * Clase encargada de generar la interfaz gr�fica de usuario (GUI, por sus
 * siglas en ingl�s).
 * 
 * Representa la vista dentro del patron de dise�o MVC.
 * 
 * NOTA: Comente lo que considere necesario.
 *
 * @author Josef Ruzicka
 * @version 1
 * @since 12/06/2019
 */
public class InterfazJuegoDeLaVida extends JFrame {

	/**
	 * serializar es convertir el estado de un objeto a un stream de bytes
	 * el serialVersionUID se utiliza deserealizando un objeto para verificar que el mensajero y el recipiente
	 * tienen clases cargadas para ese objeto que sean compatibles.
	 */
	private static final long serialVersionUID = 1L;
	// Declaraci�n de variables
	private JPanel panelBotonesCelulas;
	private JPanel panelBotones;

	private JMenuBar barraDeMenu;
	private JMenu menuDelJuegoDeLaVida;
	private JMenu menuDelPrograma;

	private JMenuItem definirDimensionDelTablero;
	private JMenuItem iniciarAleatorio;
	private JMenuItem ejecucionContinua;
	private JMenuItem reiniciarJuego;

	private JMenuItem salirDelPrograma;
	
	private JButton pausar;

	private JButton[][] arregloDeBotones;
	private int filas;
	private int columnas;
	private final Color colorCelulaViva;
	private final Color colorCelulaMuerta;

	/**
	 * 
	 * Constructor de la clase.
	 * 
	 * Inicializa los componentes necesarios para presentar la ventana.
	 * 
	 * Req: que las variables esten declaradas.
	 * Efe: inicializa las variables.
	 * Mod: valor de las variables, da forma y color a la interfaz grafica
	 *
	 */
	public InterfazJuegoDeLaVida(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.colorCelulaViva = Color.red;
		this.colorCelulaMuerta = Color.white;
		this.setFrame();
		this.setPanels();
		this.setMenu();
	}

	/**
	 * 
	 * M�todo encargado de asignar un objeto ActionListener a los componentes de la
	 * interfaz. Requiere recibir dicho objeto como par�metro.
	 *
	 * Req: que haya sido construido un objeto de tipo InterfazJuegoDeLaVida.
	 * Efe: le agrega un listener a cada boton, sin este, los botones no se activarian cuando fuesen presionados.
	 * Mod: la presencia de un listener en cada boton.
	 *
	 * @param listener -> objeto de tipo ActionListener que permite capturar la
	 *                 interacci�n del usuario con la interfaz.
	 */
	public void agregarEscuchaParaCadaBoton(ActionListener objetoEscucha) {
		for (int fila = 0; fila < arregloDeBotones.length; fila++) {
			for (int columna = 0; columna < arregloDeBotones[fila].length; columna++) {
				arregloDeBotones[fila][columna].setActionCommand(fila + "," + columna);
				arregloDeBotones[fila][columna].addActionListener(objetoEscucha);
			}
		}

		// se agrega un listener a cada uno de estos botones para que detecten cuando el usuario les hace click y ejecuten su accion.
		definirDimensionDelTablero.addActionListener(objetoEscucha);
		iniciarAleatorio.addActionListener(objetoEscucha);
		ejecucionContinua.addActionListener(objetoEscucha);
		reiniciarJuego.addActionListener(objetoEscucha);
		pausar.addActionListener(objetoEscucha);
		salirDelPrograma.addActionListener(objetoEscucha);
	}

	/**
	 * 
	 * M�todo encargado de redimencionar el arreglo de botones que utiliza la
	 * interfaz. Se sugiere que la cantidad de columnas sea mayor a la de filas,
	 * esto para que las los botones mantengan una forma cuadrada.
	 *
	 * Req: n/a
	 * Efe: cambia el tamano de la matriz de celulas, reemplaza sus botones por nuevos.
	 * Mod: el tamano de la matriz de celulas.
	 *
	 * @param filas    -> nueva cantidad de filas del arreglo de botones.
	 * @param columnas -> nueva cantidad de columnas del arreglo de botones.
	 * @param listener -> objeto que permite escuchar y que sera asignado a cada uno
	 *                 de los botones.
	 * 
	 */
	public void cambiarDimensionDeMatriz(int filas, int columnas, ActionListener listener) {
		this.filas = filas;
		this.columnas = columnas;
		// Elimina el panelBotonesCelulas anterior (y con este, el arreglo de botones anterior) y crea uno nuevo con las medidas de los parametros, tambien crea un arreglo de botones con las mismas dimensiones
		this.remove(panelBotonesCelulas);
		panelBotonesCelulas = null;
		panelBotonesCelulas = new JPanel();
		panelBotonesCelulas.setLayout(new GridLayout(this.filas, this.columnas));
		arregloDeBotones = null;
		arregloDeBotones = new JButton[this.filas][this.columnas];

		/*
		 * Instancia cada uno de los botones que corresponde con cada c�lula en el
		 * arreglo de botones "arregloDeBotones" y luego los agrega al panel donde se
		 * localizan los botones en la ventana.
		 */
		for (int fila = 0; fila < filas; fila++) {
			for (int columna = 0; columna < columnas; columna++) {
				arregloDeBotones[fila][columna] = new JButton();
				arregloDeBotones[fila][columna].setBackground(colorCelulaMuerta);
				arregloDeBotones[fila][columna].setActionCommand(fila + "," + columna);
				arregloDeBotones[fila][columna].addActionListener(listener);
				panelBotonesCelulas.add(arregloDeBotones[fila][columna]);
			}
		}
		this.add(panelBotonesCelulas);
		/**
		 * El metodo revalidate es utilizado en contenedores a los cuales se les ha removido o agregado componentes para que el layout se ajuste a los cambios
		 * a los que se ha sometido, por ejemplo cambios de tamano como es el caso que tenemos en este proyecto ya que se podria cambiar el tamano de la matriz.
		 */
		this.revalidate();
		//he leido en muchos casos de programadores en internet que esto a veces les da fallas ya que el nuevo layout se pinta sobre el anterior sin borrarlo del todo
		//o casos similares, cosa que se ve solucionada siempre cuando se agraga un .repaint() luego del revalidate por lo que yo tambien se lo agregare. perdon por no tildar.
		this.repaint();
	}

	/**
	 * M�todo encargado de cambiar el color de una c�lula en la interfaz. Si la
	 * c�lula posee el color "colorMuerta" la cambia a viva y viceversa.
	 *
	 * Req: que la fila y la columna esten dentro del rango de tamano de la matriz
	 * Efe: cambia el color de una celula de acuerdo con su estado.
	 * Mod: el color de la celula (si es que esta deba cambiarlo)
	 *
	 * @param fila    -> la fila en la que la celula en consideracion (la que se va a cambiar) se ubica.
	 * @param columna -> la columna en la que la celula en consideracion (la que se va a cambiar) se ubica.
	 */
	public void editarBotonCelula(int fila, int columna, boolean estado) {
		if (estado) {
			arregloDeBotones[fila][columna].setBackground(colorCelulaViva);
		} else {
			arregloDeBotones[fila][columna].setBackground(colorCelulaMuerta);
		}
	}

	/**
	 * 
	 * M�todo encargado de reiniciar la interfaz gr�fica de usuario a su estado
	 * inicial.
	 * 
	 * Req: que haya sido construido un objeto InterfazJuegoDeLaVida.
	 * Efe: presenta la interaz grafica de usuario en su forma inicial, elimina cualquier cambio que pudo haber tenido.
	 * Mod: la matriz celular y la interfaz.
	 * 
	 */
	public void reiniciar() {
		for (JButton[] filasDeBotones : arregloDeBotones) {
			for (JButton boton : filasDeBotones) {
				boton.setBackground(colorCelulaMuerta);
			}
		}
		this.repaint();
	}

	/**
	 * M�todo que refresca cada uno de los botones de la interfaz gr�fica de usuario
	 * para que sean iguales al estado de la c�lula correspondiente.
	 *
	 * Req: que haya sido construido un objeto InterfazJuegoDeLaVida.
	 * Efe: actualiza el interfaz con el estado mas actual de la matriz de celulas.
	 * Mod: la interfaz grafica de usuario.
	 *
	 * @param espacioCelular -> se refiere a la matriz actual en la memoria, luego de realizar los cambios de cada celula, ya que esta no se refresca en el interfaz automaticamente sin este metodo.
	 */
	public void refrescarInterfaz(boolean espacioCelular[][]) {
		for (int fila = 0; fila < arregloDeBotones.length; fila++) {
			for (int columna = 0; columna < arregloDeBotones[fila].length; columna++) {
				this.editarBotonCelula(fila, columna, espacioCelular[fila][columna]);
			}
		}
	}

	/**
	 * M�todo encargado de solicitar un n�mero positivo al usuario mediante
	 * JOptionPane.
	 *
	 * Req: que haya sido inicializado un objeto InterfazJuegoDeLaVida.
	 * Efe: solicita un numero al usuario.
	 * Mod: n/a.
	 *
	 * @param mensaje -> Mensaje que se le presenta al usuario solicitandole que ingrese un numero.
	 * @return numero -> El numero ingresado por el usuario.
	 * @throws NumberFormatException -> Excepcion utilizada cuando se recibe un string en vez del numero esperado.
	 * @throws Exception             -> Excepcion en general, maneja eventualidades inesperadas que luego se especifican (en este caso en el if).
	 */
	public int solicitarNumero(String mensaje) throws NumberFormatException, Exception {
		int numero = Integer.parseInt(JOptionPane.showInputDialog(this, mensaje));
		// este es el if mencionado en la explicacion del throws Exception en el comentario anterior.
		if (numero < 0 || numero > (arregloDeBotones.length * arregloDeBotones[0].length)) {
			throw new Exception("El n�mero digitado no es positivo o es mayor al n�mero m�ximo de c�lulas.");
		}
		return numero;
	}
	/**
	 * M�todo encargado de solicitar la dimensi�n de la matriz celular. La dimensi�n
	 * no debe ser menor a 4 y la recomendaci�n es 150 filas y 250 columnas. (Usted
	 * debe modificar y/o validar esta sugerencia)
	 *
	 * Req: que haya sido inicializado un objeto InterfazJuegoDeLaVida.
	 * Efe: solicita un numero al usuario que indica la dimension de la matriz.
	 * Mod: n/a.
	 * 
	 * @param mensaje -> Mensaje que se le presenta al usuario solicitandole que ingrese un numero.
	 * @return dimensi�n -> El numero ingresado por el usuario.
	 * @throws NumberFormatException ->  Excepcion utilizada cuando se recibe un string en vez del numero esperado.
	 * @throws Exception             -> si el usuario digita una entrada menor a 4.
	 * 
	 *                               NOTA: FALTAN EXCEPCIONES POR CONTROLAR.
	 */
	public int pedirDimension(String mensaje) throws NumberFormatException, Exception {
		int dimension;
		dimension = Integer.parseInt(JOptionPane.showInputDialog(this, mensaje+" La dimension recomendada es 150 filas y 250 columnas."));

		if (dimension < 4) {
			throw new Exception("No debe digitar n�meros negativos o menores a 4.");
		}
		return dimension;
	}

	/**
	 * M�todo que permite mostrar un mensaje al usuario por medio de JOptionPane.
	 * 
	 * Req: que haya sido inicializado un objeto InterfazJuegoDeLaVida.
	 * Efe: muestra un mensaje al usuario.
	 * Mod: n/a.
	 *
	 * @param mensaje -> variable de tipo String que contiene el mensaje a presentar
	 *                al usuario.
	 */
	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	/**
	 * M�todo que se encarga de establecer las caracteristicas que poseera la
	 * ventana.
	 * 
	 * Req: que haya sido inicializado un objeto InterfazJuegoDeLaVida.
	 * Efe: Le da titulo a la ventana, especifica que sucede si la ventana se cierra, le da tamano, evita que sea modificado su tamano, y su ubicacion.
	 * Mod: las caracteristicas de la ventana.
	 * 
	 */
	private void setFrame() {
		this.setTitle("Juego de la Vida");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setResizable(false);

	}

	/**
	 * M�todo que construye el panel de botones de la interfaz.
	 *
	 * Req: que haya sido inicializado un objeto InterfazJuegoDeLaVida.
	 * Efe: crea el panel de botones con sus respectivos botones.
	 * Mod: la interfaz de usuario ya que le agrega el panel y los botones del panel.
	 *
	 */
	private void setPanels() {
		panelBotonesCelulas = new JPanel();
		panelBotonesCelulas.setLayout(new GridLayout(filas, columnas));
		
		panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

		arregloDeBotones = new JButton[filas][columnas];

		// Instancia cada uno de los botones que representan c�lulas y los agrega al
		// panel.
		for (JButton[] celulasFilas : arregloDeBotones) {
			for (int columna = 0; columna < celulasFilas.length; columna++) {
				celulasFilas[columna] = new JButton();
				celulasFilas[columna].setBackground(colorCelulaMuerta);
				panelBotonesCelulas.add(celulasFilas[columna]);
			}
		}
		
		pausar = new JButton("Pausar");
        pausar.setToolTipText("Pausa el programa cuando est� en ejecuci�n continua.");
		panelBotones.add(pausar);

		// Agrega el objeto panel (lienzo) a la estructura (frame).
		this.add(panelBotonesCelulas, BorderLayout.CENTER);
		this.add(panelBotones, BorderLayout.PAGE_END);
	}

	/**
	 * M�todo que instancia los objetos del men� y los agrega a la interfaz gr�fica
	 * de usuario.
	 *
	 * Req: que haya sido inicializado un objeto InterfazJuegoDeLaVida.
	 * Efe: crea y agrega a la interfaz el menu del juego con sus diferentes opciones.
	 * Mod: la interfaz grafica de usuario ya que le agrega el menu.
	 *
	 */
	private void setMenu() {
		this.barraDeMenu = new JMenuBar();

		this.menuDelJuegoDeLaVida = new JMenu("Juego");
		menuDelPrograma = new JMenu("Programa");

		definirDimensionDelTablero = new JMenuItem("Definir dimensi�n");
		definirDimensionDelTablero.setToolTipText("Cambia la dimensi�n de la matriz celular del Juego de la Vida.");
		menuDelJuegoDeLaVida.add(definirDimensionDelTablero);

		iniciarAleatorio = new JMenuItem("Iniciar Aleatorio");
		iniciarAleatorio
				.setToolTipText("Inicia el juego con la cantidad de c�lulas indicada, distribuidas aleatoriamente en la matriz celular.");
		menuDelJuegoDeLaVida.add(iniciarAleatorio);

		ejecucionContinua = new JMenuItem("Ejecutar Juego de la Vida");
		ejecucionContinua.setToolTipText("Realiza el cambio de todas las generaciones autom�ticamente.");
		menuDelJuegoDeLaVida.add(ejecucionContinua);

		reiniciarJuego = new JMenuItem("Reiniciar Juego de la Vida");
		reiniciarJuego.setToolTipText("Reiniciar el Juego de la Vida a su estado original.");
		menuDelJuegoDeLaVida.add(ejecucionContinua);
		

		salirDelPrograma = new JMenuItem("Salir");
		salirDelPrograma.setToolTipText("Cierra el programa.");
		menuDelPrograma.add(salirDelPrograma);

		barraDeMenu.add(menuDelJuegoDeLaVida);
		barraDeMenu.add(menuDelPrograma);
		this.setJMenuBar(barraDeMenu);
	}

}
