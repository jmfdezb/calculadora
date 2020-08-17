package calculadora.vistas.display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.geom.GeneralPath;

public class LcdSieteSegmentos extends Lcd{
	private static final long serialVersionUID = 1L;
	private static final String SIMBOLOS_REGISTRADOS = "0123456789- ";
	private static final int NUMERO_SIMBOLOS_REGISTRADOS = SIMBOLOS_REGISTRADOS.length();
	private static final BufferedImage[] ARRAY_IMAGENES = new BufferedImage[NUMERO_SIMBOLOS_REGISTRADOS];
	private static final boolean SIMBOLOS[][] = {	{ true,true,true,true,true,true,false },		//0
													{ false,true,true,false,false,false,false },	//1
													{ true,true,false,true,true,false,true },		//2
													{ true,true,true,true,false,false,true },		//3
													{ false,true,true,false,false,true,true },		//4
													{ true,false,true,true,false,true,true },		//5
													{ true,false,true,true,true,true,true },		//6
													{ true,true,true,false,false,false,false },		//7
													{ true,true,true,true,true,true,true },			//8
													{ true,true,true,true,false,true,true },		//9
													{ false,false,false,false,false,false,true },	//-
													{ false,false,false,false,false,false,false }};	//' ' 

	
	public LcdSieteSegmentos() {
		super(SIMBOLOS_REGISTRADOS);
        if ( ARRAY_IMAGENES[0] == null ) {  inicializarImagenes(); } 
	}
	
	public LcdSieteSegmentos(int posicionX, int posicionY) {
        this();
        this.posicionX = posicionX;
        this.posicionY = posicionY;
	}
	
	private void inicializarImagenes() {
		for (int i = 0; i < NUMERO_SIMBOLOS_REGISTRADOS; i++) {
			ARRAY_IMAGENES[i] = crearImagen(SIMBOLOS_REGISTRADOS.charAt(i));
		}
	}
	
	/**
	 * Crea la imagen del dígito solicitado
	 * @param digito a mostrar en la imagen.
	 */
	protected BufferedImage crearImagen(char digito) {
        GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage imagen = gfxConf.createCompatibleImage(46, 65, Transparency.TRANSLUCENT);
        Graphics2D g2 = (Graphics2D) imagen.getGraphics();
        aplicarEfectos(g2);
        
        String nombresSegmentos = "ABCDEFG";
        GeneralPath[] segmentos = new GeneralPath[7];
        for (int i = 0; i < segmentos.length; i++) {
        	segmentos[i] = crearSegmento(nombresSegmentos.charAt(i));
        }
        
        for (int i = 0; i < segmentos.length; i++) {
        	colorearCelda(g2,segmentos[i], SIMBOLOS[SIMBOLOS_REGISTRADOS.indexOf(digito)][i]);
        }
        
        g2.dispose();
        return imagen;
	}
	
	/**
	 * Crea los polígonos que formarán cada uno de los segmentos
	 * @param letraSegmento Letra del segmento a dibujar
	 * @return El segmento generado.
	 */
	private GeneralPath crearSegmento(char letraSegmento) {
		GeneralPath segmento = new GeneralPath();
		switch (letraSegmento) {
			case 'A':
				segmento.moveTo(17, 0);
			    segmento.lineTo(28, 0);
			    segmento.lineTo(27, 4);
			    segmento.lineTo(16, 4);
				break;
			case 'B':
				segmento.moveTo(28, 0);
			    segmento.lineTo(31, 0);
			    segmento.quadTo(32, 0, 32, 5);
			    segmento.lineTo(29, 16);
			    segmento.lineTo(27, 16);
			    segmento.lineTo(26, 12);
				break;
			case 'C':
				segmento.moveTo(25, 18);
			    segmento.lineTo(29, 17);
			    segmento.lineTo(27, 27);
			    segmento.quadTo(27, 33, 22, 33);
				break;
			case 'D':
				segmento.moveTo(12, 29);
			    segmento.lineTo(23, 29);
			    segmento.lineTo(22, 33);
			    segmento.lineTo(11, 33);
				break;
			case 'E':
				segmento.moveTo(9, 17);
			    segmento.lineTo(11, 17);
			    segmento.lineTo(13, 19);
			    segmento.lineTo(10, 33);
			    segmento.lineTo(8, 33);
			    segmento.quadTo(5, 33, 7, 28);
				break;
			case 'F':
				segmento.moveTo(11, 5);
			    segmento.quadTo(11, 0, 16, 0);
			    segmento.lineTo(14, 12);
			    segmento.lineTo(11, 16);
			    segmento.lineTo(9, 16);
				break;
			case 'G':
				segmento.moveTo(16, 14);
			    segmento.lineTo(25, 14);
			    segmento.lineTo(27, 16);
			    segmento.lineTo(22, 18);
			    segmento.lineTo(15, 18);
			    segmento.lineTo(11, 16);
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
