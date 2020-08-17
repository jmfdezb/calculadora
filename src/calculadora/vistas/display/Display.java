package calculadora.vistas.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class Display extends JPanel implements ActionListener {
	private static final int TAMANO_DISPLAY_RESULTADO = 10;
	private static final long serialVersionUID = 1L;
	private final BufferedImage pantallaLcd;
	private final int elementosDisplayEntrada;
	private final char matrizDigitos [] = new char[TAMANO_DISPLAY_RESULTADO];
	private final char matrizDecimales [] = new char[TAMANO_DISPLAY_RESULTADO];
	private final LcdSieteSegmentos[] lcdDigitos = new LcdSieteSegmentos[TAMANO_DISPLAY_RESULTADO];
	private final LcdDosSegmentos[] lcdDecimales = new LcdDosSegmentos[TAMANO_DISPLAY_RESULTADO];
	private final LcdCincoPorSeisSegmentos[] lcdEntradas;
    private final javax.swing.Timer TEMPO = new javax.swing.Timer(700, this); 
    private int posicionCursor = 0;
	
    public Display(int ancho, int alto, int elementosDisplayEntrada) {
    	this.elementosDisplayEntrada = elementosDisplayEntrada;
    	this.lcdEntradas = new LcdCincoPorSeisSegmentos[elementosDisplayEntrada];
    	setBounds(0, 26, ancho, alto);
    	this.setLayout(new OverlayLayout(this));
    	setOpaque(false);
        
        pantallaLcd = crearPantallaLcd(ancho, alto);
        
        crearLcdResultado();
        resetear();
        for (int i = 0; i < lcdDigitos.length; i++) {
        	this.add(lcdDigitos[i]);
        	this.add(lcdDecimales[i]);
        }
        for (int i = 0; i < lcdEntradas.length; i++) {
        	this.add(lcdEntradas[i]);
        }
        TEMPO.start();
    }

    /**
     * Reinicia el display del resultado.
     */
    public void resetear() {
    	Arrays.fill(matrizDigitos, ' ');
    	Arrays.fill(matrizDecimales, ' ');
    	matrizDigitos[TAMANO_DISPLAY_RESULTADO-1] = '0';
    	matrizDecimales[TAMANO_DISPLAY_RESULTADO-1] = ',';
    	lcdEntradas[posicionCursor].apagarCursor();
    	TEMPO.restart();
    	aplicarCambiosMatrices();
    }
    
    private void crearLcdResultado() {
    	int posicionX = 21;
		for (int i = 0; i < lcdDigitos.length; i++) {
			this.lcdDigitos[i] = new LcdSieteSegmentos(posicionX,65);
			this.lcdDecimales[i] = new LcdDosSegmentos(posicionX+28,95);
			posicionX +=28;
		}
		
		posicionX = 25;
		for (int i = 0; i < lcdEntradas.length; i++) {
			this.lcdEntradas[i] = new LcdCincoPorSeisSegmentos(posicionX,35);
			posicionX +=22;
		}
    }
    
    /**
     * Coloca el cursor en una nueva posición
     * @param posicionCursor Nueva posición.
     */
    public void actualizarPosicionCursor(int posicionCursor) {
    	if (posicionCursor < elementosDisplayEntrada && posicionCursor >= 0) {
    		lcdEntradas[this.posicionCursor].apagarCursor();
    		this.posicionCursor = posicionCursor;
    		lcdEntradas[this.posicionCursor].encenderCursor();
    	}
    }
    
    /**
     * Muestra el contenido de las matrices de Dígitos y decimales en el display
     */
    private void aplicarCambiosMatrices() {
    	for (int i = 0; i < lcdDigitos.length; i++) {
    		this.lcdDigitos[i].setDigito(matrizDigitos[i]);
			this.lcdDecimales[i].setDigito(matrizDecimales[i]);
		}
    }
    
    /**
     * Actualiza la vista del lcd de entrada con los datos del array pasado por parámetro
     * @param arrayDisplayEntrada array con los carácteres a mostrar.
     */
    public void mostrarCambios(char [] arrayDisplayEntrada) {
    	for (int i = 0; i < elementosDisplayEntrada; i++) { this.lcdEntradas[i].setDigito(arrayDisplayEntrada[i]); }
    }
    
    /**
     * Avanza el cursor por el display de entrada
     */
    public void avanzarCursor() {
    	lcdEntradas[this.posicionCursor-1].apagarCursor();
		lcdEntradas[this.posicionCursor].encenderCursor();
    }
    
    /**
     * Retrocede el cursor por el display de entrada
     */
    public void retrocederCursor() {
    	lcdEntradas[this.posicionCursor+1].apagarCursor();
		lcdEntradas[this.posicionCursor].encenderCursor();
    }
    
    /**
     * Muestra el número en el display de resultado.
     * @param numero a mostrar.
     */
    public void setResultado(double numero) {
    	this.resetear();
    	NumberFormat formato = new DecimalFormat("##,###,###.###########");
    	int contadorPuntos = 0;
    	String numeroStr = formato.format(numero);
    	if (numeroStr.length()>11) { numeroStr = numeroStr.substring(0,11); }
    	int diferencia = matrizDecimales.length-numeroStr.length(); // Esta diferencia será el offset que se aplicará a la matriz de decimales. De esta forma quedará ajustado a la derecha.

    	for (int i = numeroStr.length()-1; i >= 0; i--) {
    		switch (numeroStr.charAt(i)) {
    		case ',':
    			matrizDecimales[i+diferencia+contadorPuntos] = '.';
    			contadorPuntos++;
    			break;
    		case '.':
    			matrizDecimales[i+diferencia+contadorPuntos] = ','; 	// Es necesario añadir un ajuste al offset. Cada punto o coma que se encuentra  
    																	// moverá el resto de dígitos de su izquiera tantas posiciones como puntos y comas haya a su derecha.
    			matrizDecimales[matrizDecimales.length-1] = ' '; // En caso de que sea un número decimal, hay que modificar la coma por defecto inicializada en la última posición de la matriz.
    			contadorPuntos++;
    			break;
    		default:
    			break;
    		}
    	}
    	numeroStr = numeroStr.replace(".", "");		// Se eliminan puntos y comas para parsear los números.
    	numeroStr = numeroStr.replace(",", "");
    	
    	int tamano = numeroStr.length();
    	diferencia = matrizDigitos.length-tamano;			// Se obtiene la diferencia para mover el número hacia derecha de la matriz
    	for (int i = 0; i < tamano; i++) { matrizDigitos[diferencia+i] = numeroStr.charAt(i); }
   		aplicarCambiosMatrices();
   		bloquearCursor(true);
    }

    /**
     * Muestra un error sintáctico en el display de entrada.
     */
    public void mostrarErrorSintactico() {
    	char[] error = {'S', 'y', 'n', 't', 'a', 'x', ' ', 'E', 'R', 'R', 'O', 'R' };
    	mostrarError(error);
    }
    
    /**
     * Muestra un error matemático en el display de entrada.
     */
    public void mostrarErrorMatematico() {
    	char[] error = {'M', 'a', 't', 'h', ' ', 'E', 'R', 'R', 'O', 'R' };
    	mostrarError(error);
    }
    
    /**
     * función auxiliar para mostrar errores por display de resultados
     * @param error Error a mostrar.
     */
    private void mostrarError(char[] error) {
    	Arrays.fill(matrizDigitos, 'a');
    	Arrays.fill(matrizDecimales, 'a');
    	bloquearCursor(true);
    	for (int i = 0; i < error.length; i++) { lcdEntradas[i].setDigito(error[i]); }
    	this.aplicarCambiosMatrices();
    }
    
    /**
     * Bloquea y desbloquea el cursor del display de entrada
     * @param bloqueado boolean bloquear o desbloquear.
     */
    public void bloquearCursor(boolean bloqueado) {
    	if (bloqueado) {
    		lcdEntradas[this.posicionCursor].apagarCursor();
        	TEMPO.stop();
    	} else {
    		lcdEntradas[this.posicionCursor].encenderCursor();
        	TEMPO.restart();
    	}
    }
    
	private BufferedImage crearPantallaLcd(int ancho, int alto) {
		GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		BufferedImage IMAGE = gfxConf.createCompatibleImage(ancho, alto, Transparency.TRANSLUCENT);
		Graphics2D g2 = (Graphics2D) IMAGE.getGraphics();
		aplicarEfectos(g2);
		
		final Color colorPantalla = new Color(222,230,205);

		g2.setColor(colorPantalla);
		g2.fillRect(21, 21, (ancho-42), 85);
		g2.dispose();

		return IMAGE;
	}
	
	private void aplicarEfectos(Graphics2D g2) {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();
		aplicarEfectos(g2);
		g2.drawImage(this.pantallaLcd, 0, 0, this);
		g2.dispose();
	}
	
	/**
	 * Controla el parpadeo del cursor mediante un temporizador
	 */
    public void actionPerformed(java.awt.event.ActionEvent event) {
        if (event.getSource().equals(TEMPO)) { 
        	if (this.posicionCursor < elementosDisplayEntrada) {
        		lcdEntradas[this.posicionCursor].alternarCursor();
        	} else {
        		lcdEntradas[elementosDisplayEntrada-1].alternarCursor();
        	}
        	repaint();
        }
    }

}
