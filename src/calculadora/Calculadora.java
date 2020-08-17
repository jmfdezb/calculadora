package calculadora;


import javax.swing.SwingUtilities;

import calculadora.controladores.Controlador;
import calculadora.modelos.MemoriaInterna;
import calculadora.vistas.MarcoCalculadora;

/**
 * <h1> Ejercicio Calculadora </h1>
 * Calculadora científica
 *	- Modelo MVC 
 *  - GUI con Swing
 *  - Display de entrada con capacidad para editar la expresión introducida.
 *  - Se han modelizado lcds de 2, 7 y 30 segmentos para simular el display de la calculadora.
 *  - Utiliza analizador Léxico + Sintáctico y árboles binarios para traducir de string a expresión matemática
 *  - Pruebas unitarias con JUnit4 para probar la conversión de una variada casuística de expresiones matemáticas.
 * 
 * 
 * @author Josep Maria Fernández Barba
 * @version 1.0
 * @since 2020-05-15
 *
 */
public class Calculadora {
	private static final int TAMANO_MEMORIA = 64;
	private static final int ELEMENTOS_DISPLAY_ENTRADA = 13;
	
	public static void main(String[] args) throws InterruptedException {
		MemoriaInterna memoria = new MemoriaInterna(TAMANO_MEMORIA,ELEMENTOS_DISPLAY_ENTRADA);
		SwingUtilities.invokeLater(new Runnable() { public void run() { ejecutarInterfaz(new Controlador(memoria)); } });
	}
	
	private static void ejecutarInterfaz(Controlador controlador) {
		new MarcoCalculadora(340,430, ELEMENTOS_DISPLAY_ENTRADA, controlador);
	}
}
