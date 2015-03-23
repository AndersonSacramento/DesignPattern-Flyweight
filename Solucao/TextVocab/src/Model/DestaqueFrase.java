package Model;

public class DestaqueFrase extends Destaque{

	public DestaqueFrase(){
		this(DESCONHECIDO);
	}
	public DestaqueFrase(byte destaque){
		super(destaque);
		this.setClasse("frase");
	}
	@Override
	public String getSimboloConhecido() {
		
		return "[f*]";
	}

	@Override
	public String getSimboloDesconhecido() {
		
		return "[f?]";
	}

	@Override
	public Destaque setDestaque(byte destaque) {
		

		return new DestaqueFrase(destaque);
	}





	


	
}
