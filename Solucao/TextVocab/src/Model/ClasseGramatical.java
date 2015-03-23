package Model;

import java.util.Collection;
import java.util.HashMap;

public class ClasseGramatical {
	public ClasseGramatical(){
		this(ClasseGramatical.DESCONHECIDO);
	}
	public ClasseGramatical(byte tipo) {
		tipos();
		this.tipo = tipo;
	}
	public String toString() {
		return ClasseGramatical.classes.get(tipo);
	}

	public void tipos() {
		if (ClasseGramatical.classes == null) {
			ClasseGramatical.classes = new HashMap<Byte, String>();
			ClasseGramatical.classes.put(VERBO, "Verbo");
			ClasseGramatical.classes.put(SUBSTANTIVO, "Substantivo");
			ClasseGramatical.classes.put(ADJETIVO, "Adjetivo");
			ClasseGramatical.classes.put(ADVERBIO, "Adverbio");
			ClasseGramatical.classes.put(DESCONHECIDO, "Desconhecido");
		}

	}
	public static Collection<String> getOpcoes(){
		return ClasseGramatical.classes.values();
	}

	public byte getTipo() {
		return tipo;
	}
	public boolean equals(Object obj){
		return this.tipo == ((ClasseGramatical)obj).getTipo();
	}

	public static final byte DESCONHECIDO = 0;
	public static final byte VERBO = 1;
	public static final byte SUBSTANTIVO = 2;
	public static final byte ADJETIVO = 3;
	public static final byte ADVERBIO = 4;
	private static HashMap<Byte, String> classes;
	private final byte tipo;
}
