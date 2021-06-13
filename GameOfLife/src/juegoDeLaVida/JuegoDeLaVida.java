package juegoDeLaVida;

import Controlador.ControladorJuegoDeLaVida;

/**
 * Clase principal del programa Juego de la vida. 
 * 
 * Aqu� se crea una variable de tipo controlador 
 * y se intacia para poder ejecutar el m�todo inicioJuegoDeLaVida.
 *
 * @author Josef Ruzicka
 * @version 1
 * @since 12/06/2019
 * 
 */
public class JuegoDeLaVida {

	/**
	 * M�todo principal necesario para ejecutar programas en Java
	 * @param args -> variable utilizada para recibir par�metros desde l�nea de comandos.
	 */
    public static void main(String[] args) {
    	
        ControladorJuegoDeLaVida controladorJuegoDeLaVida = new ControladorJuegoDeLaVida();
        controladorJuegoDeLaVida.iniciarJuegoDeLaVida();
    }

}
