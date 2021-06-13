package Modelo;

import java.util.Random;

/**
 * Clase que contiene la matriz de celulas en forma de booleans, contiene 
 * los metodos para que las reglas del juego se cumplan.
 *
 * @author Josef Ruzicka
 * @version 1
 * @since 12/06/2019
 */
 
public class EspacioCelular  {

    //Declaración de Variables.
    private int filas;
    private int columnas;
    private boolean matrizCelular [][];


    /**
     * Constructor de la clase EspacioCelular
     *
     * Req: n/a.
     * Efe: crea una matriz de celulas (booleans), inicializa las variables.
     * Mod: inicializa las variables.
     * 
     * @param filas -> tamano de filas de la matriz de celulas.
     * @param columnas -> tamano de columnas de la matriz de celulas.
     */
    public EspacioCelular(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        matrizCelular = new boolean[filas][columnas];
        for (int fila = 0; fila < matrizCelular.length; fila++) {
            for (int columna = 0; columna < matrizCelular[fila].length; columna++) {
                matrizCelular[fila][columna] = false;
            }
        }
    }

    /**
     * Retorna la cantidad de filas presentes en la comunidad.
     *
     * @return filas -> el valor que se le dio al int filas en el constructor, o en cambiar tamano.
     */
    public int getFilas() {
        return filas;
    }

    /**
     * Retorna la cantidad de columnas presentes en la comunidad.
     *
     * @return columnas, int con la cantidad de columnas de la comunidad.
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * Devuelve la matriz a su estado original, es decir, con la totalidad de sus casillas en false (mata todas las celulas)
     */
    public void reiniciar() {
    	for (int fila = 0; fila < matrizCelular.length; fila++) {
            for (int columna = 0; columna < matrizCelular[fila].length; columna++) {
                matrizCelular[fila][columna] = false;
            }
        }
    }

    /**
     * Vacía la matriz, y la vuelve a llenar con una nueva dimension de tamano.
     * 
     * @param filas -> nuevo valor del int filas, nueva dimension de matriz.
     * @param columnas -> nuevo valor del int columnas, nueva dimension de matriz.
     */
    public void cambiarTamaño(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        matrizCelular = null;
        matrizCelular = new boolean[this.filas][this.columnas];
        for (int fila = 0; fila < matrizCelular.length; fila++) {
            for (int columna = 0; columna < matrizCelular[fila].length; columna++) {
                matrizCelular[fila][columna] = false;
            }
        }
    }

    /**
     * Coloca celulas vivas en posiciones aleatorias.
     * @param celulasVivas -> la cantidad de celulas vivas que se desean colocar en la matriz
     */
    //voy a agregar manejo de error.
    public void iniciarAleatorio(int celulasVivas) throws NumberFormatException, Exception  {
    	if (celulasVivas < 0 || celulasVivas > (this.matrizCelular.length * this.matrizCelular[0].length)) {
			throw new Exception("El número digitado no es positivo o es mayor al número máximo de células.");
    		}else {
    	Random generador = new Random();
        this.reiniciar();
        int fila;
        int columna;
        while (celulasVivas > 0) {
            fila = generador.nextInt(this.getFilas());
            columna = generador.nextInt(this.getColumnas());
            if (this.matrizCelular[fila][columna] == false) {
                this.matrizCelular[fila][columna] = true;
                celulasVivas -= 1;
            	}
            }
        }
    }
    
 
  
    /**
     * retorna el estado de la matriz
     * @return
     */
    public boolean [][] getEstadoMatrizCelular() {    	
        return matrizCelular;
    }
    //voy a agregar un metodo para contar los vecinos de cada celula.
    /**
     * Cuenta los vecinos de cada celula para determinar que sucedera con esta celula en la proxima generacion.
     * 
     * @param fila -> la direccion de la celula especifica que queremos revisar.
     * @param columna -> la direccion de la celula especifica que queremos revisar.
     * @return -> la cantidad de vecinos de las celulas.
     */
    public int contarVecinos(int fila, int columna) {
    	int vecinos = 0;
		
		//vecino superior izquierdo
    	//tome en consideracion que la matriz es cuadrada.
		if (matrizCelular[(fila-1+matrizCelular.length)%matrizCelular.length][(columna-1+matrizCelular.length)%matrizCelular.length] == true){
			vecinos++;
		}
		//vecino superior
		if (matrizCelular[fila][(columna-1+matrizCelular.length)%matrizCelular.length] == true){
			vecinos++;
		}
		//vecino superior derecho
		if (matrizCelular[(fila+1)%matrizCelular.length][(columna-1+matrizCelular.length)%matrizCelular.length] == true){
			vecinos++;
		}
		//vecino izquierdo
		if (matrizCelular[(fila-1+matrizCelular.length)%matrizCelular.length][columna] == true){
			vecinos++;
		}
		//vecino derecho
		if (matrizCelular[(fila+1)%matrizCelular.length][columna] == true){
			vecinos++;
		}
		//vecino inferior izquierdo
		if (matrizCelular[(fila-1+matrizCelular.length)%matrizCelular.length][(columna+1)%matrizCelular.length] == true){
			vecinos++;
		}
		//vecino inferior
		if (matrizCelular[fila][(columna+1)%matrizCelular.length] == true){
			vecinos++;
		}
		//vecino inferior derecho
		if (matrizCelular[(fila+1)%matrizCelular.length][(columna+1)%matrizCelular.length] == true){
			vecinos++;
		}
		return vecinos;
	}
    //voy a agregar un metodo para evaluar el estado de la celula en la proxima generacion.
    /**
     * Determina si la celula va a estar viva o muerta en la proxima generacion.
     * 
     * @param fila -> la direccion de la celula especifica que queremos revisar.
     * @param columna -> la direccion de la celula especifica que queremos revisar.
     * @return proximoEstadoCelula -> el valor de la celula en la proxima generacion (viva es true, muerta es false)
     */
    public boolean evaluarCelula(int fila, int columna) {
    	boolean proximoEstadoCelula = false;
    	if (matrizCelular[fila][columna] == true) {
    		//supervivencia
    		if (this.contarVecinos(fila, columna) == 2 || this.contarVecinos(fila, columna) == 3) {
    			proximoEstadoCelula = true;
    		}
    		//muerte por soledad
    		else if (this.contarVecinos(fila, columna) == 0 || this.contarVecinos(fila, columna) == 1) {
    			proximoEstadoCelula = false;
    		}
    		//muerte por sobrepoblacion
    		else if (this.contarVecinos(fila, columna) >= 0) {
    			proximoEstadoCelula = false;
    		}
    	}else if (matrizCelular[fila][columna] == false) {
    		//nacimiento
    		if (this.contarVecinos(fila, columna) == 3) {
    			proximoEstadoCelula = true;
    		}
    	}else {
    		proximoEstadoCelula = matrizCelular[fila][columna];
    	}
    	return proximoEstadoCelula;
    }
    
    //voy a modificar esta clase para que funcione con los nuevos metodos que hice.
	public void siguienteGeneracion(EspacioCelular espacioCelular) {
		boolean matrizCelularNueva[][] = new boolean[espacioCelular.filas][espacioCelular.columnas];
     
        for (int fila = 0; fila < espacioCelular.filas; fila++) {
			for (int columna = 0; columna < espacioCelular.columnas; columna++) {
				matrizCelularNueva[fila][columna] = this.evaluarCelula(fila, columna);
			}
        }
      matrizCelular = matrizCelularNueva;
	}
}
