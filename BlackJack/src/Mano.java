
public class Mano {
	Carta[] mano = new Carta[5];
	
	public boolean estarLLena() {
		boolean respuesta = false;
		if (this.mano[4] != null) {
			respuesta = true;
		}
		return respuesta;
	}
	public int calcularMano() {
		int respuesta = 0;
		for (int carta = 0; carta < mano.length; carta++) {
			if (mano[carta] != null) {
				respuesta = respuesta + mano[carta].getValorNumerico();
			}
		}
		return respuesta;
	}
	public void agregarCarta(Baraja baraja) {
		if (this.estarLLena()== false) {
			int indice = 0;
			if (mano[indice] == null) {
				mano[indice] = baraja.top();
			}else {
				while (mano[indice+1] != null) {
					indice++;
				}
				mano[indice] = baraja.top();
			}
		}else {
			System.out.println("mano llena");
		}
	}
	public void imprimirMano() {
		for (int indice = 0; indice < mano.length; indice++) {
			if (mano[indice]!= null) {
				mano[indice].printCarta();
			}
		}	
	}
}
