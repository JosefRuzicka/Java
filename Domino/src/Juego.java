/**
 * Clase juego, funciona como clase controlador y efectua todo el juego.
 * @author Josef Ruzicka
 * @version 1
 * @since 29/05/2019
 */
import java.util.Scanner;
public class Juego {
	private int puntosJugador1;
	private int puntosJugador2;
	private PilaDeFichas pila;
	private Tablero tablero;
	private Jugador jugador1, jugador2;
	private Scanner scanner;
	private boolean gano;
	
	tablero = new tablero();
	
	/**
	 * metodo turno, efectua cada turno y verifica que non se haya 
	 * ganado el juego aun
	 * req: n/a
	 * mod: si se gano, se modifica el boolean gano
	 * efe: un turno del juego
	 * @param jugador
	 */
	public void turno(Jugador jugador) {
		System.out.println("Indique 1 para comer, 2 para colocar una ficha");
		int respuesta = jugador.solicitarNumero();
		if (respuesta == 1) {
			jugador.agregarFicha(pila.retornarFicha(pila.pila.length));
		}else {
			jugador.imprimirFichas();
				System.out.println("indique la ficha que desea agregar");
				numero jugador.solicitarNumero();
				tablero.agregarFicha(jugador.fichas[numero]);
				if (jugador.fichas.legth == 0) {
					 gano = true;
				
		}
		}
	}
	/**
	 * Metodo que invoca todos los metodos de todas las clases
	 * necesarias para jugar.
	 * req: todas las otras clases
	 * mod: n/a
	 * efe: el juego
	 */
	public void jugar() {
		Scanner scanner = new Scanner(System.in);
			
			gano = false;
			pila = new PilaDeFichas();
			puntosJugador1 = 0;
			puntosJugador2 = 0;
			System.out.println("Indique el nombre del jugador 1");
			String nombre1 = scanner.nextLine();
			jugador1.setNombre(nombre1);
			System.out.println("Indique el nombre del jugador 2");
			String nombre2 = scanner.nextLine();
			jugador2.setNombre(nombre2);
			
			//se juega hasta que alguien gane 6 puntos.
			do {
				pila.barajarPila();
				//se reparten las manos
				for (int ficha = 0; ficha<7; ficha++) {
					jugador1.agregarFicha(pila.retornarFicha(pila.pila.length));
					jugador2.agregarFicha(pila.retornarFicha(pila.pila.length));
				}
				boolean comienza1 = false;
				boolean comienza2 = false;
				//se identifica quien inicia, o se vuelve a iniciar si ninguno puede
				for (int valor = 6; valor >= 0; valor--) {
					for (int ficha = 0; ficha <7 ; ficha++) { 
					if (comienza1 == false && comienza2 == false) {
						if(jugador1.getFicha(ficha).getValor1 == valor && jugador1.getFicha().getValor2 == valor) {
						 	comienza1 = true;
					}else if (jugador1.getFicha(ficha).getValor1 == valor && jugador1.getFicha().getValor2 == valor) {
						comienza2 = true;
					}else if (valor == 0) {
						this.Jugar();
					}
						//se juega
						do {
							this.turno(jugador1);
							if (gano == true) {
								puntosJugador1++;
							}
							}
							this.turno(jugador2);
							if (gano == true) {
								puntosJugador2++;
							}
						}while (gano == false);
					}
				}
			}while (puntosJugador1 < 6 && puntosJugador2 < 6);		
		}
	/**
	 * metodo main que comienza y lleva a cabo el jueg0.
	 * req: n/a
	 * mod: n/a
	 * efe: el juego.
	 * @param args
	 */
	public static void main(String[] args) {
		Juego juego = new Juego();
		juego.jugar();
	}
}
	

