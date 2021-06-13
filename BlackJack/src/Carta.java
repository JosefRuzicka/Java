
public class Carta {
	private String valor;
	private String palo;
	
	public Carta(int valor, int palo) {
		if (valor == 0) {
			this.valor = "A";
		}else if (valor == 1) {
			this.valor = "J";
		}else if (valor == 11) {
			this.valor = "Q";
		}else if (valor == 12) {
			this.valor = "K";
		}else {
			this.valor = ""+valor;
		}
		
		switch (palo){
		case 0:
			this.palo = "corazones";
			break;
		case 1:
			this.palo = "treboles";
			break;
		case 2:
			this.palo = "espadas";
			break;
		case 3:
			this.palo = "diamantes";
			break;
		}
		
	}
	public int getValorNumerico() {
		int respuesta = 0;
		if (valor.equals("A") || valor.equals("J") || valor.equals("Q") || valor.equals("K")) {
			respuesta = 10;
		}else {
			respuesta = Integer.parseInt(valor);
		}
		return respuesta;
	}
	
	public void printCarta(){
		System.out.println(""+valor+" "+palo);
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getPalo() {
		return palo;
	}
	public void setPalo(String palo) {
		this.palo = palo;
	}
	
	
}
