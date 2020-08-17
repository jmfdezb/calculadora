package calculadora.controladores;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class EntradaTeclado {
	private static final AtajosTeclado[] ATAJOS = AtajosTeclado.getAtajos();
	
	public EntradaTeclado(JPanel fondo, Controlador controlador) {
		String etiqueta;
		int atajo, modificador;
		InputMap mapaEntrada = fondo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap mapaAccion = fondo.getActionMap();
		
		for ( int i = 0; i < ATAJOS.length; i++) {
			etiqueta = ATAJOS[i].getEtiqueta();
			atajo = ATAJOS[i].getAtajo();
			modificador = ATAJOS[i].getModificador();
			
			mapaEntrada.put(KeyStroke.getKeyStroke(atajo,modificador), etiqueta);
			mapaAccion.put(etiqueta,new AccionPulsacion(etiqueta,controlador));
		}
	}
}
