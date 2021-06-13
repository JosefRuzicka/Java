import java.util.Random;
import java.util.Scanner;
/**
 * 
 * @author Josef Ruzicka B87095
 *
 */
public class Principal {
	private Mano mano1;
	private Mano mano2;
	private Baraja baraja;
	private Scanner scanner;

	public static void main(String[] args) {
		Principal principal = new Principal();
		principal.mano1 = new Mano();
		principal.mano2 = new Mano();
		principal.baraja = new Baraja();
		principal.baraja.llenarBaraja();
		principal.baraja.barajar();
		principal.scanner = new Scanner(System.in);
		Random random = new Random();
		System.out.println("Josef Ruzicka B87095");
		
		principal.mano1.agregarCarta(principal.baraja);
		principal.mano1.agregarCarta(principal.baraja);
		principal.mano2.agregarCarta(principal.baraja);
		principal.mano2.agregarCarta(principal.baraja);
		
		boolean ganado = false;
		while(ganado == false && principal.mano1.calcularMano() <= 21) {
			while(ganado == false) {
				principal.mano1.imprimirMano();
				System.out.println("Puntaje: "+principal.mano1.calcularMano());
				System.out.println("desea otra carta?, seleccione 1 si es afirmativo, otra tecla si no lo es");
				if (principal.scanner.nextInt() == 1) {
					principal.mano1.agregarCarta(principal.baraja);
					if (principal.mano1.estarLLena() && principal.mano1.calcularMano() <= 21) {
						ganado = true;
					}
				}	
			}
			for (int indice = 0; indice < 3; indice++) {
				int aleatorio = random.nextInt(2);
				if (aleatorio == 1) {
					principal.mano2.agregarCarta(principal.baraja);
				}
			}
			if (principal.mano2.calcularMano() > 21) {
				ganado = true;
			}
			if( principal.mano1.calcularMano() <= 21 && principal.mano1.calcularMano() -21 > principal.mano2.calcularMano() ) {
				ganado = true;
			}
		}
		if (ganado == true) {
			System.out.println("has ganado crack");
		}else {
			System.out.println("has ganado noob");
		}
	}
}
