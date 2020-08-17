package calculadora.vistas.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

public class Fondo extends JPanel {
	private static final long serialVersionUID = 1L;
	private static BufferedImage fondo;
	private static final Color[] COLORES = { new Color(45,45,45), new Color(60,60,60) };
	
    public Fondo(int ancho, int alto) {
        setPreferredSize(new Dimension(ancho,alto));
        fondo = crearFondo(ancho, alto);
    }
    
    private void aplicarEfectos(Graphics2D g2) {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }
    
	private BufferedImage crearFondo(int ancho, int alto) {
		final Point2D inicio = new Point2D.Float(0, 0);
		final Point2D fin = new Point2D.Float(0, alto);
		final float[] gradiente = { 0.0f, 1.0f };
		
		
		GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		BufferedImage imagen = gfxConf.createCompatibleImage(ancho, alto, Transparency.TRANSLUCENT);
		imagen = gfxConf.createCompatibleImage(ancho, alto, Transparency.TRANSLUCENT);
		Graphics2D g2 = (Graphics2D) imagen.getGraphics();
		aplicarEfectos(g2);
		
		g2.setPaint(new LinearGradientPaint(inicio, fin, gradiente, COLORES));
		g2.fillRect(0, 0, ancho, alto);
				
		g2.setColor(Color.white);
		g2.setFont(new java.awt.Font("EurostileExtendedBlack", 1, 18));
		g2.drawString("CASIO", 22, 40);
		
		g2.setColor(new Color(84, 233, 144));
        g2.setFont(new java.awt.Font("Dyuthi", 1, 18));
        g2.drawString("COVID-19", 245, 40);
        return imagen;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();
		aplicarEfectos(g2);

		g2.drawImage(fondo, 0, 0, this);
		g2.dispose();

	}

}
