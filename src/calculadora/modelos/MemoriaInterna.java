package calculadora.modelos;

import java.util.Arrays;

public class MemoriaInterna {
	private final int elementosDisplay, tamanoMemoriaInterna;
	private final char[] matriz;
	private int posicionCursor, posicionMinimaVisible, posicionMaximaVisible;
	 
	
	public MemoriaInterna(int tamanoMemoriaInterna, int elementosDisplay) throws IllegalArgumentException {
		if ((elementosDisplay >= 0) && (elementosDisplay < tamanoMemoriaInterna)) {
			this.elementosDisplay = elementosDisplay;
		} else { throw new IllegalArgumentException("El número máximo de elementos del display es de "+tamanoMemoriaInterna+"."); }
		if (tamanoMemoriaInterna >= elementosDisplay) {
			this.tamanoMemoriaInterna = tamanoMemoriaInterna;
		} else { throw new IllegalArgumentException("El tamaño de la memoria interna debe ser igual o superior al número de elementos del display."); }
		
		this.matriz = new char[tamanoMemoriaInterna];
		this.inicializarMatriz();
	}
	
	/**
	 * Inicializa la memoria, la posición del cursor y los límites visibles del display
	 */
	private void inicializarMatriz() {
		Arrays.fill(matriz, ' ');
    	this.posicionCursor = 0;
    	this.posicionMinimaVisible = 0;
    	this.posicionMaximaVisible = this.elementosDisplay;
	}
	
	/**
	 * Inicializa la memoria, la posición del cursor y los límites visibles del display
	 */
	public void resetear() {
		this.inicializarMatriz();
	}
	
	/**
	 * Establece la posición mínima que se verá en el display
	 * @param posicion Posición mínima
	 * @throws IllegalArgumentException lanzada cuando la posición está fuera de rango.
	 */
	private void _setPosicionMinimaVisible(int posicion) throws IllegalArgumentException {
		int maximo = tamanoMemoriaInterna-elementosDisplay;
		if ((posicion >= 0) && (posicion < maximo)) { this.posicionMinimaVisible = posicion; }
		else { throw new IllegalArgumentException("Índice del mínimo visible del display fuera de rango. Posición: "+posicion+" Rango [0-"+(maximo-1)+"]."); }
	}
	
	/**
	 * Establece la posición máxima que se verá en el display
	 * @param posicion Posición máxima
	 * @throws IllegalArgumentException lanzada cuando la posición está fuera de rango.
	 */
	private void _setPosicionMaximaVisible(int posicion) throws IllegalArgumentException {
		int minimo = elementosDisplay;
		if ((posicion >= minimo) && (posicion < tamanoMemoriaInterna)) { this.posicionMaximaVisible = posicion; }
		else { throw new IllegalArgumentException("Índice del máximo visible del display fuera de rango. Posición: "+posicion+" Rango ["+(minimo)+"-"+(tamanoMemoriaInterna-1)+"]."); }
	}
	
	public void setMatriz(char[] matriz) {
		if (matriz.length == this.matriz.length) {
			for (int i = 0; i < matriz.length; i++) {
				this.matriz[i] = matriz[i];
			}
		}
	}
	
	
	/**
	 * Inserta un caracter en una posición dada
	 * @param posicion Posición donde colocar el caracter
	 * @param caracter Caracter a introducir
	 * @throws IllegalArgumentException se devuelve si la posición está fuera de rango.
	 */
	public void insertarCaracter(int posicion, char caracter) throws IllegalArgumentException {
		int limite = caracter == 'A' ? tamanoMemoriaInterna-4 : tamanoMemoriaInterna-2;
		if (( posicion >= 0 ) && (posicion < limite )) {
			_insertarCaracter(posicion,caracter);
		} else { 
			throw new IllegalArgumentException("Posición fuera de rango. Posición: "+posicion+" Rango [0-"+limite+"]."); 
		}
	}
	
	/**
	 * Inserta un caracter en una posición dada
	 * @param posicion Posición donde colocar el caracter
	 * @param caracter Caracter a introducir
	 */
	private void _insertarCaracter(int posicion, char caracter) {
		int contador = 0;
		String cadena = "";
		
		for (char c : this.matriz) { cadena+=c; }
		Arrays.fill(matriz,' ');
		String prefijo = cadena.substring(0, posicion);
		String sufijo = cadena.substring(posicion,tamanoMemoriaInterna-1);
		for (char c : prefijo.toCharArray()) { matriz[contador] = c; contador++; }
		matriz[contador] = caracter;
		contador++;
		for (char c : sufijo.toCharArray()) { matriz[contador] = c; contador++; }
	}

	/**
	 * Elimina el caracter situado antes del cursor.
	 */
	public void eliminarCaracter() {
		if (posicionCursor-1 >= 0) {
			int contador = matriz[posicionCursor-1] == 's' ? 3 : 1;
			
			while (contador > 0) { 
				_setPosicionCursor(posicionCursor-1);
				this.matriz[posicionCursor] = ' ';
				contador--;
			}
		} 
		eliminarEspaciosMatriz();
	}
	
	/**
	 * Recorre la memoria y elimina los espacios en blanco.
	 */
	private void eliminarEspaciosMatriz() {
		String aux = "";
		for (int i = 0; i < matriz.length; i++) { if (matriz[i] != ' ') { aux += matriz[i]; } }
		Arrays.fill(matriz, ' ');
		for (int i = 0; i < aux.length(); i++) {  matriz[i] = aux.charAt(i); }
	}
	
	/**
	 * Devuelve un array con los carácteres visibles en el display.
	 * @return char array
	 */
	public char[] getMatrizVisible() {
		return Arrays.copyOfRange(this.matriz, posicionMinimaVisible, posicionMaximaVisible);
	}
	
	/**
	 * Inserta un caracter en la posición del cursor. 
	 * @param caracter Caracter a insertar.
	 */
	public void insertarCaracter(char caracter) {
		int limite = caracter == 'A' ? tamanoMemoriaInterna-4 : tamanoMemoriaInterna-2;
		if (posicionCursor < limite) { // Se restan 2 al tamaño porque en la última posición de memoria no se escribe ningún dígito.
			if (caracter == 'A') {
				if (matriz[posicionCursor] != 'A') {
					this.matriz[posicionCursor] = 'A';
					_insertarCaracter(posicionCursor+1, 'n');
					_insertarCaracter(posicionCursor+2, 's');
				}
			} else {
				char aux = matriz[posicionCursor];
				this.matriz[posicionCursor] = caracter;
				if (aux == 'A') {
					this.matriz[posicionCursor+1] = ' ';
					this.matriz[posicionCursor+2] = ' ';
					eliminarEspaciosMatriz();
				}
			}
			this.avanzarCursor();
		}
	}
	
	/**
	 * Posiciona el cursor al inicio
	 */
	public void cursorInicio() {
		_setPosicionCursor(0);
	}
	
	/**
	 * Posiciona el cursor al final del texto introducido
	 */
	public void cursorFin() {
		while (matriz[posicionCursor] != ' ') {
			_setPosicionCursor(posicionCursor+1);
		}
	}
	
	/**
	 * Posiciona el cursor en un lugar determinado
	 * @param posicion Nueva posición del cursor
	 * @throws IllegalArgumentException es devuelta cuando se pasa una posición fuera de rango
	 */
	
	private void _setPosicionCursor(int posicion) throws IllegalArgumentException {
		if ((posicion >= 0) && (posicion < tamanoMemoriaInterna)) { 
			this.posicionCursor = posicion; 
			if (posicion >= elementosDisplay) {
				int posMin = posicion-elementosDisplay < tamanoMemoriaInterna-elementosDisplay-1 ? posicion-elementosDisplay+1 : tamanoMemoriaInterna-elementosDisplay-1;
				_setPosicionMinimaVisible(posMin);
				_setPosicionMaximaVisible(this.posicionMinimaVisible+elementosDisplay);
			} else {
				_setPosicionMinimaVisible(0);
				_setPosicionMaximaVisible(elementosDisplay);
			}
		} else { throw new IllegalArgumentException("Posición del cursor fuera de rango. Posición: "+posicion+" Rango [0-"+(tamanoMemoriaInterna-1)+"]."); }
	}
	
	/**
	 * Posiciona el cursor en una nueva posición
	 * @param posicion Posición nueva.
	 * @throws IllegalArgumentException lanzada si la posición está fuera de rango.
	 */
	public void setPosicionCursor(int posicion) throws IllegalArgumentException {
		_setPosicionCursor(posicion);
	}
	
	/**
	 * Avanza el cursor un símbolo.
	 * @throws IllegalArgumentException lanzada si se intenta acceder a una posición fuera de los límites
	 */
	public void avanzarCursor() throws IllegalArgumentException{
		if ((this.posicionCursor+1 < tamanoMemoriaInterna) && (matriz[posicionCursor] != ' ')) 
			if (matriz[posicionCursor] == 'A' && matriz[posicionCursor+1] == 'n') { _setPosicionCursor(this.posicionCursor+3); } else { _setPosicionCursor(this.posicionCursor+1); }
	}
	
	/**
	 * retrocede el cursor un símbolo.
	 * @throws IllegalArgumentException lanzada si se intenta acceder a una posición fuera de los límites
	 */
	public void retrocederCursor() throws IllegalArgumentException{
		if (this.posicionCursor > 0) { 
			if (matriz[posicionCursor-1] != 's')  { _setPosicionCursor(this.posicionCursor-1); } else { _setPosicionCursor(this.posicionCursor-3); }
		}
	}
	
	public int getPosicionCursor() {
		return posicionCursor;
	}
	
	public int getPosicionMinimaVisible() {
		return posicionMinimaVisible;
	}

	public int getPosicionMaximaVisible() {
		return posicionMaximaVisible;
	}

	public char[] getMatriz() {
		return matriz;
	}
}
