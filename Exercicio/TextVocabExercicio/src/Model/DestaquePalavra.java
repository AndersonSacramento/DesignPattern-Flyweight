package Model;

public class DestaquePalavra extends Destaque {

	public DestaquePalavra(){
		this(DESCONHECIDO);
	}
	public DestaquePalavra(byte destaque){
		super(destaque);
		this.setClasse("palavra");
	}
	@Override
	public String getSimboloConhecido() {
		
		return "[p*]";
	}	
	@Override
	public String getSimboloDesconhecido() {
		
		return "[p?]";
	}
	@Override
	public Destaque setDestaque(byte destaque) {
		
		return new DestaquePalavra(destaque);
	}
}
