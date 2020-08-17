package calculadora.vistas.botonera;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import calculadora.controladores.Controlador;

public class Botonera extends JPanel {
	private static final long serialVersionUID = 1L;
	private final BotoneraInferior botoneraInferior;
	private final BotoneraSuperior botoneraSuperior;
	
	public Botonera (int x, int y, int ancho, int alto, Controlador controlador) {
		this.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    c.weightx = 1.0;
	    c.weighty = 0.1;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.fill = GridBagConstraints.BOTH;
	    c.anchor = GridBagConstraints.NORTHWEST;
    	this.setBounds(x, y, ancho, alto);
    	setOpaque(false);
    	botoneraInferior = new BotoneraInferior(ancho, alto, controlador);
    	botoneraSuperior = new BotoneraSuperior(ancho, alto, controlador);
    	
    	c.gridy = 0;
    	this.add(botoneraSuperior,c);
    	c.weighty = 0;
    	c.weighty = 0.9;
    	c.gridy = 1;
    	this.add(botoneraInferior,c);
    	
    	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
 }

