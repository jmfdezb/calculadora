package calculadora.controladores;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class AccionPulsacion extends AbstractAction {
	private static final long serialVersionUID = 1L;
	Controlador controlador;

	public AccionPulsacion(String nombre, Controlador controlador) {
		this.controlador = controlador;
		putValue(Action.NAME, nombre);
		putValue(Action.SHORT_DESCRIPTION, _setDescripcion(nombre));
	}
	
	private String _setDescripcion(String caracter) {
		String str = "";
		switch (caracter) {
		case "r":
			str = "Raiz cuadrada (Tecla 'r')";
			break;
		case "^":
			str = "Elevar a (Tecla 'e')";
			break;
		case "+":
			str = "Sumar (Tecla '+')";
			break;
		case "-":
			str = "Restar (Tecla '-')";
			break;
		case "*":
			str = "Multiplicar (Tecla '*' o 'x')";
			break;
		case "/":
			str = "Dividir (Tecla '/')";
			break;
		case "RETROCEDER_CURSOR":
			str = "Retroceder el cursor una posición  (Tecla 'Flecha izquierda')";
			break;
		case "AVANZAR_CURSOR":
			str = "Avanzar el cursor una posición (Tecla 'Flecha derecha')";
			break;
		case "BORRAR_CARACTER":
			str = "Borrar un caracter (Tecla 'Borrar' o 'Suprimir')";
			break;
		case "BORRAR_TODO":
			str = "Reiniciar la calculadora (Tecla 'c')";
			break;
		case "ANS":
			str = "Recuperar el resultado anterior (Tecla 'a')";
			break;
		case "CALCULAR":
			str = "Calcular el resultado (Tecla 'Intro' o '=')";
			break;
		default: 
			str = "Introducir un '"+caracter+"'";
		}
		return str;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cadena = (String) getValue(Action.NAME);
		if (controlador.isBloqueada()) {
			switch (cadena) {
			case "BORRAR_TODO":
				controlador.reset();
				break;
			default:
				break;
			}
		} else {
			switch (cadena) {
			case "RETROCEDER_CURSOR":
				controlador.retrocederCursor();
				break;
			case "AVANZAR_CURSOR":
				controlador.avanzarCursor();
				break;
			case "CALCULAR":
				controlador.calcularResultado();
				break;
			case "BORRAR_CARACTER":
				controlador.eliminarCaracter();
				break;
			case "BORRAR_TODO":
				controlador.reset();
				break;
			case "ANS":
				controlador.insertarCaracter('A');
				break;
			case "PEGAR":
				controlador.paste();
				break;
			case "COPIAR":
				controlador.copy();
				break;
			default:
				controlador.insertarCaracter(cadena.charAt(0));
				break;
			}
		}
	}
}
