package Model;


public class Palavra extends Expressao{
	
	public Palavra(String grafia){
		this.grafia = grafia;
		
	}


	@Override
	public String visualizar(Contexto contexto) {
		
		if(contexto.getPosicaoPalavra() == 0)
		{
		  return grafia.substring(0, 1).toUpperCase() + grafia.substring(1);	
		}		
		
		return  grafia; 
	}
	@Override
	public String visualizarComDestaque(Contexto contexto) {

		return contexto.getDestaque()+visualizar(contexto)+contexto.getDestaque();
	}

	@Override
	public void setDestaque(Destaque destaque, Contexto contexto) {
		contexto.setDestaque(destaque);
		
	}

	public void setClasseGramatical(ClasseGramatical classeGramatical,
			Contexto contexto) {
		contexto.setClasseGramatical(classeGramatical);
		
	}

	@Override
	public Destaque getDestaque(Contexto contexto) {
		
		return contexto.getDestaque();
		
	}
	@Override
	protected Destaque criarDestaque(Contexto contexto) {
		
		return new DestaquePalavra();
	}
	public ClasseGramatical getClasseGramatical(Contexto contexto) {
		
		return contexto.getClasseGramatical();
	}
	
	

	@Override
	public byte defineDestaque(Contexto contexto) {
		Destaque destaque = this.getDestaque(contexto);
		if(destaque == null){
			return Destaque.DESCONHECIDO;
		}else{
			return destaque.getTipo();
		}
	}
	public String toString(){
		return grafia;
	}
	@Override
	public int getTamanho() {
		
		return grafia.length();
	}
	private String grafia = null;


}
