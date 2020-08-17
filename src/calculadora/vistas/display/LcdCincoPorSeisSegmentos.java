package calculadora.vistas.display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.geom.GeneralPath;


public class LcdCincoPorSeisSegmentos extends Lcd {
	private static final long serialVersionUID = 1L;
	private static final boolean[][][] MATRIZ_SIMBOLOS = { 
	    	{	{ false, true, true, true, false}, // '0'
	    		{ true, false, false, true, true},
	    		{ true,false, true, false, true},
	    		{ true, true, false, false, true},
	    		{ true, false, false, false, true},
	    		{ false, true, true, true, false}	},
	    	
	    	{	{ false, false, true, false, false}, // '1'
	    		{ false, true, true, false, false},
	    		{ false,false, true, false, false},
	       		{ false, false, true, false, false},
	       		{ false, false, true, false, false},
	       		{ false, true, true, true, false}	},
	    	
	    	{	{ false, true, true, true, false}, // '2'
	       		{ true, false, false, false, true},
	      		{ false, false, false, true, false},
	      		{ false, false, true, false, false},
	      		{ false, true, false, false, false},
	      		{ true, true, true, true, true}	},
	    	
	    	{	{ true, true, true, true, true}, // '3'
	      		{ false, false, false, false, true},
	      		{ false, false, true, true, false},
	      		{ false, false, false, false, true},
	      		{ true, false, false, false, true},
	      		{ false, true, true, true, false}	},
	    	
	    	{	{ false, false, false, true, false}, // '4'
	      		{ false, false, true, true, false},
	      		{ false, true, false, true, false},
	      		{ true, false, false, true, false},
	      		{ true, true, true, true, true},
	      		{ false, false, false, true, false}	},

	    	{	{ true, true, true, true, true}, // '5'
	          	{ true, false, false, false, false},
	          	{ true, true, true, true, false},
	          	{ false, false, false, false, true},
	          	{ true, false, false, false, true},
	          	{ false, true, true, true, false}	},
	       		
	    	{	{ false, true, true, true, false}, // '6'
	          	{ true, false, false, false, false},
	      		{ true, true, true, true, false},
	      		{ true, false, false, false, true},
	      		{ true, false, false, false, true},
	      		{ false, true, true, true, false}	},
	       		
	    	{	{ true, true, true, true, true}, // '7'
	      		{ false, false, false, false, true},
	      		{ false, false, false, true, false},
	      		{ false, false, true, false, false},
	      		{ false, false, true, false, false},
	      		{ false, false, true, false, false}	},
	    	
	    	{	{ false, true, true, true, false}, // '8'
	      		{ true, false, false, false, true},
	      		{ false, true, true, true, false},
	      		{ true, false, false, false, true},
	      		{ true, false, false, false, true},
	      		{ false, true, true, true, false}	},
	    	
	    	{	{ false, true, true, true, false}, // '9'
	      		{ true, false, false, false, true},
	      		{ true, false, false, false, true},
	      		{ false, true, true, true, true},
	      		{ false, false, false, false, true},
	      		{ false, true, true, true, false}	},
	    	
	       	{	{ false, false, false, false, false}, // '_'
	      		{ false, false, false, false, false},
	      		{ false, false, false, false, false},
	      		{ false, false, false, false, false},
	      		{ false, false, false, false, false},
	      		{ true, true, true, true, true}	},
	       	
	       	{	{ false, false, false, false, false}, // ' '
	      		{ false, false, false, false, false},
	      		{ false, false, false, false, false},
	      		{ false, false, false, false, false},
	      		{ false, false, false, false, false},
	      		{ false, false, false, false, false}	},
	       	
	       	{	{ false, false, false, false, false}, // '+'
	      		{ false, false, true, false, false},
	      		{ false, false, true, false, false},
	      		{ true, true, true, true, true},
	      		{ false, false, true, false, false},
	      		{ false, false, true, false, false}	},
	       	
	       	{	{ false, false, false, false, false}, // '-'
	      		{ false, false, false, false, false},
	      		{ false, false, false, false, false},
	      		{ true, true, true, true, true},
	      		{ false, false, false, false, false},
	      		{ false, false, false, false, false} },
	       	
	       	{	{ false, false, false, false, false}, // '*'
	      		{ true, false, false, false, true},
	      		{ false, true, false, true, false},
	      		{ false, false, true, false, false},
	      		{ false, true, false, true, false},
	      		{ true, false, false, false, true}	},
	       	
	       	{	{ false, false, false, false, false}, // '/'
	      		{ false, false, true, false, false},
	      		{ false, false, false, false, false},
	      		{ true, true, true, true, true},
	      		{ false, false, false, false, false},
	      		{ false, false, true, false, false}	},
	      		
	    	{	{ false, false, false, false, false}, // '.'
	      		{ false, false, false, false, false},
		      	{ false, false, false, false, false},
		      	{ false, false, false, false, false},
		      	{ false, true, true, false, false},
		      	{ false, true, true, false, false} },
	      		
	      	{	{ false, false, true, true, true}, // 'sqrt'
		      	{ false, false, true, false, false},
			   	{ false, false, true, false, false},
			   	{ true, false, true, false, false},
			    { false, true, true, false, false},
			    { false, false, true, false, false} },
	      	
		    {	{ false, false, false, false, true}, // '('
		    	{ false, false, false, true, false},
			    { false, false, false, true, false},
			    { false, false, false, true, false},
			    { false, false, false, true, false},
			    { false, false, false, false, true} },

		    {	{ true, false, false, false, false}, // ')'
			    { false, true, false, false, false},
				{ false, true, false, false, false},
				{ false, true, false, false, false},
				{ false, true, false, false, false},
				{ true, false, false, false, false} },

		    {	{ false, false, true, false, false}, // '^'
			    { false, true, false, true, false},
				{ true, false, false, false, true},
				{ false, false, false, false, false},
				{ false, false, false, false, false},
				{ false, false, false, false, false} },
		    
		    {	{ false, true, true, true, true}, // 'S'
			    { true, false, false, false, false},
				{ false, true, true, true, false},
				{ false, false, false, false, true},
				{ true, false, false, false, true},
				{ false, true, true, true, false} },
		    
		    {	{ false, false, false, false, false}, // 'y'
			    { true, false, false, false, true},
				{ false, true, false, false, true},
				{ false, false, true, true, false},
				{ false, false, true, false, false},
				{ true, true, false, false, false} },
		    
		    {	{ false, false, false, false, false}, // 'n'
				{ true, false, true, true, false}, 
			    { true, true, false, false, true},
				{ true, false, false, false, true},
				{ true, false, false, false, true},
				{ true, false, false, false, true} },
				
		    
		    {	{ false, false, true, false, false}, // 't'
			    { false, true, true, true, false},
			    { false, false, true, false, false},
				{ false, false, true, false, false},
				{ false, false, true, false, true},
				{ false, false, false, true, false} },
		    
		    {	{ false, false, false, false, false}, // 'a'
			    { false, true, true, true, false},
			    { false, false, false, false, true},
				{ false, true, true, true, true},
				{ true, false, false, false, true},
				{ false, true, true, true, true} },
		    
		    {	{ false, false, false, false, false}, // 'x'
			    { true, true, false, false, true},
				{ false, false, true, true, false},
				{ false, false, true, false, false},
				{ false, true, true, false, false},
				{ true, false, false, true, true} },
		    
		    {	{ true, true, true, true, true}, // 'E'
			    { true, false, false, false, false},
				{ true, true, true, true, false},
				{ true, false, false, false, false},
				{ true, false, false, false, false},
				{ true, true, true, true, true} },
		    
		    {	{ false, true, true, true, false}, // 'O'
				{ true, false, false, false, true},
				{ true, false, false, false, true},
				{ true, false, false, false, true},
				{ true, false, false, false, true},
				{ false, true, true, true, false} },
		    
		    {	{ true, true, true, true, false}, // 'R'
				{ true, false, false, false, true},
				{ true, false, false, false, true},
				{ true, true, true, true, false},
				{ true, false, false, true, false},
				{ true, false, false, false, true} },
		    
		    {	{ true, false, false, false, false}, // 'h
				{ true, false, false, false, false},
				{ true, false, true, true, false},
				{ true, true, false, false, true},
				{ true, false, false, false, true},
				{ true, false, false, false, true} },
		    
		    {	{ true, false, false, false, true}, // 'M'
				{ true, true, false, true, true},
				{ true, false, true, false, true},
				{ true, false, true, false, true},
				{ true, false, false, false, true},
				{ true, false, false, false, true} },	
		    
		    {	{ false, true, true, true, false}, // 'A'
				{ true, false, false, false, true},
				{ true, false, false, false, true},
				{ true, true, true, true, true},
				{ true, false, false, false, true},
				{ true, false, false, false, true} },
		    
		    {	{ false, false, false, false, false}, // 's'
				{ false, true, true, true, true}, 
				{ true, false, false, false, false},
				{ false, true, true, true, false},
				{ false, false, false, false, true},
				{ true, true, true, true, false} }
	    	};
	private static final String SIMBOLOS_REGISTRADOS = "0123456789_ +-*/.r()^SyntaxEORhMAs";
	private static final int NUMERO_SIMBOLOS_REGISTRADOS = SIMBOLOS_REGISTRADOS.length();
	private static final BufferedImage[] ARRAY_IMAGENES = new BufferedImage[NUMERO_SIMBOLOS_REGISTRADOS];
	private boolean cursorOn = false;
	
	
	public LcdCincoPorSeisSegmentos() {
        super(SIMBOLOS_REGISTRADOS);
     // Solo se lanza al crear el primer LCD.
        if ( ARRAY_IMAGENES[0] == null ) {  inicializarImagenes(); } 
	}
	
	public LcdCincoPorSeisSegmentos(int posicionX, int posicionY) {
        this();
        this.posicionX = posicionX;
        this.posicionY = posicionY;
	}

	public void alternarCursor() {
		cursorOn = !cursorOn;
		repaint();
	}
	
	public void apagarCursor() {
		cursorOn = false;
	}
	
	public void encenderCursor() {
		cursorOn = true;
	}
	
	/**
	 * Crea las imágenes de los lcds a partir de los símbolos registrados.
	 */
	private void inicializarImagenes() {
		for (int i = 0; i < NUMERO_SIMBOLOS_REGISTRADOS; i++) {
			ARRAY_IMAGENES[i] = crearImagen(SIMBOLOS_REGISTRADOS.charAt(i));
		}
	}
	
	/**
	 * Crea las imágenes de la matriz de puntos.
	 * @param digito Dígito que mostrará la matriz
	 * @return imagen creada.
	 */
	protected BufferedImage crearImagen(char digito) {
        GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage imagen = gfxConf.createCompatibleImage(46, 65, Transparency.TRANSLUCENT);
        Graphics2D g2 = (Graphics2D) imagen.getGraphics();
        aplicarEfectos(g2);
        
        GeneralPath[][] segmentos = generarMatriz();
        for (int i = 0; i < 6; i++) {
           	for (int j = 0; j < 5; j++) {
           		colorearCelda(g2,segmentos[i][j], MATRIZ_SIMBOLOS[SIMBOLOS_REGISTRADOS.indexOf(digito)][i][j]);
           	}
        }
        g2.dispose();
        return imagen;
	}
	
	/**
	 * Genera la matriz de puntos.
	 * @return Matriz generada.
	 */
	private GeneralPath[][] generarMatriz() {
		GeneralPath[][] segmentos = new GeneralPath[6][5];
		float posX = 2, posY = 0;
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 5; j++) {
				segmentos[i][j] = new GeneralPath();
				segmentos[i][j].moveTo(posX, posY);
				segmentos[i][j].lineTo(posX+3, posY);
				segmentos[i][j].lineTo(posX+3, posY+3);
				segmentos[i][j].lineTo(posX, posY+3);
				segmentos[i][j].closePath();
				posX+=4;
			}
			posX = 2;
			posY += 4;
		}
		return segmentos;
	}
		
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();
		aplicarEfectos(g2);
		if (cursorOn) {
			g2.drawImage(ARRAY_IMAGENES[10], this.posicionX, this.posicionY, this); // Si el cursor está encendido, muestra el cursor
		} else {
			try {
				g2.drawImage(ARRAY_IMAGENES[indiceArrayImagenes], this.posicionX, this.posicionY, this);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Caracter no reconocido.");
			}
		}
		g2.dispose();
	}
}
