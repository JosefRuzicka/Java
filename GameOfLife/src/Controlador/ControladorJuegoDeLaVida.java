package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import Modelo.EspacioCelular;
import Vista.InterfazJuegoDeLaVida;

/**
 * Clase controlador que se encarga de manejar la interacción y mensajes entre la interfaz y el modelo. 
 * Además se encarga de determinar la reacción ante los eventos de la interfaz.
 *
 * @author Josef Ruzicka
 * @version 1
 * @since 12/06/2019
 * NOTA: Comente lo que considere necesario.
 */
public class ControladorJuegoDeLaVida {

    //Inicio declaración de variables de las clases requeridas por el programa.
    private final InterfazJuegoDeLaVida interfazJuegoDeLaVida;
    private EspacioCelular espacioCelular;
    
    // INVESTIGAR PARA QUE SE UTILIZAN LAS VARIABLES VOLATILE
    // Las variables volatile se utilizan para que haya seguridad y congruensia de datos si son utilizadas por varios "threads" a la vez.
    // esto se asegura de que si el valor de la variable cambia en un thread, tambien cambiara en los demas threads que la utilicen.
    // un "thread" en espanol "hilo" se encarga de llevar a cabo una tarea individual, en los programas pueden haber varios threads actuando a la vez.
    private volatile boolean ejecucionContinua;
    
    // Un objeto Handler es básicamente una cola de mensajes. En este caso se utiliza para controlar los llamados que interactuan en tiempo de ejecución con la interfaz.
    private final Handler handler;


    /**
     * Constructor de la clase controlador del Juego de la Vida.
     *
     * Req: Que existan las clases InterfazJuegoDeLaVida, EspacioCelular y que esta reciba parámetros; además requiere que se hayan declarado las 3 variables
     *		(interfazJuegoDeLaVida, handler, espacioCelular)
     * Efe: Constructor de la clase, inicializa las variables.
     * Mod: crea objetos y les da tamano (size en ingles, es que no tengo la letra que va entre la n y la o en el abecedario espanol disculpas profe).
     * 
     */
    public ControladorJuegoDeLaVida() {
        interfazJuegoDeLaVida = new InterfazJuegoDeLaVida(150, 250);
        handler = new Handler();
        interfazJuegoDeLaVida.agregarEscuchaParaCadaBoton(handler);
        espacioCelular = new EspacioCelular(150, 250);
    }

    /**
     * Método que inicia la ejecución del programa Juego de la Vida.
     *
     * Req: que el constructor de Controlador se haya invocado ya.
     * Efe: hace visible la interfaz gráfica por lo que inicia el juego y comunicación con el usuario.
     * Mod: hace visible la interfaz
     * 
     */
    public void iniciarJuegoDeLaVida() {
        interfazJuegoDeLaVida.setVisible(true);
    }

    /**
     * Clase privada e interna que se encarga de implementar las interfaces
     * ActionListener y Runnable (INVESTIGAR PARA QUE SE UTILIZAN). 
     * Permite controlar los eventos de la interfaz gráfica de usuario.
     */
    // La interfaz ActionListener tiene un metodo "actionPerformed()" que es llamado cuando se presiona un botón o objeto del menu.
    // Runnable es una interfaz que tiene un metodo "run()" para llevar a cabo el un proceso que sera efectuado por un thread sin necesidad de crear una subclase.
    private class Handler implements ActionListener, Runnable {

        /**
         * Implementación del método abstracto run de Runnable. 
         * Se encarga de mantener el programa en ejecución continua hasta que el usuario lo detenga.
         *
         * Req: que el constructor de Controlador se haya invocado ya.
         * Efe: que el juego continue hasta ser interrumpido por el usuario.
         * Mod: el espacio celular cambia cada 100 milisegundos por la siguiente generación.
         * 
         */
        public void run() {
            while (ejecucionContinua) {
                espacioCelular.siguienteGeneracion(espacioCelular);
                interfazJuegoDeLaVida.refrescarInterfaz(espacioCelular.getEstadoMatrizCelular());
                
                try {
                    Thread.sleep(100);
                    //el nombre "ex" fue cambiado por "exception"
                } catch (InterruptedException exception) {
                	// InterruptedException es llamada cuando un thread que se encuentra esperando o durmiendo y otro thread lo interrumpe.
                }
            }
        }

        @Override
        /**
         * Implementaciónn del método abstracto actionPerformed de la clase ActionListener. 
         * Determina el comportamiento del programa de acuerdo a lo que el usuario realiza con la interfaz.
         *
         * Req: que el constructor de Controlador se haya invocado ya.
         * Efe: la instrución descrita sobre el botón que haya sido presionado
         * Mod: modifica el estado del juego segun lo indique el botón presionado
         * 
         */
        public void actionPerformed(ActionEvent evento) {
        	int filas = 0, columnas = 0;
        	int numero = 0;
            String accion = evento.getActionCommand();
            switch (accion) {
               // Sale del juego
            	case "Salir":
                	ejecucionContinua = false;
                    System.exit(0);
               
               // Coloca aleatoriamente las celulas que sean indicadas por el usuario     
                case "Iniciar Aleatorio":
                	
                    if (ejecucionContinua) {
                        ejecucionContinua = false;
                    }
                    try {
                        numero = interfazJuegoDeLaVida.solicitarNumero("Digite el número de células vivas que desea tener en la matriz celular."
                                + "\nMáximo actual de células: " + espacioCelular.getFilas() * espacioCelular.getColumnas());
                        espacioCelular.iniciarAleatorio(numero);
                        interfazJuegoDeLaVida.refrescarInterfaz(espacioCelular.getEstadoMatrizCelular());
                    } catch (NumberFormatException exception) {
                        interfazJuegoDeLaVida.mostrarMensaje("Debe digitar números únicamente.");
                    } catch (Exception exception) {
                        interfazJuegoDeLaVida.mostrarMensaje(exception.getMessage());
                    }
                    break;
                    
                // Cambia el tamano del tablero por el que el usuario indique
                case "Definir dimensión":
                    //Detiene la ejecució continua en caso que está este activada.
                    if (ejecucionContinua) {
                        ejecucionContinua = false;
                    }
                    try {
                        filas = interfazJuegoDeLaVida.pedirDimension("Digite la nueva cantidad de filas de la matriz celular.");
                        columnas = interfazJuegoDeLaVida.pedirDimension("Digite la nueva cantidad de columnas de la matriz celular.");
                        espacioCelular.cambiarTamaño(filas, columnas);
                        interfazJuegoDeLaVida.cambiarDimensionDeMatriz(filas, columnas, handler);
                    } catch (NumberFormatException exception) {
                        interfazJuegoDeLaVida.mostrarMensaje("Debe digitar números únicamente.");
                    } catch (Exception exception) {
                        interfazJuegoDeLaVida.mostrarMensaje(exception.getMessage());
                    }
                    break;
                    
                // Inicia el juego sin pausa    
                case "Ejecutar Juego de la Vida":
                    ejecucionContinua = true;
                    new Thread(handler).start();
                    break;
                // Pausa el juego si estaba en ejecución continua
                case "Pausar":
                    ejecucionContinua = false;
                    break;
                    
                // Reinicia el juego.    
                case "Reiniciar Juego de la Vida":
                    if (ejecucionContinua) { 
                        ejecucionContinua = false;
                    }
                    espacioCelular.reiniciar();                   
                    interfazJuegoDeLaVida.reiniciar();
                    break;
             }
        }

    }

}
