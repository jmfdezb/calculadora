package calculadora.controladores;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public enum AtajosTeclado {
	
	CERO("0",KeyEvent.VK_0, 0),
	NUMPADCERO("0",KeyEvent.VK_NUMPAD0, 0),
	UNO("1",KeyEvent.VK_1, 0),
	NUMPADUNO("1",KeyEvent.VK_NUMPAD1, 0),
	DOS("2",KeyEvent.VK_2, 0),
	NUMPADDOS("2",KeyEvent.VK_NUMPAD2, 0),
	TRES("3",KeyEvent.VK_3, 0),
	NUMPADTRES("3",KeyEvent.VK_NUMPAD3, 0),
	CUATRO("4",KeyEvent.VK_4, 0),
	NUMPADCUATRO("4",KeyEvent.VK_NUMPAD4, 0),
	CINCO("5",KeyEvent.VK_5, 0),
	NUMPADCINCO("5",KeyEvent.VK_NUMPAD5, 0),
	SEIS("6",KeyEvent.VK_6, 0),
	NUMPADSEIS("6",KeyEvent.VK_NUMPAD6, 0),
	SIETE("7",KeyEvent.VK_7, 0),
	NUMPADSIETE("7",KeyEvent.VK_NUMPAD7, 0),
	OCHO("8",KeyEvent.VK_8, 0),
	NUMPADOCHO("8",KeyEvent.VK_NUMPAD8, 0),
	NUEVE("9",KeyEvent.VK_9, 0),
	NUMPADNUEVE("9",KeyEvent.VK_NUMPAD9, 0),
	SUMA("+", KeyEvent.VK_ADD, 0),
	RESTA("-", KeyEvent.VK_SUBTRACT, 0),
	RESTA2("-", KeyEvent.VK_MINUS, 0),
	DIVISION("/", KeyEvent.VK_7, InputEvent.SHIFT_DOWN_MASK),
	DIVISION2("/", KeyEvent.VK_DIVIDE, 0),
	MULTIPLICACION("*", KeyEvent.VK_MULTIPLY, 0),
	MULTIPLICACION2("*", KeyEvent.VK_X, 0),
	ABRIRPARENTESIS("(", KeyEvent.VK_8, InputEvent.SHIFT_DOWN_MASK),
	CERRARPARENTESIS(")", KeyEvent.VK_9, InputEvent.SHIFT_DOWN_MASK),
	PUNTO(".", KeyEvent.VK_PERIOD, 0),
	RAIZ("r", KeyEvent.VK_R, 0),
	ELEVAR("^", KeyEvent.VK_E, 0),
	BORRAR_TODO("BORRAR_TODO", KeyEvent.VK_C, 0),
	BORRAR_CARACTER("BORRAR_CARACTER", KeyEvent.VK_DELETE, 0),
	BORRAR_CARACTER2("BORRAR_CARACTER", KeyEvent.VK_BACK_SPACE, 0),
	CALCULAR("CALCULAR", KeyEvent.VK_ENTER, 0),
	CALCULAR2("CALCULAR", KeyEvent.VK_EQUALS, 0),
	PEGAR("PEGAR",KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK),
	COPIAR("COPIAR",KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK),
	ANS("ANS", KeyEvent.VK_A, 0),
	AVANZAR_CURSOR("AVANZAR_CURSOR", KeyEvent.VK_RIGHT, 0),
	RETROCEDER_CURSOR("RETROCEDER_CURSOR", KeyEvent.VK_LEFT, 0);
	
	
	private final String etiqueta;
	private final int atajo;
	private final int modificador;
	
	AtajosTeclado(String nombre, int atajo, int modificador) {
		this.etiqueta = nombre;
		this.atajo = atajo;
		this.modificador = modificador;
	}
	
	public String getEtiqueta() {
		return etiqueta;
	}
	
	public int getAtajo() {
		return atajo;
	}
	
	public int getModificador() {
		return modificador;
	}
	
	public static AtajosTeclado[] getAtajos() {
		return AtajosTeclado.values();
	}
	
}
