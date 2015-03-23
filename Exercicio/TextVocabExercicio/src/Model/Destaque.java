package Model;

public abstract class Destaque  {
	public abstract String getSimboloConhecido();
	public abstract String getSimboloDesconhecido();
	public abstract Destaque setDestaque(byte destaque);
	
	public Destaque(){
		this(DESCONHECIDO);
	}
	public Destaque(byte destaque){
		this.destaque = destaque;
		
		if( destaque == CONHECIDO){
			simbolo = getSimboloConhecido();
		}else if( destaque == DESCONHECIDO){
			simbolo = getSimboloDesconhecido();
		}
	}
	
	public String toString(){
		return simbolo;
	}
	@Override
	public boolean equals(Object destaque){
		if(destaque == null){
			return false;
		}else{
			if(this.classe.equals(((Destaque)destaque).getClasse()) && 
					this.destaque == ((Destaque)destaque).destaque){
				return true;
			}
			else{
				return false;
			}
		}
		
	}
	public static String[] getOpcoes(){
		return new String[]{"Conhecido","Desconhecido"};
	}
	public  String descricao(){
		if(this.destaque == CONHECIDO){
			return "Conhecido";
		}else{
			return "Desconhecido";
		}
	}
	public String getClasse(){
		return classe;
	}
	
	public void setClasse(String classe){
		this.classe = classe;
	}
	
	public byte getTipo(){
		return this.destaque;
	}
	public String getId(){
		return classe+destaque;
	}
	public static final byte CONHECIDO = 0;
	public static final byte DESCONHECIDO = 1;
	protected final byte destaque;
	private String simbolo;
	private String classe;
}
