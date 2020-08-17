package calculadora.vistas.display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.geom.GeneralPath;


public class LcdDosSegmentos extends Lcd{
	private static final long serialVersionUID = 1L;
	private static final boolean SIMBOLOS[][] = {	{ true,false },		//'.'
													{ true,true },	//','
													{ false,false } };	//' '
	private static final String SIMBOLOS_REGISTRADOS = "., ";
	private static final int NUMERO_SIMBOLOS_REGISTRADOS = SIMBOLOS_REGISTRADOS.length();
	private static final BufferedImage[] ARRAY_IMAGENES = new BufferedImage[NUMERO_SIMBOLOS_REGISTRADOS];
    
	public LcdDosSegmentos() {
		super(SIMBOLOS_REGISTRADOS);
	}
	
	public LcdDosSegmentos(int posicionX, int posicionY) {
        this();
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        if ( ARRAY_IMAGENES[0] == null ) {  inicializarImagenes(); } 
	}
	
	private void inicializarImagenes() {
		for (int i = 0; i < NUMERO_SIMBOLOS_REGISTRADOS; i++) {
			ARRAY_IMAGENES[i] = crearImagen(SIMBOLOS_REGISTRADOS.charAt(i));
		}
	}
	
	/**
	 * Crea las imágenes de los distintos dígitos
	 * @param digito Dígito que mostrará la imagen
	 */
	protected BufferedImage crearImagen(char digito) {
        GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage imagen = gfxConf.createCompatibleImage(46, 65, Transparency.TRANSLUCENT);
        Graphics2D g2 = (Graphics2D) imagen.getGraphics();
        aplicarEfectos(g2);
        
        GeneralPath[] segmentos = { crearSegmento('.'), crearSegmento(',') };
        
        for (int i = 0; i < segmentos.length; i++) {
        	colorearCelda(g2,segmentos[i], SIMBOLOS[SIMBOLOS_REGISTRADOS.indexOf(digito)][i]);
        }
        
        g2.dispose();
        return imagen;
	}
	
	/**
	 * Crea los polígonos que formarán la imagen.
	 * @param letraSegmento 
	 * @return los segmentos creados.
	 */
	private GeneralPath crearSegmento(char letraSegmento) {
		GeneralPath segmento = new GeneralPath();
		switch (letraSegmento) {
			case '.':
				segmento.moveTo(1, 0);
				segmento.lineTo(5, 0);
			    segmento.lineTo(4, 3);
			    segmento.lineTo(0, 3);
				break;
			case ',':
		        segmento.moveTo(4, 4);
		        segmento.lineTo(0, 7);
		        segmento.lineTo(0, 4);
				break;
		}
	    segmento.closePath();
	    return segmento;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();
		aplicarEfectos(g2);
		g2.drawImage(ARRAY_IMAGENES[indiceArrayImagenes], this.posicionX, this.posicionY, this);
		g2.dispose();
	}

}
