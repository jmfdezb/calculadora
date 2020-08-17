package calculadora.vistas.botonera;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.KeyStroke;

public class BotonTipoDos extends JButton {
	private static final long serialVersionUID = 1L;
	private final Color [] colorBoton = { new Color(121,237,168), new Color(84,233,144) };
	private final Color colorBorde = Color.DARK_GRAY;
	
	private final BasicStroke PERFIL_MARCO = new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    private BufferedImage imagenBoton;
    private final int posicionX, posicionY;
	
	public BotonTipoDos(int posicionX, int posicionY, int ancho, int alto, Action accion) {
		super(accion);
		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
		this.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none");
		
		String etiqueta = (String) accion.getValue(Action.NAME);
		setHideActionText(true);
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.imagenBoton = crearImagen(ancho,alto, etiqueta);
	}
	
	 @Override
	public Dimension getPreferredSize() {
		 Dimension size = super.getPreferredSize();
	     size.width += size.height;
	     return size;
	}
	
	private void aplicarEfectos(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	}
	
	private BufferedImage crearImagen(int ancho, int alto, String etiqueta) {
        GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage imagen = gfxConf.createCompatibleImage(ancho, alto, Transparency.TRANSLUCENT);
        Graphics2D g2 = (Graphics2D) imagen.getGraphics();
        aplicarEfectos(g2);
        
        GeneralPath boton = new GeneralPath();
        boton.moveTo(0, 0);
        boton.lineTo(ancho, 0);
        boton.lineTo(ancho, alto);
        boton.lineTo(0, alto);
        boton.closePath();
        
        GeneralPath reflejos = new GeneralPath();
        reflejos.moveTo(2,2);
        reflejos.lineTo(14, 2);
        reflejos.lineTo(2, 14);
        reflejos.closePath();
        
        reflejos.moveTo(18, 2);
        reflejos.lineTo(24, 2);
        reflejos.lineTo(2, 24);
        reflejos.lineTo(2, 18);
        reflejos.closePath();
        
        reflejos.moveTo(38, 2);
        reflejos.lineTo(38, 10);
	    reflejos.lineTo(25, 22);
	    reflejos.lineTo(10, 22);
	    reflejos.lineTo(30, 2);
	    reflejos.closePath();
	        
       	colorear(g2, boton, colorBoton[0], colorBorde);
        colorear(g2, reflejos, colorBoton[1], colorBoton[0]);
        
        g2.setColor(Color.black);
        g2.drawRect(2, 2, ancho-4, alto-4);
        
        g2.setColor(Color.white);
        g2.setFont(new java.awt.Font("Aharoni", 1, 14));
        ancho = (ancho / 2)-5;
        alto = (alto / 2)+7;
        switch (etiqueta) {
        case "RETROCEDER_CURSOR":
            GeneralPath flechaIzquierda = new GeneralPath();
            flechaIzquierda.moveTo(15, 12);
            flechaIzquierda.lineTo(20, 17);
            flechaIzquierda.lineTo(20, 13);
            flechaIzquierda.lineTo(25, 14);
            flechaIzquierda.lineTo(25, 10);
            flechaIzquierda.lineTo(20, 11);
            flechaIzquierda.lineTo(20, 8);
            flechaIzquierda.closePath();
            colorear(g2, flechaIzquierda,Color.white,Color.white);
        	break;
        case "AVANZAR_CURSOR":
        	GeneralPath flechaDerecha = new GeneralPath();
        	flechaDerecha.moveTo(25, 12);
        	flechaDerecha.lineTo(20, 17);
        	flechaDerecha.lineTo(20, 13);
        	flechaDerecha.lineTo(15, 14);
        	flechaDerecha.lineTo(15, 10);
        	flechaDerecha.lineTo(20, 11);
        	flechaDerecha.lineTo(20, 8);
        	flechaDerecha.closePath();
            colorear(g2, flechaDerecha,Color.white,Color.white);
        	break;
        case "r":
        	GeneralPath raiz = new GeneralPath();
        	raiz.moveTo(12, 13);
        	raiz.lineTo(15, 18);
        	raiz.lineTo(17, 18);
        	raiz.lineTo(20, 8);
        	raiz.lineTo(30, 8);
        	raiz.lineTo(30, 7);
        	raiz.lineTo(21, 7);
        	raiz.lineTo(16, 17);
        	raiz.lineTo(14, 13);
        	raiz.closePath();
            colorear(g2, raiz,Color.white,Color.white);
        	break;
        case "^":
        	g2.drawString(etiqueta, ancho, alto);
        	break;
        default:
        	g2.drawString(etiqueta, ancho+2, alto-1);
        	break;
        }
        g2.setStroke(this.PERFIL_MARCO);
        g2.dispose();
        return imagen;
	}
	
	private void colorear(Graphics2D g2, GeneralPath boton, Color principal, Color borde) {
			g2.setColor(principal);
			g2.fill(boton);
			g2.setColor(borde);
			g2.draw(boton);
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		
        Graphics2D g2 = (Graphics2D) g.create();
		aplicarEfectos(g2);
        g2.drawImage(imagenBoton, posicionX, posicionY, this);
    }

}
