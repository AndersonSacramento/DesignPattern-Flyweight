package Model;

public class DestaqueTexto extends Destaque {

	public DestaqueTexto(){
		this(DESCONHECIDO);
	}
	public DestaqueTexto(byte destaque){
		super(destaque);
		this.setClasse("texto");
	}
	@Override
	public String getSimboloConhecido() {
		
		return "[t*]";
	}

	@Override
	public String getSimboloDesconhecido() {
		
		return "[t?]";
	}
	@Override
	public Destaque setDestaque(byte destaque) {
		
		return new DestaqueTexto(destaque);
	}



}
