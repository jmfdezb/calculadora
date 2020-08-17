package calculadora.vistas;


import javax.swing.*;

import calculadora.controladores.Controlador;
import calculadora.controladores.EntradaTeclado;
import calculadora.vistas.botonera.Botonera;
import calculadora.vistas.display.Display;
import calculadora.vistas.display.Fondo;

public class MarcoCalculadora extends JFrame {
	private static final long serialVersionUID = 1L;
	private Display display;
	private Fondo fondo;
	private Botonera botonera;
	
	public MarcoCalculadora(int ancho, int alto, int elementosDisplayEntrada, Controlador controlador) {
		this.setSize(ancho,alto);
		this.fondo = new Fondo(ancho, alto);
		this.display = new Display(340, 110, elementosDisplayEntrada);
		this.botonera = new Botonera(21, 135, 300, 258, controlador);
		
		this.add(display);
		this.add(botonera);
		this.add(fondo);
		
		new EntradaTeclado(fondo,controlador);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Calculadora - IFCT0609");
		this.setResizable(false);
		controlador.setDisplay(display);
		
		this.pack();
		this.setVisible(true);
	}
	
	public Display getDisplay() {
		return display;
	}
}
