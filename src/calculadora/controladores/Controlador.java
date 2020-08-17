package calculadora.controladores;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import calculadora.modelos.MemoriaInterna;
import calculadora.util.parser.Evaluador;
import calculadora.vistas.display.Display;

public class Controlador {
	private static final String SIMBOLOS_NUMERICOS = "0123456789";
	private static final String SIMBOLOS_PARENTESIS = "[]{}()";
	private static final String SIMBOLOS_OPERACIONES = "^e/x*-+()·.÷–•√r:";
	private static final String SIMBOLOS_ACEPTADOS = " "+SIMBOLOS_NUMERICOS+SIMBOLOS_PARENTESIS+SIMBOLOS_OPERACIONES;
	
	private final MemoriaInterna memoria;
	private Display display;
	private double anterior = 0;
	private boolean bloqueada = false, resultadoMostrado = false;

	public Controlador(MemoriaInterna memoria) {
		this.memoria = memoria;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	/**
	 * Reinicia la calculadora
	 */
	protected void reset() {
		bloqueada = false;
		resultadoMostrado = false;
		memoria.resetear();
		display.resetear();
		actualizarCursorDisplay();
	}

	/**
	 * Actualiza el display
	 */
	private void actualizarCursorDisplay() {
		display.actualizarPosicionCursor(memoria.getPosicionCursor());
		display.mostrarCambios(memoria.getMatrizVisible());
	}
	
	/**
	 * Al devolver un resultado o mostrar un error, la calculadora se bloquea.
	 * @return boolean indica si está bloqueada o no.
	 */
	protected boolean isBloqueada() {
		return bloqueada;
	}

	/**
	 * Avanza el cursor un símbolo. En caso de estar mostrando un resultado, coloca el cursor al inicio.
	 */
	protected void avanzarCursor() {
		if (resultadoMostrado) {
			resultadoMostrado = false;
			display.bloquearCursor(false); 
			memoria.cursorInicio(); 
		} else { 
			memoria.avanzarCursor(); 
		}
		actualizarCursorDisplay();
	}

	/**
	 * Retrocede el cursor un símbolo. En caso de estar mostrando un resultado, lo mueve al final.
	 */
	protected void retrocederCursor() {
		if (resultadoMostrado) {
			resultadoMostrado = false;
			display.bloquearCursor(false); 
			memoria.cursorFin(); 
		} else { 
			memoria.retrocederCursor(); 
		}
		actualizarCursorDisplay();
	}

	/**
	 * Inserta un caracter en la memoria de la calculadora.
	 * @param caracter
	 */
	protected void insertarCaracter(char caracter) {
		if (resultadoMostrado) {
			display.bloquearCursor(false);
			resultadoMostrado = false;
			this.borrarDisplayEntrada();
			if (SIMBOLOS_OPERACIONES.indexOf(caracter) != -1) { memoria.insertarCaracter('A'); }
		}
		memoria.insertarCaracter(caracter);
		actualizarCursorDisplay();
	}

	/**
	 * Elimina el caracter anterior a la posición del cursor.
	 */
	protected void eliminarCaracter() {
		if (!resultadoMostrado) {
			memoria.eliminarCaracter();
			actualizarCursorDisplay();
		}
	}

	/**
	 * Devuelve el último resultado calculado.
	 * @return Double resultado anterior.
	 */
	protected double getAnterior() {
		return this.anterior;
	}
	
	protected void calcularResultado() {
		char[] matriz = memoria.getMatriz();
		String cadena = "";
		for (char c : matriz) { cadena+=c; }
		try {
			anterior = Evaluador.stringADoble(cadena, anterior); 
		} catch ( Exception e) {
			System.out.println(e.getStackTrace().toString());
			this.bloqueada = true;
			display.mostrarErrorSintactico();
		}
		if (Double.isNaN(anterior) || Double.isInfinite(anterior)) {
			this.bloqueada = true;
			display.mostrarErrorMatematico();
		} else {
			display.setResultado(anterior);
			resultadoMostrado = true;
		}
	}
	
	/**
	 * Borra el display de entrada
	 */
	protected void borrarDisplayEntrada() {
		char[] matriz = memoria.getMatriz();
		for (int i = memoria.getPosicionCursor(); i < matriz.length; i++) { if (matriz[i] == ' ') { break; } else { this.avanzarCursor(); }  }
		while (memoria.getPosicionCursor() != 0) { this.eliminarCaracter(); }
	}
	
	protected void paste() {
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    DataFlavor flavor = DataFlavor.stringFlavor;
	    if (clipboard.isDataFlavorAvailable(flavor)) {
	    	try {
		        String text = (String) clipboard.getData(flavor);
		        for (char c : text.toCharArray()) {
		        	if (SIMBOLOS_ACEPTADOS.indexOf(c) != -1) { 
		        		switch (c) {
		        		case 'e':
		        			insertarCaracter('^');
		        			break;
		        		case '÷':
		        		case ':':
		        			insertarCaracter('/');
		        			break;
		        		case '·':
		        		case '•':
		        		case 'x':
		        			insertarCaracter('*');
		        			break;
		        		case '–':
		        			insertarCaracter('-');
		        			break;
		        		case '[':
		        		case '{':
		        			insertarCaracter('(');
		        			break;
		        		case ']':
		        		case '}':
		        			insertarCaracter(')');
		        			break;
		        		case '√':
		        			insertarCaracter('r');
		        			break;
		        		default:
		        			insertarCaracter(c);
		        			break;
		        		}
		        	}
		        }
	    	} catch (UnsupportedFlavorException | IOException e) { System.out.println(e); }
	    }
	}
	
	protected void copy() {
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    StringSelection selection = new StringSelection(Double.toString(this.anterior));
	    clipboard.setContents(selection, null);
	}
}
