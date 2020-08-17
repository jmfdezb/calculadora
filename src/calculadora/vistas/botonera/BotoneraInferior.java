package calculadora.vistas.botonera;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

import calculadora.controladores.AccionPulsacion;
import calculadora.controladores.Controlador;

public class BotoneraInferior extends JPanel {
	private static final long serialVersionUID = 1L;
//	private static final String[] NOMBRES_BOTONES = { " 7", " 8", " 9", "DEL", "AC",
//										" 4", " 5", " 6", " *", " /",
//										" 1", " 2", " 3", " +", " =",
//										" 0", " .", "ANS", " -" 			};
//	
	private static final String[] ETIQUETAS = { "7", "8", "9", "BORRAR_CARACTER", "BORRAR_TODO", 
												"4", "5", "6", "*", "/",  
												"1", "2", "3", "+", "CALCULAR", 
												"0", ".", "ANS", "-" };
	private BotonTipoUno[] botones = new BotonTipoUno[ETIQUETAS.length];
										 
	
	public BotoneraInferior (int ancho, int alto, Controlador controlador) {
		this.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    c.gridwidth = 1;
	    c.weightx = 0.1;
	    c.weighty = 0.1;
	    c.fill = GridBagConstraints.BOTH;
	    c.insets = new Insets(5,5,5,5);
    	setOpaque(false);
        int x = 0, y = 0, anchoBoton = 50, altoBoton = 45, contador = -1;
        
        for (int i = 0; i < 4; i++) {
        	for (int j = 0; j < 5; j++) {
        		if ( i == 3 && j == 4 ) { break; }
        		if (i == 2 && j == 4) { c.gridheight = 2; altoBoton = 100; } else { c.gridheight = 1; altoBoton = 45; } 
        		contador++;
        		c.gridx = j;
        	    c.gridy = i;
        		this.botones[contador] = new BotonTipoUno(x, y, anchoBoton, altoBoton, new AccionPulsacion(ETIQUETAS[contador], controlador));
        		if (ETIQUETAS[contador] == "CALCULAR") { this.botones[contador].requestFocusInWindow(); }
        		add(botones[contador],c);
        	}
        }
	}
	
	public BotonTipoUno[] getBotones() {
		return botones;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
 }
