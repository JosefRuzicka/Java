
public class Baraja {
	private Carta[] baraja;
	
	public void llenarBaraja() {
		baraja = new Carta[52];
		for (int indice = 0; indice < baraja.length; indice++) {
			for (int palo = 0; palo < 4; palo++) {
				for (int valor = 0; valor < 13; valor++) {
					baraja[indice] = new Carta(valor, palo);
				}
			}
		}
	}
	public void barajar() {
		Carta[] temporal = new Carta[52];
		for (int indice = 0; indice < baraja.length; indice++) {
			int random = (int)Math.random()*53;
			if (baraja[random]!=null) {
				temporal[indice] = baraja[random];
				baraja[random] = null;
			}else {
				indice--;
			}
		}
		baraja = temporal;
	}
	public Carta top() {
		int indice = 0;
		if (baraja[51] != null) {
			while (baraja[indice] != null) {
				indice++;
			}	
		}
		Carta carta = new Carta(0,0);
		carta.setPalo(baraja[indice].getPalo());
		carta.setValor(baraja[indice].getValor());
		baraja[indice] = null;
		return carta;
	}
}
