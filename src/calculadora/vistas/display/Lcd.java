package calculadora.vistas.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

public abstract class Lcd extends JPanel{
	private static final long serialVersionUID = 1L;
	protected static final Color[] COLOR_LED = { new Color(202,209,165), new Color(75,75,75) };
	protected static final Color[] COLOR_MARCO = { new Color(219,224,194, 228), new Color(128, 128, 128, 128) };
    protected int posicionX, posicionY, indiceArrayImagenes;
    private final String simbolos;
    
	public Lcd(String simbolos) {
        setOpaque(false);
        this.simbolos = simbolos;
        indiceArrayImagenes = simbolos.indexOf(' ');
	}
	
	public Lcd(int posicionX, int posicionY, String simbolos) {
        this(simbolos);
        this.posicionX = posicionX;
        this.posicionY = posicionY;
	}
	
	/**
	 * Mueve el puntero a la posición que coincide con la imágen del dígito introducido.
	 * @param digito Dígito a mostrar en el display
	 * @throws IllegalArgumentException
	 */
	protected void setDigito(char digito) throws IllegalArgumentException {
		indiceArrayImagenes = simbolos.indexOf(digito);
		repaint();
	}
	
	/**
	 * Conjunto de efectos a aplicar a las imágenes.
	 * @param g2 Objeto al que se le aplicarán los efectos.
	 */
	protected void aplicarEfectos(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	}
	
	protected void colorearCelda(Graphics2D g2, GeneralPath celda, boolean encendida) {
		int indice = encendida ? 1:0;
		
		g2.setColor(COLOR_LED[indice]);
		g2.fill(celda);
		g2.setColor(COLOR_MARCO[indice]);
		g2.draw(celda);
	}
	
	/**
	 * Crea la imagen del dígito solicitado
	 * @param digito a mostrar en la imagen.
	 */
	protected abstract BufferedImage crearImagen(char digito);

	@Override
	protected void paintComponent(Graphics g) { 
		super.paintComponent(g);
	}

}

