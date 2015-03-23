package Model;



public abstract class Expressao {
	
	public abstract String visualizar(Contexto contexto);
	public abstract String visualizarComDestaque(Contexto contexto);
	public abstract void setDestaque(Destaque destaque, Contexto contexto);
	public abstract Destaque getDestaque(Contexto contexto);
	public void novoDestaque(Contexto contexto){
		Destaque destaque = criarDestaque(contexto);
		setDestaque(destaque,contexto);
	}
	protected abstract Destaque criarDestaque(Contexto contexto);
	public abstract byte defineDestaque(Contexto contexto);
	public abstract int getTamanho();
}
