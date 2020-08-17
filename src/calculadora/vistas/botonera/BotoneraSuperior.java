package calculadora.vistas.botonera;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import calculadora.controladores.AccionPulsacion;
import calculadora.controladores.Controlador;

public class BotoneraSuperior extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String[] ETIQUETAS = { "r", "(", "RETROCEDER_CURSOR", "AVANZAR_CURSOR", ")", "^" };
	private BotonTipoDos[] botones = new BotonTipoDos[ETIQUETAS.length];
										 
	
	public BotoneraSuperior (int ancho, int alto, Controlador controlador) {
		this.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    c.weightx = 1.0;
	    c.weighty = 1.0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.fill = GridBagConstraints.BOTH;
	    c.insets = new Insets(5,5,5,5);
//    	this.setBounds(20, 100, ancho, 100);
    	setOpaque(false);
        int x = 0, y = 0, anchoBoton = 40, altoBoton = 25, contador = -1;
        
        for (int i = 0; i < 6; i++) {
       		contador++;
       		c.gridx = i;
        	this.botones[contador] = new BotonTipoDos(x, y, anchoBoton, altoBoton, new AccionPulsacion(ETIQUETAS[contador], controlador));
        	add(botones[contador],c);
        }
	}
	
	public BotonTipoDos[] getBotones() {
		return botones;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
 }
