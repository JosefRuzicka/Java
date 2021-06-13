/**
 * Clase ficha, tiene dos caras con un valor cada una.
 * @author Josef Ruzicka
 *@version 1
 *@since 26/06/2019
 */
public class Ficha {
	private int valor1;
	private int valor2;
	
	/**
	 * metodo que retorna el valor 1 de la ficha.
	 * req: n/a
	 * mod: n/a
	 * efe: retonra el valor 1 de la ficha.
	 * @return
	 */
	public int getValor1() {
		return this.valor1;
	}
	/**
	 * Metodo que le asigna un valor al valor 1 de la ficha
	 * req: n/a
	 * mod: el valor 1 de la ficha
	 * efe: la modificacion del valor 1 de la ficha
	 * @param valor
	 */
	public void setValor1(int valor) {
		this.valor1 = valor;
	}
	/**
	 * metodo que retorna el valor 2 de la ficha.
	 * req: n/a
	 * mod: n/a
	 * efe: retonra el valor 2 de la ficha.
	 * @return
	 */
	public int getValor2() {
		return this.valor2;
	}
	/**
	 * Metodo que le asigna un valor al valor 2 de la ficha
	 * req: n/a
	 * mod: el valor 2 de la ficha
	 * efe: la modificacion del valor 2 de la ficha
	 * @param valor
	 */
	public void setValor2(int valor) {
		this.valor2 = valor;
	}
	/**
	 * metodo que imprime en pantalla la ficha.
	 * req: n/a
	 * mod: n/a
	 * efe: imprime la ficha en pantalla
	 */
	public void imprimirFicha() {
		System.out.println("[ "+this.valor1+" : "+this.valor2+"]");
	}
}
