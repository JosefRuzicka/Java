package juegoDeLaVida;

import Controlador.ControladorJuegoDeLaVida;

/**
 * Clase principal del programa Juego de la vida. 
 * 
 * Aquí se crea una variable de tipo controlador 
 * y se intacia para poder ejecutar el método inicioJuegoDeLaVida.
 *
 * @author Josef Ruzicka
 * @version 1
 * @since 12/06/2019
 * 
 */
public class JuegoDeLaVida {

	/**
	 * Método principal necesario para ejecutar programas en Java
	 * @param args -> variable utilizada para recibir parámetros desde línea de comandos.
	 */
    public static void main(String[] args) {
    	
        ControladorJuegoDeLaVida controladorJuegoDeLaVida = new ControladorJuegoDeLaVida();
        controladorJuegoDeLaVida.iniciarJuegoDeLaVida();
    }

}
