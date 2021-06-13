import java.security.SecureRandom;
/**
 * Clase que crea y contiene todas las fichas del juego.
 * 
 * @author Josef Ruzicka
 *@version 1
 *@since 26/05/2019
 */
public class PilaDeFichas {
	public Ficha[] pila;
	
	/**
	 * Metodo constructor, crea las 28 fichas
	 * efe: crea las 28 fichas en la pila
	 */
	public PilaDeFichas() {
		pila = new Ficha[28];
		for (int indice = 0; indice < 28; indice++){
			for (int valor1 = 0; valor1 <= 6; valor1++) {
				for (int valor2 = 0; valor2 <= valor1; valor2++) {
					if (pila[indice] != null) {
					pila[indice] = new Ficha();
					pila[indice].setValor1(valor1);
					pila[indice].setValor2(valor2);
					}
				}
			}
		}
	}
	/**
	 * metodo que baraja las 28 fichas.
	 * req: n/a
	 * mod: el orden de la pila
	 * efe: cambia el orden de la pila por uno al azar
	 */
	public void barajarPila() {
		Ficha[] pilaTemporal = new Ficha[28];
		SecureRandom random = new SecureRandom();
		for (int indice = 0; indice < 28; indice++) {
			int randomInt = random.nextInt(28);
			if (pilaTemporal[randomInt] != null) {
				pilaTemporal[randomInt] = pila[randomInt];
			}else {
				indice--;
			}
		}
		pila = pilaTemporal;
	}
	/**
	 * retorna una ficha especifica de la pila.
	 * req: n/a
	 * mod: n/a
	 * efe: retorna un objeto especifico en la pila
	 * @param indice
	 * @return
	 */
	public Ficha retornarFicha(int indice) {
		return pila[indice];
	}
}
