import java.util.Scanner;
/**
 * Clase tablero, contiene el tablero y aumenta su tamano cuando
 * se le agregan fichas
 * @author Josef Ruzicka
 *@version 1
 *@since 28/05/2019
 */
public class Tablero {
	private Ficha[] tablero = new Ficha[1];
	
	/**
	 * metodo que le agrega una ficha especifica al tablero y 
	 * aumenta el tamano del tablero
	 * req: la ficha que se le agregara.
	 * mod: el tamano del tablero y su contenido.
	 * efe: la agregacion de la ficha y el cambio de tamano del tablero
	 * @param ficha
	 */
	public void agregarFicha(Ficha ficha) {
		if (tablero.length == 1) {
			tablero[0] = ficha;
		}
		else {
			System.out.println("digite 1 si quiere agregar la ficha"
					+ " a la izquierda, 2 si quiere agregarla a la derecha");
			int lado = this.solicitarInt();
			//agregar a la izquierda
			if (lado == 1) {
				//manejo de error.
				if (ficha.getValor1() == tablero[0].getValor1()
						|| ficha.getValor1() == tablero[0].getValor2()
						|| ficha.getValor2() == tablero[0].getValor1()
						|| ficha.getValor2() == tablero[0].getValor2()) {
					Ficha[] tableroTemporal = new Ficha[tablero.length+1];
				tableroTemporal[0] = ficha;
				for (int indice = 0; indice < tablero.length; indice++) {
					tableroTemporal[indice+1] = tablero[indice];
				}
				tablero = tableroTemporal;
				}else {
					System.out.println("jugada no es valida");
				}	
			}
			//agregar a la derecha
			else if (lado == 2) {
				//manejo de error.
				if (ficha.getValor1() == tablero[tablero.length-1].getValor1()
						|| ficha.getValor1() == tablero[tablero.length-1].getValor2()
						|| ficha.getValor2() == tablero[tablero.length-1].getValor1()
						|| ficha.getValor2() == tablero[tablero.length-1].getValor2()) {
				Ficha[] tableroTemporal = new Ficha[tablero.length+1];
				tableroTemporal[tableroTemporal.length] = ficha;
				for (int indice = 0; indice < tablero.length; indice++) {
					tableroTemporal[indice] = tablero[indice];
				}
				tablero = tableroTemporal;
			}else {
				System.out.println("jugada no es valida");
				}
			}
			
			// manejo de errores.
			else {
				System.out.println("Opcion no es valida");
				this.agregarFicha(ficha);
			}
		}
	}
	/**método que retorna un int
	 * req: n/a
	 * mod: n/a
	 * efe: n/a
	 * @return
	 */
	public int solicitarInt(){
		Scanner scanner = new Scanner(System.in);
		int dato = scanner.nextInt();
		return dato;
	}
}
