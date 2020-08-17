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

public class BotonTipoUno extends JButton {
	private static final long serialVersionUID = 1L;
	private final Color [] colorOperacion = { new Color(242,164,70), new Color(240,154,49) };
	private final Color [] colorDigito = { new Color(135,159,178), new Color(116,147,166) };
	private final Color [] colorResultado = { new Color(72,189,240), new Color(49,180,241) };
	private final Color [] colorBorrado = { new Color(242,69,69), new Color(240,49,49) };
	private final Color colorBorde = Color.DARK_GRAY;
	
	private final BasicStroke PERFIL_MARCO = new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    private BufferedImage imagenBoton;
    private final int posicionX, posicionY;
	
	public BotonTipoUno(int posicionX, int posicionY, int ancho, int alto, Action accion) {
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
        reflejos.lineTo(26, 2);
        reflejos.lineTo(2, 25);
        reflejos.lineTo(2, 18);
        reflejos.closePath();
        
        if (etiqueta.equalsIgnoreCase("CALCULAR")) {
	        reflejos.moveTo(46, 2);
	        reflejos.lineTo(46, 20);
	        reflejos.lineTo(4, 60);
	        reflejos.lineTo(4, 42);
	        reflejos.closePath();
	        
	        reflejos.moveTo(46, 25);
	        reflejos.lineTo(46, 33);
	        reflejos.lineTo(4, 76);
	        reflejos.lineTo(4, 68);
	        reflejos.closePath();
	        
	        reflejos.moveTo(46, 50);
	        reflejos.lineTo(46, 70);
	        reflejos.lineTo(20, 96);
	        reflejos.lineTo(4, 96);
	        reflejos.closePath();
	        
	        reflejos.moveTo(46, 80);
	        reflejos.lineTo(46, 96);
	        reflejos.lineTo(30, 96);
	        reflejos.closePath();
        } else {
	        reflejos.moveTo(48, 2);
	        reflejos.lineTo(48, 20);
	        reflejos.lineTo(20, 42);
	        reflejos.lineTo(2, 42);
	        reflejos.closePath();
	        
	        reflejos.moveTo(48, 25);
	        reflejos.lineTo(48, 33);
	        reflejos.lineTo(36, 42);
	        reflejos.lineTo(26, 42);
	        reflejos.closePath();
	        
	        reflejos.moveTo(48, 36);
	        reflejos.lineTo(48, 42);
	        reflejos.lineTo(40, 42);
	        reflejos.closePath();
        }
	        
        switch (etiqueta) {
        case "+":
        case "-":
        case "*":
        case "/":
        	colorear(g2, boton, colorOperacion[0], colorBorde);
            colorear(g2, reflejos, colorOperacion[1], colorOperacion[0]);
        	break;
        case "BORRAR_CARACTER":
        case "BORRAR_TODO":
        	colorear(g2, boton, colorBorrado[0], colorBorde);
            colorear(g2, reflejos, colorBorrado[1], colorBorrado[0]);
        	break;
        case "CALCULAR":
        	colorear(g2, boton, colorResultado[0], colorBorde);
            colorear(g2, reflejos, colorResultado[1], colorResultado[0]);
            break;
        default:
        	colorear(g2, boton, colorDigito[0], colorBorde);
            colorear(g2, reflejos, colorDigito[1], colorDigito[0]);
        	break;
        }
        
        g2.setColor(Color.black);
        g2.drawRect(2, 2, ancho-4, alto-4);
        
        g2.setColor(Color.white);
        g2.setFont(new java.awt.Font("Aharoni", 1, 18));
        ancho = (ancho / 2)-5;
        alto = (alto / 2)+7;
        
        switch (etiqueta) {
        case "CALCULAR":
        	g2.drawString("=", ancho-2, alto);
        	break;
        case "BORRAR_TODO":
        	g2.drawString("AC", ancho-7, alto);
        	break;
        case "BORRAR_CARACTER":
        	g2.drawString("DEL", ancho-15, alto);
        	break;
        case "ANS":
        	g2.drawString(etiqueta, ancho-15, alto);
        	break;
        case ".":
        	g2.drawString(etiqueta, ancho+2, alto-4);
        	break;
        case "+":
        	g2.drawString(etiqueta, ancho-3, alto);
        	break;
        case "*":
        	g2.drawString(etiqueta, ancho, alto+2);
        	break;
        default:
        	g2.drawString(etiqueta, ancho, alto);
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
