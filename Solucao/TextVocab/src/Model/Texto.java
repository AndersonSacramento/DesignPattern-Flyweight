package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Texto extends Expressao {

	Texto(String path, Contexto contexto) throws IOException
	{
		ProcessadorTexto processadorTexto  = new ProcessadorTexto();
		processadorTexto.lerTexto(path, this, contexto);
		numeroObjetosPalavra = processadorTexto.getNumeroObjetosPalavra();
		
	}
	public void addFrase(Frase frase)
	{
		frases.add(frase);
		numeroFrases++;
		numeroPalavras += frase.getNumeroPalavras();
	}

	@Override
	public String visualizar(Contexto contexto) {
		contexto.inicio();
		StringBuffer strFrase = new StringBuffer();
		for(Frase f : frases){
			strFrase.append(f.visualizar(contexto)+" ");
			contexto.proxFrase();
		}
		contexto.inicio();
		return strFrase.toString();
	}
	@Override
	public String visualizarComDestaque(Contexto contexto) {

		return contexto.getDestaque()+visualizar(contexto)+contexto.getDestaque();
	}

	@Override
	public void setDestaque(Destaque destaque, Contexto contexto) {
		contexto.setDestaque(destaque);
		
	}
	@Override
	public Destaque getDestaque(Contexto contexto) {
		// TODO Auto-generated method stub
		return contexto.getDestaque();
		
	}
	public List<Frase> getFrases(){
		return frases;
	}
	public int getNumeroPalavras(){
		return numeroPalavras;
	}
	public int getNumeroFrases(){
		return numeroFrases;
	}
	public int getNumeroObjetosPalavra(){
		return numeroObjetosPalavra;
	}
	public void atualizarDestaque(Contexto contexto){
		Index atual = contexto.getContextoAtual().clone();
		contexto.inicio();
		this.setDestaque(this.getDestaque(contexto).setDestaque(defineDestaque(contexto)),
				contexto);
		contexto.setContextoAtual(atual);
	}
	@Override
	protected Destaque criarDestaque(Contexto contexto) {
		DestaqueTexto destaqueTexto = new DestaqueTexto(defineDestaque(contexto) );
		return destaqueTexto;
	}
	@Override
	public byte defineDestaque(Contexto contexto) {
		contexto.primeiraFrase();
		for(Frase frase : frases){
		  	 if(frase.getDestaque(contexto).getTipo() == Destaque.DESCONHECIDO){
		  		contexto.inicioFrase();
		  		 return Destaque.DESCONHECIDO;
		  	 }
		  	 contexto.proxFrase();
		}
		contexto.inicioFrase();
		return Destaque.CONHECIDO;
	}
	@Override
	public int getTamanho() {
		int tamanho = 0;
		for(Frase frase: frases){
			tamanho+= frase.getTamanho();
		}
		return tamanho;
	}

	private int numeroFrases = 0;
	private int numeroPalavras = 0;
	private int numeroObjetosPalavra = 0;
    private List<Frase> frases = new ArrayList<Frase>();

}
